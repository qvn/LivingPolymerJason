## Introduction
This is an undergraduate research project for the University of Texas at Austin, Biochemistry and Chemistry Department. For uses please contact ma65p2004@gmail.com

This is a Json script for plotting chain polymerization according to Louis Gold's equation.

## Some Files Description

There are multiple files with main methods capable of running on its own:

### JSONOutput.java
This is the main file that will print the JSON output. I am not interested in getting this file longer and longer. Therefore other files were created for testing purposes. 

Do note that other Java files that are not explained here probably left alone for a long time and they were made to be working with JSONOutput.java

### Other Files with Main methods

1. SingleIM: Made to only print I and M concentration. Nothing More. Probably will be deleted in the near future since PrintURange and PrintWithRange are more capable. 

2. PrintWithRange: user is forced to define the time step, and if time step give more than a certain number of step, say 10000, user is then asked to provide the range. Mostly created to investigate U by messing around with dt and still not overflowing the memory. Maybe deleted as PrintURange is capable of getting a certain number of U.

3. PrintURange: This was made to produce U values similar to that of Gold's. Note that Initial conditions must be defined in the file. Got tired of repeating the same input in the console. Too lazy to put a parser. Allow to print a range of values that is approx = to U_desire. dt must be changed to improve precisions. 





## Misc
Some of these notes are for me, you do not need to concern about it. However, it might useful in some cases. 

### Working with git remotely at different computer
Purpose: sometimes I'm at the lib and need to work on this. Don't have my computer with me but have some java compiler. 

#### Get ready
1. Get [PortableGit](http://code.google.com/p/msysgit/downloads/)

2. Rename file from .7z to .exe to self extract. Extract it somewhere. 

3. Launch "git-bash" or "git-cmd". Both for this time.

#### Get your stuff down. 

[Source from here](http://stackoverflow.com/questions/67699/how-do-i-clone-all-remote-branches-with-git)

Get to a directory (probably your WorkSpace in Eclipse). Note that you must use Absolute path in the git-bash or git-cmd as it thinks root is where it resides.

Make git, then clone (https link is from github)
	
	git init
	git clone https://ma65p@github.com/ma65p/LivingPolymerJason.git

Git will probably added the https already. That way you don't have to enter the URL everytime, if not do:

	git remote add https://ma65p@github.com/ma65p/LivingPolymerJason.git

For braches:

	$ git branch -a
	* master
  	origin/HEAD
  	origin/master
  	origin/v1.0-stable
  	origin/experimental

If you just want to take a quick peek at an upstream branch, you can check it out directly:

	$ git checkout origin/experimental

But if you want to work on that branch, you'll need to create a local tracking branch:

	$ git checkout -b experimental origin/experimental

Remember to name experimental as experimental. Same branch name will save you some headache later. 

#### More Repo!

You can actually track more than one remote repository using git remote.
	
	$ git remote add win32 git://example.com/users/joe/myproject-win32-port
	$ git branch -a
	* master
	  origin/HEAD
	  origin/master
	  origin/v1.0-stable
	  origin/experimental
	  win32/master
	  win32/new-widgets

At this point, things are getting pretty crazy, so run gitk to see what's going on:
	
	$ gitk --all &


	

