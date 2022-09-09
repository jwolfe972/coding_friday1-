from flask import Flask, request

app = Flask("__name__")
app.config['SECRET_KEY'] = 'Jhsklal-jsjdj'

school_database = {

    "data":{

        "students": { },
        "teachers": {},
        "CurrentSIndex": 1,
        "CurrentTIndex": 1,
    }




}


@app.route("/", methods = ['GET'])
def homeDirect():
    return school_database["data"]


@app.route("/add/student", methods = ['GET', 'POST'])
def addStudent():
    fname = request.args['fname']
    lname = request.args['lname']
    id = school_database["data"]["CurrentSIndex"]


    school_database["data"]["students"][str(id)] = {"studentID": int(id), "firstName": fname, "lastName": lname}
    school_database["data"]["CurrentSIndex"]+=1
    return  school_database["data"]["students"][str(id)]


@app.route("/find/student", methods = ['GET'])
def findStudent():
    idNumber = request.args['idNumber']



    if str(idNumber) in school_database["data"]["students"]:
        return school_database["data"]["students"][str(idNumber)]
    

    return 'student not found'



@app.route("/update/student", methods = ['GET', 'PUT'])
def updateStudent():
    fname = request.args['fname']
    lname = request.args['lname']
    idNumber = request.args['studentID']
    if str(idNumber) in school_database["data"]["students"]:
        school_database["data"]["students"][str(idNumber)]['firstName'] = fname
        school_database["data"]["students"][str(idNumber)]['lastName'] = lname
        return school_database["data"]["students"][str(idNumber)]


@app.route("/delete", methods = ['DELETE'])
def deleteStudent():
    idNumber = request.args['idNumber']



    if str(idNumber) in school_database["data"]["students"]:
        school_database["data"]["students"].pop(str(idNumber))
        return school_database["data"]["students"]
    

    return 'student not found'



    
    
    
























if __name__ == "__main__":
    app.run( port=21785, debug=True)
