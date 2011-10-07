## Introduction
This is an undergraduate research project for the University of Texas at Austin, Biochemistry and Chemistry Department. For uses please contact ma65p2004@gmail.com

This is a Json script for plotting chain polymerization according to Louis Gold's equation.

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


	

