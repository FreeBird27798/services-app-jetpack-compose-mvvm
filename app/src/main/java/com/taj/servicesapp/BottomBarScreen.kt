package com.taj.servicesapp

sealed class BottomBarScreen(val title: String, val route: String, val icon: Int) {

    object Service : BottomBarScreen(
        "Service",
        BottomBarScreenRoutes.SERVICE,
        R.drawable.service
    )

    object Order : BottomBarScreen(
        "Orders",
        BottomBarScreenRoutes.ORDER,
        R.drawable.orders
    )

    object User : BottomBarScreen(
        "User",
        BottomBarScreenRoutes.USER,
        R.drawable.user
    )

    object More : BottomBarScreen(
        "More",
        BottomBarScreenRoutes.MORE,
        R.drawable.more
    )
}

object BottomBarScreenRoutes {
    const val SERVICE = "bn_service_screen"
    const val ORDER = "bn_order_screen"
    const val USER = "bn_user_screen"
    const val MORE = "bn_more_screen"
}