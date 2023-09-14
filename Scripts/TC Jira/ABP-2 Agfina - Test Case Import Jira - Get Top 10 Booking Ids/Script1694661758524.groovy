import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CucumberKW.runFeatureFile('Include/features/ABP-2 Agfina - Test Case Import Jira - Get Top 10 Booking Ids.feature')

// Mengirim permintaan ke API dan mendapatkan respons
def response = WS.sendRequestAndVerify(findTestObject('booking ids/GET - Booking Ids'))

// Memeriksa apakah respons berhasil
WS.verifyResponseStatusCode(response, 200)

// Mengurai respons JSON
def jsonSlurper = new JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response.getResponseText())

// Mengambil 10 booking ID pertama dari respons JSON
def bookingIds = jsonResponse.take(10).collect { it.bookingid }

// Menampilkan booking IDs (opsional, hanya untuk verifikasi)
println("Booking IDs yang diambil dari respons API: " + bookingIds)

// Verifikasi setiap booking ID
// Verifikasi setiap booking ID
for (bookingId in bookingIds) {
	if (bookingId >= 0) {
		println("Booking ID valid: " + bookingId)
		
	} else {
		println("Booking ID invalid: " + bookingId)
	}
}