package com.mindorks.framework


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class StackModel(
    @SerializedName("has_more") var hasMore: Boolean?,
    @SerializedName("items") var items: List<Item?>?,
    @SerializedName("quota_max") var quotaMax: Int?,
    @SerializedName("quota_remaining") var quotaRemaining: Int?
) {
    @Keep
    data class Item(
        @SerializedName("accepted_answer_id") var acceptedAnswerId: Int?,
        @SerializedName("answer_count") var answerCount: Int?,
        @SerializedName("closed_date") var closedDate: Int?,
        @SerializedName("closed_reason") var closedReason: String?,
        @SerializedName("content_license") var contentLicense: String?,
        @SerializedName("creation_date") var creationDate: Int?,
        @SerializedName("is_answered") var isAnswered: Boolean?,
        @SerializedName("last_activity_date") var lastActivityDate: Int?,
        @SerializedName("last_edit_date") var lastEditDate: Int?,
        @SerializedName("link") var link: String?,
        @SerializedName("owner") var owner: Owner?,
        @SerializedName("question_id") var questionId: Int?,
        @SerializedName("score") var score: Int?,
        @SerializedName("tags") var tags: List<String?>?,
        @SerializedName("title") var title: String?,
        @SerializedName("view_count") var viewCount: Int?
    ) {
        @Keep
        data class Owner(
            @SerializedName("accept_rate") var acceptRate: Int?,
            @SerializedName("display_name") var displayName: String?,
            @SerializedName("link") var link: String?,
            @SerializedName("profile_image") var profileImage: String?,
            @SerializedName("reputation") var reputation: Int?,
            @SerializedName("user_id") var userId: Int?,
            @SerializedName("user_type") var userType: String?
        )
    }
}