BEGIN TRANSACTION;
INSERT INTO "Purses" ("id","money") VALUES ('PRIMARY',500.0);
INSERT INTO "BasketItems" ("productId","amount") VALUES (1,2);
INSERT INTO "BasketItems" ("productId","amount") VALUES (2,1);
INSERT INTO "BasketItems" ("productId","amount") VALUES (3,1);
INSERT INTO "BasketItems" ("productId","amount") VALUES (4,7);
INSERT INTO "BasketItems" ("productId","amount") VALUES (12,5);
INSERT INTO "BasketItems" ("productId","amount") VALUES (17,1);
INSERT INTO "BasketItems" ("productId","amount") VALUES (18,1);
INSERT INTO "BasketItems" ("productId","amount") VALUES (19,4);
INSERT INTO "BasketItems" ("productId","amount") VALUES (24,1);
INSERT INTO "BasketItems" ("productId","amount") VALUES (25,1);
INSERT INTO "BasketItems" ("productId","amount") VALUES (27,1);
INSERT INTO "BasketItems" ("productId","amount") VALUES (30,12);
INSERT INTO "BasketItems" ("productId","amount") VALUES (33,9);
INSERT INTO "BasketItems" ("productId","amount") VALUES (40,1);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (1,4,1,1);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (2,7,2,1);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (3,1,3,1);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (4,2,4,2);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (5,7,5,2);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (6,5,2,3);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (7,1,10,4);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (8,6,15,4);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (9,2,20,4);
INSERT INTO "PurchaseItems" ("id","amount","productId","purchaseId") VALUES (10,1,25,4);
INSERT INTO "Purchases" ("id","date") VALUES (1,'21.04.2018');
INSERT INTO "Purchases" ("id","date") VALUES (2,'25.05.2019');
INSERT INTO "Purchases" ("id","date") VALUES (3,'11.01.2020');
INSERT INTO "Purchases" ("id","date") VALUES (4,'16.02.2020');
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (1,'Michelin Power Gravel Folding Tyre','The Michelin Power Gravel folding tyre is durable and offers good performance and grip on asphalt and off-road. The new exclusive X-Miles Compound rubber compound increases the durability of the tyres.',27.69,NULL,1);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (2,'WTB Nano 40c Race Folding Tire','FAAAASSSTT!!!!! The Nano 40c is built for speed, and let’s just politely say it delivers.
A 40mm, rounded profile and an elevated centerline provide enough volume for lower tire pressures and a smoother ride while still reducing drag and rolling resistance. This makes for an all out gravel destroyer that can still appease those clamoring for a truly big cyclocross tire. Winner? We think so. So does superhuman, beyond endurance athlete, Jay Petervary.',27.65,NULL,1);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (3,'Continental Gatorskin Folding Tire','The reptile.
Puncture-defence: With our PolyX Breaker®, we bring our car expertise to the bicycle tyre segment: Polyester has been used successfully for years in car tyres and been tried and tested many times. The extremely resistant polyester fibre is woven tightly crosswise. In this way, a very high fabric density is reached which not only makes the Breaker more resistant to foreign objects but is also especially resistant to punctures. The rolling resistance is also not negatively affected by the PolyX Breaker®.',26.81,NULL,1);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (4,'Maxxis Ravager 28" EXO TR Dual 120 Folding Tyre','The Maxxis Ravager is a gravel tyre for drivers who want to tackle difficult terrain just with their dropbars and a thin layer of rubber.
Where other tires go beyond the end of the road, the Ravager only begins. High-set square centre studs provide good climbing and braking properties. Large side studs take you safely through the next bend.',29.37,NULL,1);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (5,'Maxxis Rambler 27.5" TR EXO Dual 120 Folding Tyre','Maxxis first real Gravel Bike tire. The Rambler has the volume you need for good comfort and the profile you need for speed and control. The narrow centre studs are chamfered and therefore roll very well on hard surfaces, open side studs ensure predictable cornering behaviour on different ground conditions.',36.93,NULL,1);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (6,'Pirelli Cinturato GRAVEL H Folding Tyre','The Cinturato™ Gravel Hard Terrain is a gravel-specific tyre designed for compact terrain and the hardest surfaces. The tread features low, tightly packed knobs and elevated ability to adapt to the terrain, offering a large contact area and therefore excellent feel while riding.
Start your gravel adventure: the Cinturato™ Gravel H from Pirelli
The special SpeedGRIP Compound adds features of mechanical resistance and chemical grip without compromising the rolling efficiency. Cinturato™ Gravel Hard Terrain is a high-performance tyre with unique characteristics of grip in all weather conditions, be they dry or wet, plus a high level of puncture protection.',37.73,NULL,1);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (7,'Maxxis Speed Terrane 28" TR EXO Dual 120 Folding Tyre','The Maxxis Speed Terrane features a familiar file tread pattern optimized for early season cyclocross races. Micro-diamond knobs adorn the tread surface to offer blazing speed on hardpacked surfaces, while stacked paddle knobs down the middle offer unrivaled climbing and braking traction so you can attack whenever you like.
When the course gets twisty, the siped and offset cornering knobs will dig in to give you the support to brake later and push your tires as hard as your legs. Worry less about your tires and more about the hole shot!',37.77,NULL,1);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (8,'Shimano XT PD-M8140 Flat Pedals','The Trail and Enduro platform pedals XT PD-M8120 from Shimano are available in small to medium (recommended shoe sizes 36-43) or medium to large (recommended shoe sizes 43-48). This guarantees optimum support and performance tailored to the driver.
Perfect grip on any trail: Shimano XT PD-M8140 Flat Pedals
With ten pins on each side and a slightly curved design for greater comfort and efficiency, these trail and enduro pedals offer the unique off-road performance that can rightly be expected from the DEORE XT.

The original mountain bike group continues its legacy of remaining the standard for mountain bikers everywhere. Nearly three decades in, the DEORE XT brand is defined by its trail-tested durability, reliability and performance. Recognizing that today’s mountain bikers are riding faster and confidently on more technical terrain, the legacy of DEORE XT is more important than ever. In 2019, our pinnacle mountain bike technologies will trickle down to SHIMANO DEORE XT M8100.',61.34,NULL,2);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (9,'Wellgo MTB / ATB Platform Pedals MG6-AL','High-quality MTB / BMX / freestyle pedal forged from one piece of aluminum',20.92,NULL,2);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (10,'RFR ETP Flat Pedals','The ultra-light RFR ETP flat pedal made of technical thermoplastic offers a perfect grip in wind and weather with the exchangeable anti-slip pins. The CNC-milled Cr-Mo axis is extremely light and durable.',20.97,NULL,2);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (11,'CrankBrothers Stamp 1 Platform Pedals - Splash Edition','Crankbrothers'' Stamp 1 Splash Edition are size-specific platform pedals with a composite pedal body and plenty of grip. They provide the optimal interface between bike and shoe.',28.53,NULL,2);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (12,'NC-17 Platform Pedal Trekking Pro Alu silver','Lightweight, durable and inexpensive. For everyday use or times around the world.
Could not be better. All qualities of an outstanding pedal are combined in the Trekking Pro.',29.37,NULL,2);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (13,'ACID FLAT A4-IB Hybrid Platform Pedals','When the days get hectic, you need to know that you can rely on the grip of your pedals. From weekend tours on your trekking bike to navigating the city streets on your zippy runabout, the ACID FLAT A4-IB Hybrid platform pedals is the perfect companion. The integrated reflectors give them a classy finish and make them look good.',29.37,NULL,2);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (14,'Katana Blade Flat Pedals','The flat Katana Blade pedals are made of a sturdy body and are each equipped with 10 Evo pins for the necessary grip on all trails. The aluminium pedal body and the CNC-milled axles in combination with high-quality bearings ensure a long service life in every terrain, even under the toughest conditions. Blade is flat, robust and fast!',33.61,NULL,2);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (15,'SRAM Apex Rear Derailleur medium Cage black','SRAM again raises the bar on Rear Derailleur performance with the new Exact Actuation Apex rear derailleur.
The Apex Rear Derailleur represents the same design philosophy that has delivered our best in class products for the road market. Features include our new precise Exact Actuation ratio that offers the widest range of adjustment coupled with a precise geometry to create market leading 10 speed performance.',37.4,NULL,3);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (16,'Shimano GRX RD-RX812 1x11-speed Rear Derailleur','Based on the technology introduced by SHIMANO with the RX rear derailleurs, the GRX RD-RX812 1x11-speed rear derailleur is characterised by quiet, smooth, precise and reliable shifting, even in the most demanding terrain, thanks mainly to the SHADOW RD+ chain stabilisation system.
For gravel, road and cyclocross use: Shimano GRX 1x11-speed Rear Derailleur RD-RX812
It prevents the chain from hitting and unnecessary movements of the rear derailleur cage, thus ensuring uninterrupted switching performance. In total, the GRX family offers five rear derailleurs, which are used depending on the selection of the cassette and the shift system. The rear derailleurs with short cage (Di2: RDRX815; mechanical: RD-RX810) are used for 11-30T or 11-34T cassettes at ULTEGRA, 105 or TIAGRA level. If you use the 11-40T or 11-42T DEORE XT, SLX or DEORE groups, you will need the long cage rear derailleurs (Di2: RD-RX817; mechanical: RD-RX812), both of which have a specific lever-operated ratio for the road bike. Finally, in a 10-speed setup, SHIMANO''s HG500-10 cassettes (11-32T, 11-34T, 11-36T) with the RD-RX400 rear derailleur are used.

SHIMANO''s GRX, the first Gravel-specific component family designed for both fast progress and safe bike handling on loose surfaces, is the result of an intensive and extensive research, testing and development process that incorporates information and feedback from countless riders on trails, forest tracks, gravel tracks and asphalt all over the world.',71.34,NULL,3);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (17,'SRAM Rival 1 Type 3.0 11-speed Rear Derailleur long','The SRAM Rival 1 X-HORIZON™ rear derailleur is engineered for smooth, reliable shifting action—from the larger upper pulley offset to the 12-tooth X-SYNC™ pulley wheels
For smooth, reliable shifting action: the Sram Rival 1 Type 3.0 Rear Derailleur
By eliminating unwanted chain movement, X-HORIZON™ shifts faster, puts an end to ghost shifting and reduces shift force and chain slap. And with CAGE LOCK™ technology, wheel removal and installation are easier than ever.',75.55,NULL,3);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (18,'Shimano Dura Ace 2x11-speed Rear Derailleur RD-R9100 SS','The new DURA-ACE rear derailleur features Shimano’s Shadow RD design, which gives the derailleur a lower profile and more stable operation.
Most advanced component group of all time: Shimano Dura Ace 2x11-speed Rear Derailleur RD-R9100 SS
With the introduction of DURA-ACE R9100, Shimano has reestablished the new benchmark for road performance by improving each component and incorporating its newest technologies. DURA-ACE R9100 delivers unparalleled performance, highly-refined ergonomics and immense customization to maximize every rider’s potential.

Engineered for the next generation of race bike, DURA-ACE R9100 offers top-tier performance across numerous configurations. Available with either Di2 electronic or mechanical shifting, for the first time, riders now have the option of premium DURA-ACE hydraulic disc brake performance. New Shimano technologies further integrate the DURA-ACE as a system, including a new power meter, while riders further integrate with their bike through new E-Tube wireless connectivity. Shimano’s Rider Tuned philosophy and customization of the bicycle’s function also expands with a wider range drivetrain that now includes intuitive Shimano Synchro Shift control.',142.02,NULL,3);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (19,'Shimano GRX Di2 RD-RX817 1x11-speed Rear Derailleur','Based on the technology introduced by SHIMANO with the RX rear derailleurs, the GRX Di2 RD-RX817 1x11-speed rear derailleur is characterised by quiet, smooth, precise and reliable shifting, even in the most demanding terrain, thanks mainly to the SHADOW RD+ chain stabilisation system.
For gravel, road and cyclocross use: Shimano Di2 GRX 1x11-speed Rear Derailleur RD-RX817
It prevents the chain from hitting and unnecessary movements of the rear derailleur cage, thus ensuring uninterrupted switching performance. In total, the GRX family offers five rear derailleurs, which are used depending on the selection of the cassette and the shift system. The rear derailleurs with short cage (Di2: RD-RX815; mechanical: RD-RX810) are used for 11-30T or 11-34T cassettes at ULTEGRA, 105 or TIAGRA level. If you use the 11-40T or 11-42T DEORE XT, SLX or DEORE groups, you will need the long cage rear derailleurs (Di2: RD-RX817; mechanical: RD-RX812), both of which have a specific lever-operated ratio for the road bike. Finally, in a 10-speed setup, SHIMANO''s HG500-10 cassettes (11-32T, 11-34T, 11-36T) with the RD-RX400 rear derailleur are used.

SHIMANO''s GRX, the first Gravel-specific component family designed for both fast progress and safe bike handling on loose surfaces, is the result of an intensive and extensive research, testing and development process that incorporates information and feedback from countless riders on trails, forest tracks, gravel tracks and asphalt all over the world.',192.44,NULL,3);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (20,'SRAM Force22 rear Derailleur 11-speed short','Adopting all the technologies that provide the fastest, quietest shifting available, the new SRAM Force 22 Rear Derailleur has been designed with our Exact Actuation™ technology for precise, efficient shifting throughout the extended gear range.
We have given and even refining tooth geometry of the jockey wheel. The result is that the chain that runs smoother and quieter than ever before. With all the additional technologies we’ve managed to keep the weight the same, at well under 200 grams.',61.3,NULL,3);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (21,'SRAM Force22 WiFLi Rear Derailleur 11-speed medium','Adopting all the technologies that provide the fastest, quietest shifting available, the new SRAM Force 22 Rear Derailleur.
The Force22 has been designedwith our Exact Actuation™ technology for precise, efficient shifting throughout the extended gear range.
Sram has given and even refining tooth geometry of the jockey wheel. The result is that the chain that runs smoother and quieter than ever before.',69.71,NULL,3);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (22,'Fox Racing 38 K FLOAT 29" Factory Grip2 Tapered Boost','The Fox 38 K Float 29" Factory Boost with Kashima Coat, improved GRIP2 cartridgeis a lightweight fork with 160 to 180mm travel - designed for Enduro riders.
High stiffness, innovative and lightweight: the new 38 K Float 29" Factory Grip2 Tapered Boost from Fox
All the revolutionary features of the new 36 packed into a super-strong chassis specially designed for modern, hard Enduro racing with long suspension travel: The brand new 38 is at the starting line! Whatever gets in its way, it will never tire of panting for even more speed.

The award-winning 38 gets a huge performance boost with the improved FIT GRIP2 4-way adjustable damper. Equipped with the patent-pending dual Variable Valve Control (VVC) high- and low-speed rebound adjuster, improved compression damping and less friction overall, the 38 offers a new level of performance and adjustability that matches the X2 shocks. The high-speed compression stage and high-speed rebound stage is now easily adjustable with 8 clicks. "Factory" says it all. The best-of-the-best technology we have to offer is available in our Factory Series models with our buttery smooth, and ultra durable Genuine Kashima Coat. These products are World Cup proven.External steps in lower casting reduce weight.',1335.29,NULL,4);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (23,'RockShox
Pike Ultimate RC2 29" DebonAir Tapered Boost','RockShox Pike Ultimate RC2 featuring an updated DebonAir™ air spring paired with the Charger™ 2.1 damper for unmatched trail control for every sweeping corner or loamy drop in. Ultra-low friction SKF wiper seals and Maxima Plush damping fluid all work overtime to reduce friction, silence noise, and provide lasting fork performance.
New Charger™ 2 RC2 damper options and a new Signature Silver colorway all add up to the best performing trail fork on the market.',654.62,NULL,4);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (24,'DT Swiss F 232 One 29" Tapered Boost','Pedal to the metal? No matter whether uphill, downhill or flat over rooty trails? If your answer is yes, then the DT Swiss F 232 ONE is the right fork for you. The topology-optimized lower results in a benchmark stiffness-to-weight ratio and is therefore ready for all those steep and gnarly descents.
Sets new benchmarks in Cross Country: the F 232 One suspension fork from DT Swiss
The XC-optimized LINEAIR spring system makes you float over root carpets and offers the right amount of progression to face up to the big hits. The three-position adjustment of the INCONTROL damping offers the perfect compression level for every situation – LOCK for sprints, DRIVE for technical climbs and OPEN mode which allows you to keep your brakes open on the downhills.',823.45,NULL,4);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (25,'Fox Racing
32 K FLOAT 29" Factory StepCast FIT4 3Pos-Adj Tapered Kabolt','The Fox 32 K Float 29" Factory StepCast Boost with Kashima Coat and 3Pos-Adjust FIT4-damper is a lightweight fork with 100mm travel - designed for Cross-Country and Marathon riders. A redesigned crown increases stiffness to the same level as the Trail-proven 34
Super Lightweight Race Fork: the 32 K Float 29" Factory StepCast FIT4 3Pos-Adj Tapered Kabolt from Fox
The distinct shape of the lower leg design isn''t just for looks. The 32 StepCast (SC) chassis uses a narrow stance to save weight and the step design makes room for the spokes and brake rotor. The 32 StepCast features 32mm upper tubes and ample bushing overlap for a quality ride and the hollowed out lower legs provide even more weight savings.

"Factory" says it all. The best-of-the-best technology we have to offer is available in our Factory Series models with our buttery smooth, and ultra durable Genuine Kashima Coat. These products are World Cup proven.External steps in lower casting reduce weight.',1007.56,NULL,4);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (26,'Suntour
XCM34 29" Disc Tapered 100 Boost','The Suntour XCM34 29" beginners fork with reliable tech - manufactured exclusively for the Cube! With coil with preload adjuster, 100 mm travel, tapered steerer and 15 x 110 mm boost thru axle (no box packaging).',58.78,NULL,4);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (27,'RockShox
Judy Silver TK 29" Solo Air Tapered Boost PopLoc','The RockShox Judy Silver brings the benefits of Boost™ to bold new places with 2.8” tire compatibility and delivers proven RockShox technologies such as the Solo Air™ spring and the easy to use TurnKey™ damper with lockout about PopLoc lever.
Plus Fun & Plus Control, For All: Rock Shox Judy Silver TK 29" Solo Air Tapered Maxle Stealth 15x110mm Boost PopLoc
Who says entry level bikes can’t get all the latest features and performance? Judy is built around a bold chassis, designed with the same philosophy of the award winning Pike for the demands of modern mountain biking: stiff, yet very light. BOOST™ means also room for Plus tires, up to 2.8” in both the 27.5” and 29” models, while Torque Caps compatibility allows to add additional stiffness and control to the fork/wheel ensemble.',234.45,NULL,4);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (28,'Manitou
Mattoc Pro 27.5" 120 Tapered SL Boost','The same excellent fork as the original Mattoc, but improved in detail: The Manitou Mattoc Dorado Air replaces its predecessor and shines with valuable optimizations: Incremental Volume Adjust (IVA) allows individual tuning of progression.
A new air piston and revised seals reduce friction and are extra durable. The Hexlock SL 15 x 110mm Boost Stechachse is intuitive to use, saves weight and has a self-extracting mechanism.',218.48,NULL,4);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (29,'Magura MT5 Disc Brake Set','Introduction to the"Magura" Performance.
Class adapted for use on cycles, motorcycle racing''s proven 4-piston technology creates an absolute maximum of braking force. No matter how difficult the trail terrain, our 4-piston technology and novel new ergonomics in the brake lever give you a performance package which will fill you with confidence. The MT5 is also perfect for S-Pedelecs. The aluminium, two-finger brake levers are ergonomically perfect for your hands, allowing you to easily and accurately apply the braking power you need, even during long braking actions. Our use of Carbotecture® technology in the brake handle housing keeps the MT5''s weight down to a minimum and provides optimal braking performance.',126.01,NULL,5);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (30,'Shimano XT Disc Brake BR-M8000 Set with J02A Resin','Industry-standard braking gets even better! The DEORE XT hydraulic disc brakes are the industry benchmark for affordable high performance.
Consistently predictable and powerful, the trail-rated DEORE XT M8000 retains that standard while upgrading with a lighter, cleaner and more durable integrated master cylinder. This gives a sleeker look, saves weight and frees up valuable handlebar space. Refined Servo Wave levers provide improved feel and feedback, offering high power with a short brake pull. Like on past models, DEORE XT brakes retain tool-free reach adjust and Free Stroke adjust, whilst a narrow clamp band offers increased lever adjustment. The Rider Tuned system accommodates 140 to 203mm rotors and multiple brake pad choices.',133.61,NULL,5);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (31,'SRAM G2 R Disc Brake Set','The core of brake performance is consistency. Reliability. Trust. And with G2 R, Sram focused on offering a premium value without compromising the heart of the operation. This brake gives riders the G2s famed “Mini-Code” power and a whole suite of SRAM standard features at an absolutely unbeatable value.',142.02,NULL,5);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (32,'Shimano XT BR-M8020/8000 Disc Brake Set 850/1650mm','The BR-M8020 hydraulic 4-piston disc brake for the front wheel in combination with the BR-M8000 2-piston disc brake for the rear wheel fits seamlessly into the line-up of the Shimano XT M8000 component group (no box packaging).
4-piston front for maximum braking power and 2-piston rear for perfect dosing: Shimano XT Disc Brake Set BR-M8020/8000
The brakes feature Shimano’s ICE TECHNOLOGIES heat management system through its caliper design, compatible rotors and pads. 

Consistently predictable and powerful, the trail-rated DEORE XT M8020 retains that standard while upgrading with a lighter, cleaner and more durable integrated 4-piston master cylinder. This gives a sleeker look, saves weight and frees up valuable handlebar space. Refined Servo Wave levers provide improved feel and feedback, offering high power with a short brake pull. Like on past models, DEORE XT brakes retain tool-free reach adjust and Free Stroke adjust, whilst a narrow clamp band offers increased lever adjustment. The Rider Tuned system accommodates 140 to 203mm rotors and multiple brake pad choices.',142.82,NULL,5);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (33,'SRAM Guide RE E-Bike Scheibenbremsen-Set','Fast isn’t about pure speed, it’s about perfect speed.
The Power of Control: The Sram Guide RE
It combines SRAM''s proven Guide R lever assembly with a powerful 4-piston caliper derived from the gravity-focused Code brakes. Guide RE provides real, usable on-trail control—optimal power and modulation mean speed can be controlled in all terrain and in all situations, both uphill and downhill.',145.34,NULL,5);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (34,'SRAM Level TLM Disc Brake Set','Level TLM puts proven SRAM braking performance and consistency at your fingertips, in a sleek, lightweight design optimized specifically for cross-country and light trail use. Power and modulation are delivered via the Level TLM’s alloy lever blade, DirectLink™ actuation, DOT 5.1 fluid and the new two-piston, two-piece caliper.
The design provides superior heat management for consistent, fade-free performance all day long, and BLEEDING EDGE™ technology makes maintenance incredibly simple, so your brakes feel great all day — every day.',163.45,NULL,5);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (35,'SRAM Guide RS Disc Brakes-Set','Fast isn’t about pure speed, it’s about perfect speed.
Packed with ride-enhancing performance: The Sram Guide RS
We started from scratch, to create the perfect combination of braking reliability and control. Brand-new SwingLink™ technology provides more power, silky-smooth modulation, less deadband and better lever-feel than you’ve ever experienced. Steep-line confidence. Deep-corner dominance. Ride every trail like you own it. KNOW YOU CAN. Now featuring the World Championship-winning S4 caliper with Bleeding Edge™ and Heat Shield technologies.',163.87,NULL,5);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (36,'KMC X11 11-speed Chain','The super light X11 11-speed chain from KMC without directional dependency and half nickel plated for 11-speed derailleur systems.',16.76,NULL,6);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (37,'SRAM PowerChain PC X1 11-speed Chain','The PC-X1 chain comes from a long line of dependable, lightweight chains that are built for toughness.
One for all: the SRAM PowerChain PC X1 11-speed Chain
Designed with SRAM’s trusted XX1 geometry, the PC-X1 features solid pin construction, 11-speed PowerLock™ and smooth, efficient shifting that you can count on every time out.',18.48,NULL,6);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (38,'YBN 11-speed E-Bike Chain S11e S2 136 Links','Robust design makes this 11-speed chain extremely powerful and it easily stands up to the high-torque loads of e-bike drives.',20.97,NULL,6);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (39,'Wippermann Connex 11SE 11-speed E-Bike Chain','The 11SE is perfect for really hard work.
Its robust design makes this 11-speed chain extremely efficient and reliable. Despite its narrow width, it easily stands up to the high-torque loads of e-bike drives.',33.57,NULL,6);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (40,'Shimano
11-speed Chain CN-HG601 with Quick-Link (20 Workshop Pack)','HG-X11 directional chain realize more smoother shifts and greater overall performance. Right and left sides are optimized for front and rear shifting respectively.
Silky driving and accurate shifting: Shimano CN-HG601 11-speed Chain 116 Links with Quick-Link Connector
With an HG-X11 special asymmetric plate design and Shimano''s new SIL-TEC surface treatment, a quieter ride, increased chain life and better performance await you.',326.89,NULL,6);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (41,'YBN SLA 211 Titanium 11-speed Chain Hollow Pin','The YBN SLA 211 Titanium 11-speed chain is not just a hollow pin chain that is as durable as it is smooth and undeniably impressive in terms of performance, it is probably the world''s lightest 11-speed chain. A lightweight dream in titanium.',151.22,NULL,6);
INSERT INTO "Products" ("id","title","description","price","image","categoryId") VALUES (42,'KMC X11 DLC 11-speed Chain','The super light X11 DLC 11-speed chain from KMC without directional dependency and an Diamond Like Coating for 11-speed derailleur systems.',67.18,NULL,6);
INSERT INTO "Categories" ("id","title") VALUES (1,'Покрышки');
INSERT INTO "Categories" ("id","title") VALUES (2,'Педали');
INSERT INTO "Categories" ("id","title") VALUES (3,'Задние переключатели');
INSERT INTO "Categories" ("id","title") VALUES (4,'Вилки');
INSERT INTO "Categories" ("id","title") VALUES (5,'Тормаза');
INSERT INTO "Categories" ("id","title") VALUES (6,'Цепи');
COMMIT;
