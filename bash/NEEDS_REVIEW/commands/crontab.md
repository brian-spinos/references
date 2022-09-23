# crontab

https://crontab.guru/
https://www.youtube.com/watch?v=QZJ1drMQz1A&feature=youtu.be

```bash
$ crontab -l # See active cron jobs
$ crontab -e # Edit crontab file
$ crontab -r # remove all the contents of our crontab
```

###### Structure

```
* * * * *  command-to-execute
│ │ │ │ │
│ │ │ │ └─── day of week (0 - 6) (0 is Sunday, 7 is Sunday in some systems)
│ │ │ └───── month (1 - 12)
│ │ └─────── day of month (1 - 31)
│ └───────── hour (0 - 23)
└─────────── min (0 - 59)
```

###### Examples

```bash
# Everyday day at 9:40pm
40 21 * * * ~/script.sh


# Everyday hour at --:05
5 * * * * ~/script.sh


# Everyday hour at --:05, --:13, --:19
5,13,19 * * * * ~/script.sh


# Everyday minute
* * * * * ~/script.sh


# Execute on workdays 1AM
0 1 * * 1-5 ~/script.sh


# Execute every 10 minutes
*/10 * * * * ~/script.sh
```
