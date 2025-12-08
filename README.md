# 🧱 Diabetrack

## 1. Título y descripción breve
- **Nombre del proyecto:** Diabetrack  
- **Descripción:** “Aplicación de escritorio para el seguimiento de glucemia y el cálculo orientativo de dosis de insulina en personas con diabetes tipo 1.
Desarrollada con arquitectura cliente–servidor, JavaFX en el frontend y Spring Boot en el backend.”

---

## 2. 🎯 Objetivo del proyecto  
  
El principal objetivo del proyecto DiabeTrack es desarrollar una aplicación de escritorio que asista a personas con diabetes tipo 1 en el cálculo de la dosis de insulina a inyectar antes de las comidas, basándose en los niveles de glucosa en sangre y los alimentos seleccionados. Como objetivos específicos se consideran:  

- Facilitar el registro y seguimiento de mediciones de glucosa.
- Ofrecer un cálculo orientativo de insulina según los alimentos elegidos.
- Permitir la gestión personalizada de alimentos e históricos de usuario.
- Garantizar un acceso seguro mediante autenticación de usuario.


---

## 3. 🛠 Tecnologías empleadas  
### Frontend (aplicación de escritorio)
- Lenguaje principal: *Java.*
- Interfaz gráfica: *JavaFX - Scene Builder.*  
- CSS para estilos
### Backend (Servidor de aplicaciones)
- Servidor: Spring Boot.
- Persistencia: Spring Data JPA.  
- Hibernate  
- JasperReports (informes PDF)
 
### Backend (Servidor de aplicaciones)  
- Base de datos: MySQL.
- Herramienta de modelado y consultas: MySQL Workbench. 

### Herramientas
- IDE de desarrollo: NetBeans.
- Control de versiones: Git.  
- SmartDraw  
- TeamGantt  
- VSCode --> Postman


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
Estructura actual del backend:<br><br>
![Estructura backend](/readme_images/estructurabacktend1.PNG)


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
  
  ### 5.1.	Modelo Entidad-Relación: La siguiente figura muestra el diagrama E/R empleado en el proyecto:.    
    
    
  ![Modelo](/readme_images/modelo.PNG) 
  

---
  
  ## 5. Pruebas con Postman preintegración

  - Listar todos los alimentos  
  ![Postman](/readme_images/Postman1.PNG)  

  - Obtener un alimento por id  

    ![Postman](/readme_images/Postman2.PNG) 

  - Crear alimento (endpoint admin /usuario/{id})
     
     ![Postman](/readme_images/Postman3.PNG) 
      ![Postman](/readme_images/Postman4.PNG)
       
  - Guardar un registro de comida
     
     ![Postman](/readme_images/Postman5.PNG)

  - Guardar un registro con alimento inexistente
       
       ![Postman](/readme_images/Postman6.PNG)
       ![Postman](/readme_images/Postman7.PNG)
       ![Postman](/readme_images/Postman8.PNG)


## 6. Instalación y ejecución
⚙️ **INSTALACIÓN**  
  
  1️⃣ Requisitos previos    
    **Hardware**

  - CPU Intel i5 o superior

  - 8 GB RAM (recomendado 16 GB)

  - 1 GB de espacio libre  

**Software**

  - Java JDK 17

  - MySQL Server 8.x

  - MySQL Workbench

  - NetBeans / IntelliJ / Eclipse (solo si deseas modificar código)

  - Maven

  - Git (opcional)  

2️⃣ Instalación de la base de datos  
  - Crear base de datos vacía

  - Ejecutar en MySQL Workbench:  
  **_CREATE DATABASE diabetrack CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;_**  
    
  - Insertar datos iniciales obligatorios:      
    **ROLES**   <br>
    **_INSERT INTO roles (id_rol, nombre) VALUES_**  
**_(1, 'usuario'),_**  
**_(2, 'admin');_**  
 **ALIMENTOS** 
 **_INSERT INTO alimentos (id_alimento, nombre, carbohidratos, indice_glucemico, racion)_**   
