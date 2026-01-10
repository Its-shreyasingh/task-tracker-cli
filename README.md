#  Task Tracker CLI

A simple Java-based Command Line Interface(CLI) tool to track  daily tasks. This project helps  manage productivity by saving tasks into a local JSON file.
This project uses JSON for persistent data storage and Gradle for dependency management.

`task-tracker-cli` is a simple, no-BS Java command-line application to manage your personal tasks. Built to satisfy the [roadmap.sh](https://roadmap.sh/projects/task-tracker) backend project requirements.

##  Local Environment Details
For quick reference on this machine:
* **Project Root:** `/d/Projects/TASK-TRACKER-CLI`
* **Executable:** `/d/Projects/TASK-TRACKER-CLI/gradlew`
* **Data File:** `/d/Projects/TASK-TRACKER-CLI/tasks.json`

---

##  Installation

### 1. Using the Gradle Wrapper
You don't need to install Gradle globally.Use the included wrapper to build th project.
```bash
#clone this repo
git clone https://github.com/Its-shreyasingh/task-tracker-cli.git
cd TASK-TRACKER-CLI
```
### 2. Build the Project
Ensure you have JDK 17 or higher installed.
```bash
./gradlew build
```
### 3.Create a Shortcut (Alias)
To use the task command instead of the long Gradle path,add this to your ~.bashrc:
```bash
alias task='/d/Projects/TASK-TRACKER-CLI/gradlew -p /d/Projects/TASK-TRACKER-CLI -q run --args'
```
Then run : source ~/.bashrc


## Commands

### 1) `task`

---

**Usage**:
```console
$ task"[COMMAND] [ARGS]"
```
|Command | Description |
|:--- | :--- |
| **add** | Add a new task to your list |
| **list** | Show all tasks (can be filtered by status) |
|**update** | Change the description of an existing task |
|**delete** |Remove a task using its ID |
|**mark-in-progress** |Set a task status to in-progress |
|**mark-done** |Set a task status to 'done'|

---
### 2) `task add`
---
Add a new task to your list.

**Usage**:
```console
$ task "add 'My Task Description' 'todo'"
```
---
### 3) `task list`
---
Display your tasks in a formatted table.

**Usage**:

*All tasks*:
```console 
$ task "list"
```
*By status*:
```console
$ task "list done" (also supports todo or in-progress)
```
---
### 4) `task update`
---
Change the text of an existing task.

**Usage**:
```console
$ task "update [ID] 'New Description'"
```
---

### 5) `task mark`
---
Quickly update the progress of a task.

**Usage**:
```console 
$ task "mark-in-progress [ID]"
$ task "mark-done [ID]"
$ task "mark-to-do [ID]"
```
---
### 6) `task delete`
---
Permanently remove a task using its ID.

**Usage**:
```console
$ task "delete [ID]"
```
---

## TECHNICAL DETAILS

* **Language** : Java 17+
* **Build System** : Gradle
* **JSON Library** : Jackson (Databind & JSR310)
* **Storage** : Data is persisted locally in `tasks.json`
---



