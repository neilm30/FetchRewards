package com.githubrepo.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class Category(
    val categoryId: Int,
    @StringRes val categoryName: Int,
    @ColorRes val color: Color,
    @DrawableRes val icon: Int,
)
