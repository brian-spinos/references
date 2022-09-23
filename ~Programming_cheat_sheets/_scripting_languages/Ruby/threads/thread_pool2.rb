
#
# Still needs work, but better then
# the previous version
#
class WorkerPool
  def initialize(options)
    puts "Creating WorkerPool..."
    @worker_count = options[:workers]
    @scheduled_jobs = Queue.new
    # Workers are threads
    @workers = populate_worker_pool
  end

  #
  # An Array of Threads
  #
  def populate_worker_pool
    workers = []
    @worker_count.times do |i|
      w = Thread.new do    
        puts "CREATING WORKER #{i}\n"               
        Thread.current[:id] = i     

        catch(:my_custom_exit_error) do   
          puts "CATCH EXITTT CALLED #{i}\n"          
          while(true) do
            obj = @scheduled_jobs.pop
            bloc = obj[:bloc]
            args = obj[:args]

            # the actual execution of jobs!
            bloc.call(*args)
          end
        end
      end
      workers.push(w)
    end 
    
    return workers     
  end

  #
  # Add a job to be executed by the available workers
  #
  def schedule(*args, &block)
    puts "SCHEDULING A JOB... for app: #{args[0]}\n"
    job = {
      bloc: block,
      args: args
    }
    @scheduled_jobs.push(job)
  end

  #
  # Called after the program exits ???
  #
  def shutdown
    puts "SHUTDOWN CALLED\n"
    @worker_count.times do
      schedule do
        puts "THROW EXIT CALLED\n"
        throw :my_custom_exit_error # basic throwing errors
      end
    end
    @workers.map(&:join) # call item.join in all items of the array 
  end

  def execute(app_name, i, command)
    message = {
      app_name: app_name,
      worker_id: Thread.current[:id],
      job_id: i,
      timestamp: Time.now.strftime('%H:%M:%S %4N'),
      command: command,
    }

    # sleep 1
    sleep Random.rand * 1

    #
    # Write to files
    #
    File.open("#{app_name}.txt", 'a') do |file|
      file.write(message.to_json)
      file.write("\n\n")
    end

    
    puts "#{message}\n"
  end
end


#
# ======= Usage
#


# 10 apps
apps = [
  'client-a',
  'client-b',
  'client-c',
  'client-d',
  'client-e',
  'client-f',
  'client-g',
  'client-h',
  'client-i',
  'client-j',
]

wp = WorkerPool.new({workers: 5})

#
# 10 apps x 3 jobs = 30 jobs
# wit 5 workers,
# that is 5 jobs done at the same time
#
apps.each_with_index do |app_name, i|
  wp.schedule(app_name, i) do

    #
    # These commands will be written to a file in order,
    # relative to the app.
    #
    wp.execute(app_name, i, 'pwd')
    wp.execute(app_name, i, 'ls -la')
    wp.execute(app_name, i, 'cd ~/Desktop')
  end
end


#
# Called after the program exits
# Do we need this???
#
# at_exit { wp.shutdown }
# exit
nil


=begin OUTPUT:

