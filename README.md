# 🎮 Sudoku

<div align="center">
  
  [![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)](https://github.com/usuario/sudoku2/releases/tag/v1.0.0)
  [![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
  [![JavaFX](https://img.shields.io/badge/JavaFX-17+-green.svg)](https://openjfx.io/)
  [![License](https://img.shields.io/badge/license-MIT-purple.svg)](LICENSE)
</div>

---

## 📖 Descripción General

*Sudoku* es una implementación simple y atractiva del clásico juego de lógica Sudoku, desarrollada completamente en Java utilizando JavaFX para crear una interfaz gráfica atractiva e intuitiva. Este proyecto combina la esencia tradicional del Sudoku con una experiencia visual envolvente.

El juego está diseñado con una arquitectura limpia y modular, facilitando su mantenimiento y futuras expansiones. Ideal tanto para jugadores casuales como para personas avanzadas del Sudoku que buscan un desafío mental.

---

## ✨ Características Principales

- 🎨 *Interfaz Gráfica Moderna*: Diseño simple, intuitivo y atractivo desarrollado con JavaFX
- ✅ *Validación en Tiempo Real*: Comprueba automáticamente y detecta errores
- 🔢 *Generador de Tableros*: Crea tableros aleatorios bien formados y con solucion unica.
- 💡 *Sistema de Pistas*: Ayuda al jugador cuando se encuentra atascado

---

## 🛠️ Tecnologías y Herramientas

### Lenguajes y Frameworks
- *Java SE 17+*: Lenguaje de programación principal
- *JavaFX 17+*: Framework para la interfaz gráfica de usuario
- *FXML*: Lenguaje de marcado para diseñar interfaces JavaFX

### Herramientas de Desarrollo
- *IntelliJ IDEA*: IDE principal para el desarrollo
- *Scene Builder*: Herramienta visual para diseñar interfaces FXML
- *Maven*: Sistema de gestión de dependencias y construcción del proyecto
- *Git*: Control de versiones distribuido
- *GitHub*: Plataforma de alojamiento y colaboración del código

### Documentación
- *Javadoc*: Generación automática de documentación del código
- *Markdown*: Formato de documentación del proyecto

---

## 📁 Estructura del Proyecto

Sudoku-MiniProyecto2-main/
├── .idea/
├── .mvn/
├── javadoc/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/sudoku2/
│       │       ├── controller/
│       │       │   ├── SudokuFinalController.java
│       │       │   ├── SudokuGameController.java
│       │       │   ├── SudokuHelpController.java
│       │       │   └── SudokuWelcomeController.java
│       │       ├── model/
│       │       │   ├── board/
│       │       │   │   ├── Board.java
│       │       │   │   ├── BoardAdapter.java
│       │       │   │   └── IBoard.java
│       │       │   ├── game/
│       │       │   │   ├── Game.java
│       │       │   │   ├── GameAbstract.java
│       │       │   │   ├── GameAdapter.java
│       │       │   │   └── IGame.java
│       │       │   └── user/
│       │       │       └── User.java
│       │       ├── utils/
│       │       │   ├── AlertBox.java
│       │       │   └── IAlertBox.java
│       │       ├── view/
│       │       │   ├── SudokuFinalStage.java
│       │       │   ├── SudokuGameStage.java
│       │       │   ├── SudokuHelpStage.java
│       │       │   └── SudokuWelcomeStage.java
│       │       └── Main.java
│       └── resources/
│           └── com/example/sudoku2/
│               ├── css/
│               │   ├── ayuda.png
│               │   ├── back.png
│               │   ├── background.png
│               │   ├── background2.png
│               │   ├── background3.png
│               │   ├── backToMenu.png
│               │   ├── button2.png
│               │   ├── check.png
│               │   ├── exit.png
│               │   ├── gameComplete.png
│               │   ├── howToPlay.png
│               │   ├── solution.png
│               │   └── styles.css
│               ├── fxml/
│               │   ├── sudoku-final-view.fxml
│               │   ├── sudoku-game-view.fxml
│               │   ├── sudoku-help-view.fxml
│               │   └── sudoku-welcome-view.fxml
│               └── images/
│                   └── logo.png
├── target/
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml
├── README.md
└── LICENSE


---

## 🚀 Instalación y Ejecución

### Prerrequisitos

Antes de comenzar, asegúrate de tener instalado:

- ☕ *Java JDK 17 o superior* - [Descargar aquí](https://www.oracle.com/java/technologies/downloads/)
- 📦 *Maven 3.6+* - [Descargar aquí](https://maven.apache.org/download.cgi)
- 🔧 *Git* - [Descargar aquí](https://git-scm.com/downloads)
- 💻 *IntelliJ IDEA* (Recomendado) - [Descargar aquí](https://www.jetbrains.com/idea/download/)

### Verificar Instalaciones
bash
# Verificar Java
java -version

# Verificar Maven
mvn -version

# Verificar Git
git --version


### Pasos de Instalación

#### 1️⃣ Clonar el Repositorio
bash
git clone https://github.com/usuario/MiniProyecto2.git
cd sudoku-aventura-tinta


#### 2️⃣ Compilar el Proyecto con Maven
bash
mvn clean install


#### 3️⃣ Ejecutar la Aplicación

*Opción A: Usando Maven*
bash
mvn javafx:run


*Opción B: Usando IntelliJ IDEA*
1. Abre el proyecto en IntelliJ IDEA
2. Espera a que Maven descargue las dependencias
3. Busca la clase Main.java en src/main/java/com/sudoku/
4. Haz clic derecho y selecciona "Run 'Main.main()'"

*Opción C: Usando el JAR ejecutable*
bash
java -jar target/sudoku-aventura-tinta-1.0.0.jar


---

## 🎮 Cómo Jugar

1. *Iniciar el Juego*: Al abrir la aplicación, selecciona "Jugar" desde el menú principal
2. *Completar el Tablero*: Rellena las celdas vacías con números del 1 al 6
3. *Reglas del Sudoku*:
   - Cada fila debe contener los números del 1 al 6 sin repetir
   - Cada columna debe contener los números del 1 al 6 sin repetir
   - Cada cuadrícula 2x3 debe contener los números del 1 al 6 sin repetir
4. *Usar Pistas*: Si te atascas, puedes usar el botón de ayuda (limitadas por partida)
5. *Validar Solución*: presiona el boton de validar cuando completes el tablero correctamente

---

## 🔄 Uso de Git y Control de Versiones

### Flujo de Trabajo con Git

Este proyecto sigue un flujo de trabajo estructurado utilizando Git y GitHub para el control de versiones.

#### Configuración Inicial
bash
# Configurar usuario de Git
git config --global user.name "Tu Nombre"
git config --global user.email "tu.email@ejemplo.com"

# Clonar el repositorio
git clone https://github.com/usuario/MiniProyecto2.git
cd sudoku-aventura-tinta


#### Estructura de Ramas

- *main*: Rama principal con el código estable y versiones lanzadas
- *develop*: Rama de desarrollo con las últimas características integradas
- *feature/nombre-feature*: Ramas para nuevas características
- *bugfix/nombre-bug*: Ramas para corrección de errores
- *hotfix/nombre-hotfix*: Ramas para correcciones urgentes en producción

#### Crear una Nueva Rama
bash
# Crear y cambiar a una nueva rama de feature
git checkout -b feature/nueva-caracteristica

# Crear y cambiar a una rama de bugfix
git checkout -b bugfix/correccion-error


#### Realizar Commits
bash
# Añadir archivos al staging area
git add .

# O añadir archivos específicos
git add src/main/java/com/sudoku/controller/GameController.java

# Realizar commit con mensaje descriptivo
git commit -m "feat: Implementar sistema de pistas en el juego"

# Ejemplos de mensajes de commit según convención:
# feat: Nueva característica
# fix: Corrección de error
# docs: Cambios en documentación
# style: Cambios de formato, sin afectar código
# refactor: Refactorización de código
# test: Añadir o modificar tests
# chore: Tareas de mantenimiento


#### Subir Cambios al Repositorio Remoto
bash
# Subir la rama al repositorio remoto
git push origin feature/nueva-caracteristica


#### Crear Pull Request

1. Ve a GitHub en el repositorio del proyecto
2. Haz clic en "Pull Requests" > "New Pull Request"
3. Selecciona la rama base (develop) y la rama a fusionar (feature/nueva-caracteristica)
4. Añade un título descriptivo y descripción detallada
5. Asigna revisores si es necesario
6. Haz clic en "Create Pull Request"

#### Fusionar Pull Request

Una vez revisado y aprobado:
1. Haz clic en "Merge Pull Request"
2. Selecciona el tipo de merge (squash, rebase o merge commit)
3. Confirma el merge
4. Elimina la rama feature si ya no es necesaria

#### Crear Tag de Versión
bash
# Cambiar a la rama main
git checkout main

# Crear tag anotado para la versión final
git tag -a v1.0.0 -m "Versión 1.0.0 - Lanzamiento inicial de Sudoku"

# Subir el tag al repositorio remoto
git push origin v1.0.0

# Ver todos los tags
git tag -l

# Ver detalles de un tag específico
git show v1.0.0


#### Sincronizar con el Repositorio Remoto
bash
# Actualizar referencias del repositorio remoto
git fetch origin

# Fusionar cambios de la rama remota
git pull origin main

# O usar rebase para mantener historial lineal
git pull --rebase origin main


#### Comandos Útiles
bash
# Ver estado del repositorio
git status

# Ver historial de commits
git log --oneline --graph --all

# Ver diferencias entre archivos
git diff

# Deshacer cambios en un archivo
git checkout -- archivo.java

# Ver ramas locales y remotas
git branch -a

# Eliminar rama local
git branch -d feature/nombre-feature

# Eliminar rama remota
git push origin --delete feature/nombre-feature


### Historial de Versiones

| Versión | Fecha | Descripción |
|---------|-------|-------------|
| v1.0.0 | 15/10/2025 | Lanzamiento completo con todas las características principales |
| v0.8.0 | 15/10/2025 | Versión beta con funcionalidades completas |
| v0.5.0 | 13/10/2025 | Primera versión no funcional con interfaz básica |

---

## 📚 Documentación Adicional

### Generar Javadoc

Para generar la documentación Javadoc del proyecto:
bash
mvn javadoc:javadoc


La documentación se generará en: target/site/apidocs/index.html

### Recursos de Aprendizaje

- 📖 [Documentación Oficial de JavaFX](https://openjfx.io/javadoc/17/)
- 🎓 [Tutorial de Scene Builder](https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm)
- 📝 [Guía de Maven](https://maven.apache.org/guides/)
- 🔧 [Convenciones de Commits](https://www.conventionalcommits.org/)

---

## 👥 Autores

Este proyecto fue desarrollado por:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/usuario1">
        <img src="https://via.placeholder.com/100/3498DB/FFFFFF?text=AM" width="100px;" alt="Ignacio Henao Henao"/><br />
        <sub><b>Ignacio Henao Henao</b></sub>
      </a><br />
      <sub>Desarrollador Full Stack</sub><br />
      💻 🎨 📖
    </td>
    <td align="center">
      <a href="https://github.com/usuario2">
        <img src="https://via.placeholder.com/100/E74C3C/FFFFFF?text=JM" width="100px;" alt="Samuel Saldaña Giraldo"/><br />
        <sub><b>Samuel Saldaña Giraldo</b></sub>
      </a><br />
      <sub>Desarrollador Full Stack</sub><br />
      💻 🎨 📖
    </td>
  </tr>
</table>

### Contribuciones

- *Ignacio Henao Henao*: implementación del modelo de datos, lógica del juego y generador de tableros
- *Samuel Saldaña Giraldo*: Diseño de interfaz gráfica, controladores JavaFX e integración de Scene Builder

---

## 🤝 Contribuir al Proyecto

Las contribuciones son bienvenidas. Si deseas contribuir:

1. Fork el repositorio
2. Crea una rama para tu feature (git checkout -b feature/AmazingFeature)
3. Realiza commit de tus cambios (git commit -m 'feat: Add some AmazingFeature')
4. Push a la rama (git push origin feature/AmazingFeature)
5. Abre un Pull Request

### Directrices de Contribución

- Sigue las convenciones de código establecidas en el proyecto
- Escribe tests para nuevas funcionalidades
- Actualiza la documentación según sea necesario
- Usa mensajes de commit descriptivos siguiendo [Conventional Commits](https://www.conventionalcommits.org/)

---

## 🐛 Reporte de Errores

Si encuentras algún error o tienes sugerencias, por favor:

1. Verifica que el error no haya sido reportado anteriormente en [Issues](https://github.com/usuario/MiniProyecto2/issues)
2. Crea un nuevo Issue con:
   - Descripción clara del problema
   - Pasos para reproducir el error
   - Comportamiento esperado vs comportamiento actual
   - Capturas de pantalla si es aplicable
   - Información del sistema (OS, versión de Java, etc.)

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

MIT License

Copyright (c) 2025 Ignacio Henao Henao & Samuel Saldaña Giraldo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


---

## 🙏 Agradecimientos

- Al equipo de JavaFX por proporcionar un excelente framework para aplicaciones de escritorio
- A la comunidad de código abierto por las librerías y recursos utilizados
- A nuestros profesores y mentores por su guía durante el desarrollo

---

## 📞 Contacto

- 📧 Email: ignacio.henao@correounivalle.edu.com | samuelsaldana586@gmail.com
- 🐙 GitHub: [@usuario1](https://github.com/usuario1) | [@usuario2](https://github.com/usuario2)

---

<div align="center">
  <p>Hecho con ❤️ y ☕ por Ignacio Henao Henao y Samuel Saldaña Giraldo</p>
  <p>© 2025 Sudoku - Versión 1.0.0</p>
  
  ⭐ ¡Si te gusta este proyecto, dale una estrella en GitHub! ⭐
</div>
