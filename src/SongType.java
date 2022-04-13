public enum SongType {
    Rock,Metal,Pop,Rap,ClassicalMusic,Jazz;

    public SongType determine(String type)
    {
        if(type.equals("Rock"))
        {
            return Rock;
        }
        if(type.equals("Metal"))
        {
            return Metal;
        }
        if(type.equals("Pop"))
        {
            return Pop;
        }
        if(type.equals("Rap"))
        {
            return Rap;
        }
        if(type.equals("Classical Music"))
        {
            return ClassicalMusic;
        }
        if(type.equals("Jazz"))
        {
            return Jazz;
        }

        return null;
    }
}
