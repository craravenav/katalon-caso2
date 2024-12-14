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
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonSlurper

def response = WS.sendRequest(findTestObject('Api-Users'))
// Capturo la respuesta
int statusCode = response.getStatusCode()
// Verifico que el Codigo de la respuesta sea 200 OK
if (statusCode == 200) {
	KeywordUtil.logInfo("Código de estado correcto: ${statusCode}")
} else {
	KeywordUtil.markFailed("Error: Código de estado inesperado ${statusCode}")
}

// Convierto la respuesta en formato JSON para despues buscar dentro del JSON
def responseBody = response.getResponseBodyContent()
def jsonResponse = new JsonSlurper().parseText(responseBody)

if (jsonResponse.token && jsonResponse.token.trim()) {
	KeywordUtil.logInfo("El campo 'token' está presente y tiene valor: ${jsonResponse.token}")
} else {
	KeywordUtil.markFailed("Error: El campo 'token' no está presente o no tiene un valor válido")
}