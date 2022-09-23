# ssh-agent


###### ssh-agent
In Unix, `ssh-agent` is a background program that handles passwords for `ssh` private keys. 

###### ssh-add
The `ssh-add` command prompts the user for a private key password and adds it to the list maintained by `ssh-agent`.

Once you add a password to `ssh-agent`, you will not be prompted for it when using `ssh` or scp to connect to hosts with your public key.

The public part of the key loaded into the agent must be put on the target system in `~/.ssh/authorized_keys`.


###### Usage

```bash
$ eval `ssh-agent` # init the agent

# Now enter the command:  ssh-add
# and enter your private key password.
# When you log out, enter the command:  kill $SSH_AGENT_PID
#     to run this command automatically when you log out, place it in 
#     your .bash_logout file.
```
