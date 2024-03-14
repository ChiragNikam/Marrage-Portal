package com.petukji.marrageportal.auth.domain

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.petukji.marrageportal.auth.data.PersonalDetails
import java.time.LocalDate

class PersonalDetailsViewModel : ViewModel() {
    private val _personalDetails = mutableStateOf(PersonalDetails())
    val personalDetails: State<PersonalDetails> = _personalDetails

    fun updateFirstName(firstName: String) {
        _personalDetails.value = _personalDetails.value.copy(firstName = firstName)
    }

    fun updateLastName(lastName: String) {
        _personalDetails.value = _personalDetails.value.copy(lastName = lastName)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    fun updateDateOfBirth(date: String) {
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val formattedDate = formatter.format(LocalDate.parse(date))
        _personalDetails.value = _personalDetails.value.copy(dateOfBirth = formattedDate)
    }

    fun updateAge(age: String) {
        _personalDetails.value = _personalDetails.value.copy(age = age)
    }

    fun updatePhoneNo(phoneNo: String) {
        _personalDetails.value = _personalDetails.value.copy(phoneNo = phoneNo)
    }

    fun updateEmail(email: String) {
        _personalDetails.value = _personalDetails.value.copy(email = email)
    }

    fun updateMaritalStatus(maritalStatus: String) {
        _personalDetails.value = _personalDetails.value.copy(maritalStatus = maritalStatus)
    }

    fun updateTimeOfBirth(timeOfBirth: String) {
        _personalDetails.value = _personalDetails.value.copy(timeOfBirth = timeOfBirth)
    }

    fun updateBloodGroup(bloodGroup: String) {
        _personalDetails.value = _personalDetails.value.copy(bloodGroup = bloodGroup)
    }

    fun updateHeight(height: String) {
        _personalDetails.value = _personalDetails.value.copy(height = height)
    }

    fun updateWeight(weight: String) {
        _personalDetails.value = _personalDetails.value.copy(weight = weight)
    }

    fun updateColor(color: String) {
        _personalDetails.value = _personalDetails.value.copy(color = color)
    }

    fun updateBodyType(bodyType: String) {
        _personalDetails.value = _personalDetails.value.copy(bodyType = bodyType)
    }


    fun updateDiet(diet: String) {
        _personalDetails.value = _personalDetails.value.copy(diet = diet)
    }

    fun updatePhysicallyDisable(physicallyDisable: Boolean) {
        _personalDetails.value = _personalDetails.value.copy(physicallyDisable = physicallyDisable)
    }

    fun updateDisability(disability: String) {
        _personalDetails.value = _personalDetails.value.copy(disability = disability)
    }

    fun updateAnyDisease(anyDisease: Boolean) {
        _personalDetails.value = _personalDetails.value.copy(anyDisease = anyDisease)
    }

    fun updateManglik(manglik: Boolean) {
        _personalDetails.value = _personalDetails.value.copy(manglik = manglik)
    }

    fun updatePersonalDetails(personalDetails: PersonalDetails) {
        _personalDetails.value = personalDetails
    }

    fun resetPersonalDetails() {
        _personalDetails.value = PersonalDetails()
    }

    fun getPersonalDetails(): PersonalDetails {
        return _personalDetails.value
    }

    fun updateDegree(degree: String) {
        _personalDetails.value = _personalDetails.value.copy(degree = degree)
    }

    fun updatePassOutYear(passOutYear: String) {
        _personalDetails.value = _personalDetails.value.copy(passOutYear = passOutYear)
    }

    fun updateCollege(college: String) {
        _personalDetails.value = _personalDetails.value.copy(college = college)
    }

    fun updateCompany(company: String) {
        _personalDetails.value = _personalDetails.value.copy(company = company)
    }

    fun updateDesignation(designation: String) {
        _personalDetails.value = _personalDetails.value.copy(designation = designation)
    }

    fun updateAnnualIncome(annualIncome: String) {
        _personalDetails.value = _personalDetails.value.copy(annualIncome = annualIncome)
    }

    fun updateExperience(experience: String) {
        _personalDetails.value = _personalDetails.value.copy(experience = experience)
    }

    fun updateAddress(address: String) {
        _personalDetails.value = _personalDetails.value.copy(address = address)
    }


}