**_VALUES_**   
**_(1, 'Manzana', 12, 40, 100),_**   
**_(2, 'Banana', 22, 55, 100),_**   
**_(3, 'Pan blanco', 49, 70, 30),_**   
**_(4, 'Arroz blanco cocido', 28, 73, 100),_**   
**_(5, 'Pasta cocida', 25, 50, 100),_**   
**_(6, 'Leche entera', 5, 30, 100),_**   
**_(7, 'Yogur natural', 4, 35, 100),_**   
**_(8, 'Galletas María', 69, 60, 30),_**   
**_(9, 'Cereal corn flakes', 84, 81, 30),_**   
**_(10, 'Zanahoria cocida', 10, 49, 100);_**   

3️⃣ Configuración del backend (Spring Boot)

Edita el archivo application.properties:  
  __spring.datasource.url=jdbc:mysql://localhost:3306/diabetrack__  
__spring.datasource.username=root__  
__spring.datasource.password=***__  
__spring.jpa.hibernate.ddl-auto=update__    
  A continuación ejecutar el backend:  
  **_mvn spring boot:run_** 

4️⃣ Instalación del cliente JavaFX

  -  Abrir el proyecto frontend_diabetrack en NetBeans.

  - Verificar que JavaFX está configurado correctamente.

  - Ejecutar la aplicación con Run Project o, si tienes un JAR empaquetado, doble clic sobre él.

  5️⃣ Configuración del cliente

El cliente obtiene la URL del servidor desde:  
**_config.properties_**
  

- 🚀 **EJECUCIÓN**

Una vez instalada la app se abrirá a través de su ejecutable, cargará una splash screen durante unos segundos:  
  
![Splash](/readme_images/splash.png)
  
A continuación aparecerá la pantalla de login:  
  
![Login](/readme_images/login.png)
  
  
En caso de que no tengamos cuenta creada, desde la pantalla de login podemos acceder al formulario para darnos de alta en el sistema. El formulario está correctamente configurado desde el controlador para validar que los campos contienen información en los formatos solicitados:    

![Registro](/readme_images/registro1.png)   
  
![Registro](/readme_images/registro2.png)   
  
![Registro](/readme_images/Registro3.PNG)   
  
![Registro](/readme_images/Registro4.PNG)       
  
  En caso de cumplimentar correctamente todos los campos solicitados se creará la cuenta una vez validado el formulario.  

Tras la creación del la cuenta podremos iniciar sesión para poder utilizar las diferentes funcionalidades de la app. Una vez ingresadas las credenciales accederemos al panel principal:  

![Dashboard](/readme_images/dashboard.png)  
  
  Contaremos con las siguientes opciones:
- Registrar entrada: perite registrar las diferentes ingestas con sus correspondientes valores que permitirán realizar y registrar el cálculo del bolo. Muestra en pantalla los últimos 5 registros.    
  
  ![RegistroEntrada1](/readme_images/RegistrarEntrada1.png)   
    
 
- Cálculo del bolo: nos permite realizar el cálculo del bolo en base a diferentes valores y alimentos escogidos, pero sin registro de ingestas.    

![CalculoBolo](/readme_images/CalculoBolo.png)   

- Informes: permite la descarga en PDF de diferentes informes.    

![Informe](/readme_images/Informe1.png)    
  
  ![Informe](/readme_images/Informe2.png)


   


---



## 7. Funcionalidades principales  
La aplicación tendrá, entre otras, las siguientes funcionalidades:
- Autenticación de usuarios: acceso seguro con usuario y contraseña.
- Gestión de alimentos: base de datos con diferentes alimentos y su índice glucémico. Posibilidad de añadir nuevos alimentos.
- Cálculo de insulina: el usuario introduce el nivel de glucosa en sangre, selecciona alimentos y cantidades; la aplicación devuelve la dosis estimada de insulina.
- Registro de histórico: guardado de las últimas mediciones y dosis calculadas.


---

## 8. 👨‍💻  Créditos y licencia 
- **Autor:**  Roberto Abelleira Pesqueira
- **Licencia:** “Uso académico.

---
