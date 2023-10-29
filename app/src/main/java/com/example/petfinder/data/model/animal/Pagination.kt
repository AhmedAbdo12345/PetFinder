package com.example.petfinder.data.model.animal

import java.io.Serializable

data class Pagination(
    val _links: LinksX?,
    val count_per_page: Int?,
    val current_page: Int?,
    val total_count: Int?,
    val total_pages: Int?
): Serializable