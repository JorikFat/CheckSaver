package dev.jorik.checksaver.core

import java.text.SimpleDateFormat
import java.util.*

val ruLocale = Locale("ru", "RU")

val dateFormat = SimpleDateFormat("dd.MM.yyyy", ruLocale)
val timeFormat = SimpleDateFormat("HH:mm", ruLocale)