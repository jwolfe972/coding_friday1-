
import csv
import matplotlib.pyplot as plt
import numpy as np


lowList = []
normalList = []
pre_hypertension = []
high_stage_1 = []
high_stage_2 = []



def rTType(systolic, diastolic):
    if int(diastolic) < 60 and int(systolic) < 90:
        return 'low'
    elif int(diastolic) < 80 and int(systolic) < 120:
        return 'normal'
    elif int(diastolic) < 90 and int(systolic) < 140:
        return 'pre hypertension'
    elif int(diastolic) < 100 and int(systolic) < 160:
        return 'high stage 1 hypertension'
    else :
        return 'high stage 2 hypertension'



# LOW = DIASTOLIC < 60 AND SYSTOLIC < 90
# NORMAL = DIASTOLIC 60 <= x < 80 AND SYSTOLIC 90 <= x < 120
# PRE = DIASTOLIC 80 <= X < 90 AND SYSTOLIC 120 <= X < 140
# HIGH 1 = DIASTOLIC 90 <= X < 100 AND SYSTOLIC 140 <= X < 160
# HIGH 2 = DIASTOLIC 100 <= X < INF AND SYSTOLIC 160 <= X < INFS
def aggregateBPData():
    count = 0
    x = []
    y = []
    with open('blood_pressure.csv', 'r') as bpData:
        next(bpData)
        reader = csv.reader(bpData)
        for row in reader:
            systolic = row[1]
            diastolic = row[2]
            x.append(int(diastolic))
            y.append(int(systolic))

            if int(diastolic) < 60 and int(systolic) < 90:
                lowList.append({'id': count, 'systolic': row[1], 'diastolic': row[2]})
            elif int(diastolic) < 80 and int(systolic) < 120:
                normalList.append({'id': count, 'systolic': row[1], 'diastolic': row[2]})
            elif int(diastolic) < 90 and int(systolic) < 140:
                pre_hypertension.append({'id': count, 'systolic': row[1], 'diastolic': row[2]})
            elif int(diastolic) < 100 and int(systolic) < 160:
                high_stage_1.append({'id': count, 'systolic': row[1], 'diastolic': row[2]})
            else:
                high_stage_2.append({'id': count, 'systolic': row[1], 'diastolic': row[2]})

            count+=1

    return x,y


            


            







def renderBPChart():
    data = aggregateBPData()


    fig, ax = plt.subplots(figsize=(12, 6))
    ax.grid()
  
    plt.xlim(40,150)
    plt.ylim(70,220)
    ax.scatter(data[0], data[1], marker="o", color='green')

    

    # # plt.scatter(data[0], data[1])

    plt.xlabel('Diastolic')
    plt.ylabel('Systolic')
    plt.title('BP Chart')
    start, end = ax.get_xlim()
    ax.xaxis.set_ticks(np.arange(start, end, 10))
    start, end = ax.get_ylim()
    ax.yaxis.set_ticks(np.arange(start, end, 10))

    





    plt.show()



def main():
    # print('hello world')
    renderBPChart()



if __name__ == "__main__":
    main()