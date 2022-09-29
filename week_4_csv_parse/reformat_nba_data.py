import csv


# Received the csv data for the Nba player information between 1996 - 2021 seasons
# Link to the dataset: https://www.kaggle.com/datasets/justinas/nba-players-data

#                               Purpose of Program:
#                   Create a new csv file so that the IDs for each player are made and able to be seen structurally

players = {}
playerFields = ['entry_id', 'player_id', 'player_first_name', 'player_last_name', 'team_abbreviation', 'age', 'player_height', 'player_weight', 'college', 'country', 'draft_year', 'draft_round', 'draft_number', 'gp', 'pts', 'reb', 'ast', 'net_rating', 'oreb_pct', 'dreb_pct', 'usg_pct', 'ts_pct', 'ast_pct', 'season']
playerStorage = []






def readData():
    with open('all_seasons.csv', 'r') as dataFile:
        next(dataFile)
        reader = csv.reader(dataFile)
        for row in reader:
            seperatedName = row[1].upper().split()
            lastName = ""
            for item in seperatedName[1:]:
                lastName+= str(item) + ' '
                
            
            if str(row[1]).upper() not in players:
                players[str(row[1]).upper()] = {
                    
                    
                    
                    
                    "player_id": len(players) + 1,
                    "stats":       [
                    
                    
                            {
                    'entry_id': int(row[0]),
                    "player_id": len(players) + 1,
                    'player_first_name': seperatedName[0],
                    'player_last_name': lastName.strip(),
                    'team_abbreviation': row[2],
                    'age': row[3],
                    'player_height': row[4],
                    'player_weight': row[5],
                    'college': row[6].upper(),
                    'country': row[7],
                    'draft_year': row[8],
                    'draft_round': row[9],
                    'draft_number': row[10],
                    'gp': row[11],
                    'pts': row[12],
                    'reb': row[13],
                    'ast': row[14],
                    'net_rating': row[15],
                    'oreb_pct': row[16],
                    'dreb_pct': row[17],
                    'usg_pct': row[18],
                    'ts_pct': row[19],
                    'ast_pct': row[20],
                    'season': int(str(row[21][0:4]))
                    
                    
                    
                    
                }
                ]
                    
                }
                
                
                
          
            else:
                players[str(row[1]).upper()]['stats'].append(
                    
                    
                                 {
                    'entry_id': int(row[0]),
                    'player_id': players[str(row[1]).upper()]["player_id"],
                     'player_first_name': seperatedName[0],
                    'player_last_name': lastName.strip(),
                    
                    'team_abbreviation': row[2],
                    'age': row[3],
                    'player_height': row[4],
                    'player_weight': row[5],
                    'college': row[6].upper(),
                    'country': row[7],
                    'draft_year': row[8],
                    'draft_round': row[9],
                    'draft_number': row[10],
                    'gp': row[11],
                    'pts': row[12],
                    'reb': row[13],
                    'ast': row[14],
                    'net_rating': row[15],
                    'oreb_pct': row[16],
                    'dreb_pct': row[17],
                    'usg_pct': row[18],
                    'ts_pct': row[19],
                    'ast_pct': row[20],
                    'season': int(str(row[21][0:4]))
                    
                    
                    
                    
                }
                    
                    
                )
      

    for player in players:
        for elem in players[player]['stats']:
            playerStorage.append(elem)
    
                
def writeToCSV(nameOfFile, storage, fieldList):
    try:
        with open(f"{nameOfFile}.csv", 'w', newline='') as csvFile:
            writer = csv.DictWriter(csvFile, fieldnames=fieldList)
            writer.writeheader()
            for data in storage:
                writer.writerow(data)

          
    except IOError:
        print("I/O error")
                
                
            
            
if __name__ == "__main__":

    readData()
    writeToCSV('seasons_modified', playerStorage, playerFields )
    
    name = "Jordan Wolfe"
    
    
    dividedName = name.split()
    
    
    
    print(dividedName[0], dividedName[len(dividedName)-1])
    
    
    
    
    


