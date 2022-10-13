
from unittest import result
from flask import Flask, request, render_template
from csv import writer
from analysis import rTType

app = Flask("__name__")
app.config['SECRET_KEY'] = 'eueubfeiJw92'


@app.route("/")
def home():

    return render_template("index.html")

@app.route("/bp")
def bpPage():

    return render_template("bp.html")


@app.route("/add-bp", methods=['POST', 'GET'])
def addBP():
    name = request.form['name']
    systolic = request.form['systolic']
    dialotic = request.form['Dialotic']

    with open('blood_pressure.csv', 'a+', newline='\n') as write_obj:
        # Create a writer object from csv module
        csv_writer = writer(write_obj)
        # Add contents of list as last row in the csv file
        csv_writer.writerow([name, systolic, dialotic])
        

    return render_template('result.html', result=str(rTType(systolic, dialotic)))  


if __name__ == "__main__":
    app.run(port=21785, debug=True)