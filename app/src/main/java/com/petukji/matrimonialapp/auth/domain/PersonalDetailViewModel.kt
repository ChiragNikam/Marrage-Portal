package com.petukji.matrimonialapp.auth.domain

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petukji.matrimonialapp.auth.data.api_data.RegistrationRequestData
import com.petukji.matrimonialapp.bottom_nav.data.api_request.Users
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate

class PersonalDetailsViewModel : ViewModel() {
    private val _personalDetails = mutableStateOf(RegistrationRequestData())
    val personalDetails: State<RegistrationRequestData> = _personalDetails

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
        _personalDetails.value = _personalDetails.value.copy(dob = formattedDate)
    }

    fun updateAge(age: String) {
        _personalDetails.value = _personalDetails.value.copy(age = age)
    }

    fun updatePhoneNo(mobile: String) {
        _personalDetails.value = _personalDetails.value.copy(mobile = mobile)
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
        _personalDetails.value = _personalDetails.value.copy(gotra = bloodGroup)
    }

    fun updateHeight(height: String) {
        _personalDetails.value = _personalDetails.value.copy(height = height)
    }

    fun updateWeight(weight: String) {
        _personalDetails.value = _personalDetails.value.copy(dependentUponYou = weight)
    }

    fun updateColor(color: String) {
        _personalDetails.value = _personalDetails.value.copy(bodyColor = color)
    }

    fun updateBodyType(bodyType: String) {
        _personalDetails.value = _personalDetails.value.copy(bodyType = bodyType)
    }


    fun updateDiet(diet: String) {
        _personalDetails.value = _personalDetails.value.copy(dietPreference = diet)
    }

    fun updatePhysicallyDisable(physicallyDisable: Boolean) {
        _personalDetails.value = _personalDetails.value.copy(doshan = "$physicallyDisable")
    }

    fun updateDisability(disability: String) {
        _personalDetails.value = _personalDetails.value.copy(habitSeverity = disability)
    }

    fun updateAnyDisease(anyDisease: Boolean) {
        _personalDetails.value = _personalDetails.value.copy(badHabit = "$anyDisease")
    }

    fun updateManglik(manglik: Boolean) {
        _personalDetails.value = _personalDetails.value.copy(zodiac = "$manglik")
    }

    fun updatePersonalDetails(personalDetails: RegistrationRequestData) {
        _personalDetails.value = personalDetails
    }

    fun updateDegree(degree: String) {
        _personalDetails.value = _personalDetails.value.copy(degree1 = degree)
    }

    fun updatePassOutYear(passOutYear: String) {
        _personalDetails.value = _personalDetails.value.copy(passoutYear1 = passOutYear)
    }

    fun updateCollege(college: String) {
        _personalDetails.value = _personalDetails.value.copy(college1 = college)
    }

    fun updateCompany(company: String) {
        _personalDetails.value = _personalDetails.value.copy(companyName = company)
    }

    fun updateDesignation(designation: String) {
        _personalDetails.value = _personalDetails.value.copy(designation = designation)
    }

    fun updateAnnualIncome(annualIncome: String) {
        _personalDetails.value = _personalDetails.value.copy(annualIncome = annualIncome)
    }

    fun updateExperience(experience: String) {
        _personalDetails.value = _personalDetails.value.copy(occupation = experience)
    }

    fun updateAddress(address: String) {
        _personalDetails.value = _personalDetails.value.copy(permanentAddress = address)
    }

    fun updateCity(city: String) {
        _personalDetails.value = _personalDetails.value.copy(permanentCity = city)
    }

    fun updateCorrespondenceCity(city: String) {
        _personalDetails.value = _personalDetails.value.copy(correspondenceCity = city)
    }

    fun updateState(state: String) {
        _personalDetails.value = _personalDetails.value.copy(permanentState = state)
    }

    fun updateCorrespondenceState(state: String) {
        _personalDetails.value = _personalDetails.value.copy(correspondenceState = state)
    }

    fun updateCountry(country: String) {
        _personalDetails.value = _personalDetails.value.copy(permanentCountry = country)
    }

    fun updateCorrespondenceCountry(country: String) {
        _personalDetails.value = _personalDetails.value.copy(correspondenceCountry = country)
    }

    fun updatePermanentAddress(address: String) {
        _personalDetails.value = _personalDetails.value.copy(permanentAddress = address)
    }

    fun updateCorrespondenceAddress(address: String) {
        _personalDetails.value = _personalDetails.value.copy(correspondenceAddress = address)
    }

    fun updatePermanentPincode(pincode: String) {
        _personalDetails.value = _personalDetails.value.copy(permanentPIN = pincode)
    }

    fun updateCorrespondencePincode(pincode: String) {
        _personalDetails.value = _personalDetails.value.copy(correspondencePIN = pincode)
    }
    //FamilyDetail
    fun updateFatherName(fatherName: String) {
        _personalDetails.value = _personalDetails.value.copy(fatherName = fatherName)
    }

    fun updateMotherName(motherName: String) {
        _personalDetails.value = _personalDetails.value.copy(motherName = motherName)
    }

    fun updateBrotherName(brother: String) {
        _personalDetails.value = _personalDetails.value.copy(brothers = brother)
    }

    fun updateSisterName(sister: String) {
        _personalDetails.value = _personalDetails.value.copy(sisters = sister)
    }

    fun totalFamilyMembers(totalFamilyMembers: String) {
        _personalDetails.value = _personalDetails.value.copy(totalFamily = totalFamilyMembers)
    }
    fun updateFatherOccupation(fatherOccupation: String) {
        _personalDetails.value = _personalDetails.value.copy(fatherOccupation = fatherOccupation)
    }

    fun updateMotherOccupation(motherOccupation: String) {
        _personalDetails.value = _personalDetails.value.copy(motherOccupation = motherOccupation)
    }

//    fun resetPersonalDetails() {
//        _personalDetails.value = PersonalDetails()
//    }

    fun getPersonalDetails(): RegistrationRequestData {
        return _personalDetails.value
    }

    fun registerUser(){
        viewModelScope.launch {
            val user = Users()
            val registrationResponse = async { user.service.registerUser(personalDetails.value) }

            val finalRegistrationResponse = registrationResponse.await()
            if (finalRegistrationResponse.isSuccessful){
                Log.d("registration", "Registration Successfully")
            } else{
                Log.e("registration", "Registration Failed: ${finalRegistrationResponse.errorBody()}")
            }
        }
    }

}