Creating WorkerPool...
SCHEDULING A JOB... for app: client-a
SCHEDULING A JOB... for app: client-b
SCHEDULING A JOB... for app: client-c
SCHEDULING A JOB... for app: client-d
SCHEDULING A JOB... for app: client-e
SCHEDULING A JOB... for app: client-f
SCHEDULING A JOB... for app: client-g
SCHEDULING A JOB... for app: client-h
SCHEDULING A JOB... for app: client-i
SCHEDULING A JOB... for app: client-j
=> nil
CREATING WORKER 0
CREATING WORKER 1
CREATING WORKER 2
CREATING WORKER 3
CREATING WORKER 4
CATCH EXITTT CALLED 4
CATCH EXITTT CALLED 2
CATCH EXITTT CALLED 3
CATCH EXITTT CALLED 1
CATCH EXITTT CALLED 0
{:app_name=>"client-c", :worker_id=>1, :job_id=>2, :timestamp=>"07:07:43 8632", :command=>"pwd"}
{:app_name=>"client-d", :worker_id=>2, :job_id=>3, :timestamp=>"07:07:43 8632", :command=>"pwd"}
{:app_name=>"client-b", :worker_id=>3, :job_id=>1, :timestamp=>"07:07:43 8631", :command=>"pwd"}
{:app_name=>"client-e", :worker_id=>0, :job_id=>4, :timestamp=>"07:07:43 8633", :command=>"pwd"}
{:app_name=>"client-a", :worker_id=>4, :job_id=>0, :timestamp=>"07:07:43 8630", :command=>"pwd"}
{:app_name=>"client-d", :worker_id=>2, :job_id=>3, :timestamp=>"07:07:44 8680", :command=>"ls -la"}
{:app_name=>"client-c", :worker_id=>1, :job_id=>2, :timestamp=>"07:07:44 8690", :command=>"ls -la"}
{:app_name=>"client-a", :worker_id=>4, :job_id=>0, :timestamp=>"07:07:44 8691", :command=>"ls -la"}
{:app_name=>"client-e", :worker_id=>0, :job_id=>4, :timestamp=>"07:07:44 8691", :command=>"ls -la"}
{:app_name=>"client-b", :worker_id=>3, :job_id=>1, :timestamp=>"07:07:44 8687", :command=>"ls -la"}
{:app_name=>"client-a", :worker_id=>4, :job_id=>0, :timestamp=>"07:07:45 9548", :command=>"cd ~/Desktop"}
{:app_name=>"client-d", :worker_id=>2, :job_id=>3, :timestamp=>"07:07:45 9548", :command=>"cd ~/Desktop"}
{:app_name=>"client-b", :worker_id=>3, :job_id=>1, :timestamp=>"07:07:45 9548", :command=>"cd ~/Desktop"}
{:app_name=>"client-e", :worker_id=>0, :job_id=>4, :timestamp=>"07:07:45 9549", :command=>"cd ~/Desktop"}
{:app_name=>"client-c", :worker_id=>1, :job_id=>2, :timestamp=>"07:07:45 9546", :command=>"cd ~/Desktop"}
{:app_name=>"client-i", :worker_id=>1, :job_id=>8, :timestamp=>"07:07:46 9564", :command=>"pwd"}
{:app_name=>"client-g", :worker_id=>2, :job_id=>6, :timestamp=>"07:07:46 9564", :command=>"pwd"}
{:app_name=>"client-h", :worker_id=>0, :job_id=>7, :timestamp=>"07:07:46 9564", :command=>"pwd"}
{:app_name=>"client-j", :worker_id=>4, :job_id=>9, :timestamp=>"07:07:46 9565", :command=>"pwd"}
{:app_name=>"client-f", :worker_id=>3, :job_id=>5, :timestamp=>"07:07:46 9562", :command=>"pwd"}
{:app_name=>"client-g", :worker_id=>2, :job_id=>6, :timestamp=>"07:07:47 9580", :command=>"ls -la"}
{:app_name=>"client-h", :worker_id=>0, :job_id=>7, :timestamp=>"07:07:47 9582", :command=>"ls -la"}
{:app_name=>"client-f", :worker_id=>3, :job_id=>5, :timestamp=>"07:07:47 9586", :command=>"ls -la"}
{:app_name=>"client-j", :worker_id=>4, :job_id=>9, :timestamp=>"07:07:48 0546", :command=>"ls -la"}
{:app_name=>"client-i", :worker_id=>1, :job_id=>8, :timestamp=>"07:07:48 0545", :command=>"ls -la"}
{:app_name=>"client-h", :worker_id=>0, :job_id=>7, :timestamp=>"07:07:48 9671", :command=>"cd ~/Desktop"}
{:app_name=>"client-g", :worker_id=>2, :job_id=>6, :timestamp=>"07:07:48 9673", :command=>"cd ~/Desktop"}
{:app_name=>"client-i", :worker_id=>1, :job_id=>8, :timestamp=>"07:07:49 0553", :command=>"cd ~/Desktop"}
{:app_name=>"client-f", :worker_id=>3, :job_id=>5, :timestamp=>"07:07:49 0553", :command=>"cd ~/Desktop"}
{:app_name=>"client-j", :worker_id=>4, :job_id=>9, :timestamp=>"07:07:49 0551", :command=>"cd ~/Desktop"}

=end
