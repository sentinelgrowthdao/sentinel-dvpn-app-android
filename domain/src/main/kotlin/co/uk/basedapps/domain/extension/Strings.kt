package co.uk.basedapps.domain.extension

fun String?.isNotNullOrEmpty() = !this.isNullOrEmpty()

fun String.Companion.empty() = ""

fun String.takeIfNotBlank(): String? = takeIf { it.isNotBlank() }
