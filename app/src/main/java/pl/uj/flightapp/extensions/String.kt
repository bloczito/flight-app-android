package pl.uj.flightapp.extensions

fun String.format(): String {
    if (this.contains("H") && this.contains("M")) {
        val hours = this.substringAfter("T").substringBefore("H")
        val minutes = this.substringAfter("H").substringBefore("M")
        return "${hours}h ${minutes}min"
    } else if (this.contains("H")) {
        val hours = this.substringAfter("T").substringBefore("H")
        return "${hours}h"
    } else if (this.contains("M")) {
        val minutes = this.substringAfter("T").substringBefore("M")
        return "${minutes}min"
    }
    return ""
}