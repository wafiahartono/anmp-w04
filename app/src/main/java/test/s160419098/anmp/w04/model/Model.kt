package test.s160419098.anmp.w04.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id: String?,
    @SerializedName("student_name") val fullName: String?,
    @SerializedName("birth_of_date") val dateOfBirth: String?,
    @SerializedName("phone") val phoneNumber: String?,
    @SerializedName("photo_url") val photoUrl: String?,
)

data class Aircraft(
    val id: Int?,
    val name: String?,
    val type: String?,
    val country: String?,
    val manufacturer: String?,
    @SerializedName("firstFlightDate") val firstFlight: String?,
    @SerializedName("introducedDate") val introduced: String?,
    val performance: Performance?,
    val features: List<String>,
    val dimension: Dimension?,
    val imageUrl: String?,
) {
    data class Performance(
        val topSpeed: Int?,
        val cruiseSpeed: Int?,
        val range: Int?,
        val combatRange: Int?,
        val serviceCeiling: Int?,
    )

    data class Dimension(
        val length: Int?,
        val wingspan: Int?,
        val height: Int?,
    )
}