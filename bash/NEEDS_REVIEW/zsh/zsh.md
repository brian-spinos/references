# ZSH


```


# Download oh-my-zsh (make sure to remove ~/.oh-my-zsh before)
sh -c "$(curl -fsSL https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"




# Now configure the ~/.zshrc  file for further configuration


#----------
# why is iterm fonts not working?
# => because you need to install the fonts
#=> https://github.com/powerline/fonts


# clone
git clone https://github.com/powerline/fonts.git
# install
cd fonts
./install.sh
# clean-up a bit
cd ..
rm -rf fonts

#=> Powerline fonts installed to /Users/brianspinos777/Library/Fonts

#----------

The reason I couldnt get the fonts was that I had to do:
- I had to go to iTerm2 > Preferences... > Profiles > Text > Non-ASCII Font > Meslo





#-----(right side stuff on the terminal, together with agnoster theme!)
git clone https://github.com/bhilburn/powerlevel9k.git ~/.oh-my-zsh/custom/themes/powerlevel9k
#-----

#----------------------



echo $ZSH_THEME

echo $ZSH_CUSTOM


subl $ZSH_CUSTOM 

/Users/brianspinos777/.oh-my-zsh/custom

/Users/brianspinos777/.oh-my-zsh/oh-my-zsh.sh


subl ~/.zshrc


echo "\ue0b0 \u00b1 \ue0a0 \u27a6 \u2718 \u26a1 \u2699"  # symbols


/Users/brianspinos777/.oh-my-zsh  # real stuff folder!!!
/Users/brianspinos777/.oh-my-zsh/custom  # my new naive stuff ???



# set variables (no $ sign)
export FOO=BAR


export ZSH_CUSTOM=/Users/brianspinos777/.oh-my-zsh


# zsh github
https://github.com/agnoster/agnoster-zsh-theme
```









