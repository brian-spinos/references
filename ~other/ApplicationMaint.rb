#=======================================================
# RUN APP:
# ruby ./foo.rb

# use Rx? https://github.com/ReactiveX/RxRuby

# TODO: app models are not being used, command needs to know about the status
# of the prev command, so it knows what to do
# - this script needs a log folder: './___LOGS'

#=======================================================
class ThreadPool
  def initialize(size)
    @size = size # size of the pool that you want
    @jobs = Queue.new
    @pool = Array.new(@size) do |i| # @size here are the number of elements in the array, zero-based
      Thread.new do # create a thread for each of all elements in the array 
        #Thread.current[:id] = i #  assign an ID for each thread, each ID correspond to an element in the array above, zero-based
        catch(:exit) do
          loop do
            job, args = @jobs.pop # we might not need the args
            # puts "ARG 1: #{args[0].inspect}"
            # puts "ARG 2: #{args[1]}"
            job.call(*args)
          end
        end
      end
    end
  end

  # Push jobs to pool
  def schedule(*args, &block)
    @jobs << [block, args]
  end

  def shutdown
    @size.times do
      schedule { throw :exit }
    end
    @pool.map(&:join)
  end

end

#======================================================= Model

class App
  attr_accessor(
    :name,
    :currentCommand, 
    :currentCommandOutput,
    :currentStatus,
    :isComplate,
    :shouldStop,
    :errors,
    :prevStepSuccedded
  )
end

#=======================================================

class ApplicationMaint

  def initialize(appsToRun, action, threadCount, logsFolder, appsFolder)
    @appsToRun = appsToRun
    @action = action
    @threadCount = threadCount
    @logsFolder = logsFolder
    @appsFolder = appsFolder
    @threadPool = ThreadPool.new(threadCount)
  end

  def start
    # if the program ends, finish up the batch of the pool size!
    at_exit { @threadPool.shutdown }  

    # SCHEDULE JOBS
    @appsToRun.split(",").each do |appName| 
      app = App.new()
      app.name = appName
      @threadPool.schedule(app, 123) { runApp(app) }
    end
  end

  def runApp(app)
    if folderExists(app)
      case @action
      when 'ACTION_001'
        action1(app)
      when 'ACTION_002'
        action1(app)
      when 'ACTION_003'
        action1(app)
      when 'ACTION_004'
        action1(app)
      when 'ACTION_005'
        action1(app)
      else
        puts 'ACTION FAIL'
      end
    else
      folderDoesNotExist(app)
    end
  end

  def folderExists(app)
    return true
  end

  def folderDoesNotExist(app)
  end

  # action1
  # action2
  # action3
  def action1(app)
    puts app
    gitPullOriginMaster(app)
    gitPushProductionMaster(app)
  end

  def gitPullOriginMaster(app)
    commandString = 'pwd'  # `cd #{@appsFolder} && git pull origin master`
    ts = Time.now.strftime("%H:%M:%S %4N")
        # Run command
    commandResult = `#{commandString}`

    # initial log for command 
    logForApp(app, "============================= command: #{commandString}")
    logForApp(app, "--> timestamp:")
    logForApp(app, "#{ts}")
    logForApp(app, "--> output:")
    logForApp(app, "#{commandResult}")

    # HANDLE COMMAND RESULT
    case commandResult
    when 'Already up-to-date'
      # update model
      app.currentCommand = 'abc'
      app.currentCommandOutput = 'abc'
      app.currentStatus = 'cmd done'
      app.isComplate = false
      app.shouldStop = false
      app.errors = [1,2,3]
      app.prevStepSuccedded = true
    when /abc/
      # update model
      app.currentCommand = 'abc'
      app.currentCommandOutput = 'abc'
      app.currentStatus = 'cmd done'
      app.isComplate = false
      app.shouldStop = false
      app.errors = [1,2,3]
      app.prevStepSuccedded = true

    when 'Already up-to-date'
      # update model
      app.currentCommand = 'abc'
      app.currentCommandOutput = 'abc'
      app.currentStatus = 'cmd done'
      app.isComplate = false
      app.shouldStop = false
      app.errors = [1,2,3]
      app.prevStepSuccedded = true
    when 'Already up-to-date'
      # update model
      app.currentCommand = 'abc'
      app.currentCommandOutput = 'abc'
      app.currentStatus = 'cmd done'
      app.isComplate = false
      app.shouldStop = false
      app.errors = [1,2,3]
      app.prevStepSuccedded = true
    else
      puts 'AAAA8888 FAIL'
    end

    # final log for command
    # logForApp(app, "gitPullOriginMaster::done") 
  end

  def gitPushProductionMaster(app)
    commandString = 'ls -la' # `cd #{@appFolder} && git pull origin master`
    ts = Time.now.strftime("%H:%M:%S %4N")
        # Run command
    commandResult = `#{commandString}`

    # initial log for command 
    logForApp(app, "============================= command: #{commandString}")
    logForApp(app, "--> timestamp:")
    logForApp(app, "#{ts}")
    logForApp(app, "--> output:")
    logForApp(app, "#{commandResult}")

    # HANDLE COMMAND RESULT
    case commandResult
    when 'Already up-to-date'
      # update model
      app.currentCommand = 'abc'
      app.currentCommandOutput = 'abc'
      app.currentStatus = 'cmd done'
      app.isComplate = false
      app.shouldStop = false
      app.errors = [1,2,3]
      app.prevStepSuccedded = true

    when /total 112/
      # update model
      app.currentCommand = 'abc'
      app.currentCommandOutput = 'abc'
      app.currentStatus = 'cmd done'
      app.isComplate = false
      app.shouldStop = false
      app.errors = [1,2,3]
      app.prevStepSuccedded = true

    when 'Already up-to-date'
      # update model
      app.currentCommand = 'abc'
      app.currentCommandOutput = 'abc'
      app.currentStatus = 'cmd done'
      app.isComplate = false
      app.shouldStop = false
      app.errors = [1,2,3]
      app.prevStepSuccedded = true
    when 'Already up-to-date'
      # update model
      app.currentCommand = 'abc'
      app.currentCommandOutput = 'abc'
      app.currentStatus = 'cmd done'
      app.isComplate = false
      app.shouldStop = false
      app.errors = [1,2,3]
      app.prevStepSuccedded = true
    else
      puts 'AAAA8888 FAIL'
      # update model
    end

    # final log for command
    # logForApp(app, "gitPullOriginMaster::done") 
  end


  def logForApp(app, msg)
    app_date = '2022_02_05' # app.date
    app_name = app.name
    appLogFile = "#{app_date}_#{app_name}.txt"

    `cd #{@logsFolder} && echo "#{msg}" >> #{appLogFile}`
  end
