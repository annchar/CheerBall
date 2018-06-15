package com.azaless.cheerball.view.model

import com.google.gson.annotations.SerializedName

data class Team(@SerializedName("_links") val link: Link?,
                val name: String?,
                val code: String?,
                val shortName: String?,
                val squadMarketValue: String?,
                val crestUrl: String?
)

data class Link(val self: Href?,
                val fixtures: Href?,
                val players: Href?)

//data class LinkSelf(val self: Href?)
//data class LinkFixtures(val fixtures: Href?)
//data class LinkPlayers(val players: Href?)

data class Href(val href: String?)
