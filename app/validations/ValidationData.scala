package validations

import javax.inject._

class ValidationData @Inject() () {

  var message: String = "";

  var valid: Boolean = false

  def validateName(name: String): Boolean = {
    if (this.valid) {
      if (this.checkEmpty(name)) {
        this.message = "Name is empty."
        return false
      } else {
        if (!this.checkMinMaxString(name, 3, 255)) {
          this.message = "Amount of character invalid for name. Min=3 and Max=255."
          return false
        }
      }
      return true
    }
    true
  }

  def validateEmail(email: String, pemail: String, statusEdit: Boolean): Boolean = {
    if (this.valid) {
      if (statusEdit) {
        if (email == pemail) {
          return true
        }
      }
      if (this.checkEmpty(email)) {
        this.message = "E-mail is empty."
        return false
      } else {
        if (!this.checkMinMaxString(email, 7, 255)) {
          this.message = "Amount of character invalid for e-mail. Min=7 and Max=255."
          return false
        } else {
          if (!this.checkPattern(email, """^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$""")) {
            this.message = "Invalid E-mail."
            return false
          }
        }
      }
      return true
    }
    true
  }

  def validateEnglishLevel(englishLevel: String): Boolean = {
    if (this.valid) {
      if (this.checkEmpty(englishLevel)) {
        this.message = "Gender is empty."
        return false
      } else {
        if (!this.checkMinMaxString(englishLevel, 2, 2)) {
          this.message = "Amount of character invalid for english level."
          return false
        } else {
          if (!this.checkPattern(englishLevel, """^(A1|A2|B1|B2|C1)$""")) {
            this.message = "Invalid english level."
            return false
          }
        }
      }
      return true
    }
    true
  }

  def validateDataLogin(data: String, field: String): Boolean = {
    if (this.checkEmpty(data)) {
      message = s"${field} is empty."
      return false
    } else {
      if (!this.checkMinMaxString(data, 8, 45)) {
        message = s"Amount of character invalid for ${field}."
        return false
      }
    }
    true
  }

  def checkMinMaxString(value: String, min: Int, max: Int): Boolean = if (value.length >= min) if (value.length <= max) true else false else false

  def checkRangeNumber(value: BigDecimal, min: BigDecimal, max: BigDecimal): Boolean = if (value >= min) if (value <= max) true else false else false

  private def checkEmpty(data: String): Boolean = if (data.isEmpty) true else false

  private def checkPattern(data: String, pattern: String): Boolean = if (data.matches(pattern)) true else false


}