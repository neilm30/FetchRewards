
package com.fetchrewards.presentation.composeui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.fetchrewards.R
import com.fetchrewards.domain.entity.FetchRewardsEntity
import com.fetchrewards.presentation.intent.DataIntent
import com.fetchrewards.presentation.state.DataState
import com.fetchrewards.theme.BlueLight
import com.fetchrewards.theme.OrangeLight

// Constants
private val DEFAULT_10_PADDING = 10.dp

@Composable
fun HomeScreen(
    navController: NavHostController,
    state: DataState,
    onEvent: (DataIntent) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(DataIntent.GetRewardsList)
    }
    HomeScreenComponent(
        state = state,
        onRetry = {
            onEvent(DataIntent.GetRewardsList)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenComponent(
    state: DataState,
    onRetry: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors =
                    TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = Color.Black,
                    ),
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    )
                },
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            HandleDataState(state = state, onRetry)
        }
    }
}

@Composable
fun HandleDataState(
    state: DataState,
    onRetry: () -> Unit,
) {
    when (state) {
        is DataState.Loading -> {
            if (state.loading) {
                CircularProgressComponent()
            }
        }

        is DataState.Error -> {
            ErrorStateComponent(state.error, onRetry)
        }

        is DataState.Success -> {
            FetchRewardsList(state.data)
        }
        is DataState.Inactive -> {
        }
    }
}

@Composable
fun FetchRewardsList(data: List<FetchRewardsEntity>) {
    // Remember the scroll state
    val listState = rememberLazyListState()
    val groupedByListIDItems = data.groupBy { it.listId }
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(5.dp),
        modifier =
            Modifier
                .fillMaxSize()
                .background(BlueLight),
    ) {
        groupedByListIDItems.forEach { (listId, rowList) ->
            item {
                GroupHeader(listId)
            }
            items(rowList, key = { it.id ?: 1 }) { item ->
                ItemRow(item, onItemClick = {
                })
            }

            item {
                HorizontalDivider(
                    modifier = Modifier.padding(3.dp),
                    thickness = 3.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun GroupHeader(listId: Int?) {
    Text(
        text = stringResource(R.string.list_id, listId ?: 0),
        modifier =
            Modifier
                .fillMaxWidth()
                .background(OrangeLight)
                .padding(4.dp),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
    )
}

@Composable
fun ItemRow(
    item: FetchRewardsEntity,
    onItemClick: (String) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                },
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = (stringResource(R.string.id, item.id ?: 0)),
            modifier =
                Modifier
                    .padding(4.dp)
                    .weight(0.5f),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = stringResource(R.string.name, item.name ?: ""),
            modifier =
                Modifier
                    .padding(4.dp).weight(0.5f),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun CircularProgressComponent() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Red,
            strokeWidth = 8.dp,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round,
        )
    }
}

@Composable
fun ErrorStateComponent(
    error: Pair<String?, String?>,
    onRetry: () -> Unit,
) {
    AlertDialogComponent(code = error.first ?: "", message = error.second ?: "", onRetry)
}

@Composable
fun AlertDialogComponent(
    code: String,
    message: String,
    onRetry: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                            onRetry.invoke()
                        }) {
                            Text(stringResource(R.string.retry_again))
                        }
                    },
                    title = {
                        Text(text = code, color = Color.Black)
                    },
                    text = {
                        Text(text = message, color = Color.DarkGray)
                    },
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
                )
            }
        }
    }
}