end


#======================================================= Entry point
appsToRun = "fooApp,barApp,bazApp,aaaApp"
action = "ACTION_001"
threadCount = 15
logsFolder = './___LOGS'
appsFolder = "../apps"

# select apps to run, select ACTION, click submit
service = ApplicationMaint.new(
  appsToRun, 
  action, 
  threadCount,
  logsFolder,
  appsFolder
)

service.start


#======================================================= Notes

# git pull origin master
# git push production master 
# heroku run rake db:migrate
# heroku restart
# heroku maintenance:on
# heroku run rake db:migrate
# heroku restart 
# heroku maintenance:off 
# end logs


# let logForApp = (app, logTitle, message) => {
#   log_line = "==========="
#   ts = 'Time.now.strftime("%H:%M:%S %4N")'
#   logFolder = "/log"
#   appLogFile = `#{app.date}_#{app.name}.txt`
#   exec(`cd #{logFolder} && echo "#{log_line} #{logTitle} #{ts} - #{message}" >> #{appLogFile}`)
# }

# deleteLogs() # single and all
# writeAppLogFile(app)
# updateAppModel(app)

# 3. select action to run for each app:
#    - pull, push, end_to_log
#     - pull, push, migrate, heroku_restart, end_to_log
#     - pull, push, maintenance_on, migrate, heroku_restart, maintenance_off, end_to_log


# SCHEDULE_APP_RUN -> select_ACTION -> RUN_ACTION -> RUN_COMMAND_LIST
