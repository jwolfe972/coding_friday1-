import csv



# Received the csv data for the Nba player information between 1996 - 2021 seasons
# Link to the dataset: https://www.kaggle.com/datasets/justinas/nba-players-data
# produces players.csv and player_stats_info.csv
players = {}
countUp = 1
stats = {}
playerStorage = []
playerFields = [ 'player_id', 'player_first_name', 'player_last_name', 'college', 'country', 'draft_year', 'draft_round', 'draft_number']
statsFields = ['entry_id', 'player_id',  'team_abbreviation', 'age', 'player_height', 'player_weight', 'gp', 'pts', 'reb', 'ast', 'net_rating', 'oreb_pct', 'dreb_pct', 'usg_pct', 'ts_pct', 'ast_pct', 'season']
statStorage = []





def createPlayersCSV():
    
    #create players table csv
    with open('seasons_modified.csv', 'r') as dataFile:
        next(dataFile)
        reader = csv.reader(dataFile)
        for row in reader:
            
            if row[1] not in players :
                players[row[1]] =  {
                    
                    
                    'player_id': row[1],
                    'player_first_name': row[2].upper(),
                    'player_last_name': row[3].upper(),
                    'college': row[8].upper(),
                    'country': row[9],
                    'draft_year': row[10],
                    'draft_round': row[11],
                    'draft_number': row[12],
                }
    # readSecondPlayer()
                
    for player in players:
        playerStorage.append(players[player])
        
    try:
        with open("players.csv", 'w', newline='') as csvFile:
            writer = csv.DictWriter(csvFile, fieldnames=playerFields)
            writer.writeheader()
            for data in playerStorage:
                writer.writerow(data)
    except IOError:
        print('IO ERROR')
        
        
def createPlayerInfoTable():
    global countUp
    #create players table csv
    with open('seasons_modified.csv', 'r') as dataFile:
        next(dataFile)
        reader = csv.reader(dataFile)
        for row in reader:
            
            if row[1] not in stats :
                stats[row[1]] =  {
                    
                    
                    
                    'stats': [
                        
                        
                        
                        {

                            'entry_id': countUp,
                            'player_id': row[1],
                            'team_abbreviation': row[4],
                            'age': row[5],

                            'player_height': row[6],
                            'player_weight': row[7],
                            'gp': row[13],
                            'pts': row[14],
                            'reb': row[15],
                            'ast': row[16],
                            'net_rating': row[17],
                            'oreb_pct': row[18],
                            'dreb_pct': row[19],
                            'usg_pct': row[20],
                            'ts_pct': row[21],
                            'ast_pct': row[22],
                            'season': int(str(row[23][0:4]))

                        }
                        
                        
                        
                    ],
                
                 
               
                }
            else:
                stats[row[1]]['stats'].append({
                    
                          'entry_id': countUp,
                     'player_id': row[1],    
                    'team_abbreviation': row[4],
                    'age': row[5],
                   
                    'player_height': row[6],
                    'player_weight': row[7],
                    'gp': row[13],
                    'pts': row[14],
                    'reb': row[15],
                    'ast': row[16],
                    'net_rating': row[17],
                    'oreb_pct': row[18],
                    'dreb_pct': row[19],
                    'usg_pct': row[20],
                    'ts_pct': row[21],
                    'ast_pct': row[22],
                    'season': int(str(row[23][0:4]))
                    
                    
                    
                    
                    
                    
                })
            countUp+=1
                
                
                
                
    for item in stats:
        for elem in stats[item]['stats']:
            statStorage.append(elem)
    
    
    


        
    try:
        with open("player_stats_info.csv", 'w', newline='') as csvFile:
            writer = csv.DictWriter(csvFile, fieldnames=statsFields)
            writer.writeheader()
            for data in statStorage:
                writer.writerow(data)
    except IOError:
        print('IO ERROR')
        
        
# def readSecondPlayer():
#     with open('Player_1950s.csv', 'r') as dataFile:
#         next(dataFile)
#         reader = csv.reader(dataFile)
#         for row in reader:
#             seperatedName = row[1].upper().split()
#             lastName = ""
#             for item in seperatedName[1:]:
#                 lastName+= str(item) + ' '
                
#             if str(row[1].upper().strip() not in players):
#                 players[row[1]] =  {
                    
                    
#                     'player_id': len(players)+1,
#                     'player_first_name': seperatedName[0],
#                     'player_last_name': lastName,
#                     'college': row[4].upper(),
#                     'country': row[8],
#                     'draft_year': ' ',
#                     'draft_round': ' ',
#                     'draft_number': ' '
#                 }


if __name__ == "__main__":
    createPlayersCSV()
    createPlayerInfoTable()
        
    
        

                
        

