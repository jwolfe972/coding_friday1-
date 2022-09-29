import matplotlib.pyplot as plt
import csv

def getPlayerTableInMemory():
    players = {}
    with open('players.csv', 'r') as dataFile:
        next(dataFile)
        reader = csv.reader(dataFile)
        for row in reader:
            players[int(row[0])] = {


                    'player_id': int(row[0]),
                    'player_first_name': row[1].upper(),
                    'player_last_name': row[2].upper(),
                    'college': row[3].upper(),
                    'country': row[4],
                    'draft_year': row[5],
                    'draft_round': row[6],
                    'draft_number': row[7],

            }
    return players

def getplayerStatsInfo():
    playerStatInfo = {}
    with open('player_stats_info.csv', 'r') as dataFile:
        next(dataFile)
        reader = csv.reader(dataFile)
        for row in reader:
            if int(row[1]) not in playerStatInfo:
                playerStatInfo[int(row[1])] = {


                    "stats": [


                        {

                            'entry_id': int(row[0]),
                            'player_id': int(row[1]),
                            'team_abbreviation': row[2],
                            'age': row[3],

                            'player_height': row[4],
                            'player_weight': row[5],
                            'gp': int(row[6]),
                            'pts': row[7],
                            'reb': row[8],
                            'ast': row[9],
                            'net_rating': row[10],
                            'oreb_pct': row[11],
                            'dreb_pct': row[12],
                            'usg_pct': row[13],
                            'ts_pct': row[14],
                            'ast_pct': row[15],
                            'season': int(row[16])





                         }


                    ]







                }
            else:
                playerStatInfo[int(row[1])]['stats'].append(
                    {

                        'entry_id': int(row[0]),
                        'player_id': int(row[1]),
                        'team_abbreviation': row[2],
                        'age': row[3],

                        'player_height': row[4],
                        'player_weight': row[5],
                        'gp': int(row[6]),
                        'pts': row[7],
                        'reb': row[8],
                        'ast': row[9],
                        'net_rating': row[10],
                        'oreb_pct': row[11],
                        'dreb_pct': row[12],
                        'usg_pct': row[13],
                        'ts_pct': row[14],
                        'ast_pct': row[15],
                        'season': int(row[16])

                    }

                )
    return playerStatInfo

playerTable = getPlayerTableInMemory()
playerStatsTable = getplayerStatsInfo()

#example: generate a graph for the best average scoring average for a given draft class year




def generateDraftClassData(year):
    player_ids = []
    statInfos = []
    for item in playerTable:
        if playerTable[item]['draft_year'] == year:
            player_ids.append(int(playerTable[item]['player_id']))

    for playerID in player_ids:
        statInfos.append(getplayerStatsInfo()[int(playerID)]['stats'])



    return statInfos


def generateCareerPointAveragesByYear(statsInfo):
    careerPoints = {}
    player_id = -1


    for item in statsInfo:
        totalPoints = 0
        for statYear in item:
            if statYear['player_id'] not in careerPoints:
                careerPoints[statYear['player_id']] = [
                    (float(statYear['pts']), int(statYear['season']))
                ]
            else:
                careerPoints[statYear['player_id']].append(
                    (float(statYear['pts']), int(statYear['season']))

                )



    return careerPoints


def generateGraph(data, year):
    xList = []
    yList = []
    f = plt.figure()
    f.set_figwidth(20)
    f.set_figheight(10)
    for item in data:
        xList = []
        yList = []
        for elem in data[item]:
            xList.append(elem[1])
            yList.append(elem[0])

        plt.plot(xList, yList, label=f'{playerTable[item]["player_first_name"]} {playerTable[item]["player_last_name"]}')
        xList.clear()
        yList.clear()
    # naming the x axis


    plt.xlabel('year')
    # naming the y axis
    plt.ylabel('points per game')
    plt.legend()
    plt.legend(bbox_to_anchor=(1.02, 1), loc='upper right', borderaxespad=0)
    # giving a title to my graph
    plt.title(f'Draft class {year} analysis ppg')

    plt.savefig(f"{year}_DraftClass_PPG_Averages.pdf", format="pdf", bbox_inches="tight")
    plt.show()














    # return result1





    # get players from the year draft







if __name__ == "__main__":
    playerInfo = getPlayerTableInMemory()
    playerStatInfo = getplayerStatsInfo()
    draftClassInfo = generateDraftClassData('2009')
    cp = generateCareerPointAveragesByYear(draftClassInfo)

    generateGraph(cp, 2009)
















