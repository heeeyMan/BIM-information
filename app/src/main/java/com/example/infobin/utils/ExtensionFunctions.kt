package com.example.infobin.utils

fun StringBuilder.toDigital(text: String): StringBuilder {
    this.setLength(0)
    for (element in text) {
        if (element in '0'..'9') {
            this.append(element)
        }
    }
    return this
}
fun StringBuilder.applyFormat(text: String, template: String): StringBuilder {
    val numPlace = 'X'
    this.setLength(0)
    var templateIndex = 0
    var textIndex = 0
    while (templateIndex < template.length && textIndex < text.length) {
        if (template[templateIndex] == numPlace) {
            this.append(text[textIndex])
            ++textIndex
        } else {
            this.append(template[templateIndex])
        }
        ++templateIndex
    }
    return this
}
