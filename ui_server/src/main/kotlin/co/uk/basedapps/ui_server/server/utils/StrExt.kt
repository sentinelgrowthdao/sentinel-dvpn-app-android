package co.uk.basedapps.ui_server.server.utils

import android.util.Patterns

private const val MailTo = "mailto:"

fun String.isValidEmail() =
  !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidMailTo() =
  this.startsWith(MailTo) && this.removePrefix(MailTo).isValidEmail()
