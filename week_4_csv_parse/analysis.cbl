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
           05 FILLER                  PIC XX VALUE SPACES.
           05 SEASONS-PLAYED          PIC XX.


       01  STORE-DATE.
           05 CURR-YEAR               PIC XXXX.
           05 CURR-MONTH              PIC XX.
           05 CURR-DAY                PIC XX.
           05 CURR-HR                 PIC XX.
           05 CURR-MIN                PIC XX.
           05 CURR-SEC                PIC XX.



       01  HEADER-LINE-1.
           05 FILLER                  PIC X(72) VALUE SPACES.
           05 TXT                     PIC X(27)
              VALUE 'JORDANS NBA PPG REPORT FOR '.
           05 MONTH-F                 PIC XX.
           05 FILLER                  PIC X VALUE '/'.
           05 DAY-F                   PIC XX.
           05 FILLER                  PIC X VALUE '/'.
           05 YEAR-F                  PIC XXXX.
           05 FILLER                  PIC XX VALUE SPACES.
           05 HR-F                    PIC XX.
           05 FILLER                  PIC X VALUE ':'.
           05 MIN-F                   PIC XX.
           05 FILLER                  PIC X VALUE ':'.
           05 SEC-F                   PIC XX.

       01  HEADER-LINE-2.
           05 FILLER                  PIC X VALUE SPACE.
           05 F-TXT                   PIC X(5) VALUE 'FIRST'.
           05 FILLER                  PIC X(9) VALUE SPACES.
           05 L-TXT                   PIC X(4) VALUE 'LAST'.
           05 FILLER                  PIC X(19) VALUE SPACES.
           05 CO-TXT                  PIC X(7) VALUE 'COLLEGE'.
           05 FILLER                  PIC X(28) VALUE SPACES.
           05 COU-TXT                 PIC X(7) VALUE 'COUNTRY'.
           05 FILLER                  PIC X(28) VALUE SPACES.
           05 DR-ONE                  PIC X(5) VALUE 'DRAFT'.
           05 FILLER                  PIC X(4) VALUE SPACES.
           05 DR-TWO                  PIC X(5) VALUE 'DRAFT'.
           05 FILLER                  PIC X(8) VALUE SPACES.
           05 DR-THREE                PIC X(5) VALUE 'DRAFT'.
           05 FILLER                  PIC X(6) VALUE SPACES.
           05 TEAM-TXT                PIC X(4) VALUE 'TEAM'.
           05 FILLER                  PIC XX VALUE SPACES.
           05 AGE-TXT                 PIC X(3) VALUE 'AGE'.
           05 FILLER                  PIC X VALUE SPACE.
           05 GP-TXT                  PIC XX VALUE 'GP'.
           05 FILLER                  PIC XXX VALUE SPACES.
           05 PPG-TXT                 PIC XXX VALUE 'PPG'.
           05 FILLER                  PIC XXXX VALUE SPACES.
           05 RPG-TXT                 PIC XXX VALUE 'RPG'.
           05 FILLER                  PIC X(4) VALUE SPACES.
           05 AST-TXT                 PIC XXX VALUE 'APG'.
           05 FILLER                  PIC XXX VALUE SPACES.
           05 SEASON-TXT              PIC X(6) VALUE 'SEASON'.

       01  HEADER-LINE-3.
           05 FILLER                  PIC X VALUE SPACE.
           05 NAME-TXT-ONE            PIC X(4) VALUE 'NAME'.
           05 FILLER                  PIC X(10) VALUE SPACES.
           05 NAME-TXT-TWO            PIC X(4) VALUE 'NAME'.
           05 FILLER                  PIC X(90) VALUE SPACES.
           05 YEAR-TXT                PIC X(4) VALUE 'YEAR'.
           05 FILLER                  PIC X(4) VALUE SPACES.
           05 ROUND-TXT               PIC X(5) VALUE 'ROUND'.
           05 FILLER                  PIC X(8) VALUE SPACES.
           05 PICK-TXT                PIC X(4) VALUE 'PICK'.


       01  BORDER-LINE.
           05 LINE-BAR-1      PIC X(58)    VALUE 
           '----------------------------------------------------------'.
           05 LINE-BAR-2      PIC X(58)    VALUE 
           '----------------------------------------------------------'.
           05 LINE-BAR-3      PIC X(58)    VALUE 
           '----------------------------------------------------------'.
           05 LINE-BAR-4     PIC X(8) VALUE '--------'.














           
       



       PROCEDURE DIVISION.
        MAIN.
           PERFORM 100-OPEN-FILES.
           PERFORM PREPARE-DATE .

           PERFORM 102-WRITE-FILE UNTIL EOF = 'Y'
           PERFORM 105-CLOSE.
           GOBACK.

        100-OPEN-FILES.
           OPEN INPUT NBA-RECORDS OUTPUT NBA-OUTPUT.
           PERFORM 101-READ-TIL-FINISH.

       PREPARE-DATE.
           MOVE FUNCTION CURRENT-DATE TO STORE-DATE 
           MOVE CURR-YEAR TO YEAR-F
           MOVE CURR-MONTH  TO MONTH-F
           MOVE CURR-DAY TO DAY-F
           MOVE CURR-HR  TO HR-F
           MOVE CURR-MIN TO MIN-F
           MOVE CURR-SEC  TO SEC-F.
        
       PREPARE-HEADER.
           MOVE HEADER-LINE-1 TO PRINT-LINE
           WRITE PRINT-LINE AFTER ADVANCING PAGE.
           MOVE SPACES TO PRINT-LINE
           WRITE  PRINT-LINE  AFTER ADVANCING 2 LINES.

           

        101-READ-TIL-FINISH.
           READ NBA-RECORDS  
              AT END MOVE 'Y' TO EOF
           END-READ.


       120-SEC-HEADER.
            MOVE HEADER-LINE-2 TO PRINT-LINE
           WRITE  PRINT-LINE AFTER ADVANCING 1 LINES
           MOVE HEADER-LINE-3 TO PRINT-LINE
           WRITE PRINT-LINE AFTER ADVANCING  1 LINE
           MOVE BORDER-LINE TO PRINT-LINE
           WRITE PRINT-LINE AFTER ADVANCING 1 LINE.



        110-REPORT-PLAYER.
           PERFORM PREPARE-HEADER
           MOVE FNAME-HOLD  TO FNAME-REP
           MOVE LNAME-HOLD  TO LNAME-REP
           MOVE SEASON-COUNT TO SEASONS-PLAYED 
           COMPUTE AVG-PT = (AVG-HOLDER-PT / SEASON-COUNT )
           COMPUTE AVG-REB  = (AVG-HOLDER-REB / SEASON-COUNT )
           COMPUTE  AVG-AST = (AVG-HOLDER-AST / SEASON-COUNT )
           MOVE PLAYER-REPORT TO PRINT-LINE.
           WRITE PRINT-LINE AFTER ADVANCING 1 LINE.

        102-WRITE-FILE.

           IF CURR-ID = 'NONE'
           
              THEN PERFORM PREPARE-HEADER
               MOVE ID-VAL TO CURR-ID 
              MOVE F-NAME-IN TO FNAME-HOLD
              MOVE L-NAME-IN TO LNAME-HOLD
              PERFORM 120-SEC-HEADER
           END-IF.

           IF CURR-ID NOT = 'NONE' AND CURR-ID NOT = ID-VAL
          
              THEN

               PERFORM 110-REPORT-PLAYER
      *        MOVE SPACES TO PRINT-LINE
      *       WRITE PRINT-LINE AFTER ADVANCING PAGE
              MOVE ID-VAL TO CURR-ID 
              MOVE 0 TO AVG-HOLDER-AST 
               MOVE 0 TO AVG-HOLDER-PT 
                MOVE 0 TO AVG-HOLDER-REB 
                MOVE 0 TO SEASON-COUNT
                MOVE F-NAME-IN TO FNAME-HOLD 
                MOVE L-NAME-IN TO LNAME-HOLD
                PERFORM PREPARE-HEADER
                PERFORM 120-SEC-HEADER
          
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
              
             
      
              


   
