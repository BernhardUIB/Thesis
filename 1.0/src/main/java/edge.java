public class edge {
    Node start;
    Node end;
    int duration;


    public edge(Node start, Node end){
        this.start = start;
        this.end = end;
        this.duration = 14;

    }
}

/*
en edge representerer en kontakt (ikke bekjentskap)
en edge har en TTL? (random generert)
en edge vil dersom den har en infected node i en ende,
og en frisk i en annen, ved hvert tidssteg tilfeldig føre smitten over.
delete en edge etter 14 dager da den ikke lenger er vært å spare på (ikke lenger smitterisiko)


en smitteoverføring:
r*node.resistance*tid (ish)

*/
