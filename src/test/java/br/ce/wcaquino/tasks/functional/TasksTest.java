package br.ce.wcaquino.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
			// fechar o browser
			driver.quit();
		}		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descricao
			// driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
			// fechar o browser
			driver.quit();
		}		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			// driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
			// fechar o browser
			driver.quit();
		}		
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		
		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			// fechar o browser
			driver.quit();
		}		
	}
}
/*
Obs: tive erro na execucao do programa teste com Slenium WebDriver. Consegui resolver 
instalando via apt-get:
https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver

Parte do erro que tive:

java.lang.IllegalStateException: The path to the driver executable must be set by the 
webdriver.chrome.driver system property; for more information, 
see https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver. The latest version can be 
downloaded from http://chromedriver.storage.googleapis.com/index.html
*/
