Smart Homes IoT APIs

**Note: **All responses are in json format, and have additional attributes in case of function executes successfully "response: (data requested)" and “status: success”. In case of error “status: failure” and “error: (Error Message)”

**Users Functions:**

1. **Signup: **

**		Url: **"(domain name)/api/users/auth/signup"

		**Method: **POST

**		Parameters: **name, email, birthDate, password, confirmPassword

		**Success: **return the newly created user: (id, name, email, birthDate).

		**Error: **"Invalid or Missing Parameters", “Passwords don’t match”

2. **Login: **

**		Url: **"(domain name)/api/users/auth/login"

**Method: **POST

**		Parameters: **email, password

		**Success: **return user details: (id, name, email, birthDate).

		**Error: **"Invalid email or password"

3. **Update User: **

**		Url: **"(domain name)/api/users/update/{userId}"

**Method: **POST

**		Parameters: **name, birthDate, password, confirmPassword

		**Success: **return updated  user details: (id, name, email, birthDate).

		**Error: **"Parameters are missing or Invalid Parameter names.",

			"Can't find User with given id",

"Password and Confirm password don't match"

			

4. **Add Home: **

**		Url: **"(domain name)/api/users/add-home/{userId}"

		**Method: **POST

**		Parameters: **homeName, homeAddress

		**Success: **return new home details: (id, name, address, topic).

		**Error: **"Can’t find User with given id", “Invalid or Missing Parameters”

**Homes Functions:**

1. **Get Homes associated to a user: **

**		Url: **"(domain name)/api/homes/get/{userId}"

**Method: **GET

		**Success: **return the newly created user: (id, name, email, birthDate).

		**Error: **"Invalid or Missing Parameters", “Passwords don’t match”

2. **Update Home: **

**		Url: **"(domain name)/api/homes/update/{homeId}"

**Method: **POST

**Parameters: **homeName, homeAddress

		**Success: **return the updated home: (id, name, email, birthDate).

		**Error: **"Invalid or Missing Parameters"

3. **Add device to home: **

**		Url: **"(domain name)/api/homes/add-device/{homeId}"

**Method: **POST

**Parameters: **deviceName, deviceType, deviceDescription, pinNumber

		**Success: **returns the new device: (id, name, type, description, pin_number).

		**Error: **"Invalid or Missing Parameters", “Can’t find home with given id”

4. **Remove device from home: **

**		Url: **"(domain name)/api/homes/remove-device/{homeId}/{deviceId}"

**Method: **GET

		**Success: **returns status: success.

		**Error: **"Can't find Home with given id", “Can't find Device with given id”

5. **Update device of home: **

**		Url: **"(domain name)/api/homes/update-device/{homeId}/{deviceId}"

**Method: **POST

**Parameters: **deviceName, deviceType, deviceDescription, pinNumber

		**Success: **returns status: success.

		**Error: **"Can't find Home with given id", 

"Can't find Device with given id",  

   			"Parameters are missing or Invalid parameter names."

6. **Add user to home: **

**		Url: **"(domain name)/api/homes/add-user/{homeId}/{userId}"

**Method: **GET

		**Success: **returns status: success.

		**Error: **"Can't find Home/User with given id"

7. **Add Log to device: **

**		Url: **"(DN)/api/homes/add-log-to-device/{homeId}/{deviceId}/{command}"

**Method: **GET

		**Success: **returns status: success.

		**Error: **"Can't find Home/Device with given id"

8. **Get device logs: **

**		Url: **"(DN)/api/homes/get-device-logs/{homeId}/{deviceId}"

**Method: **GET

		**Success: **returns logs (command, created_at), status: success.

		**Error: **"Can't find Home/Device with given id"

