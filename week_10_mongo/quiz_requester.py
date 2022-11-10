import requests
import json


LETTER_CHOICES = ['A', 'B', 'C', 'D', 'E', 'F']
answerKey = {}


def getQuizFromID(id):
    quiz = {}
    
    quizAttempt = {}
    requestURL = "http://127.0.0.1:8080/quiz/custom-v2?quizNumber=" + str(id)
    print(requestURL)
    print('\n\n')
    requestResponse = requests.get(requestURL)
    
    # 200 = OK response
    if(requestResponse.status_code == 200):
        responseJson = requestResponse.json()
        answerKey = responseJson['answerKey']
        quiz = responseJson['questions']
        
        
        for elem in quiz:
            if elem['questionType'] == 'MULTIPLE_CHOICE' or elem['questionType'] == 'TRUE_OR_FALSE' :
                print(elem['question'])
                letterLoop = 0
                for choice in elem['answerChoices']:
                    print(f'{LETTER_CHOICES[letterLoop]}. {choice}')
                    letterLoop = letterLoop+1

            
            
            answer = input('Choose your Response (Number or Letter not Answer Choice)')
            quizAttempt[elem['question']] = str(answer)
                
            
        return quizAttempt, answerKey
        
        
    else:
        print(f'Invalid request: Response Code {requestResponse.status_code}')
        
def gradeQuiz(quizAttempt, key):
    print(quizAttempt)
    print(key)
    numRight = 0
    numWrong = 0
    for item in quizAttempt:
        if item in key:
            if quizAttempt[item] == key[item]:
                numRight += 1
            else:
                numWrong += 1
    print(f'Num Correct: {numRight}, Num Wrong {numWrong}')
                
        


def takeQuiz():
    idVal = input("Enter a Quiz ID to access a quiz: ")
    
    resp = getQuizFromID(idVal)   
    gradeQuiz(resp[0], resp[1])
    
    

        
        
if __name__ == "__main__":
    takeQuiz()