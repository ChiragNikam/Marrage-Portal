package com.petukji.matrimonialapp.auth.domain

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.petukji.matrimonialapp.auth.data.api_data.RegistrationRequestData
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfileRequest
import com.petukji.matrimonialapp.bottom_nav.data.api_request.Users
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.concurrent.TimeUnit

class PersonalDetailsViewModel : ViewModel() {

    // firebase instance
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    private val _personalDetails = mutableStateOf(RegistrationRequestData())
    val personalDetails: State<RegistrationRequestData> = _personalDetails

    private val _isRegistrationSuccess = MutableStateFlow(false)
    val isRegistrationSuccess get() = _isRegistrationSuccess

    // login using mobile and OTP
    val _verificationId = MutableStateFlow<String?>(null)
    val verificationId: StateFlow<String?> = _verificationId

    // check if user is registered
    private val _isUserRegistered = MutableStateFlow(false)
    val isUserRegistered get() = _isUserRegistered

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun updateCurrentUserMobile(){
        if (currentUser != null){
            _personalDetails.value =  _personalDetails.value.copy(mobile = currentUser.phoneNumber!!)
            _personalDetails.value = _personalDetails.value.copy(mobileKey = currentUser.phoneNumber!!)
            _personalDetails.value = _personalDetails.value.copy(userID = currentUser.phoneNumber!!)
        }
    }


    fun updateUserRegistered(status: Boolean){
        _isUserRegistered.value = status
    }

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

    fun getPersonalDetails(): RegistrationRequestData {
        return _personalDetails.value
    }

    suspend fun checkUserRegistered(): Boolean {
        var isUserRegistered = viewModelScope.async {
            val user = Users()
            val userProfileResp = async {
                user.service.getSingleUserData(
                    UserProfileRequest(
                        currentUser?.phoneNumber.toString()
                    )
                )
            }

            val finalProfileResponse = userProfileResp.await()
            if (finalProfileResponse.isSuccessful){
                return@async true
            } else{
                val errorBody = finalProfileResponse.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody ?: "").getString("error")
                } catch (e: JSONException) {
                    "Unknown error occurred"
                }
                if (errorMessage == "User not found")
                    return@async false
                else
                    return@async true
            }
        }
        return isUserRegistered.await()
    }

    fun registerUser(isSuccess: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val user = Users()
            val registrationResponse = async { user.service.registerUser(personalDetails.value) }

            val finalRegistrationResponse = registrationResponse.await()
            if (finalRegistrationResponse.isSuccessful) {
                Log.d("registration", "Registration Successfully")
                _isRegistrationSuccess.value = true
                isSuccess(true, "")
            } else {
                val errorBody = finalRegistrationResponse.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody ?: "").getString("error")
                } catch (e: JSONException) {
                    "Unknown error occurred"
                }
                isSuccess(false, errorMessage)
                Log.e(
                    "registration",
                    "Registration Failed: ${finalRegistrationResponse.code()} $errorMessage"
                )
            }
        }
    }

    fun verifyOtp(phoneNumber: String, otp: String, onResult: (Boolean) -> Unit) {
        val credential = PhoneAuthProvider.getCredential(verificationId.value!!, otp)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    onResult(true)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d("sign-in", task.exception?.message.toString())
                    onResult(false)
                }
            }
    }
}