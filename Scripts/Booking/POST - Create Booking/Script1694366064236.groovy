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
import groovy.json.JsonSlurper as JsonSlurper

// Mengirim permintaan POST API
def response = WS.sendRequestAndVerify(findTestObject('booking/POST - Create booking'))

// Memeriksa apakah respons berhasil
WS.verifyResponseStatusCode(response, 200)

// Mengurai respons JSON
def jsonSlurper = new JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response.getResponseText())

// Memeriksa apakah respons JSON berisi booking ID
assert jsonResponse.bookingid != null : "Booking ID tidak ada dalam respons."

// Menampilkan booking ID yang diambil dari respons API (opsional)
println("Booking ID yang diambil dari respons API: " + jsonResponse.bookingid)

// Verifikasi booking ID sesuai dengan kebutuhan Anda
// Misalnya, Anda dapat memeriksa apakah booking ID adalah angka positif atau melakukan verifikasi lainnya
assert jsonResponse.bookingid > 0 : "Booking ID valid."