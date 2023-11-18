package com.sudarshan.gweiland_native_android_task.data.response.model.image

data class USDT(
    val category: String,
    val contract_address: List<ContractAddres>,
    val date_added: String,
    val date_launched: Any,
    val description: String,
    val id: Int,
    val infinite_supply: Boolean,
    val is_hidden: Int,
    val logo: String,
    val name: String,
    val notice: String,
    val platform: PlatformX,
    val self_reported_circulating_supply: Any,
    val self_reported_market_cap: Any,
    val self_reported_tags: Any,
    val slug: String,
    val subreddit: String,
    val symbol: String,
    val tag_groups: List<String>,
    val tag_names: List<String>,
    val tags: List<String>,
    val twitter_username: String,
    val urls: Urls
)