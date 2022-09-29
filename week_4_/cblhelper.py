
from analysis import getplayerStatsInfo
from analysis import getPlayerTableInMemory
from analysis import generateDraftClassData
import math 






if __name__ == "__main__":
    cblDatData = {}
    playerTable = getPlayerTableInMemory()
    playerStatsTable = getplayerStatsInfo()
    #change this variable to effect the report data
    draftClassYear = '2003'
    draftClassData = generateDraftClassData(draftClassYear)
    print(len(draftClassData))
    
    
    outputFile = open("NBADATA.dat", "w")
    
    for item in draftClassData:
        for elem in item:
            fname = playerTable[elem['player_id']]['player_first_name'].ljust(12)
            lname = playerTable[elem['player_id']]['player_last_name'].ljust(18)
            college = playerTable[elem['player_id']]['college'].ljust(39)
            country = playerTable[elem['player_id']]['country'].ljust(32)
            draft_year = playerTable[elem['player_id']]['draft_year'].ljust(9)
            draft_round = playerTable[elem['player_id']]['draft_round'].ljust(9)
            draft_pick = playerTable[elem['player_id']]['draft_number'].ljust(9)
            team_abr = elem['team_abbreviation']
            age_in = math.floor(  float(elem['age']))
            gp = str(elem['gp']).zfill(2)
            pts = round(float(elem['pts']), 2)
            format_float = "{:.2f}".format(pts)
            remDot =  format_float.replace('.', '').zfill(4)
            idVal = str(elem['player_id']).zfill(4)
            
            
            
            
            
            rebounds = round(float(elem['reb']), 2)
            format_float = "{:.2f}".format(rebounds)
            rebOut =  format_float.replace('.', '').zfill(4)
            
            
            
            assits = round(float(elem['ast']), 2)
            format_float = "{:.2f}".format(assits)
            assistOut =  format_float.replace('.', '').zfill(4)
            
            
            season = elem['season']
            
            
            
            
            
            outputFile.write(f'{idVal}{fname}{lname}{college}{country}{draft_year}{draft_round}{draft_pick}{team_abr}{age_in}{gp}{remDot}{rebOut}{assistOut}{season}')
            outputFile.write("\n")
            
    outputFile.close()
            