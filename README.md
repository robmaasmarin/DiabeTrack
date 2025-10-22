# 🧱 Diabetrack

## 1. Título y descripción breve
- **Nombre del proyecto:** Diabetrack  
- **Descripción:** “Aplicación para el seguimiento de glucemia y cálculo de dosis de insulina adaptada a la alimentación.”

---

## 2. Objetivos  
  
El principal objetivo del proyecto DiabeTrack es desarrollar una aplicación de escritorio que asista a personas con diabetes tipo 1 en el cálculo de la dosis de insulina a inyectar antes de las comidas, basándose en los niveles de glucosa en sangre y los alimentos seleccionados. Como objetivos específicos se consideran:  

- Facilitar el registro y seguimiento de mediciones de glucosa.
- Ofrecer un cálculo orientativo de insulina según los alimentos elegidos.
- Permitir la gestión personalizada de alimentos e históricos de usuario.
- Garantizar un acceso seguro mediante autenticación de usuario.


---

## 3. Tecnologías empleadas  
### Frontend (aplicación de escritorio)
- Lenguaje principal: *Java.*
- Interfaz gráfica: *JavaFX - Scene Builder.*
### Backend (Servidor de aplicaciones)
- Servidor: Spring Boot.
- Persistencia: Spring Data JPA.
- Base de datos: MySQL.
- Herramienta de modelado y consultas: MySQL Workbench.
### Frontend & Backend
- IDE de desarrollo: NetBeans.
- Control de versiones: Git.


---

## 4. Estructura del proyecto
Para la estructura del proyecto he creado único repositorio que contendrá pero separará en dos grandes bloques:  
- backend_diabetrack: carpeta reservada para la lógica del servidor o backend.  
- frontend_diabetrack: contiene la interfaz gráfica y lógica de presentación del proyecto.  
  
La idea es que el proyecto cuente con una estructura bien organizada por capas, siguiendo el Modelo Vista Controlador.  

### FRONTEND  
A continuación, se muestra la estructura actual del proyecto:

![Estructura frontend](/readme_images/estructurafrontend.PNG)




Siguiendo el Modelo Vista Controlador he ido creando:
- Carpetas separadas para models, controllers, fxml y css.
- Carpetas dedicadas en resources para recursos gráficos.
- Separación entre código fuente (src) y compilado (build). 
   
### BACKEND  

Para el __backend__ también se contempla una estructura siguiendo el mismo patrón, con una estructura de paquetes diferenciados para:
- Gestionar peditiones REST.
- Contener lógica de negocio (por ejemplo el cálculo de insulina).
- Repository: comunicación con la BBDD.
- Model entidades JPA mapeadas a las tablas.
Un primer boceto para la estructura del backend podría ser el siguiente:  
src/
	- main/java/com/diabetrack/  
		- controller/  
			UsuarioController.java  
			AlimentoController.java  
			EntradaController.java  
		- model/  
			Usuario.java  
			Alimento.java  
			Entrada.java  
			DetalleEntrada.java  
		- repository/  
			UsuarioRepository.java  
			AlimentoRepository.java  
			EntradaRepository.java  
			DetalleEntradaRepository.java  
		- Service/  
			UsuarioService.java  
			AlimentoService.java  
			EntradaService.java  
			DetalleEntradaService.java  
		- dto/ (en caso de necesitar enviar datos combinados)  
		DiabetrackApplication.java

Esta organización permitirá mantener una clara separación entre las capas de presentación, negocio y acceso a datos, facilitando el trabajo en equipo y las futuras ampliaciones.

### BASE DE DATOS  

La aplicación contará con una base de datos relacional MySQL, diseñada para mantener integridad referencial y escalabilidad.  

Se contemplan al menos cinco tablas relacionadas, incluyendo una tabla de roles y relaciones entre usuarios, alimentos, registros e historial.  
Boceto de la bbdd:  

### Tablas de usuarios: contiene datos personales y de acceso de los usuarios registrados (pacientes).
  
  ![Tabla usuarios](/readme_images/tablausers.png)  
    
### Tabla de roles: define los roles de usuario dentro del sistema:   
![Tabla roles](/readme_images/tablaroles.png)    
### Tabla categorías: clasificación de los alimentos según su tipo o familia.  
![Tabla categorías](/readme_images/tablacategorías.png)   
### Tabla de alimentos: contendrá la info nutricional de los alimentos y los vinculará a la categoría correspondiente:  
![Tabla alimentos](/readme_images/tablaalimentos.png)   
### Tabla de registros: guardar el histórico de mediciones y dosis de insulina calculadas por el usuario.  
![Tabla registros](/readme_images/tablaregistros.png)   
  
  

---

## 5. Instalación y ejecución
   
![En construcción](/readme_images/work.png)

---



## 7. Funcionalidades principales  
La aplicación tendrá, entre otras, las siguientes funcionalidades:
- Autenticación de usuarios: acceso seguro con usuario y contraseña.
- Gestión de alimentos: base de datos con diferentes alimentos y su índice glucémico. Posibilidad de añadir nuevos alimentos.
- Cálculo de insulina: el usuario introduce el nivel de glucosa en sangre, selecciona alimentos y cantidades; la aplicación devuelve la dosis estimada de insulina.
- Registro de histórico: guardado de las últimas mediciones y dosis calculadas.


---

## 8. Capturas

  
Una vez instalada la app se abrirá a través de su ejecutable, cargará una splash screen durante unos segundos:  
  
![Splash](/readme_images/splash.png)
  
A continuación aparecerá la pantalla de login:  

![Login](/readme_images/login.png)
  
Una vez iniciemos sesión nos encontraremos con el panel principal:  

![Dashboard](/readme_images/dashboard.png)
  
En caso de que no tengamos cuenta creada, desde la pantalla de login podemos acceder al formulario para darnos de alta en el sistema:  

![Registro](/readme_images/registro1.png) ![Registro](/readme_images/registro2.png)

---

## 9. Créditos y licencia 
- **Autor:**  Roberto Abelleira Pesqueira
- **Licencia:** “Uso académico.

---
