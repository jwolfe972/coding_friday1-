       IDENTIFICATION DIVISION.
       PROGRAM-ID. NBAREPT.
       AUTHOR. WOLFE.
       DATE-WRITTEN. 09/29/2022.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.


            SELECT NBA-RECORDS ASSIGN TO "NBADATA.dat"
            ORGANIZATION IS LINE SEQUENTIAL.
  
  
            SELECT NBA-OUTPUT ASSIGN TO "NBA_REPORT.doc"
            ORGANIZATION IS LINE SEQUENTIAL.
  
  
  



       DATA DIVISION.
       FILE SECTION.
       FD NBA-RECORDS RECORDING MODE IS F.
        01 DATA-RECORD.
           05 ID-VAL                  PIC XXXX.
           05 F-NAME-IN               PIC X(12).
           05 L-NAME-IN               PIC X(18).
           05 COLLEGE-IN              PIC X(39).
           05 COUNTRY-IN              PIC X(32).
           05 DRAFT-YEAR-IN           PIC X(9).
           05 DRAFT-ROUND-IN          PIC X(9).
           05 DRAFT-PICK-IN           PIC X(9).
           05 TEAM-AREV-IN            PIC XXX.
           05 AGE-IN                  PIC 99.
           05 GP-IN                   PIC 99.
           05 PTS-IN                  PIC 9(2)V99.
           05 REB-IN                  PIC 9(2)V99.
           05 AST-IN                  PIC 9(2)V99.
           05 SEASON-IN               PIC 9(4).
       

       FD NBA-OUTPUT RECORDING MODE IS F.
       01 PRINT-LINE                 PIC X(200).



       WORKING-STORAGE SECTION.
       01 LIVE-VARIABLES.
          05 EOF                           PIC X VALUE 'N'.
          05 NUM-PLAYERS-CLASS             PIC 999 VALUE 0.
          05 AVG-HOLDER-PT                    PIC 9(4)V99 VALUE 0.
          05 AVG-HOLDER-REB                    PIC 9(4)V99 VALUE 0.
          05 AVG-HOLDER-AST                    PIC 9(4)V99 VALUE 0.
          05 NUM-YEARS                     PIC 99 VALUE 0.
          05 CURR-ID                       PIC XXXX VALUE 'NONE'.
          05 SEASON-COUNT                  PIC 99 VALUE 0.
          05 FNAME-HOLD                    PIC X(12) VALUE SPACES.
          05 LNAME-HOLD                    PIC X(18) VALUE SPACES.
       01 OUTPUT-PARM.
           05 F-NAME-OT              PIC X(12).
           05 FILLER                  PIC XX VALUE SPACES.
           05 L-NAME-OT               PIC X(18).
           05 FILLER                  PIC XX VALUE SPACES.
           05 COLLEGE-OT              PIC X(39).
           05 FILLER                  PIC XX VALUE SPACES.
           05 COUNTRY-OT              PIC X(32).
           05 FILLER                  PIC XX VALUE SPACES.
           05 DRAFT-YEAR-OT           PIC X(9).
           05 FILLER                  PIC XX VALUE SPACES.
           05 DRAFT-ROUND-OT          PIC X(9).
           05 FILLER                  PIC XX VALUE SPACES.
           05 DRAFT-PICK-OT           PIC X(9).
           05 FILLER                  PIC XX VALUE SPACES.
           05 TEAM-AREV-OT            PIC XXX.
           05 FILLER                  PIC XX VALUE SPACES.
           05 AGE-OT                  PIC 99.
           05 FILLER                  PIC XX VALUE SPACES.
           05 GP-OT                   PIC 99.
           05 FILLER                  PIC XX VALUE SPACES.
           05 PTS-OT                  PIC 9(2).99.
           05 FILLER                  PIC XX VALUE SPACES.
           05 REB-OT                  PIC 9(2).99.
           05 FILLER                  PIC XX VALUE SPACES.
           05 AST-OT                  PIC 9(2).99.
           05 FILLER                  PIC XX VALUE SPACES.
           05 SEASON-OT               PIC 9(4).

       01  PLAYER-REPORT.
           05 FNAME-REP               PIC X(12).
           05 FILLER                  PIC XX VALUE SPACES.
           05 LNAME-REP               PIC X(18).
           05 FILLER                  PIC XX VALUE SPACES.
           05 AVG-PT                  PIC 99.99.
           05 FILLER                  PIC XX VALUE SPACES.
           05 AVG-REB                 PIC 99.99.
           05 FILLER                  PIC XX VALUE SPACES.
           05 AVG-AST                 PIC 99.99.
           
       



       PROCEDURE DIVISION.
           MAIN.
              PERFORM 100-OPEN-FILES.
              PERFORM 102-WRITE-FILE UNTIL EOF = 'Y'
              PERFORM 105-CLOSE.
              GOBACK.

           100-OPEN-FILES.
              OPEN INPUT NBA-RECORDS OUTPUT NBA-OUTPUT.
              PERFORM 101-READ-TIL-FINISH.

           101-READ-TIL-FINISH.
              READ NBA-RECORDS  
                 AT END MOVE 'Y' TO EOF
              END-READ.



           110-REPORT-PLAYER.
              MOVE FNAME-HOLD  TO FNAME-REP
              MOVE LNAME-HOLD  TO LNAME-REP
              COMPUTE AVG-PT = (AVG-HOLDER-PT / SEASON-COUNT )
              COMPUTE AVG-REB  = (AVG-HOLDER-REB / SEASON-COUNT )
              COMPUTE  AVG-AST = (AVG-HOLDER-AST / SEASON-COUNT )
              MOVE PLAYER-REPORT TO PRINT-LINE
              WRITE PRINT-LINE AFTER ADVANCING PAGE .

           102-WRITE-FILE.

              IF CURR-ID = 'NONE'
                 THEN MOVE ID-VAL TO CURR-ID 
                 MOVE F-NAME-IN TO FNAME-HOLD
                 MOVE L-NAME-IN TO LNAME-HOLD
              END-IF.

              IF CURR-ID NOT = 'NONE' AND CURR-ID NOT = ID-VAL
             
                 THEN

                  PERFORM 110-REPORT-PLAYER
                  MOVE SPACES TO PRINT-LINE
                 WRITE PRINT-LINE AFTER ADVANCING PAGE
                 MOVE ID-VAL TO CURR-ID 
                 MOVE 0 TO AVG-HOLDER-AST 
                  MOVE 0 TO AVG-HOLDER-PT 
                   MOVE 0 TO AVG-HOLDER-REB 
                   MOVE 0 TO SEASON-COUNT
                   MOVE F-NAME-IN TO FNAME-HOLD 
                   MOVE L-NAME-IN TO LNAME-HOLD
              END-IF .




              ADD PTS-IN  TO AVG-HOLDER-PT
              ADD AST-IN  TO AVG-HOLDER-AST 
              ADD REB-IN  TO AVG-HOLDER-REB
              ADD 1 TO SEASON-COUNT
              MOVE F-NAME-IN TO F-NAME-OT
              MOVE L-NAME-IN TO L-NAME-OT
              MOVE COLLEGE-IN  TO COLLEGE-OT
              MOVE COUNTRY-IN  TO COUNTRY-OT 
              MOVE DRAFT-YEAR-IN TO DRAFT-YEAR-OT
              MOVE DRAFT-ROUND-IN TO DRAFT-ROUND-OT
              MOVE DRAFT-PICK-IN TO DRAFT-PICK-OT 
              MOVE TEAM-AREV-IN TO TEAM-AREV-OT 
              MOVE GP-IN  TO GP-OT
              MOVE AGE-IN  TO AGE-OT 
              MOVE PTS-IN TO PTS-OT
              MOVE REB-IN TO REB-OT 
              MOVE AST-IN TO AST-OT
              MOVE SEASON-IN TO SEASON-OT 

              MOVE OUTPUT-PARM TO PRINT-LINE
              WRITE PRINT-LINE  AFTER ADVANCING 1 LINE
              PERFORM 101-READ-TIL-FINISH.


           105-CLOSE.
              
           PERFORM 110-REPORT-PLAYER
           MOVE SPACES TO PRINT-LINE
           WRITE PRINT-LINE AFTER ADVANCING PAGE
             CLOSE NBA-RECORDS .
             CLOSE NBA-OUTPUT.
           
             
      
              


   
