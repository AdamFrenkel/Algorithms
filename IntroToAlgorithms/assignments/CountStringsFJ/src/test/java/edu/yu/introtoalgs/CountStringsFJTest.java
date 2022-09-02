package edu.yu.introtoalgs;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CountStringsFJTest {
    @Test
    public void testBasics(){
        String[] strings = {"aa", "abc","peanuts","cows","aa","aaa","is","a","aa"};
        CountStringsFJ csf = new CountStringsFJ(strings,"aa",676);
        assertEquals(3,csf.doIt());
        csf = new CountStringsFJ(strings,"aa",1);
        assertEquals(3,csf.doIt());
    }
    @Test
    public void testSpeed() {
        String hayFever = "Hay Fever was a comical play with a strong message. The actors throughout this play steadily used others to gain their own attention. The message this play left behind after many laughs was do not use others, plain and simple. The space was a well-constructed thrust stage however, the stage was not raised off the ground and the seats appeared to be mobile which lead to the belief that this is an Environmental Theater. As for the layout of the stage, there was a couch and a table in center stage for the first two acts, a door to the unseen outside front of the house upstage right, there were two large double doors upstage center leading to the backyard garden, and a raised floor upstage left containing a piano, a bookcase and a door leading to the home library. Stage left there was a half spiral staircase that lead up to the second floor which had a painting and lights that extended across to stage right. The second floor also contained the bedrooms for the house but the doors were not in sight. The detail was greater than expected: the hardwood floor throughout the first floor seemed to be genuine, the actual bookcase filled with real books not stage books, and the extremely large double doors with translucent glass and floral designs lead to the Garden, which featured an array of plants behind the doors. The entire space was well lit for the duration of the play; the stage took place inside the Bliss’ home so the bright lighting added a confortable feel to the inside of the house. The theatre was nearly sold out; it was hard to spot an empty seat, including the balcony and lower level seating. The crowd contained nearly an even amount of student audience members and senior citizens; there was few in the audience that appeared middle aged The play was well cast, the entire Bliss family did a great job showing how overly dramatic and deceitful the family had become. Each actor portrayed their character accurately; there were no standouts that didn’t match the character. Each visitor in the first act seemed to be feasible as far as the possibility of a relationship was concerned. All of the family members acted genuine. There was one actor who stood out as a perfect fit for his character. Taylor Rascher played Simon Bliss and played him perfectly. Rascher’s character Simon was a young man who was dramatic and romantic so much so it was almost to the point of ridiculousness. Rascher stole the show in the first act when he was romancing with Michelle Luz, who plays Myra Arundel. Rascher was displaying his love for Michelle Luz (Myra) in the most dramatic of fashions; he was playing the Bliss’ game of pretending to be in complete love with someone and then a moment later change his mind. Rascher was proclaiming his love with elegant speeches and coddling up next to her and eventually kissing her. Rascher made this scene particularly hilarious because he was completely over the top in typical Bliss fashion. Of course, all of this was for naught because Luz ended up kissing Joe Hubbard’s character David Bliss in act two and also Rascher proclaimed his engagement to Caitlin Stagemoller’s character, Jackie Coryton. Rascher made his voice more shrill and audible to appear to be more dramatic and create more attention for himself. In the same way, Rascher used grander gestures with his arms (flailing, waving, etc.) to cause a more dramatic effect The play was well interpreted by all whom were involved. Steven Wrentmore, the Director, kept the 1920’s feel by dressing in all 1920’s costumes and everyone spoke as if they were living at the time. Michelle Bisbee, the scene designer, made the inside of the home appear 1920’s because everything was grand. The Bliss’ home was grand with the spiral staircase, the very large backdoors, and the eloquent piano. The actors’ mannerisms seemed like they were portraying a silent film. In older movies, actors seemed very dramatic and had flamboyant actions to prove so; the actors in Hay Fever shared the same feel for the dramatics. As far as Stephen Wrentmore’s directing goes, he did an excellent job. The scene when Chris Karl (Richard) and Caitlin Stegemoller (Jackie) enter and are left alone to make small talk with each other is the best pertaining to directing. The two actors used the entire stage in this scene and were very awkward with one another. This was Wrentmore’s doing because you could tell he had a vision for this scene in particular because it seemed very crisp and well rehearsed. The actors played it perfect with the excessively long pauses in their awkward small talk that the crowd was laughing through the entire scene. It appeared that Wrentmore instructed the actors, to keep their pauses longer than natural to heighten the awkward tension in the scene, which made it brilliant The blocking throughout the play worked with the floor plan very well. All of the blocking worked seamlessly; the actors were never out of sight or in awkward positioning (ex. turned around, talking to someone behind them while face forward, etc.) even during the second act in the first scene while all eight actors were on set. One part of the set that stood out was the staircase, it is obviously very large, but the way Owen Virgin followed Megan Davis up the stairs was seamless. They both walked up the stairs with footsteps I unison, and Owen Virgin was so focused on every detail of Davis, it almost screamed out how much he was infatuated with her. The artistic intent of this production was to entertain, and it fulfilled that intent completely. The entire audience was laughing during most of the production, I was even laughing out loud, which doesn’t happen very often. Every interaction between the characters was comical at one point during the production. An example of this playing out would be in the second act when Rascher busts through the door and proclaims his engagement. The only purpose of this is to entertain and entertain it did. The actors seemed natural moving along with their blocking and engaging with each other on stage. There was no point at which it was confusing why someone moved here or there, every movement made sense.The overall mood that was portrayed by the combination of lighting, sound, set, and costumes was very light and cheerful; at no point did the mood drop to something darker or saddening. This is common with many comedies because it becomes hard to laugh if the overall mood is down and dreary. The theatre space was very personal. First of all, it takes place in someone’s home so it is immediately personal. Also, the stage was built into the crowd just about so the audience felt like they were living the action out as it unfolded. The scenic design showed the audience without a doubt it was the 1920’s, with the barometer on the wall, the staircase, the piano, and the lights upstairs. However, there was little evidence to show what location the play took place All of the costumes were well designed for the appropriate characters. Adam Espinoza did a fine job of showing how these people were all upper class with nice dresses and suits and tuxes, even when they were home alone with only each other as company. Megan Davis’ costumes represented her personality very well by drawing all attention to her with bright colors and silk-like texture. As far as the lighting goes, there were no changes to the lighting during the play, except for at the end of each act. The lighting emphasized a bright and cheerful mood throughout the play.This production was definitely representational because it doesn’t break the fourth wall into the audience. The actors never had any asides or soliloquies that were directed toward audience. The set and lighting were representational due to the realistic feel throughout the house, everything was relatable to the average person. The Bliss’ home looked like an ordinary home from the 1920’s. The overall message seemed to be: do not mislead people or play games with their emotions, or they will leave you. This was most clear during act three when Luz, Karl, Stegemoller, and Virgin were all discussing how uncomfortable they all had felt the day before in the house and decided to leave as soon as possible. This was due to the way the Bliss’ had romantic engagements just to get attention for themselves and did not care about the feelings of the people they were hurting.";
        //~1448 words
        //        String[] strings = hayFever.split(" ");
//        CountStringsFJ csf = new CountStringsFJ(strings,"was",10);
//        assertEquals(48,csf.doIt());
        long[][] storage = new long[8][16];
        int i = -1;
        for (int n = 1000; n <= 16000; n += 1000) {
            i++;
            StringBuilder stringB = new StringBuilder();
            stringB.append(hayFever.repeat(n));
            String s = stringB.toString();
            String[] strings = s.split(" ");
            System.out.println("array size = " + strings.length);
            storage[0][i] = strings.length;
            CountStringsFJ csf = new CountStringsFJ(strings, "was", strings.length + 10);
            long time1 = System.nanoTime();
            int test1 = csf.doIt();
            long time2 = System.nanoTime();
            System.out.println("no threshold");
            long totalTime1 = time2 - time1;
            System.out.println(totalTime1);
            storage[1][i] = totalTime1;
            assertEquals((n * 48), csf.doIt());
            csf = new CountStringsFJ(strings, "was", 1);
            time1 = System.nanoTime();
            int test2 = csf.doIt();
            time2 = System.nanoTime();
            System.out.println("threshold of 1");
            long totalTime2 = time2 - time1;
            storage[2][i] = totalTime2;
            System.out.println(totalTime2);
            csf = new CountStringsFJ(strings, "was", 10000);
            time1 = System.nanoTime();
            int test3 = csf.doIt();
            time2 = System.nanoTime();
            System.out.println("threshold of 10000");
            long totalTime3 = time2 - time1;
            System.out.println(totalTime3);
            storage[3][i] = totalTime3;
            csf = new CountStringsFJ(strings, "was", 20000);
            time1 = System.nanoTime();
            int test4 = csf.doIt();
            time2 = System.nanoTime();
            System.out.println("threshold of 20000");
            long totalTime4 = time2 - time1;
            System.out.println(totalTime4);
            storage[4][i] = totalTime4;
            csf = new CountStringsFJ(strings, "was", 25000);
            time1 = System.nanoTime();
            int test5 = csf.doIt();
            time2 = System.nanoTime();
            System.out.println("threshold of 25000");
            long totalTime7 = time2 - time1;
            System.out.println(totalTime7);
            storage[5][i] = totalTime7;
            csf = new CountStringsFJ(strings, "was", 30000);
            time1 = System.nanoTime();
            int test6 = csf.doIt();
            time2 = System.nanoTime();
            System.out.println("threshold of 30000");
            long totalTime6 = time2 - time1;
            storage[6][i] = totalTime6;
            System.out.println(totalTime6);
            csf = new CountStringsFJ(strings, "was", 50000);
            time1 = System.nanoTime();
            int test7 = csf.doIt();
            time2 = System.nanoTime();
            System.out.println("threshold of 50000");
            long totalTime5 = time2 - time1;
            System.out.println(totalTime5);
            storage[7][i] = totalTime5;

            long percentageGain = totalTime1 / totalTime7;
            System.out.println("25000/no = " + percentageGain);

            //System.out.println("1000 over no = " + totalTime3/totalTime1);
            assertEquals((n * 48), test1);
            assertEquals(test1, test2);
            assertEquals(test2, test3);
            assertEquals(test2, test4);
            assertEquals(test2, test5);
            assertEquals(test2, test6);
            assertEquals(test2, test7);


        }
        for(int j = 0; j<8; j++){
            for(int k = 0; k<16; k++){
                System.out.print(storage[j][k] +"       ");
            }
            System.out.println();
        }
       // System.out.println("Lets see");
        //System.out.println(Arrays.deepToString(storage));
    }

}