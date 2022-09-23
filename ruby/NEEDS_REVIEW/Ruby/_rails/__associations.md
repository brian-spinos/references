# Has Many Through

```ruby
# Use this:
# rails g scaffold User name
# rails g scaffold Subject name
# rails g model SubjectUser subject:references user:references

class User < ApplicationRecord
  has_many :subject_user
  has_many :subjects, through: :subject_user
end

class SubjectUser < ApplicationRecord
  belongs_to :user
  belongs_to :subject
end

class Subject < ApplicationRecord
  has_many :subject_user
  has_many :users, through: :subject_user
end

User.create(name: "brian")
u = User.first
u.subjects
s = Subject.create(name: "math")
u.subjects = [s]
s.users
```


# Models and Queries

```ruby

#========= Has many


class User < ApplicationRecord
  # sql = "select * from cars where user_id = #{id}"
  has_many :cars
end

class Car < ApplicationRecord
  # sql = "select * from users where id = #{user_id}"
  belongs_to :user
end


#========= Has many through


class Physician < ApplicationRecord

  # sql = "select * from appointments where physician_id = #{id}"
  has_many :appointments
  
  # sql = "SELECT patients.* FROM patients 
  #        INNER JOIN appointments ON patients.id = appointments.patient_id 
  #        WHERE appointments.physician_id = #{id}"
  has_many :patients, through: :appointments
  
end
 
class Appointment < ApplicationRecord
  # sql = "select * from physicians where id = #{physician_id}"
  belongs_to :physician 

  # sql = "select * from patients where id = #{patient_id}"
  belongs_to :patient 
end
 
class Patient < ApplicationRecord
  # sql = "select * from appointments where patient_id = #{id}"
  has_many :appointments

  # sql = "SELECT physicians.* FROM physicians 
  #        INNER JOIN appointments ON physicians.id = appointments.physician_id 
  #        WHERE appointments.patient_id = #{id}"
  has_many :physicians, through: :appointments
end
```
