# Adventures in Exodus

#### Dupa o gramada de erori, bugguri si reinitializare proiect am resit sa producem intr-o oarecare masura un joc micut de tipul top-down turn-based rpg.

```python
  Jocul in principu cuprinde : 
  -5 clase
  -Sistem de leveling (momentan acesta fiind hard coded pana la level 5 maxim)
  -Sistem de quest-uri
  
  Parti lucrate : 
    - Cosmin : main frame-ul al jocului (instantiere window, incarcare/afisare/distrugere resurse), constructie harta (evenimente, coliziuni)
    - Nicu : Implementat clasele de incarcat/afisat harta cat si sistemul audio din joc
    - Razvan : Tot ce inseamna scenele jocului, de la aspect si functionalitate interfata
    
  Comun : 
    -Quest-uri, ideii npc-uri, functionalitate npc-uri, inamici
    -Efecte sonore, muzica
    -Functionalitate inventar
    Etc...
    
  Bug-uri cunoscute : 
    -Bug major : problema la incarcarea majoritatii hartii ale jocului
    -Cateva dintre tranziitile implementate nu au mai vrut sa functioneze
    -Itemele nu vor sa se incarce in lume
  Ce se mai poate extinde:
    -Sistem AI
    -Harta extinsa
    -Implementat sistem de status-uri rpg (STR, VIT, INT...)
    -Implementat abilitati unice
    Imaginatia fiind limita
```

