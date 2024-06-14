package com.mind.small.enums

/**
 * create by Rui on 2024-06-13
 * desc:
 */
enum class ModuleEnums(val pModuleType: String,val clazzName: String) {
    FACE_MIND_EN("FaceMind", "com.mind.face.FaceHandler"),
    PRINTER_MIND_EN("PrinterMind", "com.mind.printer.PrinterHandler"),
}