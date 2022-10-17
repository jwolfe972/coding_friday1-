import requests



serverAddress = 'http://127.0.0.1:21785'




def testGetRequest():
    requestResponse = requests.get(serverAddress).json()

    for student in requestResponse["students"]:
        print(requestResponse["students"][student])








testGetRequest()
    







