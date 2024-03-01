# Module 7

# Vision
The Task Manager Application is designed to streamline project management and task tracking, inspired by the robust functionalities of Jira. This application aims to facilitate the organization of tasks, enhance team collaboration, and improve productivity through an intuitive and user-friendly interface.

# Features 
Task Management: Create, edit, and manage tasks with ease.  
Shortcuts: Utilize keyboard shortcuts to navigate through the application swiftly.  
User-Friendly Interface: An intuitive interface that makes task management hassle-free.

# Running the software
Clone the repository or download the source code.  
Ensure JavaFX is properly configured on your IDE or build tool.  
Run TaskManagerApplication.java to start the application.

# Accessing Functionalities
Main Window: Launch the application to view the main task manager window where you can view your task list.  
Adding Tasks: Access by clicking the Create button. This opens a new window where you name, describe, and assign a state to the task.   
Editing Tasks: Access by clicking the Edit button.  This opens a window that shows all fields as they currently are. Edit the field and click Save.  
Delete Tasks: Delete a task by clicking on the specific task to be deleted and then click the Delete button. This prompts a window to ensure you are prepared to permanently delete the selected task.  
Shortcuts Window: Open the shortcuts window from the main menu to learn about keyboard shortcuts available to enhance your productivity.  

# Drag and Drop Feature
After a task is created, you are able to drag the card from one state to another, in any direction.   
For example, if a task is sitting in the TODO state, you can drag it over to the DOING state once started.  
Another example being that a task was assumed to be DONE but there is more to the task than expected, the task can be dragged back into the DOING state.  


# Desired, but not yet implemented.
The escape button shortcut is implemented, but the enter and delete shortcuts are not yet implemented.  
User assignment per task would be a great implementation to ensure there aren't multiple people working on one task.  
Priority level per task would also be great. Seeing what is top priority would allow task that are MOST important to be complete before tasks with a lower priority.   
Login authentication so that, instead of seeing the whole teams tasks, users can only see what tasks are assigned to them.

