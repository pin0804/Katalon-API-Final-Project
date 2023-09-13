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
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

def response = WS.sendRequest(findTestObject('booking ids/GET - Id by date'))

// Memeriksa apakah respons berhasil
WS.verifyResponseStatusCode(response, 200)

// Mengurai respons JSON
def jsonSlurper = new JsonSlurper()

def jsonResponse = jsonSlurper.parseText(response.getResponseText())

def bookingIds = jsonResponse.collect({ 
        it.bookingid
    })

// Menampilkan booking ID yang diambil dari respons API (opsional)
println('Booking ID yang diambil dari respons API: ' + jsonResponse.bookingid)

// Verifikasi booking ID apakah booking ID adalah angka positif atau melakukan verifikasi lainnya
for (def bookingid : bookingIds) {
    if (bookingid >= 0) {
        println('Booking ID valid: ' + bookingid)
    } else {
        println('Booking ID invalid: ' + bookingid)
    }
}


// Memeriksa apakah JSON respons kosong
def jsonResponse2 = response.getResponseBodyContent()
assert jsonResponse2 != null && !jsonResponse2.trim().isEmpty() : "JSON respons kosong."
