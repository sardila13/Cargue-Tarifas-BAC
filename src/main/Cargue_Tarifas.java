package main;

import java.awt.GridLayout;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import trv.ChromeDriver;
import trv.WebDriver;
import trv.WebElement;

public class Cargue_Tarifas {
	
	public static int ID = 3;
	
	public static String url="http://10.0.8.94:8080/login.aspx";
	
	private File archivo;
	
	private BufferedReader br;
	
	protected String contrasenha;
	
	protected String usuario;
	
	public Cargue_Tarifas() {
		setProperties();
		crearArchivoLectura();
		
		WebDriver driver = crearDriver();
		
		driver.navigate(url);
		
		System.out.println("Inicia navegación en Google Chrome");
		obtenerContraseñas();
		
		Robot robot = new Robot();
		
		iniciarSesión(driver);
		
		//TODO metodo para navegar el menu y llegar al url de creación de tarifa
		
		crearSolicitudes(driver,robot);   

	}

	public void setProperties() {
		System.setProperty("webdriver.chrome.driver","E:\\INFORMACION\\ECLIPSE\\eclipse\\Selenium\\chromedriver_win32\\chromedriver.exe");
	}
	
	public void obtenerContraseñas() {
		JPasswordField jpf = new JPasswordField(24);
		JFrame ventana = new JFrame();
		JLabel lbl1 = new JLabel("Usuario:");
		JTextField txtUsuario = new JTextField();
		JLabel lbl2 = new JLabel("Contraseña:");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		panel.add(lbl1);
		panel.add(txtUsuario);
		panel.add(lbl2);
		panel.add(jpf);
		ventana.add(panel);
		int x = JOptionPane.showConfirmDialog(null, panel , "Iniciar sesión", JOptionPane.OK_CANCEL_OPTION);
		usuario = txtUsuario.getText();
		contrasenha = jpf.getText();
	}
	
	public void crearArchivoLectura() {
		archivo = new File("ruta");
		try {
			br = new BufferedReader(new FileReader(archivo));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public WebDriver crearDriver() {
		WebDriver driver=new ChromeDriver();
		return driver;
	}
	
	public void iniciarSesion(WebDriver driver) {
		WebElement username = driver.findElement(By.id("cphCuerpo_TxtUsario"));
		driver.findElement(By.id("cphCuerpo_TxtUsario")).sendKeys(usuario);

		//TODO
		WebElement password = driver.findElement(By.id("cphCuerpo_TxtContrasena"));
		password.sendKeys(contrasenha);

		WebElement BtnIngresar=driver.findElement(By.id("cphCuerpo_BtnAutenticar"));
		BtnIngresar.click();
		
		Thread.sleep(2000);
	}
	
	public void navegarMenu() {
		
	}
	
	public void crearSolicitudes(WebDriver driver, Robot robot) {
		String linea = br.readLine();
		while(linea != null) {
			String[] valores = linea.split(",");
			
			WebElement crear = driver.findElement(By.id("cphCuerpo_btnCrear"));
			crear.click();
			Thread.sleep(200);
			
			//Inicia a crear la solicitud.
			buscarElementoWeb("cphCuerpo_txtNombre", driver).sendKeys(valores[0]);
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_ddZonaTransporte", driver).sendKeys(valores[1]);
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_ddModalidad", driver).sendKeys(valores[2]);
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_ddlServicio", driver).sendKeys(valores[3]);

			Thread.sleep(500);
			
			buscarElementoWeb("cphCuerpo_txtFechaIniVigencia", driver).sendKeys(valores[4]);
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_txtFechaFinVigencia", driver).sendKeys(valores[5]);
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_ddTipoMoneda", driver).sendKeys(valores[6]);
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_txtIncrementoFestivo", driver).sendKeys(valores[7]);
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_wucCreadorFormulas", driver).sendKeys(valores[8]);
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_txtIncrementoFestivo", driver).sendKeys(valores[9]);
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_btnValidar", driver).click();
			Thread.sleep(200);
			
			buscarElementoWeb("cphCuerpo_btnAgregar", driver).click();
			Thread.sleep(100);
			
			buscarElementoWeb("cphCuerpo_btnGuardar", driver).click();
			Thread.sleep(300);
			
			System.out.println("Prueba de commit");
		}
	}
	
	public WebElement buscarElementoWeb(String id, WebDriver driver) {
		return driver.findElement(By.id(id));
	}
	
	public static void main(String[] args) { 

		Cargue_Tarifas main = new Cargue_Tarifas();
	}

}
