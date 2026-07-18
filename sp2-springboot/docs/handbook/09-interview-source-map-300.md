# TLBank Interview Source Map (300)

Repository-grounded interview questions validated against the current `sp2-springboot` codebase and related monorepo paths.

> Draft source: `sp2-springboot/docs/drafts/interview-question-catalog-draf.md`. Claims were checked against source; planned or partial behavior is labelled in notes where relevant.

---

## Table of Contents

- [Category Index](#category-index)
- [Technology Index](#technology-index)
- [Business Feature Index](#business-feature-index)
- [File Path Index](#file-path-index)
- [Difficulty Index](#difficulty-index)
- [Questions Q001–Q300](#questions-q001q300)
- [Referenced File Appendix](#referenced-file-appendix)

---

## Category Index

### Application State Machine

[Q051](#Q051), [Q052](#Q052), [Q053](#Q053), [Q054](#Q054), [Q055](#Q055), [Q056](#Q056), [Q057](#Q057), [Q058](#Q058), [Q059](#Q059), [Q060](#Q060)

### Audit Logging and AOP

[Q264](#Q264), [Q277](#Q277), [Q282](#Q282)

### Behavioral Questions Based on the Project

[Q239](#Q239), [Q240](#Q240), [Q241](#Q241), [Q242](#Q242), [Q243](#Q243), [Q244](#Q244), [Q245](#Q245), [Q246](#Q246), [Q247](#Q247), [Q248](#Q248), [Q249](#Q249), [Q280](#Q280), [Q287](#Q287), [Q291](#Q291), [Q292](#Q292), [Q293](#Q293), [Q297](#Q297)

### Clean / Hexagonal Architecture

[Q027](#Q027), [Q028](#Q028), [Q029](#Q029), [Q030](#Q030), [Q031](#Q031), [Q032](#Q032), [Q033](#Q033), [Q034](#Q034), [Q035](#Q035), [Q036](#Q036), [Q037](#Q037), [Q038](#Q038), [Q039](#Q039), [Q040](#Q040), [Q276](#Q276), [Q284](#Q284), [Q288](#Q288)

### Credit Review Workflow

[Q145](#Q145), [Q146](#Q146), [Q147](#Q147), [Q148](#Q148)

### Docker and Docker Compose

[Q183](#Q183), [Q184](#Q184), [Q185](#Q185), [Q186](#Q186), [Q187](#Q187), [Q188](#Q188), [Q189](#Q189), [Q190](#Q190), [Q285](#Q285)

### Domain Events

[Q099](#Q099), [Q100](#Q100), [Q274](#Q274)

### Domain-Driven Design

[Q041](#Q041), [Q042](#Q042), [Q043](#Q043), [Q044](#Q044), [Q045](#Q045), [Q046](#Q046), [Q047](#Q047), [Q048](#Q048), [Q049](#Q049), [Q050](#Q050), [Q251](#Q251), [Q253](#Q253), [Q254](#Q254), [Q255](#Q255), [Q256](#Q256), [Q262](#Q262), [Q266](#Q266), [Q271](#Q271)

### File Upload and Storage

[Q177](#Q177), [Q178](#Q178), [Q179](#Q179), [Q180](#Q180), [Q181](#Q181), [Q182](#Q182), [Q298](#Q298)

### Flyway Migrations

[Q118](#Q118), [Q119](#Q119), [Q120](#Q120), [Q121](#Q121), [Q122](#Q122), [Q123](#Q123), [Q124](#Q124), [Q125](#Q125), [Q126](#Q126)

### GitHub Actions CI/CD

[Q191](#Q191), [Q193](#Q193), [Q194](#Q194), [Q195](#Q195), [Q196](#Q196), [Q197](#Q197), [Q289](#Q289)

### In-Memory Cache

[Q086](#Q086), [Q141](#Q141), [Q142](#Q142), [Q143](#Q143), [Q144](#Q144)

### Integration Testing

[Q011](#Q011), [Q150](#Q150)

### JPA and Hibernate

[Q089](#Q089), [Q090](#Q090), [Q091](#Q091), [Q092](#Q092), [Q093](#Q093), [Q094](#Q094), [Q095](#Q095), [Q257](#Q257), [Q258](#Q258), [Q259](#Q259), [Q260](#Q260)

### JaCoCo

[Q157](#Q157), [Q158](#Q158), [Q159](#Q159), [Q160](#Q160)

### Java 17 Features

[Q009](#Q009), [Q010](#Q010), [Q012](#Q012), [Q013](#Q013), [Q014](#Q014), [Q015](#Q015), [Q016](#Q016), [Q269](#Q269)

### Notifications (Mock)

[Q161](#Q161), [Q162](#Q162), [Q163](#Q163), [Q164](#Q164), [Q165](#Q165), [Q166](#Q166)

### OTP Flow

[Q135](#Q135), [Q136](#Q136), [Q137](#Q137), [Q138](#Q138), [Q139](#Q139), [Q140](#Q140)

### Observability and Logging

[Q217](#Q217), [Q218](#Q218), [Q219](#Q219), [Q220](#Q220), [Q221](#Q221), [Q222](#Q222), [Q234](#Q234)

### Performance and Scalability

[Q097](#Q097), [Q098](#Q098), [Q209](#Q209), [Q210](#Q210), [Q211](#Q211), [Q212](#Q212), [Q213](#Q213), [Q214](#Q214), [Q215](#Q215), [Q216](#Q216), [Q263](#Q263)

### Project Overview and One-Repository Strategy

[Q001](#Q001), [Q002](#Q002), [Q003](#Q003), [Q004](#Q004), [Q005](#Q005), [Q006](#Q006), [Q007](#Q007), [Q008](#Q008), [Q200](#Q200), [Q279](#Q279), [Q294](#Q294), [Q299](#Q299)

### REST API Design and OpenAPI

[Q061](#Q061), [Q062](#Q062), [Q063](#Q063), [Q064](#Q064), [Q065](#Q065), [Q066](#Q066), [Q067](#Q067), [Q068](#Q068), [Q265](#Q265), [Q278](#Q278)

### Redis and Idempotency

[Q132](#Q132), [Q134](#Q134), [Q268](#Q268)

### Reports (Excel and PDF)

[Q133](#Q133), [Q171](#Q171), [Q172](#Q172), [Q173](#Q173), [Q174](#Q174), [Q175](#Q175), [Q176](#Q176)

### Repository Pattern (Ports and Adapters)

[Q101](#Q101), [Q102](#Q102), [Q103](#Q103), [Q104](#Q104), [Q105](#Q105), [Q106](#Q106), [Q107](#Q107), [Q108](#Q108), [Q109](#Q109), [Q110](#Q110), [Q270](#Q270)

### SQL Server and H2

[Q111](#Q111), [Q112](#Q112), [Q113](#Q113), [Q114](#Q114), [Q115](#Q115), [Q116](#Q116), [Q117](#Q117)

### Schedulers

[Q167](#Q167), [Q168](#Q168), [Q169](#Q169), [Q170](#Q170), [Q192](#Q192)

### Security and Production Risks

[Q201](#Q201), [Q202](#Q202), [Q203](#Q203), [Q204](#Q204), [Q205](#Q205), [Q206](#Q206), [Q207](#Q207), [Q208](#Q208), [Q286](#Q286)

### Session Authentication vs JWT

[Q087](#Q087), [Q088](#Q088)

### Spring Boot 3.x

[Q017](#Q017), [Q018](#Q018), [Q019](#Q019), [Q020](#Q020), [Q021](#Q021), [Q022](#Q022), [Q023](#Q023), [Q024](#Q024), [Q025](#Q025), [Q026](#Q026)

### Spring Security

[Q077](#Q077), [Q078](#Q078), [Q079](#Q079), [Q080](#Q080), [Q081](#Q081), [Q082](#Q082), [Q083](#Q083), [Q084](#Q084), [Q085](#Q085), [Q096](#Q096), [Q250](#Q250), [Q261](#Q261), [Q267](#Q267)

### System Design Evolution

[Q231](#Q231), [Q232](#Q232), [Q233](#Q233), [Q235](#Q235), [Q236](#Q236), [Q237](#Q237), [Q238](#Q238)

### Technical Debt and Known Limitations

[Q223](#Q223), [Q224](#Q224), [Q225](#Q225), [Q226](#Q226), [Q227](#Q227), [Q228](#Q228), [Q229](#Q229), [Q230](#Q230), [Q290](#Q290)

### Terraform (Local)

[Q198](#Q198), [Q199](#Q199), [Q295](#Q295)

### Testing (JUnit + Mockito + MockMvc)

[Q149](#Q149), [Q151](#Q151), [Q152](#Q152), [Q153](#Q153), [Q154](#Q154), [Q155](#Q155), [Q156](#Q156), [Q273](#Q273), [Q283](#Q283), [Q296](#Q296)

### Transactions

[Q127](#Q127), [Q128](#Q128), [Q129](#Q129), [Q130](#Q130), [Q131](#Q131), [Q272](#Q272), [Q281](#Q281)

### Validation and Exception Handling

[Q069](#Q069), [Q070](#Q070), [Q071](#Q071), [Q072](#Q072), [Q073](#Q073), [Q074](#Q074), [Q075](#Q075), [Q076](#Q076), [Q252](#Q252), [Q275](#Q275), [Q300](#Q300)

---

## Technology Index

### Bean Validation

[Q069](#Q069), [Q070](#Q070), [Q071](#Q071), [Q072](#Q072), [Q073](#Q073), [Q074](#Q074), [Q075](#Q075), [Q076](#Q076), [Q252](#Q252), [Q275](#Q275), [Q300](#Q300)

### DDD

[Q010](#Q010), [Q029](#Q029), [Q034](#Q034), [Q041](#Q041), [Q042](#Q042), [Q043](#Q043), [Q044](#Q044), [Q045](#Q045), [Q046](#Q046), [Q047](#Q047), [Q048](#Q048), [Q049](#Q049), [Q050](#Q050), [Q058](#Q058), [Q099](#Q099), [Q100](#Q100), [Q130](#Q130), [Q251](#Q251), [Q253](#Q253), [Q254](#Q254), [Q255](#Q255), [Q256](#Q256), [Q262](#Q262), [Q266](#Q266), [Q271](#Q271), [Q274](#Q274)

### Docker

[Q183](#Q183), [Q184](#Q184), [Q185](#Q185), [Q186](#Q186), [Q187](#Q187), [Q188](#Q188), [Q189](#Q189), [Q190](#Q190), [Q196](#Q196), [Q285](#Q285)

### Domain model

[Q051](#Q051), [Q052](#Q052), [Q053](#Q053), [Q054](#Q054), [Q055](#Q055), [Q056](#Q056), [Q057](#Q057), [Q058](#Q058), [Q059](#Q059), [Q060](#Q060), [Q145](#Q145), [Q146](#Q146), [Q147](#Q147), [Q148](#Q148)

### Excel / PDF

[Q133](#Q133), [Q155](#Q155), [Q171](#Q171), [Q172](#Q172), [Q173](#Q173), [Q174](#Q174), [Q175](#Q175), [Q176](#Q176), [Q226](#Q226)

### Flyway

[Q019](#Q019), [Q114](#Q114), [Q118](#Q118), [Q119](#Q119), [Q120](#Q120), [Q121](#Q121), [Q122](#Q122), [Q123](#Q123), [Q124](#Q124), [Q125](#Q125), [Q126](#Q126)

### General / repository knowledge

[Q002](#Q002), [Q003](#Q003), [Q004](#Q004), [Q006](#Q006), [Q008](#Q008), [Q200](#Q200), [Q203](#Q203), [Q206](#Q206), [Q208](#Q208), [Q210](#Q210), [Q212](#Q212), [Q214](#Q214), [Q216](#Q216), [Q225](#Q225), [Q229](#Q229), [Q230](#Q230), [Q232](#Q232), [Q233](#Q233), [Q236](#Q236), [Q239](#Q239), [Q240](#Q240), [Q242](#Q242), [Q243](#Q243), [Q244](#Q244), [Q245](#Q245), [Q247](#Q247), [Q248](#Q248), [Q249](#Q249), [Q263](#Q263), [Q279](#Q279), [Q286](#Q286), [Q287](#Q287), [Q290](#Q290), [Q291](#Q291), [Q292](#Q292), [Q294](#Q294), [Q297](#Q297), [Q299](#Q299)

### GitHub Actions

[Q005](#Q005), [Q191](#Q191), [Q193](#Q193), [Q194](#Q194), [Q195](#Q195), [Q196](#Q196), [Q197](#Q197), [Q289](#Q289)

### Hexagonal architecture

[Q027](#Q027), [Q028](#Q028), [Q029](#Q029), [Q030](#Q030), [Q031](#Q031), [Q032](#Q032), [Q033](#Q033), [Q034](#Q034), [Q035](#Q035), [Q036](#Q036), [Q037](#Q037), [Q038](#Q038), [Q039](#Q039), [Q040](#Q040), [Q044](#Q044), [Q053](#Q053), [Q068](#Q068), [Q101](#Q101), [Q102](#Q102), [Q103](#Q103), [Q104](#Q104), [Q105](#Q105), [Q106](#Q106), [Q107](#Q107), [Q108](#Q108), [Q109](#Q109), [Q110](#Q110), [Q133](#Q133), [Q155](#Q155), [Q158](#Q158), [Q159](#Q159), [Q171](#Q171), [Q172](#Q172), [Q173](#Q173), [Q174](#Q174), [Q175](#Q175), [Q176](#Q176), [Q185](#Q185), [Q194](#Q194), [Q209](#Q209), [Q217](#Q217), [Q226](#Q226), [Q231](#Q231), [Q235](#Q235), [Q238](#Q238), [Q270](#Q270), [Q276](#Q276), [Q280](#Q280), [Q284](#Q284), [Q288](#Q288), [Q289](#Q289), [Q293](#Q293)

### In-memory cache

[Q086](#Q086), [Q097](#Q097), [Q141](#Q141), [Q142](#Q142), [Q143](#Q143), [Q144](#Q144), [Q215](#Q215), [Q228](#Q228)

### JPA / Hibernate

[Q019](#Q019), [Q026](#Q026), [Q029](#Q029), [Q034](#Q034), [Q045](#Q045), [Q048](#Q048), [Q050](#Q050), [Q067](#Q067), [Q068](#Q068), [Q089](#Q089), [Q090](#Q090), [Q091](#Q091), [Q092](#Q092), [Q093](#Q093), [Q094](#Q094), [Q095](#Q095), [Q102](#Q102), [Q107](#Q107), [Q115](#Q115), [Q181](#Q181), [Q257](#Q257), [Q258](#Q258), [Q259](#Q259), [Q260](#Q260), [Q270](#Q270)

### JUnit / Mockito / MockMvc

[Q011](#Q011), [Q149](#Q149), [Q150](#Q150), [Q151](#Q151), [Q152](#Q152), [Q153](#Q153), [Q154](#Q154), [Q155](#Q155), [Q156](#Q156), [Q213](#Q213), [Q250](#Q250), [Q273](#Q273), [Q283](#Q283), [Q296](#Q296)

### JaCoCo

[Q157](#Q157), [Q158](#Q158), [Q159](#Q159), [Q160](#Q160)

### Java 17

[Q009](#Q009), [Q010](#Q010), [Q012](#Q012), [Q013](#Q013), [Q014](#Q014), [Q015](#Q015), [Q016](#Q016), [Q046](#Q046), [Q058](#Q058), [Q060](#Q060), [Q069](#Q069), [Q136](#Q136), [Q138](#Q138), [Q140](#Q140), [Q152](#Q152), [Q175](#Q175), [Q202](#Q202), [Q253](#Q253), [Q254](#Q254), [Q255](#Q255), [Q267](#Q267), [Q269](#Q269)

### Local file storage

[Q177](#Q177), [Q178](#Q178), [Q179](#Q179), [Q180](#Q180), [Q181](#Q181), [Q182](#Q182), [Q298](#Q298)

### Logging / MDC

[Q080](#Q080), [Q217](#Q217), [Q218](#Q218), [Q219](#Q219), [Q220](#Q220), [Q221](#Q221), [Q222](#Q222), [Q234](#Q234), [Q237](#Q237), [Q264](#Q264), [Q277](#Q277), [Q282](#Q282)

### Mock integrations

[Q161](#Q161), [Q162](#Q162), [Q163](#Q163), [Q164](#Q164), [Q165](#Q165), [Q166](#Q166)

### OTP

[Q046](#Q046), [Q060](#Q060), [Q085](#Q085), [Q093](#Q093), [Q103](#Q103), [Q107](#Q107), [Q129](#Q129), [Q135](#Q135), [Q136](#Q136), [Q137](#Q137), [Q138](#Q138), [Q139](#Q139), [Q140](#Q140), [Q150](#Q150), [Q152](#Q152), [Q164](#Q164), [Q168](#Q168), [Q170](#Q170), [Q201](#Q201), [Q204](#Q204), [Q205](#Q205), [Q222](#Q222), [Q223](#Q223), [Q235](#Q235), [Q251](#Q251), [Q265](#Q265), [Q266](#Q266), [Q272](#Q272), [Q277](#Q277)

### OpenAPI

[Q020](#Q020), [Q061](#Q061), [Q062](#Q062), [Q063](#Q063), [Q064](#Q064), [Q065](#Q065), [Q066](#Q066), [Q067](#Q067), [Q068](#Q068), [Q207](#Q207), [Q265](#Q265), [Q278](#Q278)

### Redis

[Q016](#Q016), [Q132](#Q132), [Q134](#Q134), [Q205](#Q205), [Q228](#Q228), [Q268](#Q268)

### SQL Server / H2

[Q111](#Q111), [Q112](#Q112), [Q113](#Q113), [Q114](#Q114), [Q115](#Q115), [Q116](#Q116), [Q117](#Q117), [Q125](#Q125), [Q150](#Q150), [Q156](#Q156), [Q224](#Q224)

### Scheduling

[Q060](#Q060), [Q097](#Q097), [Q098](#Q098), [Q129](#Q129), [Q140](#Q140), [Q167](#Q167), [Q168](#Q168), [Q169](#Q169), [Q170](#Q170), [Q192](#Q192), [Q211](#Q211)

### Spring AOP

[Q073](#Q073), [Q264](#Q264), [Q277](#Q277), [Q282](#Q282)

### Spring Boot

[Q007](#Q007), [Q017](#Q017), [Q018](#Q018), [Q019](#Q019), [Q020](#Q020), [Q021](#Q021), [Q022](#Q022), [Q023](#Q023), [Q024](#Q024), [Q025](#Q025), [Q026](#Q026), [Q151](#Q151), [Q213](#Q213), [Q219](#Q219)

### Spring Security

[Q070](#Q070), [Q077](#Q077), [Q078](#Q078), [Q079](#Q079), [Q080](#Q080), [Q081](#Q081), [Q082](#Q082), [Q083](#Q083), [Q084](#Q084), [Q085](#Q085), [Q087](#Q087), [Q088](#Q088), [Q096](#Q096), [Q250](#Q250), [Q261](#Q261), [Q267](#Q267)

### Spring Transactions

[Q093](#Q093), [Q094](#Q094), [Q110](#Q110), [Q127](#Q127), [Q128](#Q128), [Q129](#Q129), [Q130](#Q130), [Q131](#Q131), [Q227](#Q227), [Q246](#Q246), [Q272](#Q272), [Q281](#Q281)

### Terraform (local)

[Q001](#Q001), [Q197](#Q197), [Q198](#Q198), [Q199](#Q199), [Q241](#Q241), [Q295](#Q295)

---

## Business Feature Index

### API contract

[Q020](#Q020), [Q061](#Q061), [Q062](#Q062), [Q063](#Q063), [Q064](#Q064), [Q065](#Q065), [Q066](#Q066), [Q067](#Q067), [Q068](#Q068), [Q207](#Q207), [Q265](#Q265), [Q278](#Q278)

### API errors & validation

[Q013](#Q013), [Q061](#Q061), [Q064](#Q064), [Q067](#Q067), [Q069](#Q069), [Q070](#Q070), [Q071](#Q071), [Q072](#Q072), [Q073](#Q073), [Q074](#Q074), [Q075](#Q075), [Q076](#Q076), [Q078](#Q078), [Q099](#Q099), [Q136](#Q136), [Q170](#Q170), [Q177](#Q177), [Q180](#Q180), [Q252](#Q252), [Q269](#Q269), [Q275](#Q275), [Q300](#Q300)

### Application lifecycle

[Q012](#Q012), [Q051](#Q051), [Q052](#Q052), [Q053](#Q053), [Q054](#Q054), [Q055](#Q055), [Q056](#Q056), [Q057](#Q057), [Q058](#Q058), [Q059](#Q059), [Q060](#Q060), [Q114](#Q114), [Q140](#Q140), [Q145](#Q145), [Q225](#Q225), [Q244](#Q244), [Q246](#Q246), [Q257](#Q257), [Q273](#Q273)

### Architecture boundaries

[Q027](#Q027), [Q028](#Q028), [Q029](#Q029), [Q031](#Q031), [Q032](#Q032), [Q036](#Q036), [Q037](#Q037), [Q040](#Q040), [Q101](#Q101), [Q102](#Q102), [Q270](#Q270), [Q276](#Q276), [Q288](#Q288)

### Audit logging

[Q024](#Q024), [Q072](#Q072), [Q073](#Q073), [Q074](#Q074), [Q076](#Q076), [Q079](#Q079), [Q117](#Q117), [Q118](#Q118), [Q126](#Q126), [Q128](#Q128), [Q135](#Q135), [Q149](#Q149), [Q201](#Q201), [Q202](#Q202), [Q220](#Q220), [Q264](#Q264), [Q277](#Q277), [Q281](#Q281), [Q282](#Q282)

### Authentication & authorization

[Q023](#Q023), [Q025](#Q025), [Q033](#Q033), [Q070](#Q070), [Q077](#Q077), [Q078](#Q078), [Q079](#Q079), [Q080](#Q080), [Q081](#Q081), [Q082](#Q082), [Q083](#Q083), [Q084](#Q084), [Q085](#Q085), [Q087](#Q087), [Q088](#Q088), [Q096](#Q096), [Q124](#Q124), [Q135](#Q135), [Q179](#Q179), [Q184](#Q184), [Q196](#Q196), [Q201](#Q201), [Q202](#Q202), [Q203](#Q203), [Q204](#Q204), [Q205](#Q205), [Q206](#Q206), [Q207](#Q207), [Q208](#Q208), [Q220](#Q220), [Q235](#Q235), [Q250](#Q250), [Q261](#Q261), [Q267](#Q267), [Q271](#Q271), [Q286](#Q286)

### CI/CD pipeline

[Q003](#Q003), [Q005](#Q005), [Q008](#Q008), [Q030](#Q030), [Q034](#Q034), [Q057](#Q057), [Q058](#Q058), [Q066](#Q066), [Q071](#Q071), [Q092](#Q092), [Q145](#Q145), [Q146](#Q146), [Q147](#Q147), [Q148](#Q148), [Q174](#Q174), [Q189](#Q189), [Q191](#Q191), [Q193](#Q193), [Q194](#Q194), [Q195](#Q195), [Q196](#Q196), [Q197](#Q197), [Q198](#Q198), [Q199](#Q199), [Q210](#Q210), [Q225](#Q225), [Q257](#Q257), [Q258](#Q258), [Q289](#Q289), [Q290](#Q290), [Q300](#Q300)

### Credit review workflow

[Q038](#Q038), [Q043](#Q043), [Q049](#Q049), [Q100](#Q100), [Q104](#Q104), [Q105](#Q105), [Q109](#Q109), [Q110](#Q110), [Q130](#Q130), [Q145](#Q145), [Q146](#Q146), [Q147](#Q147), [Q148](#Q148), [Q156](#Q156), [Q182](#Q182), [Q209](#Q209), [Q214](#Q214), [Q222](#Q222), [Q227](#Q227), [Q232](#Q232), [Q238](#Q238), [Q242](#Q242), [Q246](#Q246), [Q267](#Q267), [Q273](#Q273)

### Document upload / storage

[Q006](#Q006), [Q050](#Q050), [Q053](#Q053), [Q054](#Q054), [Q065](#Q065), [Q092](#Q092), [Q106](#Q106), [Q153](#Q153), [Q167](#Q167), [Q172](#Q172), [Q177](#Q177), [Q178](#Q178), [Q179](#Q179), [Q180](#Q180), [Q181](#Q181), [Q182](#Q182), [Q190](#Q190), [Q200](#Q200), [Q203](#Q203), [Q204](#Q204), [Q208](#Q208), [Q216](#Q216), [Q255](#Q255), [Q279](#Q279), [Q290](#Q290), [Q298](#Q298), [Q299](#Q299)

### Domain events

[Q044](#Q044), [Q047](#Q047), [Q099](#Q099), [Q100](#Q100), [Q130](#Q130), [Q274](#Q274)

### Domain model

[Q041](#Q041), [Q042](#Q042), [Q045](#Q045), [Q048](#Q048), [Q253](#Q253), [Q254](#Q254), [Q256](#Q256), [Q262](#Q262)

### Evolution & limitations

[Q229](#Q229), [Q230](#Q230), [Q233](#Q233), [Q236](#Q236), [Q237](#Q237)

### Idempotent application create

[Q011](#Q011), [Q016](#Q016), [Q035](#Q035), [Q063](#Q063), [Q132](#Q132), [Q134](#Q134), [Q268](#Q268), [Q269](#Q269)

### JPA and Hibernate

[Q089](#Q089), [Q090](#Q090), [Q091](#Q091), [Q094](#Q094), [Q095](#Q095), [Q259](#Q259), [Q260](#Q260)

### Java 17 Features

[Q009](#Q009), [Q014](#Q014), [Q015](#Q015)

### Local Terraform demo

[Q001](#Q001), [Q197](#Q197), [Q198](#Q198), [Q199](#Q199), [Q241](#Q241), [Q295](#Q295)

### Local container runtime

[Q183](#Q183), [Q184](#Q184), [Q185](#Q185), [Q186](#Q186), [Q187](#Q187), [Q188](#Q188), [Q189](#Q189), [Q190](#Q190), [Q196](#Q196), [Q224](#Q224), [Q285](#Q285)

### Notifications (mock)

[Q010](#Q010), [Q038](#Q038), [Q096](#Q096), [Q099](#Q099), [Q100](#Q100), [Q161](#Q161), [Q162](#Q162), [Q163](#Q163), [Q164](#Q164), [Q165](#Q165), [Q166](#Q166), [Q235](#Q235), [Q238](#Q238)

### OTP verification

[Q046](#Q046), [Q060](#Q060), [Q085](#Q085), [Q093](#Q093), [Q103](#Q103), [Q107](#Q107), [Q129](#Q129), [Q135](#Q135), [Q136](#Q136), [Q137](#Q137), [Q138](#Q138), [Q139](#Q139), [Q140](#Q140), [Q150](#Q150), [Q152](#Q152), [Q164](#Q164), [Q168](#Q168), [Q170](#Q170), [Q201](#Q201), [Q204](#Q204), [Q205](#Q205), [Q222](#Q222), [Q223](#Q223), [Q235](#Q235), [Q251](#Q251), [Q265](#Q265), [Q266](#Q266), [Q272](#Q272), [Q277](#Q277)

### Observability and Logging

[Q217](#Q217), [Q218](#Q218), [Q219](#Q219), [Q221](#Q221), [Q234](#Q234)

### Performance and Scalability

[Q213](#Q213)

### Product / parameter cache

[Q039](#Q039), [Q086](#Q086), [Q097](#Q097), [Q098](#Q098), [Q108](#Q108), [Q141](#Q141), [Q142](#Q142), [Q143](#Q143), [Q144](#Q144), [Q154](#Q154), [Q170](#Q170), [Q195](#Q195), [Q212](#Q212), [Q215](#Q215), [Q228](#Q228), [Q263](#Q263), [Q284](#Q284)

### Project narrative / trade-offs

[Q239](#Q239), [Q240](#Q240), [Q243](#Q243), [Q245](#Q245), [Q247](#Q247), [Q248](#Q248), [Q249](#Q249), [Q280](#Q280), [Q287](#Q287), [Q291](#Q291), [Q292](#Q292), [Q293](#Q293), [Q297](#Q297)

### Redis idempotency store

[Q016](#Q016), [Q132](#Q132), [Q134](#Q134), [Q205](#Q205), [Q228](#Q228), [Q268](#Q268)

### Reporting

[Q068](#Q068), [Q133](#Q133), [Q155](#Q155), [Q158](#Q158), [Q159](#Q159), [Q171](#Q171), [Q172](#Q172), [Q173](#Q173), [Q174](#Q174), [Q175](#Q175), [Q176](#Q176), [Q194](#Q194), [Q209](#Q209), [Q226](#Q226), [Q231](#Q231)

### Repository strategy

[Q002](#Q002), [Q004](#Q004), [Q007](#Q007), [Q294](#Q294)

### SQL Server and H2

[Q111](#Q111), [Q112](#Q112), [Q113](#Q113)

### Scheduled jobs

[Q060](#Q060), [Q097](#Q097), [Q098](#Q098), [Q129](#Q129), [Q140](#Q140), [Q167](#Q167), [Q168](#Q168), [Q169](#Q169), [Q170](#Q170), [Q192](#Q192), [Q211](#Q211)

### Schema migration

[Q019](#Q019), [Q062](#Q062), [Q063](#Q063), [Q066](#Q066), [Q085](#Q085), [Q114](#Q114), [Q115](#Q115), [Q116](#Q116), [Q118](#Q118), [Q119](#Q119), [Q120](#Q120), [Q121](#Q121), [Q122](#Q122), [Q123](#Q123), [Q124](#Q124), [Q125](#Q125), [Q126](#Q126), [Q203](#Q203), [Q205](#Q205)

### Spring Boot 3.x

[Q017](#Q017), [Q018](#Q018), [Q021](#Q021), [Q022](#Q022), [Q026](#Q026)

### Test coverage

[Q157](#Q157), [Q158](#Q158), [Q159](#Q159), [Q160](#Q160)

### Testing (JUnit + Mockito + MockMvc)

[Q151](#Q151), [Q283](#Q283), [Q296](#Q296)

### Transactions

[Q127](#Q127), [Q131](#Q131)

---

## File Path Index

Each path links to its appendix entry. Question IDs link to the question heading.

- [`.github/workflows/ci.yml`](#file--github-workflows-ci-yml) — [Q005](#Q005), [Q007](#Q007), [Q008](#Q008), [Q088](#Q088), [Q158](#Q158), [Q160](#Q160), [Q189](#Q189), [Q191](#Q191), [Q193](#Q193), [Q194](#Q194), [Q195](#Q195), [Q196](#Q196), [Q197](#Q197), [Q243](#Q243)
- [`.github/workflows/markdown.yml`](#file--github-workflows-markdown-yml) — [Q197](#Q197), [Q289](#Q289)
- [`.github/workflows/terraform.yml`](#file--github-workflows-terraform-yml) — [Q197](#Q197), [Q198](#Q198), [Q295](#Q295)
- [`SP2/src/main/java/dao/DbConnection.java`](#file-SP2-src-main-java-dao-DbConnection-java) — [Q007](#Q007)
- [`infra/local/main.tf`](#file-infra-local-main-tf) — [Q008](#Q008), [Q198](#Q198), [Q199](#Q199), [Q241](#Q241), [Q295](#Q295)
- [`infra/local/outputs.tf`](#file-infra-local-outputs-tf) — [Q199](#Q199)
- [`infra/local/terraform.tfstate`](#file-infra-local-terraform-tfstate) — [Q199](#Q199)
- [`infra/local/variables.tf`](#file-infra-local-variables-tf) — [Q199](#Q199)
- [`sp2-springboot/.cursor/rules.md`](#file-sp2-springboot-cursor-rules-md) — [Q200](#Q200), [Q240](#Q240)
- [`sp2-springboot/.env.example`](#file-sp2-springboot-env-example) — [Q204](#Q204)
- [`sp2-springboot/README.md`](#file-sp2-springboot-README-md) — [Q001](#Q001)
- [`sp2-springboot/docker-compose.yml`](#file-sp2-springboot-docker-compose-yml) — [Q021](#Q021), [Q186](#Q186), [Q187](#Q187), [Q188](#Q188), [Q189](#Q189), [Q190](#Q190), [Q219](#Q219), [Q298](#Q298)
- [`sp2-springboot/docker/app/Dockerfile`](#file-sp2-springboot-docker-app-Dockerfile) — [Q021](#Q021), [Q158](#Q158), [Q183](#Q183), [Q184](#Q184), [Q185](#Q185), [Q285](#Q285)
- [`sp2-springboot/docker/sqlserver/init/01-init-database.sql`](#file-sp2-springboot-docker-sqlserver-init-01-init-database-sql) — [Q186](#Q186)
- [`sp2-springboot/docker/sqlserver/init/02-create-app-user.sql`](#file-sp2-springboot-docker-sqlserver-init-02-create-app-user-sql) — [Q186](#Q186)
- [`sp2-springboot/docs/README.md`](#file-sp2-springboot-docs-README-md) — [Q289](#Q289), [Q290](#Q290)
- [`sp2-springboot/docs/decisions/0001-use-clean-architecture.md`](#file-sp2-springboot-docs-decisions-0001-use-clean-architecture-md) — [Q002](#Q002), [Q006](#Q006), [Q015](#Q015), [Q027](#Q027), [Q032](#Q032), [Q040](#Q040), [Q104](#Q104), [Q143](#Q143), [Q225](#Q225), [Q240](#Q240), [Q243](#Q243), [Q244](#Q244), [Q279](#Q279), [Q299](#Q299)
- [`sp2-springboot/docs/decisions/0002-use-ddd.md`](#file-sp2-springboot-docs-decisions-0002-use-ddd-md) — [Q041](#Q041)
- [`sp2-springboot/docs/decisions/0004-use-github-actions.md`](#file-sp2-springboot-docs-decisions-0004-use-github-actions-md) — [Q194](#Q194), [Q239](#Q239)
- [`sp2-springboot/docs/decisions/0005-use-terraform-local.md`](#file-sp2-springboot-docs-decisions-0005-use-terraform-local-md) — [Q239](#Q239), [Q241](#Q241), [Q295](#Q295)
- [`sp2-springboot/docs/decisions/0006-session-over-jwt.md`](#file-sp2-springboot-docs-decisions-0006-session-over-jwt-md) — [Q025](#Q025), [Q081](#Q081), [Q085](#Q085), [Q087](#Q087), [Q239](#Q239)
- [`sp2-springboot/docs/decisions/0007-h2-vs-sqlserver.md`](#file-sp2-springboot-docs-decisions-0007-h2-vs-sqlserver-md) — [Q119](#Q119)
- [`sp2-springboot/docs/decisions/README.md`](#file-sp2-springboot-docs-decisions-README-md) — [Q006](#Q006)
- [`sp2-springboot/docs/design/00-sdd-overview.md`](#file-sp2-springboot-docs-design-00-sdd-overview-md) — [Q006](#Q006)
- [`sp2-springboot/docs/design/02-architecture-design.md`](#file-sp2-springboot-docs-design-02-architecture-design-md) — [Q006](#Q006), [Q027](#Q027), [Q033](#Q033)
- [`sp2-springboot/docs/design/07-security-design.md`](#file-sp2-springboot-docs-design-07-security-design-md) — [Q203](#Q203)
- [`sp2-springboot/docs/design/11-audit-logging.md`](#file-sp2-springboot-docs-design-11-audit-logging-md) — [Q201](#Q201), [Q220](#Q220)
- [`sp2-springboot/docs/design/13-scheduler-design.md`](#file-sp2-springboot-docs-design-13-scheduler-design-md) — [Q211](#Q211)
- [`sp2-springboot/docs/design/16-testing-strategy.md`](#file-sp2-springboot-docs-design-16-testing-strategy-md) — [Q213](#Q213), [Q224](#Q224), [Q245](#Q245), [Q296](#Q296)
- [`sp2-springboot/docs/design/19-cursor-implementation-roadmap.md`](#file-sp2-springboot-docs-design-19-cursor-implementation-roadmap-md) — [Q200](#Q200), [Q294](#Q294)
- [`sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md`](#file-sp2-springboot-docs-design-20-maintenance-and-future-enhancement-md) — [Q038](#Q038), [Q062](#Q062), [Q087](#Q087), [Q133](#Q133), [Q167](#Q167), [Q171](#Q171), [Q172](#Q172), [Q192](#Q192), [Q204](#Q204), [Q205](#Q205), [Q206](#Q206), [Q222](#Q222), [Q223](#Q223), [Q224](#Q224), [Q227](#Q227), [Q228](#Q228), [Q230](#Q230), [Q234](#Q234), [Q242](#Q242), [Q248](#Q248), [Q249](#Q249), [Q274](#Q274), [Q279](#Q279), [Q280](#Q280), [Q290](#Q290), [Q294](#Q294), [Q299](#Q299)
- [`sp2-springboot/docs/handbook/00-project-overview.md`](#file-sp2-springboot-docs-handbook-00-project-overview-md) — [Q001](#Q001), [Q002](#Q002), [Q003](#Q003), [Q004](#Q004), [Q006](#Q006), [Q099](#Q099), [Q249](#Q249), [Q287](#Q287), [Q289](#Q289), [Q290](#Q290), [Q292](#Q292), [Q293](#Q293)
- [`sp2-springboot/docs/handbook/01-repository-handbook.md`](#file-sp2-springboot-docs-handbook-01-repository-handbook-md) — [Q001](#Q001), [Q006](#Q006)
- [`sp2-springboot/docs/handbook/03-business-feature-handbook.md`](#file-sp2-springboot-docs-handbook-03-business-feature-handbook-md) — [Q004](#Q004)
- [`sp2-springboot/docs/handbook/06-system-design-handbook.md`](#file-sp2-springboot-docs-handbook-06-system-design-handbook-md) — [Q209](#Q209), [Q231](#Q231), [Q232](#Q232), [Q233](#Q233), [Q237](#Q237), [Q238](#Q238)
- [`sp2-springboot/docs/handbook/07-cheat-sheet.md`](#file-sp2-springboot-docs-handbook-07-cheat-sheet-md) — [Q279](#Q279)
- [`sp2-springboot/pom.xml`](#file-sp2-springboot-pom-xml) — [Q015](#Q015), [Q017](#Q017), [Q040](#Q040), [Q107](#Q107), [Q157](#Q157), [Q158](#Q158), [Q159](#Q159), [Q160](#Q160), [Q174](#Q174), [Q222](#Q222), [Q224](#Q224), [Q285](#Q285), [Q296](#Q296)
- [`sp2-springboot/src/main/java/com/tlbank/lending/TlbankLendingApplication.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-TlbankLendingApplication-java) — [Q018](#Q018)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-ApplicationAppService-java) — [Q030](#Q030), [Q031](#Q031), [Q037](#Q037), [Q094](#Q094), [Q096](#Q096), [Q100](#Q100), [Q106](#Q106), [Q127](#Q127), [Q131](#Q131), [Q151](#Q151), [Q227](#Q227), [Q252](#Q252), [Q256](#Q256), [Q274](#Q274), [Q281](#Q281), [Q286](#Q286), [Q288](#Q288)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationSummaryResponse.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-ApplicationSummaryResponse-java) — [Q276](#Q276)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/DocumentInfoResponse.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-DocumentInfoResponse-java) — [Q182](#Q182)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/MaskedApplicantResponse.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-MaskedApplicantResponse-java) — [Q096](#Q096)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/package-info.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-package-info-java) — [Q036](#Q036)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-cache-service-CacheManagementService-java) — [Q086](#Q086), [Q144](#Q144), [Q263](#Q263), [Q284](#Q284)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/AddressRequest.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-request-AddressRequest-java) — [Q069](#Q069)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/ApplicantRequest.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-request-ApplicantRequest-java) — [Q069](#Q069), [Q275](#Q275)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/CreateApplicationRequest.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-request-CreateApplicationRequest-java) — [Q035](#Q035), [Q069](#Q069)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/dto/response/DocumentUploadResponse.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-response-DocumentUploadResponse-java) — [Q276](#Q276)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/dto/response/LoginResponse.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-response-LoginResponse-java) — [Q035](#Q035)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-idempotency-IdempotencyService-java) — [Q009](#Q009), [Q011](#Q011), [Q035](#Q035), [Q063](#Q063), [Q067](#Q067), [Q132](#Q132), [Q269](#Q269)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/notification/service/NotificationServiceImpl.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-notification-service-NotificationServiceImpl-java) — [Q162](#Q162), [Q164](#Q164), [Q229](#Q229), [Q233](#Q233)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-otp-service-OtpAppService-java) — [Q046](#Q046), [Q103](#Q103), [Q135](#Q135), [Q137](#Q137), [Q138](#Q138), [Q139](#Q139), [Q150](#Q150), [Q164](#Q164), [Q204](#Q204), [Q205](#Q205), [Q251](#Q251), [Q272](#Q272), [Q277](#Q277)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/parameter/service/SystemParameterService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-parameter-service-SystemParameterService-java) — [Q098](#Q098), [Q141](#Q141), [Q143](#Q143), [Q180](#Q180)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/DailyStatisticsData.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-report-service-DailyStatisticsData-java) — [Q175](#Q175)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-report-service-ReportAppService-java) — [Q133](#Q133), [Q171](#Q171), [Q173](#Q173), [Q176](#Q176), [Q209](#Q209), [Q231](#Q231)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportFormat.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-report-service-ReportFormat-java) — [Q176](#Q176)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ApproveCaseCommand.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-review-service-ApproveCaseCommand-java) — [Q267](#Q267)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-review-service-ReviewAppService-java) — [Q104](#Q104), [Q109](#Q109), [Q110](#Q110), [Q130](#Q130), [Q148](#Q148), [Q209](#Q209), [Q214](#Q214)
- [`sp2-springboot/src/main/java/com/tlbank/lending/application/user/service/UserAppService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-application-user-service-UserAppService-java) — [Q206](#Q206)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAction.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditAction-java) — [Q277](#Q277)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditAspect-java) — [Q024](#Q024), [Q072](#Q072), [Q073](#Q073), [Q074](#Q074), [Q076](#Q076), [Q135](#Q135), [Q277](#Q277), [Q282](#Q282), [Q291](#Q291)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditContext.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditContext-java) — [Q135](#Q135), [Q264](#Q264)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditDetailBuilder.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditDetailBuilder-java) — [Q201](#Q201), [Q282](#Q282)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditIpResolver.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditIpResolver-java) — [Q076](#Q076)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLog.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditLog-java) — [Q117](#Q117), [Q220](#Q220)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditLogWriter-java) — [Q024](#Q024), [Q074](#Q074), [Q079](#Q079), [Q128](#Q128), [Q149](#Q149), [Q281](#Q281), [Q291](#Q291)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/audit/Auditable.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-Auditable-java) — [Q073](#Q073), [Q282](#Q282)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/config/AsyncConfig.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-AsyncConfig-java) — [Q018](#Q018), [Q024](#Q024)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/config/CommonConfig.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-CommonConfig-java) — [Q022](#Q022), [Q060](#Q060)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/config/JpaConfig.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-JpaConfig-java) — [Q089](#Q089)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulerConfig.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-SchedulerConfig-java) — [Q167](#Q167), [Q192](#Q192)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-SchedulingConfig-java) — [Q018](#Q018), [Q033](#Q033), [Q167](#Q167), [Q192](#Q192)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/config/StandardApiResponses.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-StandardApiResponses-java) — [Q064](#Q064)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/config/SwaggerConfig.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-SwaggerConfig-java) — [Q020](#Q020), [Q207](#Q207)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/entity/BaseEntity.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-entity-BaseEntity-java) — [Q089](#Q089)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/exception/BusinessException.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-exception-BusinessException-java) — [Q071](#Q071)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-exception-ErrorCode-java) — [Q013](#Q013), [Q033](#Q033), [Q265](#Q265), [Q300](#Q300)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/exception/WorkflowException.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-exception-WorkflowException-java) — [Q071](#Q071)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-response-ApiResponse-java) — [Q061](#Q061), [Q300](#Q300)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/response/FieldErrorDetail.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-response-FieldErrorDetail-java) — [Q061](#Q061), [Q075](#Q075)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/response/PageResponse.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-response-PageResponse-java) — [Q148](#Q148)
- [`sp2-springboot/src/main/java/com/tlbank/lending/common/util/MaskingUtil.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-common-util-MaskingUtil-java) — [Q096](#Q096)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Address.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-Address-java) — [Q253](#Q253)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Applicant.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-Applicant-java) — [Q048](#Q048), [Q254](#Q254), [Q275](#Q275)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-Application-java) — [Q014](#Q014), [Q015](#Q015), [Q027](#Q027), [Q029](#Q029), [Q034](#Q034), [Q037](#Q037), [Q041](#Q041), [Q042](#Q042), [Q045](#Q045), [Q049](#Q049), [Q051](#Q051), [Q053](#Q053), [Q054](#Q054), [Q055](#Q055), [Q056](#Q056), [Q057](#Q057), [Q058](#Q058), [Q131](#Q131), [Q146](#Q146), [Q210](#Q210), [Q236](#Q236)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-ApplicationId-java) — [Q043](#Q043), [Q091](#Q091), [Q247](#Q247), [Q251](#Q251), [Q256](#Q256)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-ApplicationStatus-java) — [Q009](#Q009), [Q012](#Q012), [Q014](#Q014), [Q042](#Q042), [Q051](#Q051), [Q052](#Q052), [Q055](#Q055), [Q059](#Q059), [Q114](#Q114), [Q145](#Q145), [Q243](#Q243), [Q246](#Q246)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/CardProductId.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-CardProductId-java) — [Q043](#Q043)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentInfo.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-DocumentInfo-java) — [Q050](#Q050), [Q255](#Q255)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentType.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-DocumentType-java) — [Q050](#Q050), [Q179](#Q179)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Email.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-Email-java) — [Q009](#Q009), [Q010](#Q010), [Q254](#Q254)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/MobileNumber.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-MobileNumber-java) — [Q009](#Q009), [Q010](#Q010), [Q252](#Q252), [Q254](#Q254)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/WorkflowHistory.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-WorkflowHistory-java) — [Q034](#Q034), [Q058](#Q058), [Q257](#Q257)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-repository-ApplicationRepository-java) — [Q028](#Q028), [Q101](#Q101), [Q110](#Q110)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/package-info.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-repository-package-info-java) — [Q036](#Q036)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-event-ApplicationApprovedEvent-java) — [Q038](#Q038), [Q044](#Q044), [Q047](#Q047), [Q109](#Q109), [Q130](#Q130)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationCancelledEvent.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-event-ApplicationCancelledEvent-java) — [Q223](#Q223), [Q274](#Q274)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationSubmittedEvent.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-event-ApplicationSubmittedEvent-java) — [Q044](#Q044), [Q047](#Q047)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/event/OtpGeneratedEvent.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-event-OtpGeneratedEvent-java) — [Q047](#Q047), [Q223](#Q223)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpPurpose.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-otp-OtpPurpose-java) — [Q235](#Q235), [Q266](#Q266)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-otp-OtpRecord-java) — [Q045](#Q045), [Q046](#Q046), [Q060](#Q060), [Q136](#Q136), [Q138](#Q138), [Q140](#Q140), [Q235](#Q235), [Q266](#Q266)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/VerifyResult.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-otp-VerifyResult-java) — [Q046](#Q046), [Q136](#Q136)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/product/CardProduct.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-product-CardProduct-java) — [Q045](#Q045), [Q262](#Q262)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/product/ProductFeature.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-product-ProductFeature-java) — [Q262](#Q262)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/product/repository/CardProductRepository.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-product-repository-CardProductRepository-java) — [Q039](#Q039)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-ReviewCase-java) — [Q041](#Q041), [Q045](#Q045), [Q049](#Q049), [Q145](#Q145), [Q146](#Q146), [Q147](#Q147), [Q232](#Q232), [Q273](#Q273)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCaseId.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-ReviewCaseId-java) — [Q043](#Q043)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCaseSearchCriteria.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-ReviewCaseSearchCriteria-java) — [Q105](#Q105)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewRemark.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-ReviewRemark-java) — [Q147](#Q147)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/review/repository/ReviewCaseRepository.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-repository-ReviewCaseRepository-java) — [Q104](#Q104), [Q105](#Q105), [Q110](#Q110)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-service-WorkflowDomainService-java) — [Q030](#Q030), [Q032](#Q032), [Q225](#Q225)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/user/Role.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-user-Role-java) — [Q270](#Q270)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/user/User.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-user-User-java) — [Q045](#Q045), [Q270](#Q270), [Q271](#Q271)
- [`sp2-springboot/src/main/java/com/tlbank/lending/domain/user/UserId.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-domain-user-UserId-java) — [Q260](#Q260)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CacheKeys.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-cache-CacheKeys-java) — [Q141](#Q141), [Q212](#Q212)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CacheStore.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-cache-CacheStore-java) — [Q143](#Q143), [Q144](#Q144), [Q228](#Q228)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-cache-CachedCardProductRepository-java) — [Q039](#Q039), [Q086](#Q086), [Q108](#Q108), [Q212](#Q212), [Q284](#Q284)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-cache-InMemoryCacheStore-java) — [Q086](#Q086), [Q097](#Q097), [Q141](#Q141), [Q142](#Q142), [Q212](#Q212), [Q215](#Q215), [Q228](#Q228)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-event-NotificationEventHandler-java) — [Q002](#Q002), [Q038](#Q038), [Q044](#Q044), [Q099](#Q099), [Q100](#Q100), [Q109](#Q109), [Q130](#Q130), [Q233](#Q233)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-event-ReviewEventHandler-java) — [Q002](#Q002), [Q038](#Q038), [Q044](#Q044), [Q049](#Q049), [Q100](#Q100), [Q227](#Q227), [Q238](#Q238)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/IdempotencyEntry.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-idempotency-IdempotencyEntry-java) — [Q016](#Q016)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/IdempotencyStore.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-idempotency-IdempotencyStore-java) — [Q028](#Q028)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/InMemoryIdempotencyStore.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-idempotency-InMemoryIdempotencyStore-java) — [Q134](#Q134)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-idempotency-RedisIdempotencyStore-java) — [Q016](#Q016), [Q028](#Q028), [Q132](#Q132), [Q134](#Q134), [Q229](#Q229), [Q268](#Q268)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/EmailSender.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-EmailSender-java) — [Q165](#Q165)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockEmailSender.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-MockEmailSender-java) — [Q008](#Q008), [Q161](#Q161)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-MockSmsSender-java) — [Q008](#Q008), [Q161](#Q161), [Q165](#Q165)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/NotificationTemplate.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-NotificationTemplate-java) — [Q163](#Q163), [Q166](#Q166)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/SmsSender.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-SmsSender-java) — [Q165](#Q165)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicantEmbeddable.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicantEmbeddable-java) — [Q048](#Q048)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationDocumentEntity.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicationDocumentEntity-java) — [Q050](#Q050), [Q181](#Q181)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicationEntity-java) — [Q029](#Q029), [Q089](#Q089), [Q090](#Q090), [Q091](#Q091), [Q092](#Q092), [Q127](#Q127), [Q259](#Q259)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicationJpaRepository-java) — [Q068](#Q068), [Q091](#Q091), [Q095](#Q095), [Q102](#Q102)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicationRepositoryImpl-java) — [Q027](#Q027), [Q028](#Q028), [Q029](#Q029), [Q034](#Q034), [Q092](#Q092), [Q101](#Q101), [Q102](#Q102), [Q107](#Q107), [Q210](#Q210), [Q258](#Q258), [Q297](#Q297)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/WorkflowHistoryEntity.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-WorkflowHistoryEntity-java) — [Q034](#Q034), [Q257](#Q257), [Q258](#Q258)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-otp-OtpJpaRepository-java) — [Q093](#Q093), [Q103](#Q103), [Q129](#Q129), [Q272](#Q272)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpRepositoryImpl.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-otp-OtpRepositoryImpl-java) — [Q107](#Q107), [Q137](#Q137), [Q138](#Q138)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/product/CardProductRepositoryImpl.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-product-CardProductRepositoryImpl-java) — [Q039](#Q039), [Q108](#Q108)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-user-UserEntity-java) — [Q259](#Q259), [Q260](#Q260), [Q270](#Q270), [Q271](#Q271)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-report-ExcelReportGenerator-java) — [Q133](#Q133), [Q155](#Q155), [Q171](#Q171), [Q172](#Q172), [Q226](#Q226)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/PdfReportGenerator.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-report-PdfReportGenerator-java) — [Q133](#Q133), [Q172](#Q172), [Q174](#Q174), [Q226](#Q226)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/CacheRefreshScheduler.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-scheduler-CacheRefreshScheduler-java) — [Q098](#Q098), [Q170](#Q170)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/DailyStatisticsScheduler.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-scheduler-DailyStatisticsScheduler-java) — [Q169](#Q169), [Q170](#Q170), [Q231](#Q231)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-scheduler-OtpCleanupScheduler-java) — [Q022](#Q022), [Q060](#Q060), [Q093](#Q093), [Q129](#Q129), [Q140](#Q140), [Q168](#Q168), [Q170](#Q170)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/DocumentStorageService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-storage-DocumentStorageService-java) — [Q106](#Q106)
- [`sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-storage-LocalDocumentStorageService-java) — [Q008](#Q008), [Q053](#Q053), [Q106](#Q106), [Q177](#Q177), [Q178](#Q178), [Q179](#Q179), [Q180](#Q180), [Q181](#Q181), [Q182](#Q182), [Q208](#Q208), [Q216](#Q216), [Q255](#Q255), [Q298](#Q298)
- [`sp2-springboot/src/main/java/com/tlbank/lending/package-info.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-package-info-java) — [Q036](#Q036)
- [`sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-api-advice-GlobalExceptionHandler-java) — [Q009](#Q009), [Q013](#Q013), [Q031](#Q031), [Q070](#Q070), [Q071](#Q071), [Q072](#Q072), [Q075](#Q075), [Q265](#Q265), [Q300](#Q300)
- [`sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-api-v1-ApplicationApiController-java) — [Q031](#Q031), [Q037](#Q037), [Q062](#Q062), [Q063](#Q063), [Q064](#Q064), [Q065](#Q065), [Q066](#Q066), [Q067](#Q067), [Q177](#Q177), [Q278](#Q278), [Q286](#Q286)
- [`sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ReviewApiController.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-api-v1-ReviewApiController-java) — [Q267](#Q267)
- [`sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/SchedulerApiController.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-api-v1-SchedulerApiController-java) — [Q169](#Q169)
- [`sp2-springboot/src/main/java/com/tlbank/lending/presentation/web/ApplicationWebController.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-web-ApplicationWebController-java) — [Q278](#Q278)
- [`sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-security-config-SecurityConfig-java) — [Q017](#Q017), [Q020](#Q020), [Q023](#Q023), [Q025](#Q025), [Q033](#Q033), [Q070](#Q070), [Q077](#Q077), [Q081](#Q081), [Q083](#Q083), [Q084](#Q084), [Q085](#Q085), [Q087](#Q087), [Q112](#Q112), [Q203](#Q203), [Q207](#Q207), [Q208](#Q208), [Q250](#Q250)
- [`sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-security-filter-MdcLoggingFilter-java) — [Q080](#Q080), [Q218](#Q218), [Q234](#Q234), [Q237](#Q237)
- [`sp2-springboot/src/main/java/com/tlbank/lending/security/handler/CustomAccessDeniedHandler.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-security-handler-CustomAccessDeniedHandler-java) — [Q070](#Q070)
- [`sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginFailureHandler.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-security-handler-LoginFailureHandler-java) — [Q078](#Q078), [Q202](#Q202)
- [`sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-security-handler-LoginSuccessHandler-java) — [Q076](#Q076), [Q079](#Q079), [Q082](#Q082), [Q088](#Q088), [Q261](#Q261), [Q271](#Q271)
- [`sp2-springboot/src/main/java/com/tlbank/lending/security/handler/SessionExpiredStrategy.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-security-handler-SessionExpiredStrategy-java) — [Q084](#Q084)
- [`sp2-springboot/src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-security-service-UserDetailsServiceImpl-java) — [Q078](#Q078), [Q083](#Q083), [Q094](#Q094)
- [`sp2-springboot/src/main/java/com/tlbank/lending/security/util/LoginResponseMode.java`](#file-sp2-springboot-src-main-java-com-tlbank-lending-security-util-LoginResponseMode-java) — [Q082](#Q082), [Q261](#Q261)
- [`sp2-springboot/src/main/resources/application-dev.yml`](#file-sp2-springboot-src-main-resources-application-dev-yml) — [Q003](#Q003), [Q111](#Q111), [Q112](#Q112), [Q113](#Q113), [Q119](#Q119), [Q121](#Q121), [Q168](#Q168), [Q268](#Q268), [Q298](#Q298)
- [`sp2-springboot/src/main/resources/application-prod.yml`](#file-sp2-springboot-src-main-resources-application-prod-yml) — [Q003](#Q003), [Q008](#Q008)
- [`sp2-springboot/src/main/resources/application-staging.yml`](#file-sp2-springboot-src-main-resources-application-staging-yml) — [Q003](#Q003), [Q119](#Q119)
- [`sp2-springboot/src/main/resources/application.yml`](#file-sp2-springboot-src-main-resources-application-yml) — [Q019](#Q019), [Q020](#Q020), [Q021](#Q021), [Q121](#Q121), [Q123](#Q123), [Q211](#Q211), [Q219](#Q219), [Q298](#Q298)
- [`sp2-springboot/src/main/resources/db/dev-seed/V100__seed_test_data.sql`](#file-sp2-springboot-src-main-resources-db-dev-seed-V100__seed_test_data-sql) — [Q122](#Q122)
- [`sp2-springboot/src/main/resources/db/dev-seed/V101__add_user_136628.sql`](#file-sp2-springboot-src-main-resources-db-dev-seed-V101__add_user_136628-sql) — [Q122](#Q122)
- [`sp2-springboot/src/main/resources/db/migration-sqlserver/V1__create_users.sql`](#file-sp2-springboot-src-main-resources-db-migration-sqlserver-V1__create_users-sql) — [Q111](#Q111), [Q115](#Q115), [Q116](#Q116)
- [`sp2-springboot/src/main/resources/db/migration/V10__extend_system_parameters.sql`](#file-sp2-springboot-src-main-resources-db-migration-V10__extend_system_parameters-sql) — [Q120](#Q120)
- [`sp2-springboot/src/main/resources/db/migration/V14__reshape_audit_logs_for_sprint9.sql`](#file-sp2-springboot-src-main-resources-db-migration-V14__reshape_audit_logs_for_sprint9-sql) — [Q117](#Q117), [Q118](#Q118), [Q126](#Q126)
- [`sp2-springboot/src/main/resources/db/migration/V1__create_users.sql`](#file-sp2-springboot-src-main-resources-db-migration-V1__create_users-sql) — [Q019](#Q019), [Q111](#Q111), [Q115](#Q115), [Q116](#Q116), [Q121](#Q121), [Q123](#Q123)
- [`sp2-springboot/src/main/resources/db/migration/V3__create_applications.sql`](#file-sp2-springboot-src-main-resources-db-migration-V3__create_applications-sql) — [Q114](#Q114), [Q257](#Q257)
- [`sp2-springboot/src/main/resources/db/migration/V5__create_review_cases.sql`](#file-sp2-springboot-src-main-resources-db-migration-V5__create_review_cases-sql) — [Q147](#Q147)
- [`sp2-springboot/src/main/resources/db/migration/V6__create_audit_logs.sql`](#file-sp2-springboot-src-main-resources-db-migration-V6__create_audit_logs-sql) — [Q126](#Q126)
- [`sp2-springboot/src/main/resources/db/migration/V8__add_user_last_login.sql`](#file-sp2-springboot-src-main-resources-db-migration-V8__add_user_last_login-sql) — [Q124](#Q124)
- [`sp2-springboot/src/main/resources/db/migration/V9__add_user_business_id.sql`](#file-sp2-springboot-src-main-resources-db-migration-V9__add_user_business_id-sql) — [Q124](#Q124)
- [`sp2-springboot/src/main/resources/logback-spring.xml`](#file-sp2-springboot-src-main-resources-logback-spring-xml) — [Q080](#Q080), [Q217](#Q217), [Q218](#Q218), [Q221](#Q221), [Q234](#Q234)
- [`sp2-springboot/src/test/java/com/tlbank/lending/TlbankLendingApplicationTests.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-TlbankLendingApplicationTests-java) — [Q283](#Q283)
- [`sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-application-ApplicationFlowIntegrationTest-java) — [Q026](#Q026), [Q125](#Q125), [Q150](#Q150), [Q156](#Q156), [Q213](#Q213)
- [`sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-application-ApplicationIdempotencyIntegrationTest-java) — [Q011](#Q011)
- [`sp2-springboot/src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-application-application-ApplicationAppServiceTest-java) — [Q101](#Q101), [Q151](#Q151)
- [`sp2-springboot/src/test/java/com/tlbank/lending/application/notification/NotificationServiceImplTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-application-notification-NotificationServiceImplTest-java) — [Q162](#Q162)
- [`sp2-springboot/src/test/java/com/tlbank/lending/application/otp/OtpAppServiceTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-application-otp-OtpAppServiceTest-java) — [Q152](#Q152)
- [`sp2-springboot/src/test/java/com/tlbank/lending/application/parameter/SystemParameterServiceCacheTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-application-parameter-SystemParameterServiceCacheTest-java) — [Q154](#Q154)
- [`sp2-springboot/src/test/java/com/tlbank/lending/application/parameter/SystemParameterServiceTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-application-parameter-SystemParameterServiceTest-java) — [Q154](#Q154)
- [`sp2-springboot/src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-common-audit-AuditAspectTest-java) — [Q149](#Q149), [Q291](#Q291)
- [`sp2-springboot/src/test/java/com/tlbank/lending/domain/application/AddressTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-domain-application-AddressTest-java) — [Q253](#Q253)
- [`sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationStatusTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-domain-application-ApplicationStatusTest-java) — [Q246](#Q246), [Q273](#Q273), [Q299](#Q299)
- [`sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-domain-application-ApplicationTest-java) — [Q056](#Q056), [Q240](#Q240), [Q244](#Q244), [Q245](#Q245)
- [`sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStoreTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-infrastructure-cache-InMemoryCacheStoreTest-java) — [Q142](#Q142)
- [`sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/report/ExcelReportGeneratorTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-infrastructure-report-ExcelReportGeneratorTest-java) — [Q155](#Q155), [Q175](#Q175)
- [`sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageServiceTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-infrastructure-storage-LocalDocumentStorageServiceTest-java) — [Q153](#Q153)
- [`sp2-springboot/src/test/java/com/tlbank/lending/presentation/api/v1/ReviewApiControllerTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-presentation-api-v1-ReviewApiControllerTest-java) — [Q026](#Q026), [Q156](#Q156)
- [`sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java`](#file-sp2-springboot-src-test-java-com-tlbank-lending-security-SecurityIntegrationTest-java) — [Q026](#Q026), [Q078](#Q078), [Q250](#Q250)
- [`sp2-springboot/src/test/resources/application-dev.yml`](#file-sp2-springboot-src-test-resources-application-dev-yml) — [Q011](#Q011), [Q125](#Q125)

---

## Difficulty Index

### Basic (41)

[Q001](#Q001), [Q003](#Q003), [Q004](#Q004), [Q006](#Q006), [Q018](#Q018), [Q020](#Q020), [Q021](#Q021), [Q036](#Q036), [Q065](#Q065), [Q117](#Q117), [Q121](#Q121), [Q122](#Q122), [Q124](#Q124), [Q133](#Q133), [Q139](#Q139), [Q141](#Q141), [Q154](#Q154), [Q159](#Q159), [Q163](#Q163), [Q168](#Q168), [Q175](#Q175), [Q176](#Q176), [Q192](#Q192), [Q197](#Q197), [Q198](#Q198), [Q241](#Q241), [Q243](#Q243), [Q244](#Q244), [Q245](#Q245), [Q247](#Q247), [Q253](#Q253), [Q265](#Q265), [Q266](#Q266), [Q279](#Q279), [Q283](#Q283), [Q287](#Q287), [Q289](#Q289), [Q292](#Q292), [Q293](#Q293), [Q294](#Q294), [Q295](#Q295)

### Intermediate (159)

[Q002](#Q002), [Q005](#Q005), [Q007](#Q007), [Q009](#Q009), [Q010](#Q010), [Q011](#Q011), [Q012](#Q012), [Q013](#Q013), [Q014](#Q014), [Q015](#Q015), [Q017](#Q017), [Q019](#Q019), [Q022](#Q022), [Q023](#Q023), [Q025](#Q025), [Q026](#Q026), [Q027](#Q027), [Q028](#Q028), [Q031](#Q031), [Q033](#Q033), [Q035](#Q035), [Q041](#Q041), [Q042](#Q042), [Q043](#Q043), [Q045](#Q045), [Q048](#Q048), [Q051](#Q051), [Q052](#Q052), [Q054](#Q054), [Q056](#Q056), [Q057](#Q057), [Q058](#Q058), [Q061](#Q061), [Q062](#Q062), [Q063](#Q063), [Q064](#Q064), [Q066](#Q066), [Q067](#Q067), [Q069](#Q069), [Q071](#Q071), [Q074](#Q074), [Q075](#Q075), [Q077](#Q077), [Q080](#Q080), [Q082](#Q082), [Q083](#Q083), [Q084](#Q084), [Q086](#Q086), [Q089](#Q089), [Q090](#Q090), [Q091](#Q091), [Q093](#Q093), [Q095](#Q095), [Q096](#Q096), [Q099](#Q099), [Q101](#Q101), [Q103](#Q103), [Q105](#Q105), [Q107](#Q107), [Q109](#Q109), [Q111](#Q111), [Q112](#Q112), [Q113](#Q113), [Q115](#Q115), [Q116](#Q116), [Q118](#Q118), [Q119](#Q119), [Q123](#Q123), [Q125](#Q125), [Q129](#Q129), [Q131](#Q131), [Q134](#Q134), [Q136](#Q136), [Q138](#Q138), [Q140](#Q140), [Q144](#Q144), [Q145](#Q145), [Q146](#Q146), [Q147](#Q147), [Q148](#Q148), [Q151](#Q151), [Q152](#Q152), [Q153](#Q153), [Q155](#Q155), [Q156](#Q156), [Q157](#Q157), [Q158](#Q158), [Q160](#Q160), [Q161](#Q161), [Q164](#Q164), [Q165](#Q165), [Q166](#Q166), [Q167](#Q167), [Q169](#Q169), [Q172](#Q172), [Q173](#Q173), [Q177](#Q177), [Q178](#Q178), [Q181](#Q181), [Q182](#Q182), [Q183](#Q183), [Q184](#Q184), [Q186](#Q186), [Q187](#Q187), [Q188](#Q188), [Q190](#Q190), [Q191](#Q191), [Q194](#Q194), [Q195](#Q195), [Q199](#Q199), [Q204](#Q204), [Q206](#Q206), [Q207](#Q207), [Q212](#Q212), [Q213](#Q213), [Q215](#Q215), [Q216](#Q216), [Q217](#Q217), [Q218](#Q218), [Q219](#Q219), [Q220](#Q220), [Q221](#Q221), [Q222](#Q222), [Q223](#Q223), [Q225](#Q225), [Q226](#Q226), [Q230](#Q230), [Q232](#Q232), [Q234](#Q234), [Q238](#Q238), [Q239](#Q239), [Q240](#Q240), [Q242](#Q242), [Q248](#Q248), [Q249](#Q249), [Q250](#Q250), [Q251](#Q251), [Q252](#Q252), [Q254](#Q254), [Q256](#Q256), [Q257](#Q257), [Q259](#Q259), [Q261](#Q261), [Q262](#Q262), [Q263](#Q263), [Q264](#Q264), [Q267](#Q267), [Q268](#Q268), [Q274](#Q274), [Q276](#Q276), [Q278](#Q278), [Q280](#Q280), [Q282](#Q282), [Q285](#Q285), [Q288](#Q288), [Q290](#Q290), [Q296](#Q296), [Q298](#Q298), [Q300](#Q300)

### Advanced (100)

[Q008](#Q008), [Q016](#Q016), [Q024](#Q024), [Q029](#Q029), [Q030](#Q030), [Q032](#Q032), [Q034](#Q034), [Q037](#Q037), [Q038](#Q038), [Q039](#Q039), [Q040](#Q040), [Q044](#Q044), [Q046](#Q046), [Q047](#Q047), [Q049](#Q049), [Q050](#Q050), [Q053](#Q053), [Q055](#Q055), [Q059](#Q059), [Q060](#Q060), [Q068](#Q068), [Q070](#Q070), [Q072](#Q072), [Q073](#Q073), [Q076](#Q076), [Q078](#Q078), [Q079](#Q079), [Q081](#Q081), [Q085](#Q085), [Q087](#Q087), [Q088](#Q088), [Q092](#Q092), [Q094](#Q094), [Q097](#Q097), [Q098](#Q098), [Q100](#Q100), [Q102](#Q102), [Q104](#Q104), [Q106](#Q106), [Q108](#Q108), [Q110](#Q110), [Q114](#Q114), [Q120](#Q120), [Q126](#Q126), [Q127](#Q127), [Q128](#Q128), [Q130](#Q130), [Q132](#Q132), [Q135](#Q135), [Q137](#Q137), [Q142](#Q142), [Q143](#Q143), [Q149](#Q149), [Q150](#Q150), [Q162](#Q162), [Q170](#Q170), [Q171](#Q171), [Q174](#Q174), [Q179](#Q179), [Q180](#Q180), [Q185](#Q185), [Q189](#Q189), [Q193](#Q193), [Q196](#Q196), [Q200](#Q200), [Q201](#Q201), [Q202](#Q202), [Q203](#Q203), [Q205](#Q205), [Q208](#Q208), [Q209](#Q209), [Q210](#Q210), [Q211](#Q211), [Q214](#Q214), [Q224](#Q224), [Q227](#Q227), [Q228](#Q228), [Q229](#Q229), [Q231](#Q231), [Q233](#Q233), [Q235](#Q235), [Q236](#Q236), [Q237](#Q237), [Q246](#Q246), [Q255](#Q255), [Q258](#Q258), [Q260](#Q260), [Q269](#Q269), [Q270](#Q270), [Q271](#Q271), [Q272](#Q272), [Q273](#Q273), [Q275](#Q275), [Q277](#Q277), [Q281](#Q281), [Q284](#Q284), [Q286](#Q286), [Q291](#Q291), [Q297](#Q297), [Q299](#Q299)

---

## Questions Q001–Q300

Validated question bodies follow. Numbering, categories, difficulty, and technical content are unchanged from the source map validation pass.

---
<a id="Q001"></a>

## Q001 — Why is the TLBank backend kept in one monorepo alongside the Terraform configuration, CI pipeline, and a legacy Servlet project?

### 中文筆記

單一 repository 的目的是讓不同技術同時共存，可以觀察跨技術整合時產生的依賴與取捨。Redis 冪等、CI/CD pipeline、Terraform IaC 若分散在獨立 repo，彼此間的版本對齊與 trade-off 就難以在單一敘事中討論。SP2（舊 Servlet 專案）保留在同一個 repo 則呈現了學習歷程，從 Servlet JSP 到 Clean Architecture Spring Boot 的演進可以直接對比。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/handbook/00-project-overview.md
- sp2-springboot/docs/handbook/01-repository-handbook.md
- sp2-springboot/README.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Checks whether the repository layout was intentional or accidental; tests the candidate's ability to articulate portfolio scope versus production design.

### Possible follow-up questions

- When would you split this into separate repositories?
- What problems arise if Terraform, CI, and application code share the same commit history?
- Does keeping a legacy Servlet project in the same repo create any CI risk?

### Verification status

- Documentation-only


---

<a id="Q002"></a>

## Q002 — The project is described as a "modular monolith." What does that mean, and why was it chosen over microservices?

### 中文筆記

Modular monolith 指單一可部署 JAR（tlbank-lending），但內部以 Clean Architecture 的 package boundary 分層，各層之間透過 port 介面溝通，不直接跨層呼叫實作類別。選擇 monolith 的理由：單人 portfolio 規模不需要跨網路的服務呼叫；微服務的觀察性（分散式追蹤、Service Mesh）超出目前範疇；事件處理器（ReviewEventHandler、NotificationEventHandler）已是未來拆分的自然接縫。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/handbook/00-project-overview.md
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java

### Verified classes and methods

- ReviewEventHandler
- NotificationEventHandler

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Tests understanding of architectural granularity decisions; separates engineers who can articulate trade-offs from those who repeat buzzwords.

### Possible follow-up questions

- Where would you draw the microservice boundary if the team grew to five engineers?
- What would you extract first — OTP, review, or notifications?
- How would the event bus change from in-process to a broker?

### Verification status

- Verified


---

<a id="Q003"></a>

## Q003 — TLBank uses three environment profiles: dev, staging, and prod. What is different in each, and why is prod never actually deployed?

### 中文筆記

dev profile：H2 in-memory 資料庫、db/migration Flyway 路徑、db/dev-seed 種子資料、Redis 可選（localhost:6379）。staging profile：SQL Server 2022 Docker 容器、db/migration-sqlserver 路徑、Docker Compose 部署在本機 Mac。prod profile：config 存在（application-prod.yml），但 repository 沒有對應的雲端基礎設施，CD pipeline 只部署到 local staging，不對外。prod 未部署是因為此 repository 是 portfolio 性質，不做實際雲端費用的承諾。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/resources/application-dev.yml
- sp2-springboot/src/main/resources/application-staging.yml
- sp2-springboot/src/main/resources/application-prod.yml
- sp2-springboot/docs/handbook/00-project-overview.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Checks environment management knowledge; also verifies that the candidate does not overclaim a "production deployment" that does not exist.

### Possible follow-up questions

- How does Flyway know which migration folder to use per profile?
- What would you need to add before this system could go to a real cloud prod?
- Why keep a prod config at all if it is never deployed?

### Verification status

- Documentation-only


---

<a id="Q004"></a>

## Q004 — What business problem does TLBank solve, and why is credit card application intake a useful domain for demonstrating backend engineering?

### 中文筆記

業務問題：數位銀行需要線上信用卡申請受理、身份驗證（OTP）、文件上傳、審核工作流程與通知。這個 domain 適合展示後端工程的原因：State machine（申請狀態轉換）要求工作流程完整性；重複送件問題需要 idempotency 設計；SMS/Email 失敗不應回滾核心交易，要求 side-effect isolation；不同角色（申請人、審核員、管理員）需要獨立的存取控制路徑。業務邏輯單純，工程複雜度則可充分展現。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/handbook/00-project-overview.md
- sp2-springboot/docs/handbook/03-business-feature-handbook.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Assesses whether the candidate can connect business requirements to technical design choices; weak candidates describe features but cannot explain why they required specific engineering.

### Possible follow-up questions

- What would break if idempotency were removed?
- Why is side-effect isolation relevant for notifications in this domain?
- How does RBAC map to the three roles in this system?

### Verification status

- Documentation-only


---

<a id="Q005"></a>

## Q005 — The repository has no automatic promotion to staging — deployment requires workflow_dispatch. Why is that design intentional?

### 中文筆記

Staging 環境是本機 Mac（self-hosted runner），不是雲端常駐服務，不能由 Push 自動觸發，否則每次 push 到 main 都會嘗試部署到可能不在線上的 Mac。workflow_dispatch 讓部署時機完全由人工決定，避免自動觸發打斷 local Docker Compose 運行中的狀態。另外，staging 部署步驟需要 MSSQL_SA_PASSWORD、GHCR_USERNAME、GHCR_TOKEN 等 secret，手動觸發可以確保有人知道環境就緒再部署。正確描述：CI 是自動的（push/PR 觸發），CD 是手動的（workflow_dispatch）。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Intermediate

### Verified source files

- .github/workflows/ci.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests awareness that not all CD pipelines are push-to-deploy; also checks honesty about staging environment constraints.

### Possible follow-up questions

- What would need to change to make deployment automatic to a cloud environment?
- How would you add a rollback mechanism to the current deploy step?
- Why does deploy-staging have if: github.event_name == 'workflow_dispatch' as a guard?

### Verification status

- Documentation-only


---

<a id="Q006"></a>

## Q006 — Where is the formal documentation for this project, and what types of documents exist?

### 中文筆記

文件分三種結構：ADR（Architecture Decision Records）位於 docs/decisions/，記錄每個設計決策的 context、alternatives 與 trade-offs（共 7 份：0001–0007）。SDD（Software Design Documents）位於 docs/design/，涵蓋架構設計、API 規格、資安設計、部署設計等 21 份。Handbook 位於 docs/handbook/，以工程師視角整理面試準備、技術深度、feature → file mapping 等（共 9 份）。Onboarding 文件位於 docs/onboarding/。這個分層設計讓不同深度的讀者可以按需取用。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/handbook/00-project-overview.md
- sp2-springboot/docs/decisions/README.md
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md
- sp2-springboot/docs/design/00-sdd-overview.md
- sp2-springboot/docs/design/02-architecture-design.md
- sp2-springboot/docs/handbook/01-repository-handbook.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Checks whether engineers document decisions alongside code; tests familiarity with ADR practice specifically.

### Possible follow-up questions

- What is the difference between an ADR and an SDD chapter?
- When would you add a new ADR to this project?
- What is missing from the current documentation set?

### Verification status

- Documentation-only


---

<a id="Q007"></a>

## Q007 — The SP2 Servlet/JSP project exists in the same repository as the Spring Boot backend. What risk does this create in CI, and how is it mitigated?

### 中文筆記

風險：若 CI workflow 掃描整個 repository root，SP2 的舊 Servlet 程式碼（無 Maven wrapper、無 Spring Boot pom.xml）可能干擾 build。ci.yml 透過 paths filter 將觸發條件限制在 sp2-springboot/** 與 .github/workflows/ci.yml，確保 SP2 目錄的異動不會觸發 CI pipeline。Maven 的 working-directory: ${{ env.APP_DIR }}（APP_DIR = sp2-springboot）也確保 build 只在 Spring Boot 子目錄執行，完全隔離舊 Servlet 專案。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Intermediate

### Verified source files

- .github/workflows/ci.yml
- SP2/src/main/java/dao/DbConnection.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests understanding of monorepo path filtering in GitHub Actions; checks whether candidates understand blast radius in CI pipelines.

### Possible follow-up questions

- What would happen if someone accidentally edited a file in SP2/ and pushed to main?
- How would you enforce that no one merges PRs that touch SP2/ without review?
- How does APP_DIR as a global env variable reduce maintenance cost?

### Verification status

- Documentation-only


---

<a id="Q008"></a>

## Q008 — The project overview states "no cloud production in this repository." What would be the minimum changes needed to deploy this system to a real cloud environment?

### 中文筆記

最低改動清單：Terraform 需要加入 AWS（或 GCP/Azure）provider，建立 ECS/EC2、RDS、ElastiCache，替換 local provider 的 hashicorp/local。Spring Session 要外部化到 Redis，否則多實例無法共享 session。application-prod.yml 的 datasource、Redis 連線資訊需要改為環境變數或 secrets manager 注入。LocalDocumentStorageService 需要換成 S3 或對應的 object storage 實作。Mock SMS/Email sender 需要換成真實 third-party service（Twilio、SendGrid 等）。CI pipeline 的 deploy-staging job 需要換成雲端部署（ECS update-service、kubectl apply 等）。（校正：現況 Redis 僅用於冪等存放；快取為 InMemoryCacheStore，Session 在 JVM 記憶體。）

### Category

Project Overview and One-Repository Strategy

### Difficulty

Advanced

### Verified source files

- infra/local/main.tf
- sp2-springboot/src/main/resources/application-prod.yml
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockEmailSender.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java
- .github/workflows/ci.yml

### Verified classes and methods

- LocalDocumentStorageService
- MockEmailSender
- MockSmsSender

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Tests production readiness thinking; checks whether candidates can identify what portfolio shortcuts would be unsafe in production.

### Possible follow-up questions

- Which of these changes would you prioritize first?
- How would you handle session externalization without downtime?
- What observability would you add before going live?

### Verification status

- Verified


---

<a id="Q009"></a>

## Q009 — Which Java 17 language features does this project use, and where can they be found?

### 中文筆記

record：MobileNumber、Email、ApplicationId、IdempotencyEntry、request DTOs（不可變值物件，自動產生 constructor / equals / hashCode）。switch 表達式：GlobalExceptionHandler.handleBusinessException() 依 ErrorCode 映射 HTTP status，語法比 if-else if 更簡潔且 exhaustive。Map.of()：ApplicationStatus.ALLOWED_TRANSITIONS 建立不可變的狀態轉換 map。EnumSet：CANCELLABLE_STATUSES、REQUIRED_DOCUMENT_TYPES、ALLOWED_TRANSITIONS 的 value，型別安全且記憶體效率優於 HashSet。HexFormat.of()（Java 17 新增）：IdempotencyService.hashRequest() 將 SHA-256 byte array 格式化為 hex string。

### Category

Java 17 Features

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/MobileNumber.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Email.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java

### Verified classes and methods

- ApplicationStatus.ALLOWED_TRANSITIONS (private static)
- GlobalExceptionHandler.handleBusinessException()
- IdempotencyService.hashRequest() (private)

### Execution flow

1. Controller receives optional Idempotency-Key header on create application
2. IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find()
3. On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock()
4. On hit with same hash, rebuild prior ResponseEntity; on hash conflict, return conflict error

### Why this may be asked

Validates practical Java 17 knowledge beyond naming the version; confirms the candidate can point to concrete usages.

### Possible follow-up questions

- Why is record more appropriate than a regular class for MobileNumber?
- What is the exhaustiveness guarantee of a switch expression versus a switch statement?
- Why is EnumSet preferred over HashSet<ApplicationStatus>?

### Verification status

- Verified


---

<a id="Q010"></a>

## Q010 — MobileNumber and Email are implemented as Java record types. Why is record a natural fit for value objects in DDD?

### 中文筆記

DDD 的 value object 特性：immutability（狀態不可更改）、equality by value（不用 identity）、self-validation（構建即驗證）。Java record 天生符合：compact constructor 可在構建時執行驗證（MobileNumber 驗證 ^09\d{8}$ pattern；Email 驗證 @ 符號存在），equals/hashCode 自動以所有欄位計算，final 欄位保證不可變。不需要 Lombok @Value 或手動實作，程式碼簡潔且意圖明確。

### Category

Java 17 Features

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/MobileNumber.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Email.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests whether the candidate can connect Java language features to DDD tactical patterns; distinguishes those who understand record from those who use it by imitation.

### Possible follow-up questions

- Can a record implement an interface? Does MobileNumber or Email do so?
- What happens if you call new MobileNumber("0812345678")?
- Why does Email only check for @ and not a full RFC 5322 pattern?

### Verification status

- Documentation-only


---

<a id="Q011"></a>

## Q011 — ApplicationIdempotencyIntegrationTest exists. What behavior does it lock in, and which store does the test profile use?

### 中文筆記

整合測試以 @ActiveProfiles("dev") 搭配 test 資源覆寫，將 tlbank.idempotency.store 設為 memory。測試覆蓋相同 Idempotency-Key 重送回傳、內容衝突與鎖定語意，不依賴本機 Redis。

### Category

Integration Testing

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java
- sp2-springboot/src/test/resources/application-dev.yml
- sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java

### Verified classes and methods

- IdempotencyService.execute()
- InMemoryIdempotencyStore

### Execution flow

1. Test boots Spring context with test application-dev.yml (store=memory)
2. HTTP create application with Idempotency-Key
3. Assertions cover replay and conflict paths via IdempotencyService

### Why this may be asked

Separates candidates who only describe Redis from those who know how tests isolate the port.

### Possible follow-up questions

- Why not use Redis in CI for this test?
- Which HTTP header carries the key?
- How is request body hashing involved in conflict detection?

### Verification status

- Verified


---

<a id="Q012"></a>

## Q012 — ApplicationStatus.ALLOWED_TRANSITIONS is defined with Map.of(). What are the limitations of Map.of() and why do they not matter here?

### 中文筆記

Map.of() 限制：最多 10 個 key-value 對（超過要用 Map.ofEntries()）；key 和 value 不得為 null；傳回的 Map 不可修改（UnsupportedOperationException on put）；迭代順序未保證。在 ALLOWED_TRANSITIONS 中只有 5 個 entry（INIT, OTP_VERIFIED, DOCUMENT_UPLOADED, SUBMITTED, UNDER_REVIEW），遠未達到 10 個上限。null 狀態在 canTransitionTo(null) 中已有提前檢查。不可修改性在此是優點：狀態轉換規則應為常數，runtime 不應更改。

### Category

Java 17 Features

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java

### Verified classes and methods

- ApplicationStatus.ALLOWED_TRANSITIONS (private static)
- ApplicationStatus.canTransitionTo()

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Tests depth of Java collections knowledge; a candidate who uses Map.of() without knowing its constraints cannot defend the choice.

### Possible follow-up questions

- What would happen if you tried to call ALLOWED_TRANSITIONS.put(APPROVED, EnumSet.of(INIT))?
- How would you refactor this if the transition table grew beyond 10 states?
- Why is APPROVED and REJECTED not a key in ALLOWED_TRANSITIONS?

### Verification status

- Verified


---

<a id="Q013"></a>

## Q013 — The GlobalExceptionHandler uses a switch expression on ErrorCode. What advantage does this provide over a chain of if-else if statements?

### 中文筆記

Switch 表達式優點：編譯器可驗證 exhaustiveness（若未覆蓋所有 enum case 且沒有 default，會有 warning 或 error），減少漏掉新 ErrorCode 的風險。Arrow syntax（case X -> value）避免 fall-through 問題，每個 case 是獨立的表達式。結果直接賦值給 HttpStatus status 變數，比 if-else if 每個分支都要寫 status = ... 更清晰。目前有 default -> HttpStatus.BAD_REQUEST 作為 fallback，表示任何未明確映射的 ErrorCode 都回 400。

### Category

Java 17 Features

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java

### Verified classes and methods

- GlobalExceptionHandler.handleBusinessException()

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Tests Java 14+ switch expression knowledge in a real usage context; assesses understanding of exhaustiveness checking.

### Possible follow-up questions

- What happens if a new ErrorCode is added without updating the switch?
- When would you remove the default branch from the switch expression?
- Why does IDEMPOTENCY_KEY_CONFLICT map to CONFLICT (409) rather than BAD_REQUEST?

### Verification status

- Verified


---

<a id="Q014"></a>

## Q014 — Where in the codebase are EnumSet.of() and EnumSet.allOf() used, and what makes EnumSet more efficient than HashSet for these use cases?

### 中文筆記

使用位置：ApplicationStatus.ALLOWED_TRANSITIONS 的 value（EnumSet.of(OTP_VERIFIED, CANCELLED) 等）；Application.CANCELLABLE_STATUSES（EnumSet.of(INIT, OTP_VERIFIED, DOCUMENT_UPLOADED)）；Application.REQUIRED_DOCUMENT_TYPES（EnumSet.allOf(DocumentType.class)）。EnumSet 的效率：內部以 bit vector（long）實作，每個 enum constant 對應一個 bit 位；contains() 是 O(1) 位元運算，不需要 hash 計算與桶定址；記憶體佔用固定且緊湊。在高頻呼叫的 canTransitionTo()/狀態判斷中，bit vector 效能優於 HashSet。

### Category

Java 17 Features

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- ApplicationStatus.ALLOWED_TRANSITIONS (private static)
- Application

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Tests deep understanding of Java collections; separates those who use EnumSet from those who know why to use it.

### Possible follow-up questions

- Can EnumSet contain null? What happens if you try?
- What is the maximum number of enum constants an EnumSet can hold efficiently in a single long?
- Why does EnumSet.allOf(DocumentType.class) work without hardcoding each DocumentType value?

### Verification status

- Verified


---

<a id="Q015"></a>

## Q015 — Lombok is used throughout the domain and infrastructure layers. What annotations appear in the domain model, and is there any risk from depending on Lombok in the domain layer?

### 中文筆記

Domain layer 中使用的 Lombok：@Getter（Application、ApplicationId、domain entities）、@Builder / @Builder.Default（Application、WorkflowHistory）、@RequiredArgsConstructor（infrastructure 的 repository impl、service 類別）、@Slf4j（schedulers、services）。風險：Lombok 是 annotation processor（compile-time），不是 runtime dependency，但 domain layer 理想上應僅依賴 JDK。若日後移除 Lombok，所有 domain 的 @Builder、@Getter 需要手動補回。ADR 0001 明確說明這個 leak 是已知取捨，以減少 boilerplate 為優先。

### Category

Java 17 Features

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md
- sp2-springboot/pom.xml

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests awareness of the tension between DDD purity and practical tooling; a candidate who says "domain should have zero dependencies" but cannot explain the Lombok exception misunderstands pragmatic trade-offs.

### Possible follow-up questions

- How would you remove Lombok from the domain layer without changing behaviour?
- Does @Builder.Default on workflowHistories and documentInfos affect how the builder works?
- Is Lombok a compile-time or runtime dependency? What does that mean for the domain purity argument?

### Verification status

- Documentation-only


---

<a id="Q016"></a>

## Q016 — IdempotencyEntry is a record. What does storing a record in Redis via Jackson look like, and what serialization concern must be handled?

### 中文筆記

IdempotencyEntry 是 record（requestHash、httpStatus、responseBody 三個 component）。Jackson 序列化 record：預設以 component name 為 JSON key，反序列化時需要 @JsonCreator 或 Jackson 2.12+ 對 record 的原生支援（可自動偵測 canonical constructor）。RedisIdempotencyStore.save() 以 ObjectMapper.writeValueAsString(entry) 序列化為 JSON string 存入 Redis；find() 以 objectMapper.readValue(json, IdempotencyEntry.class) 還原。若 Spring Boot 的 ObjectMapper bean 未啟用 FAIL_ON_UNKNOWN_PROPERTIES=false，schema 變更時舊 Redis value 可能造成反序列化失敗。

### Category

Java 17 Features

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/IdempotencyEntry.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java

### Verified classes and methods

- RedisIdempotencyStore.find()
- RedisIdempotencyStore.save()

### Execution flow

1. Controller receives optional Idempotency-Key header on create application
2. IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find()
3. On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock()
4. On hit with same hash, rebuild prior ResponseEntity; on hash conflict, return conflict error

### Why this may be asked

Tests intersection of Java records and serialization; advanced question that reveals whether the candidate has considered production failure modes.

### Possible follow-up questions

- What happens in Redis if you rename a field in IdempotencyEntry and deploy without flushing old keys?
- How would you version the idempotency entry schema?
- Why does RedisIdempotencyStore.find() return Optional.empty() on deserialization failure rather than rethrowing?

### Verification status

- Verified


---

<a id="Q017"></a>

## Q017 — Spring Boot 3.4.2 is pinned in pom.xml. What changed from Spring Boot 2.x to 3.x that is most relevant to this project?

### 中文筆記

Spring Boot 3.x 最相關的變化：Jakarta EE 10 命名空間替換（javax.* → jakarta.*），pom.xml 中的 import 全部是 jakarta.servlet.*、jakarta.validation.*。Spring Security 6.x（隨 Boot 3 升級）的 HttpSecurity 配置從 method chaining 改為 lambda DSL（csrf(csrf -> ...)、sessionManagement(session -> ...)）。@SpringBootTest 預設行為改變（WebEnvironment.DEFINED_PORT vs MOCK）。Actuator 的 health endpoint 格式調整。SpringDoc OpenAPI 也從 2.x 升到 3.x。

### Category

Spring Boot 3.x

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/pom.xml
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- SecurityConfig.securityFilterChain()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Tests awareness of migration concerns; many teams are mid-migration from Boot 2 to Boot 3.

### Possible follow-up questions

- Is there any javax.* import remaining in the codebase that would indicate an incomplete migration?
- What does @EnableMethodSecurity replace from Spring Security 5?
- What would break if you downgraded to Spring Boot 2.7 without code changes?

### Verification status

- Verified


---

<a id="Q018"></a>

## Q018 — TlbankLendingApplication is the entry point. What annotations does it carry and what does each do?

### 中文筆記

@SpringBootApplication 是組合 annotation，等同於 @Configuration + @EnableAutoConfiguration + @ComponentScan(basePackages = "com.tlbank.lending")。@EnableScheduling（如果存在）啟用 scheduler 支援，否則 @Scheduled annotation 無效。@EnableAsync（可能在 AsyncConfig 中定義而非 entry point）啟用 @Async 支援，讓 AuditLogWriter.saveAsync() 真正非同步執行。主要需確認 TlbankLendingApplication 是否有額外的 @EnableScheduling、@EnableAsync，或這些被移至 SchedulingConfig、AsyncConfig 配置類別。

### Category

Spring Boot 3.x

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/TlbankLendingApplication.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/config/AsyncConfig.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java

### Verified classes and methods

- TlbankLendingApplication
- AsyncConfig
- SchedulingConfig

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Tests foundational Spring Boot knowledge; ensures the candidate understands the bootstrap process.

### Possible follow-up questions

- What package does @ComponentScan scan if no basePackages is specified?
- If @EnableAsync is missing, what happens when AuditLogWriter.saveAsync() is called?
- What is the difference between @Configuration and @SpringBootConfiguration?

### Verification status

- Verified


---

<a id="Q019"></a>

## Q019 — application.yml sets ddl-auto: validate for Hibernate. What does that mean, and why is it the right choice for a Flyway-managed schema?

### 中文筆記

ddl-auto: validate：Hibernate 啟動時掃描所有 @Entity 類別，驗證資料庫中的 table 和 column 是否與 entity 對應，但不做任何 DDL 修改（不 create、不 alter、不 drop）。正確選擇的原因：Flyway 是 schema 的唯一真相來源（V1__create_users.sql 等），Hibernate 不應自行修改 schema，否則 Flyway checksum 與實際 schema 可能出現不一致。若 Flyway migration 執行後 entity 欄位不匹配，validate 會在 startup 立即失敗，比在 runtime 執行時才報錯更早發現問題。create/update 在 staging/prod 是危險的，可能 drop data。

### Category

Spring Boot 3.x

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/application.yml
- sp2-springboot/src/main/resources/db/migration/V1__create_users.sql

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Application starts with active Spring profile
2. Flyway migrates scripts from profile-specific locations
3. Hibernate ddl-auto=validate checks entities against schema

### Why this may be asked

ddl-auto settings are a classic production incident source; tests whether the candidate understands the relationship between Hibernate and schema migration tools.

### Possible follow-up questions

- What would happen at startup if ddl-auto: validate is set but a Flyway migration was not run?
- Under what circumstances would ddl-auto: create-drop be acceptable?
- How does Hibernate validation differ between H2 and SQL Server dialects?

### Verification status

- Documentation-only


---

<a id="Q020"></a>

## Q020 — The project uses springdoc-openapi. Where is the Swagger UI accessible, and how is it secured?

### 中文筆記

Swagger UI 路徑：/swagger-ui/**、/swagger-ui.html、/v3/api-docs/**。SecurityConfig 中這些路徑被設定為 permitAll()，任何人都可以在不登入的情況下存取。SwaggerConfig 提供 OpenAPI 元資料配置（title、version、description）。注意：Swagger UI 在 dev/staging 開放是合理的（開發調試），但在 prod 應考慮關閉或加上 IP 白名單。application.yml 或 profile 中可能有 springdoc.api-docs.enabled=false 在 prod 停用。這是目前已知的安全 gap 之一。

### Category

Spring Boot 3.x

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/config/SwaggerConfig.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java
- sp2-springboot/src/main/resources/application.yml

### Verified classes and methods

- SecurityConfig.securityFilterChain()
- SwaggerConfig

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Tests awareness of security implications of API documentation exposure; candidates often overlook this in portfolios.

### Possible follow-up questions

- What would you add to application-prod.yml to disable Swagger in production?
- Why might exposing /v3/api-docs/** in production be a security concern?
- What role does springdoc.swagger-ui.path play if configured?

### Verification status

- Verified


---

<a id="Q021"></a>

## Q021 — Spring Boot Actuator is included in the project. How is it used, and what endpoints are exposed?

### 中文筆記

Actuator 在此 project 的主要用途：Docker Compose 的 app service 使用 Actuator healthcheck 作為 container health endpoint（healthcheck.test 可能呼叫 /actuator/health）。application.yml 中 management endpoint 的設定決定哪些 endpoint 對外暴露。預設 Spring Boot 3 只暴露 /actuator/health 和 /actuator/info，其他（metrics、env、beans 等）需明確設定 management.endpoints.web.exposure.include。在 staging 的 Docker Compose healthcheck 中，Actuator /actuator/health 回應 200 是 container 健康的判斷依據。

### Category

Spring Boot 3.x

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/resources/application.yml
- sp2-springboot/docker-compose.yml
- sp2-springboot/docker/app/Dockerfile

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Actuator is a key operational component; candidates who never think about healthcheck endpoints lack production awareness.

### Possible follow-up questions

- What is the difference between /actuator/health and /actuator/health/liveness?
- Should /actuator/env be exposed in production? Why or why not?
- How does Docker Compose use the Actuator health endpoint to control service startup order?

### Verification status

- Documentation-only


---

<a id="Q022"></a>

## Q022 — CommonConfig.java exists in the common/config package. What kind of beans would typically live there?

### 中文筆記

CommonConfig 通常包含跨層共享的 utility bean：ObjectMapper bean（自訂 Jackson 序列化配置，供 IdempotencyService、RedisIdempotencyStore 等注入使用）；Clock bean（OtpCleanupScheduler 注入 Clock 而非直接呼叫 LocalDateTime.now()，讓測試可以注入 fixed clock）；ModelMapper 或其他轉換工具（若有）。Clock bean 的設計選擇特別重要：scheduler 測試可以注入 mock Clock 驗證邊界條件，比 mocking LocalDateTime.now() 更 clean。

### Category

Spring Boot 3.x

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/config/CommonConfig.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests Spring dependency injection design; Clock injection is a well-known testability pattern worth calling out explicitly.

### Possible follow-up questions

- Why inject Clock instead of calling LocalDateTime.now() directly in the scheduler?
- How would you write a test that verifies OtpCleanupScheduler marks OTPs expired at exactly midnight?
- What is the risk of using a singleton ObjectMapper bean shared across all components?

### Verification status

- Documentation-only


---

<a id="Q023"></a>

## Q023 — @EnableMethodSecurity is on SecurityConfig. What does this enable, and is it used in this project?

### 中文筆記

@EnableMethodSecurity 啟用 @PreAuthorize、@PostAuthorize、@Secured、@RolesAllowed 等方法級別的安全注解（Spring Security 6 替代舊版 @EnableGlobalMethodSecurity）。是否在此 project 使用：主要的角色控制集中在 SecurityConfig.authorizeHttpRequests() 的 URL-level 設定（hasRole("ADMIN")、hasAnyRole("REVIEWER", "ADMIN")），方法層級的 @PreAuthorize 在 controllers 或 services 中可能存在，但主要守衛在 URL pattern。啟用它但不用方法級別注解不會產生問題，但也沒有發揮效用。

### Category

Spring Boot 3.x

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests Spring Security depth; distinguishes URL-based authorization from method-level authorization.

### Possible follow-up questions

- What is the difference between hasRole("ADMIN") and hasAuthority("ROLE_ADMIN")?
- When would you prefer @PreAuthorize on a service method over URL-based rules?
- What does proxyTargetClass = true do when used with @EnableMethodSecurity?

### Verification status

- Documentation-only


---

<a id="Q024"></a>

## Q024 — The project uses @Async for audit log writing. What configuration is needed for @Async to work, and where is it in this project?

### 中文筆記

@Async 生效需要：@EnableAsync（在某個 @Configuration 類別上，此 project 中在 AsyncConfig 或 entry point 上），且呼叫者必須透過 Spring proxy 呼叫（同一個 class 內的 self-invocation 會繞過 proxy，@Async 無效）。AuditLogWriter.saveAsync() 被 AuditAspect 呼叫，AuditAspect 是獨立 bean，透過 Spring proxy 注入 AuditLogWriter，所以 @Async 正常生效。AsyncConfig 通常也定義自訂 Executor bean（ThreadPoolTaskExecutor），設定核心執行緒數、佇列大小、thread name prefix，方便 log 追蹤與資源控管。

### Category

Spring Boot 3.x

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/config/AsyncConfig.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java

### Verified classes and methods

- AuditLogWriter.saveAsync()
- AsyncConfig

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

@Async misuse (especially self-invocation) is a common Spring pitfall; tests whether the candidate understands Spring proxy mechanics.

### Possible follow-up questions

- What happens if AuditAspect calls auditLogWriter.saveAsync() and the thread pool queue is full?
- How would you handle exceptions thrown inside an @Async method?
- What is the default executor used if no custom Executor bean is defined?

### Verification status

- Verified


---

<a id="Q025"></a>

## Q025 — The project configures SessionCreationPolicy.IF_REQUIRED. What does this mean, and when would you change it to STATELESS?

### 中文筆記

IF_REQUIRED：Spring Security 僅在需要時建立 session（使用者認證成功後、或需要存儲 SecurityContext 時）。這是 Thymeleaf server-rendered portal 的正確選擇，因為 browser 需要維持登入狀態。STATELESS：每個請求都必須攜帶認證憑據（如 Bearer token），Spring Security 不建立也不讀取 session，適合 pure REST API + JWT 場景。何時切換：若日後為行動端或第三方服務增加 JWT filter chain（/api/v2/**），該 filter chain 設定 STATELESS，而 Thymeleaf 的 filter chain 繼續使用 IF_REQUIRED，兩個 filter chain 並存。

### Category

Spring Boot 3.x

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java
- sp2-springboot/docs/decisions/0006-session-over-jwt.md

### Verified classes and methods

- SecurityConfig.securityFilterChain()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Tests Spring Security session management knowledge; relevant to the JWT vs session trade-off discussion.

### Possible follow-up questions

- What is NEVER policy, and when would you use it?
- How does SessionCreationPolicy.STATELESS interact with @SessionAttributes?
- Can you have two SecurityFilterChain beans with different session policies?

### Verification status

- Verified


---

<a id="Q026"></a>

## Q026 — @SpringBootTest appears in multiple test classes. What does it load, and when should you use it versus @WebMvcTest or @DataJpaTest?

### 中文筆記

@SpringBootTest：加載整個 Spring Application Context（所有 bean、所有 auto-configuration），適合整合測試，確認多個 layer 協同運作。速度較慢。@WebMvcTest(Controller.class)：只加載 Web layer（controller、filters、GlobalExceptionHandler），mock service layer，速度快，適合測試 HTTP 行為、validation、response format。@DataJpaTest：只加載 JPA 相關 bean（entity manager、repositories），使用 in-memory H2，適合測試 repository 查詢邏輯。此 project 的 integration tests（ApplicationFlowIntegrationTest、SecurityIntegrationTest）用 @SpringBootTest，controller unit tests（ReviewApiControllerTest）用 @WebMvcTest。

### Category

Spring Boot 3.x

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java
- sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java
- sp2-springboot/src/test/java/com/tlbank/lending/presentation/api/v1/ReviewApiControllerTest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

@SpringBootTest overuse is a common test performance problem; candidates should know the slice annotations.

### Possible follow-up questions

- What is WebEnvironment.RANDOM_PORT versus WebEnvironment.MOCK?
- How does @MockBean differ from @Mock in a Spring context?
- When would you use @DataJpaTest with a real SQL Server via Testcontainers instead of H2?

### Verification status

- Documentation-only


---

<a id="Q027"></a>

## Q027 — The project follows Clean Architecture with a strict dependency rule. Which direction do dependencies flow, and how is this enforced?

### 中文筆記

依賴方向：presentation → application → domain ← infrastructure。Domain 不依賴任何外層：Application（aggregate）只 import JDK 和 Lombok，沒有 @Entity、沒有 Spring 注解。Infrastructure 依賴 domain port（ApplicationRepository、OtpRepository 等介面），實作這些 port 而非被 domain 所知。目前的執行方式：package 命名約定 + code review，沒有自動化工具（如 ArchUnit）在 CI 強制驗證。ADR 0001 承認有少數 leak（@Service 出現在接近 domain 的地方）。未來改善方向：加入 ArchUnit 規則，讓 CI 能自動拒絕違反依賴方向的 PR。

### Category

Clean / Hexagonal Architecture

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/decisions/0001-use-clean-architecture.md
- sp2-springboot/docs/design/02-architecture-design.md
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Clean Architecture is easy to claim but hard to implement correctly; interviewers will probe the actual dependency graph.

### Possible follow-up questions

- Can you open Application.java and show me that it has no @Entity annotation?
- What ArchUnit rule would you write to enforce the dependency direction?
- What is the difference between Clean Architecture and Hexagonal Architecture?

### Verification status

- Documentation-only


---

<a id="Q028"></a>

## Q028 — What is the "Ports and Adapters" pattern, and where does TLBank implement it?

### 中文筆記

Ports and Adapters（Hexagonal Architecture）：Port 是 domain 定義的介面（interface），Adapter 是 infrastructure 實作該介面的具體類別。Port 讓 domain 依賴抽象而非具體實作，外部技術可以替換而不影響 domain 邏輯。TLBank 的實例：Port = ApplicationRepository（domain package 中的介面），Adapter = ApplicationRepositoryImpl（infrastructure package，實作 Port，內部使用 ApplicationJpaRepository）。同樣的 pattern：OtpRepository / OtpRepositoryImpl、IdempotencyStore / RedisIdempotencyStore / InMemoryIdempotencyStore、EmailSender / MockEmailSender、SmsSender / MockSmsSender、DocumentStorageService / LocalDocumentStorageService。

### Category

Clean / Hexagonal Architecture

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/IdempotencyStore.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests whether the candidate understands hexagonal architecture beyond naming it; requires pointing to concrete port/adapter pairs.

### Possible follow-up questions

- Why is ApplicationRepository in the domain package rather than infrastructure?
- How would you swap LocalDocumentStorageService for an S3 adapter without changing the domain?
- What is the "driving" versus "driven" port distinction in hexagonal architecture?

### Verification status

- Documentation-only


---

<a id="Q029"></a>

## Q029 — ApplicationRepositoryImpl maps between ApplicationEntity (JPA) and Application (domain aggregate). Why is this mapping necessary, and what is the cost?

### 中文筆記

Mapping 的必要性：domain aggregate Application 不帶任何 JPA 注解（@Entity、@Column 等），才能在不依賴資料庫的情況下做 unit test。ApplicationEntity 帶 JPA 注解，負責對應資料庫 schema。若兩個合一（在 aggregate 上直接加 @Entity），domain test 就需要啟動 Spring Data JPA 整個 stack。Mapping 的成本：每次 save() 和 findById() 都需要手動或工具協助的 entity ↔ domain 轉換，程式碼量增加（toDomain()、toEntity()、applyToEntity() 方法）。MapStruct 在 pom.xml 中有 classpath 但目前未使用，手動 mapping 是目前的實作。

### Category

Clean / Hexagonal Architecture

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- ApplicationRepositoryImpl.toDomain() (private)
- ApplicationRepositoryImpl.toEntity() (private)
- ApplicationRepositoryImpl.applyToEntity() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The entity/domain split is the most common point of confusion in Clean Architecture; tests whether the candidate understands why it exists.

### Possible follow-up questions

- What would break if you added @Entity directly to Application?
- MapStruct is on the classpath but unused. When would you add it?
- How does applyToEntity() differ from toEntity(), and why are both needed?

### Verification status

- Verified


---

<a id="Q030"></a>

## Q030 — The domain layer has a service package containing WorkflowDomainService. What distinguishes a domain service from an application service?

### 中文筆記

Domain service（WorkflowDomainService）：包含跨多個 aggregate 或不適合放在單一 aggregate 上的業務規則；不持有狀態；屬於 domain layer，不依賴 Spring（理論上），但 WorkflowDomainService 標有 @Service（ADR 0001 中承認的 Spring leak）。Application service（ApplicationAppService、ReviewAppService 等）：協調 use case 流程，呼叫 domain aggregate 方法，與 repository port 互動，處理 transaction，轉換 DTO；是 port 的呼叫端，不含業務規則本身。主要區別：domain service 描述「業務規則是什麼」，application service 描述「how to execute a use case」。

### Category

Clean / Hexagonal Architecture

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java

### Verified classes and methods

- WorkflowDomainService
- ApplicationAppService

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The domain service vs application service distinction is a common DDD confusion; answers reveal the depth of the candidate's understanding.

### Possible follow-up questions

- Can WorkflowDomainService call a repository port? Should it?
- Why does ApplicationAppService handle transactions but WorkflowDomainService does not?
- Is @Service on WorkflowDomainService a violation of the dependency rule?

### Verification status

- Verified


---

<a id="Q031"></a>

## Q031 — How does the presentation layer prevent business logic from leaking into controllers?

### 中文筆記

防止業務邏輯進入 controller 的機制：Controller 只做三件事：@Valid 觸發 JSR-303 validation（欄位格式）、委派給一個 application service 方法、將結果包進 ApiResponse 回傳。業務規則（狀態轉換、OTP 驗證、文件完整性）全部在 domain aggregate 和 application service 中執行。Exception 翻譯在 GlobalExceptionHandler 集中處理，controller 不需要 try-catch。只有薄薄一層 DTO mapping（controller 接收 CreateApplicationRequest，轉成 application service 理解的參數）。任何在 controller 中出現的 if 判斷業務條件，都是 red flag。

### Category

Clean / Hexagonal Architecture

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java

### Verified classes and methods

- GlobalExceptionHandler

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Fat controllers are a common anti-pattern; tests whether the candidate understands layer responsibility separation.

### Possible follow-up questions

- Show me an example controller method. Does it contain any business logic?
- What would happen if you moved the OTP verification logic into the controller?
- How does @RestControllerAdvice work, and why is it preferable to try-catch in each controller?

### Verification status

- Verified


---

<a id="Q032"></a>

## Q032 — What are the known architectural impurities (leaks) in this codebase, and how would you fix them?

### 中文筆記

ADR 0001 明確承認的 impurity：@Service annotation 出現在接近 domain layer 的地方（WorkflowDomainService），使 domain 有 Spring 依賴。MapStruct 在 classpath 但未用，手動 mapping 有重複程式碼的風險。部分 Spring 類型（如 Pageable）可能出現在 domain port 介面（ApplicationRepository），使 domain 依賴 Spring Data 的分頁 abstraction。修復方式：用純 Java 定義 WorkflowDomainService（不加 @Service），改由 @Configuration 的 @Bean 方法建立實例並注入；將 Pageable 從 domain port 移除，改用自訂的 SearchCriteria 值物件；若 mapping 增加，引入 MapStruct。

### Category

Clean / Hexagonal Architecture

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

The best answers acknowledge trade-offs honestly rather than claiming a perfect codebase; tests intellectual honesty and pragmatic judgment.

### Possible follow-up questions

- How would ArchUnit help catch these leaks in CI automatically?
- Is the @Service annotation on WorkflowDomainService a compile-time or runtime dependency?
- What is the business risk of these architectural impurities in a production system?

### Verification status

- Documentation-only


---

<a id="Q033"></a>

## Q033 — How do the common and security packages fit into the Clean Architecture layering?

### 中文筆記

common：跨切關注點（cross-cutting concerns），不屬於任何業務 layer，不依賴業務 layer（common 可被所有 layer 引用）。包含：ApiResponse（HTTP 回應信封）、ErrorCode、BusinessException、WorkflowException、AuditAspect、AsyncConfig、CommonConfig、工具類別（MaskingUtil、DateUtil）。security：同為跨切關注點，依賴 domain layer（需要知道 Role enum、User aggregate），也依賴 infrastructure layer（UserRepositoryImpl 提供 UserDetails 給 Spring Security）。security 不是 hexagonal 的標準 layer，是 Spring Security 整合的特殊位置。

### Category

Clean / Hexagonal Architecture

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java
- sp2-springboot/docs/design/02-architecture-design.md

### Verified classes and methods

- ApiResponse
- ErrorCode
- SecurityConfig

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Tests understanding of how cross-cutting concerns fit into layered architectures; a frequently misunderstood area.

### Possible follow-up questions

- Can a domain class import from common? Is that a dependency rule violation?
- Why doesn't Spring Security live in infrastructure instead of its own security package?
- What would you move out of common if the project scaled to a larger team?

### Verification status

- Verified


---

<a id="Q034"></a>

## Q034 — How does TLBank handle the mapping between domain aggregates and JPA entities for the WorkflowHistory embedded in Application?

### 中文筆記

Application aggregate 包含 List<WorkflowHistory> 作為子值物件列表（記錄每次狀態轉換）。對應的 JPA 結構：ApplicationEntity 包含 List<WorkflowHistoryEntity>（一對多關係），WorkflowHistoryEntity 有 @ManyToOne 關聯回 ApplicationEntity。ApplicationRepositoryImpl.toDomain() 將 WorkflowHistoryEntity list 轉換為 WorkflowHistory domain 物件列表；toEntity() 反向轉換。每次 Application.save() 時，若有新的 WorkflowHistory（狀態轉換後 transitionTo() 產生的），applyToEntity() 需要正確同步新增的 history 到 entity，避免 JPA orphan removal 問題。

### Category

Clean / Hexagonal Architecture

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/WorkflowHistoryEntity.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/WorkflowHistory.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- ApplicationRepositoryImpl.toDomain() (private)
- ApplicationRepositoryImpl.applyToEntity() (private)
- WorkflowHistoryEntity

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The aggregate-with-history mapping is one of the most complex parts of the repository; tests JPA and DDD knowledge simultaneously.

### Possible follow-up questions

- What JPA cascade type would you use for WorkflowHistoryEntity?
- What happens if the domain Application has new WorkflowHistory entries that the entity does not yet know about?
- How would orphanRemoval affect the history list if you accidentally called clear() on it?

### Verification status

- Verified


---

<a id="Q035"></a>

## Q035 — The application layer has both a top-level idempotency sub-package and a dto sub-package. What does each contain, and why are they in application rather than infrastructure?

### 中文筆記

application.idempotency：IdempotencyService（use case 協調器），負責 idempotency key 解析、SHA-256 hash 計算、lock 取得、cache 命中判斷、response 重建。依賴 IdempotencyStore port（在 infrastructure 中有 Redis 和 memory 兩種實作）。application.dto：request/response DTO 類別（CreateApplicationRequest、LoginResponse 等），定義 API 邊界的資料形狀，與 domain aggregate 解耦（controller 傳 DTO 給 application service，service 拆解後操作 domain）。兩者都在 application 而非 infrastructure，因為它們是 use case 層的關注點：協調業務流程（idempotency 是 use case 的 orchestration concern，DTO 是 use case 的 I/O contract），不是技術實作細節。

### Category

Clean / Hexagonal Architecture

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/CreateApplicationRequest.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/dto/response/LoginResponse.java

### Verified classes and methods

- IdempotencyService
- CreateApplicationRequest

### Execution flow

1. Controller receives optional Idempotency-Key header on create application
2. IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find()
3. On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock()
4. On hit with same hash, rebuild prior ResponseEntity; on hash conflict, return conflict error

### Why this may be asked

Tests layering knowledge beyond the basics; candidates who understand why things are placed where they are demonstrate deeper architectural thinking.

### Possible follow-up questions

- Could IdempotencyService be in infrastructure? What would change?
- Why not put request DTOs in the presentation layer directly?
- What is the difference between an application-layer DTO and a domain value object?

### Verification status

- Verified


---

<a id="Q036"></a>

## Q036 — What is the role of package-info.java files in this project?

### 中文筆記

package-info.java 在此 project 中有兩個用途：(1) 提供 package-level Javadoc 說明該 package 的職責和 layer 歸屬（例如說明這是 application service layer 的進入點）；(2) 在 com.tlbank.lending root 的 package-info.java 中記錄整個 Hexagonal Architecture 的 layer 分工（作為 living documentation，任何人 open 這個 package 都能看到架構概述）。這是讓架構決策 visible in code 的良好實踐，比只在 wiki 記錄更容易被開發者看到，與 ADR 互補。

### Category

Clean / Hexagonal Architecture

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/package-info.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/package-info.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/package-info.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests code documentation practices; surprisingly few candidates are aware of package-level Javadoc.

### Possible follow-up questions

- What is the difference between package-info.java and a README.md in a source directory?
- Can package-info.java carry annotations? What use case would that serve?
- Is this pattern common in the Java ecosystem, or specific to certain project styles?

### Verification status

- Documentation-only


---

<a id="Q037"></a>

## Q037 — If you had to add a new feature — say, credit limit assignment after approval — where would each piece of code live in the layered architecture?

### 中文筆記

Domain layer：Application aggregate 新增 assignCreditLimit(CreditLimit limit) 方法；CreditLimit value object（record with validation）；ApplicationStatus.APPROVED 可能需要額外狀態或 flag。Application layer：ApplicationAppService 新增 assignCreditLimit(ApplicationId, CreditLimitRequest) use case 方法；DTO CreditLimitRequest。Infrastructure layer：若需要外部信評 API，新增 CreditScoringService port 和對應 adapter；若只是更新 DB 欄位，ApplicationRepositoryImpl 已有 save()，不需改動 port。Presentation layer：ApplicationApiController 新增 endpoint（PUT /api/v1/applications/{id}/credit-limit）；@Auditable 加在 service 方法。

### Category

Clean / Hexagonal Architecture

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Design questions on existing systems reveal whether the candidate has internalized the architecture or just memorized the structure.

### Possible follow-up questions

- Would you add CreditLimit as a field on Application or create a new aggregate?
- Where would you place the credit limit business rule (e.g., minimum NT$10,000)?
- How would you test this feature end-to-end with the existing test setup?

### Verification status

- Documentation-only


---

<a id="Q038"></a>

## Q038 — The NotificationEventHandler and ReviewEventHandler are described as "natural seams" for future broker integration. Why?

### 中文筆記

目前的 event 機制：domain events（ApplicationApprovedEvent、ApplicationSubmittedEvent 等）透過 Spring ApplicationEventPublisher 發布，event handler 在同一個 JVM process 內的 @EventListener 處理，完全 in-process，沒有網路跳轉。為何是 broker 的自然接縫：event handler 已經是 domain logic（application service）與 side effect（通知、review 建立）的分離點。若要接 Kafka/RabbitMQ，只需將 @EventListener 替換為向 broker publish message 的 adapter，domain aggregate 發 event 的方式不變。NotificationEventHandler 負責 SMS/Email mock，ReviewEventHandler 負責建立 ReviewCase，兩者職責清晰，是低耦合的接縫。

### Category

Clean / Hexagonal Architecture

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests forward-looking design thinking and understanding of event-driven architecture evolution.

### Possible follow-up questions

- What transactional concern arises when you move from in-process events to a message broker?
- How would you handle the case where the broker is down but the database transaction committed?
- What is the Outbox pattern, and how would it apply here?

### Verification status

- Documentation-only


---

<a id="Q039"></a>

## Q039 — How is CachedCardProductRepository structured, and what does it demonstrate about the Ports and Adapters pattern?

### 中文筆記

CachedCardProductRepository 是 CardProductRepository port 的 decorator 實作：包裝另一個 CardProductRepository（JPA 實作），在外層加入 cache 邏輯（先查 CacheStore，cache miss 才查 JPA，查到後存入 cache）。這展示 Ports and Adapters 的 composability：port 介面允許多個 adapter，adapter 可以互相裝飾而不改變 domain 的呼叫方式。Domain 和 application service 只知道 CardProductRepository 介面，不知道底層是 JPA、cache、還是兩者組合。Spring @Primary 或 @Qualifier 決定 injection 時用哪個實作。

### Category

Clean / Hexagonal Architecture

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/product/repository/CardProductRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/product/CardProductRepositoryImpl.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

The decorator pattern applied to a port interface is an elegant DDD pattern rarely seen in junior portfolios.

### Possible follow-up questions

- What @Qualifier or @Primary annotation is needed to make Spring inject CachedCardProductRepository?
- What happens to cached data if a card product is updated in the database?
- How would CacheManagementService.refresh() interact with CachedCardProductRepository?

### Verification status

- Documentation-only


---

<a id="Q040"></a>

## Q040 — What would you need to add to enforce the dependency rule automatically in CI?

### 中文筆記

ArchUnit：Java 函式庫，可以寫成 JUnit 測試驗證 class import 依賴關係。例如：noClasses().that().resideInPackage("..domain..").should().dependOnClassesThat().resideInPackage("..infrastructure..") 驗證 domain 不依賴 infrastructure。加入 pom.xml dependency（com.tngtech.archunit:archunit-junit5），在 test class 中撰寫規則，mvn verify 時自動執行。好處：CI 會在任何違反依賴方向的 PR 合入時自動 fail，不需要 code review 人工發現。成本：需要了解 ArchUnit DSL；初期設定需要處理已知 exception（如 WorkflowDomainService 的 @Service leak），可用 ignoreDependency() 記錄已知例外。

### Category

Clean / Hexagonal Architecture

### Difficulty

Advanced

### Verified source files

- sp2-springboot/pom.xml
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Tests whether the candidate knows how to operationalize architectural constraints beyond code review.

### Possible follow-up questions

- How would you handle existing violations when first introducing ArchUnit to a project?
- What ArchUnit rule would verify that controllers do not call repository implementations directly?
- Are there alternatives to ArchUnit for enforcing package structure in Java?

### Verification status

- Documentation-only


---

<a id="Q041"></a>

## Q041 — TLBank uses "DDD-lite." What does that mean, and which full DDD tactical patterns are missing?

### 中文筆記

DDD-lite：採用 DDD 的部分 tactical pattern，不做完整的 DDD 實作。此 project 有的：aggregate（Application、ReviewCase）、value object（MobileNumber、Email、ApplicationId）、domain event（ApplicationApprovedEvent 等）、repository port（ApplicationRepository）、domain service（WorkflowDomainService）。此 project 缺少或簡化的：完整的 AggregateRoot base class（沒有統一的 domain event collection 機制，event 由 application service 或 aggregate method 觸發）；Entity 與 Aggregate Root 的嚴格區分（ReviewCase 是否真的是 aggregate root 還是 entity 可以討論）；Bounded Context 的明確 mapping 和 Context Map；Anti-Corruption Layer（ACL）。

### Category

Domain-Driven Design

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/decisions/0002-use-ddd.md
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

"DDD" is often claimed without understanding; this question separates those who understand the tactical pattern landscape from those who use the term superficially.

### Possible follow-up questions

- Is Application a proper aggregate root? What makes something an aggregate root?
- How would you add a formal AggregateRoot base class for domain event collection?
- What is a Bounded Context, and is TLBank operating in one or multiple?

### Verification status

- Documentation-only


---

<a id="Q042"></a>

## Q042 — Application is the aggregate root. What responsibility does it have for maintaining invariants?

### 中文筆記

Application 作為 aggregate root 的 invariant 責任：(1) 狀態轉換合法性：transitionTo() 驗證 ALLOWED_TRANSITIONS，非法轉換拋 WorkflowException，aggregate 自身保護狀態一致性，不依賴外部 service 來判斷合法性。(2) 文件完整性：submit() 呼叫 validateRequiredDocuments()，確保所有 DocumentType（NATIONAL_ID、INCOME_PROOF、RESIDENCE_PROOF）都已上傳，否則拋 BusinessException(INCOMPLETE_DOCUMENTS)。(3) 取消限制：cancel() 只在 CANCELLABLE_STATUSES（INIT、OTP_VERIFIED、DOCUMENT_UPLOADED）允許，已 SUBMITTED 的申請不可取消。Invariant 的校驗在 aggregate 內部，application service 呼叫 aggregate 方法，不繞過 invariant 直接操作 entity。

### Category

Domain-Driven Design

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java

### Verified classes and methods

- Application.submit()
- Application.cancel()
- Application.transitionTo() (private)
- Application.validateRequiredDocuments() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Invariant enforcement is the core value proposition of aggregates; tests whether the candidate understands why business rules belong in the domain.

### Possible follow-up questions

- What happens if ApplicationAppService tries to move an application from APPROVED back to SUBMITTED?
- Why is transitionTo() private? What would break if it were public?
- Could you write a unit test that verifies APPROVED → SUBMITTED throws WorkflowException without any Spring context?

### Verification status

- Verified


---

<a id="Q043"></a>

## Q043 — What is the difference between ApplicationId, CardProductId, and ReviewCaseId? Why are they not just String or UUID?

### 中文筆記

ApplicationId、CardProductId、ReviewCaseId 都是 value object（record），封裝 ID 的格式和驗證邏輯。優點：型別安全，findById(ApplicationId id) 和 findById(ReviewCaseId id) 是不同的型別，不會意外互換；自文件化，方法簽名明確說明預期哪種 ID；集中 validation（ApplicationId 若有格式規則，在 compact constructor 驗證）。若只用 String：findById(String applicationId) 與 findById(String reviewCaseId) 在呼叫端難以區分，compiler 不會報錯，但 runtime 可能傳錯。這是 DDD 中「Primitive Obsession」anti-pattern 的解決方案。

### Category

Domain-Driven Design

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/CardProductId.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCaseId.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Primitive Obsession vs. typed IDs is a fundamental DDD design question; tests whether the candidate can explain the intent, not just describe the implementation.

### Possible follow-up questions

- What is the "Primitive Obsession" anti-pattern?
- How does the JPA infrastructure layer handle ApplicationId when persisting to the database?
- Is there a performance cost to wrapping IDs in value objects?

### Verification status

- Documentation-only


---

<a id="Q044"></a>

## Q044 — Domain events exist in the domain/event package, but event handlers are in infrastructure/event. Why is this separation important?

### 中文筆記

分離的理由：Domain events（ApplicationApprovedEvent、ApplicationSubmittedEvent 等）描述「業務上發生了什麼」，是 domain 的語言，不應依賴 infrastructure 技術（SMS SDK、JPA）。Event handler（NotificationEventHandler、ReviewEventHandler）是 infrastructure concern，負責把 domain 發生的事件轉譯為具體的技術行動（呼叫 mock SMS sender、建立 ReviewCase），可以依賴 Spring @EventListener、repository port、notification service 等。若 event 和 handler 在同一個 package，domain event 類別可能被誘使直接引用 infrastructure bean，破壞依賴方向。這個設計讓 domain event 可在不啟動 infrastructure 的情況下被 domain unit test 驗證。

### Category

Domain-Driven Design

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationSubmittedEvent.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

The event/handler separation reveals understanding of the dependency direction rule applied to event-driven code.

### Possible follow-up questions

- Who publishes domain events — the aggregate, the application service, or the domain service?
- What happens if ReviewEventHandler throws an exception — does it roll back the original application submission?
- How would you test NotificationEventHandler in isolation?

### Verification status

- Documentation-only


---

<a id="Q045"></a>

## Q045 — What are the five domain aggregates or main entity clusters in TLBank, and what are their boundaries?

### 中文筆記

主要 aggregate / entity cluster：(1) Application（申請人信用卡申請，包含 Applicant、WorkflowHistory、DocumentInfo 作為 child entity / value object）；(2) OtpRecord（OTP 驗證記錄，獨立生命週期，有 OtpStatus、purpose、expiry、retry count）；(3) ReviewCase（審核工作，關聯 Application，有 ReviewRemark 子集合）；(4) CardProduct（卡片產品資訊，包含 ProductFeature 子集合，唯讀為主）；(5) User（系統用戶，有 Role，由 ADMIN 管理）；SystemParameter（系統參數，簡單的 key-value，可能是 entity 而非完整 aggregate）。Aggregate 邊界：跨 aggregate 的存取透過 ID 引用（Application 用 CardProductId 而非直接持有 CardProduct），不直接導航。

### Category

Domain-Driven Design

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/product/CardProduct.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/user/User.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Being able to enumerate and explain aggregate boundaries is a fundamental DDD competency test.

### Possible follow-up questions

- Why does Application reference CardProductId rather than the full CardProduct object?
- What would happen to the aggregate boundary if Application directly navigated to CardProduct?
- Is SystemParameter an aggregate? What criteria would you use to decide?

### Verification status

- Documentation-only


---

<a id="Q046"></a>

## Q046 — OtpRecord has a VerifyResult type. What does that suggest about how OTP verification is designed?

### 中文筆記

VerifyResult 作為回傳型別，暗示 OTP 驗證的結果有多種可能性（不只是 true/false），並且結果帶有業務意義：OTP 正確（成功）、OTP 錯誤（retry count 遞增）、OTP 過期（直接失敗，不增加 retry）、retry 次數超過上限（鎖定）。使用 VerifyResult 而非拋 exception 的設計：允許 caller 根據具體結果做不同處理，不需要 try-catch 多種 exception 型別；exception 適合「不可預期的錯誤」，OTP 錯誤是預期的業務流程。VerifyResult 可能是 enum 或 sealed class，每個 case 帶有對應的業務含義。

### Category

Domain-Driven Design

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/VerifyResult.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Result types versus exceptions is a design philosophy question; tests whether the candidate has intentional error handling strategy.

### Possible follow-up questions

- In what scenarios would you use a Result type instead of throwing an exception?
- How does OtpAppService translate VerifyResult into an HTTP response?
- What Java construct would make VerifyResult exhaustively pattern-matchable?

### Verification status

- Documentation-only


---

<a id="Q047"></a>

## Q047 — Domain events in TLBank are plain Java objects with no framework annotations. What are the implications of this design?

### 中文筆記

Domain event 是 plain Java（如 record 或 simple class），只帶業務資料（applicationId、timestamp 等），沒有 Spring 或 Jackson 注解。意義：(1) domain event 可被 domain unit test 直接建立和驗證，不需要 Spring context；(2) event handler 可以在不改動 domain event class 的情況下更換（將 @EventListener 替換為 Kafka producer）；(3) event class 是 domain 語言的一部分，不洩漏 infrastructure concern。限制：若 event 需要跨服務傳輸（Kafka），需要在 infrastructure layer 做 domain event → message DTO 的轉換，不能直接序列化 domain event 到 topic（除非有意設計相容性）。

### Category

Domain-Driven Design

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationSubmittedEvent.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/OtpGeneratedEvent.java

### Verified classes and methods

- ApplicationApprovedEvent
- ApplicationSubmittedEvent

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Tests understanding of how to keep domain events framework-agnostic; relevant to event-driven architecture evolution.

### Possible follow-up questions

- What data should a domain event always include?
- How would you serialize a domain event to JSON for Kafka without adding Jackson annotations to the domain event itself?
- Should domain events be immutable? Why?

### Verification status

- Verified


---

<a id="Q048"></a>

## Q048 — Applicant is embedded inside Application. Is Applicant an entity or a value object? How do you tell?

### 中文筆記

判斷 Entity vs Value Object：Entity 有 identity（ID），生命週期獨立，可更改。Value Object 沒有 identity，以值相等，通常不可變，描述 aggregate 的某個屬性。Applicant 在此 project 中：包含姓名、身份證號、聯絡資訊（MobileNumber、Email、Address）等描述性資料，沒有獨立的 ApplicantId，不在 Application 外部被單獨查找或修改，是 Application aggregate 的屬性描述。因此 Applicant 是 value object（或 embeddable value object cluster）。JPA 層面：Applicant 對應 ApplicantEmbeddable（@Embeddable），embed 到 ApplicationEntity table，沒有自己的 primary key。

### Category

Domain-Driven Design

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Applicant.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicantEmbeddable.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Entity vs value object distinction is foundational DDD; many candidates confuse the two.

### Possible follow-up questions

- What @Embeddable and @Embedded JPA annotations are used for Applicant?
- If two Application objects have the same Applicant data, are they equal by DDD definition?
- When would you elevate Applicant to an entity with its own ID?

### Verification status

- Documentation-only


---

<a id="Q049"></a>

## Q049 — ReviewCase appears to be a separate aggregate from Application. Why isn't the review data just part of the Application aggregate?

### 中文筆記

分離的理由（aggregate 邊界設計）：(1) 不同生命週期：Application 的生命週期是「申請人的申請流程」（INIT 到 SUBMITTED），ReviewCase 的生命週期是「審核員的工作流程」（UNDER_REVIEW 到 APPROVED/REJECTED）；(2) 不同的寫入方：申請人操作 Application，審核員操作 ReviewCase，分離避免 concurrent write conflict；(3) 不同的 consistency boundary：application 的狀態和 review 的備註、決定可以獨立 commit，不需要在同一個 transaction 中更新；(4) 若合併，Application aggregate 會膨脹，每次 reviewer 加備註都要 lock 整個 Application。ReviewEventHandler 在 ApplicationSubmittedEvent 後自動建立 ReviewCase，保持兩個 aggregate 的資料一致性。

### Category

Domain-Driven Design

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Aggregate boundary design is a senior-level DDD topic; reveals whether the candidate understands consistency, contention, and lifecycle.

### Possible follow-up questions

- What is the consistency guarantee between Application.status = APPROVED and ReviewCase.status = APPROVED?
- Could there be a scenario where Application is APPROVED but ReviewCase is still UNDER_REVIEW?
- How does the Outbox pattern help with the consistency between these two aggregates?

### Verification status

- Documentation-only


---

<a id="Q050"></a>

## Q050 — DocumentInfo is stored as a list inside Application. Is DocumentInfo an entity or a value object, and how is it persisted?

### 中文筆記

DocumentInfo 描述上傳的文件資訊（DocumentType enum、storage path、上傳時間等），沒有獨立的 business identity，不在 Application 外單獨存取。判定：value object，是 Application aggregate 的子集合。JPA 層面：ApplicationDocumentEntity（@Entity）持有外鍵關聯 ApplicationEntity，有自己的資料庫 table 和 surrogate primary key（技術 ID），但在 domain 層面仍視為 value object，沒有 domain-level DocumentId。這是 DDD 中常見的妥協：JPA 的 @ElementCollection 雖可 embed，但 one-to-many entity 的查詢效能通常較佳，因此用 entity table 存，但 domain 層面把它當 value object 對待。

### Category

Domain-Driven Design

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentInfo.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentType.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationDocumentEntity.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

The JPA/DDD impedance mismatch for collections is a practical question that surfaces in every real project.

### Possible follow-up questions

- What is @ElementCollection, and why was a separate @Entity chosen instead?
- How does Application.validateRequiredDocuments() verify all three DocumentType values are present?
- Could a user upload the same DocumentType twice? What happens to the validation?

### Verification status

- Documentation-only


---

<a id="Q051"></a>

## Q051 — Walk me through every valid state transition in ApplicationStatus. Which transitions are intentionally missing, and why?

### 中文筆記

合法轉換（ALLOWED_TRANSITIONS map）：INIT → OTP_VERIFIED 或 CANCELLED；OTP_VERIFIED → DOCUMENT_UPLOADED 或 CANCELLED；DOCUMENT_UPLOADED → SUBMITTED 或 CANCELLED；SUBMITTED → UNDER_REVIEW；UNDER_REVIEW → APPROVED 或 REJECTED。刻意缺少的轉換：APPROVED/REJECTED/CANCELLED 不在 map 中作為 key，代表這三個狀態是終止態，不可再轉換。SUBMITTED → CANCELLED 不允許（已進入審核佇列的申請不讓申請人自行撤回）。UNDER_REVIEW → CANCELLED 不允許（審核中不可中途取消）。APPROVED → REJECTED 不允許（已核准不可反悔，需走其他業務流程）。

### Category

Application State Machine

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- ApplicationStatus.ALLOWED_TRANSITIONS (private static)
- ApplicationStatus.canTransitionTo()

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Reading a state machine and explaining business intent from code is a key backend skill; tests whether the candidate can map code to business rules.

### Possible follow-up questions

- What HTTP status does the API return when a client sends an invalid state transition?
- Why is CANCELLED reachable from only three statuses and not from SUBMITTED?
- If the business required allowing applicants to withdraw after submission, where exactly would you add that transition?

### Verification status

- Verified


---

<a id="Q052"></a>

## Q052 — ApplicationStatus.canTransitionTo() returns false for null. Why is this defensive check necessary?

### 中文筆記

canTransitionTo(null) 的防禦性檢查：方法開頭有 if (next == null) { return false; }，避免後續 ALLOWED_TRANSITIONS.get(this) 返回的 Set 呼叫 contains(null) 拋 NullPointerException（EnumSet 不允許 null 元素，contains(null) 在 EnumSet 中會拋 NPE）。更深層的理由：呼叫端（Application.transitionTo()）傳入 next 參數，若未做驗證直接呼叫 canTransitionTo(null)，就會在 EnumSet.contains(null) 處拋出非預期的 NPE，而非業務可理解的 WorkflowException。提前 return false 讓呼叫端收到可預期的「不允許轉換」行為，再由 transitionTo() 拋出 WorkflowException，錯誤語意更清楚。

### Category

Application State Machine

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java

### Verified classes and methods

- ApplicationStatus.canTransitionTo()

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Defensive null handling is a hallmark of production-quality code; tests whether the candidate can explain why a one-line null check is architecturally significant.

### Possible follow-up questions

- What would happen without the null check if you called INIT.canTransitionTo(null)?
- Does HashSet.contains(null) behave differently from EnumSet.contains(null)?
- Would adding @NonNull to the parameter signature be a better approach?

### Verification status

- Verified


---

<a id="Q053"></a>

## Q053 — Application.uploadDocuments() allows calling it twice from DOCUMENT_UPLOADED. What business scenario does this support, and what risk does it introduce?

### 中文筆記

業務場景：申請人先上傳一份文件（轉換到 DOCUMENT_UPLOADED），發現格式不對，刪除重傳另一份同類型文件，或補充上傳額外文件。目前的 uploadDocuments() 邏輯：if (status == OTP_VERIFIED) 才轉換狀態，if (status == DOCUMENT_UPLOADED) 則跳過狀態轉換直接 addAll。風險：(1) 可以無限次上傳，documentInfos list 只增不減，同一 DocumentType 可能存在多份文件。validateRequiredDocuments() 用 Set 收集 type，只確認 type 存在，不驗重複。(2) 理論上可以上傳超過預期數量的文件，如果存儲後端是磁碟（LocalDocumentStorageService），攻擊者可反覆上傳耗盡磁碟空間。(3) 重複上傳同 type 的文件沒有 deduplication 或 overwrite 機制，審核員可能看到多份同類型文件而產生混淆。

### Category

Application State Machine

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java

### Verified classes and methods

- Application.uploadDocuments()
- Application.validateRequiredDocuments() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

This is a subtle state machine flaw that reveals whether candidates think about edge cases and security, not just happy paths.

### Possible follow-up questions

- How would you add a file size quota per application to prevent disk exhaustion?
- Should re-uploading the same DocumentType replace or append to the document list?
- What would you change in validateRequiredDocuments() to reject duplicates?

### Verification status

- Verified


---

<a id="Q054"></a>

## Q054 — Application.submit() calls validateRequiredDocuments() before the state transition. Why is this ordering critical?

### 中文筆記

順序關鍵性：validateRequiredDocuments() 先執行，若拋 BusinessException(INCOMPLETE_DOCUMENTS)，狀態轉換的 transitionTo(SUBMITTED, ...) 就不會執行，Application 保持在 DOCUMENT_UPLOADED 狀態。若順序顛倒（先 transitionTo，後 validateRequiredDocuments()），一旦驗證失敗，狀態已被改成 SUBMITTED，但業務規則要求文件不完整時不能 SUBMIT，造成 aggregate 進入不一致狀態。transitionTo() 還會在 workflowHistories 新增記錄，若先轉換後驗證失敗，history 會記錄一筆「成功轉換到 SUBMITTED」的錯誤記錄。這是 aggregate 設計的重要原則：先驗證所有前置條件，再執行有副作用的操作（狀態改變、history 新增）。

### Category

Application State Machine

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- Application.submit()
- Application.validateRequiredDocuments() (private)
- Application.transitionTo() (private)

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Pre-condition validation ordering is a classic correctness question; tests the candidate's understanding of fail-fast design.

### Possible follow-up questions

- Write a unit test that verifies submit() leaves the status unchanged when documents are incomplete.
- What does the existing ApplicationTest.submit_whenDocumentsIncomplete_shouldThrowBusinessException() assert?
- Could validateRequiredDocuments() be moved into transitionTo() as a general pre-condition hook?

### Verification status

- Verified


---

<a id="Q055"></a>

## Q055 — Why does Application.cancel() not use ALLOWED_TRANSITIONS the way transitionTo() does? It checks CANCELLABLE_STATUSES instead.

### 中文筆記

設計意圖：ALLOWED_TRANSITIONS 表示「從某個狀態可以合法轉換到哪些狀態」，這是狀態機的通用規則。但 cancel() 是個特殊的業務操作，語意是「申請人主動放棄」，而非純粹的狀態機轉換。用 CANCELLABLE_STATUSES（EnumSet.of(INIT, OTP_VERIFIED, DOCUMENT_UPLOADED)）明確表示「在哪些狀態下申請人有權取消」，這是業務規則的顯式宣告，比隱含在 ALLOWED_TRANSITIONS 的 CANCELLED key 中更容易理解。注意 ALLOWED_TRANSITIONS 的 INIT 和 OTP_VERIFIED 的 value 也包含 CANCELLED，表示可以 via transitionTo 到 CANCELLED，但 cancel() 用自己的 set 做門檻判斷，使得 DOCUMENT_UPLOADED → CANCELLED 雖然不在 ALLOWED_TRANSITIONS，卻可以透過 cancel() 達成（cancel() 直接呼叫 transitionTo(CANCELLED, ...)，而 transitionTo() 呼叫 canTransitionTo() 驗證，所以 DOCUMENT_UPLOADED → CANCELLED 必須也在 ALLOWED_TRANSITIONS 中才能成功）。

### Category

Application State Machine

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java

### Verified classes and methods

- Application.cancel()
- Application
- ApplicationStatus.ALLOWED_TRANSITIONS (private static)

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Two overlapping but distinct rule sets for cancellation is a subtle design question; reveals whether the candidate can trace through the code logic to identify consistency requirements.

### Possible follow-up questions

- Is DOCUMENT_UPLOADED → CANCELLED present in ALLOWED_TRANSITIONS? What happens if it is missing?
- Could you simplify cancel() to just call transitionTo(CANCELLED) without the CANCELLABLE_STATUSES check?
- When would you add SUBMITTED → CANCELLED to the transition rules?

### Verification status

- Verified


---

<a id="Q056"></a>

## Q056 — ApplicationTest runs with no Spring context. How is this achieved, and what does it tell you about the domain layer design?

### 中文筆記

無 Spring context 的實現：ApplicationTest 是純 JUnit 5（@Test 注解即可），沒有 @SpringBootTest、@ExtendWith(SpringExtension.class) 或任何 Spring test 注解。Application aggregate 是純 Java 類別（@Getter、@Builder 是 Lombok，compile-time，無 runtime Spring 依賴），沒有 @Entity、@Service 等 Spring/JPA 注解，不需要 Spring context 就能實例化。測試直接用 Application.builder()...build() 建立 aggregate，呼叫方法，用 AssertJ 驗證狀態。這告訴：domain layer 的設計達到了 clean architecture 的核心目標——業務邏輯可以在不啟動任何框架的情況下快速、獨立地測試，test execution 速度快（毫秒級），不需要資料庫或 Spring container。

### Category

Application State Machine

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- Application

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The ability to test domain logic without a framework is a key Clean Architecture benefit; tests whether the candidate can connect architecture to testability.

### Possible follow-up questions

- How long does ApplicationTest take to run compared to ApplicationFlowIntegrationTest?
- What would you need to change in Application for ApplicationTest to require Spring?
- Write a test method name that verifies the approve() → reject() transition fails.

### Verification status

- Verified


---

<a id="Q057"></a>

## Q057 — workflowHistories is annotated with @Builder.Default in Application. What happens without this annotation, and why does it matter for the state machine?

### 中文筆記

不加 @Builder.Default 的後果：Lombok @Builder 生成的 builder 預設對 List 欄位不初始化（值為 null），除非 field 有初始值且加了 @Builder.Default。若 workflowHistories = null，Application.transitionTo() 呼叫 workflowHistories.add(WorkflowHistory...) 會拋 NullPointerException。@Builder.Default 確保 workflowHistories = new ArrayList<>() 即使在 builder pattern 下也能正確初始化。在 state machine context 中，每次狀態轉換都必須能記錄 history，若 list 為 null 就整個 workflow 崩潰，影響業務操作的原子性。同理 documentInfos 也有相同的 @Builder.Default。

### Category

Application State Machine

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- Application
- Application.transitionTo() (private)

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

@Builder.Default is a Lombok subtlety that trips up developers who use the annotation without understanding it; tests practical Lombok knowledge.

### Possible follow-up questions

- What is the difference between @Builder.Default and assigning a default in the constructor?
- How do you verify in a test that workflowHistories is properly initialized after builder construction?
- Does @Builder.Default affect serialization with Jackson?

### Verification status

- Verified


---

<a id="Q058"></a>

## Q058 — WorkflowHistory is created inside transitionTo(). Who owns the responsibility for generating the history record — the aggregate or the caller?

### 中文筆記

由 aggregate 自身生成：transitionTo() 是 private 方法，在驗證通過後自動建立 WorkflowHistory（fromStatus、toStatus、operator、remark、operatedAt）並加入 workflowHistories。這是正確的設計：aggregate 是業務不變式的守護者，history 是狀態轉換的一部分，不應讓 caller（application service）有機會「轉換狀態但忘記記錄 history」或「記錄錯誤的 history」。若改為由 caller 建立 history 並傳入 aggregate，就打破了封裝，讓業務規則分散在 application service 和 aggregate 之間。目前設計讓 Application.approve("reviewer", "Documents look good") 一個呼叫完成狀態轉換 + history 記錄，caller 不需要知道內部細節。

### Category

Application State Machine

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/WorkflowHistory.java

### Verified classes and methods

- Application.transitionTo() (private)
- WorkflowHistory

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Encapsulation of aggregate side effects is a DDD best practice; tests whether the candidate understands why keeping history generation inside the aggregate matters.

### Possible follow-up questions

- What is the operatedAt timestamp set to, and could there be a timezone issue?
- How would you test that exactly one WorkflowHistory is added per state transition?
- What happens to WorkflowHistory records stored in Application when the aggregate is saved to the database?

### Verification status

- Verified


---

<a id="Q059"></a>

## Q059 — APPROVED and REJECTED are not keys in ALLOWED_TRANSITIONS. What is the implication for canTransitionTo() when called on these statuses?

### 中文筆記

ALLOWED_TRANSITIONS.get(APPROVED) 返回 null（key 不存在）。canTransitionTo() 的邏輯：Set<ApplicationStatus> allowed = ALLOWED_TRANSITIONS.get(this) → 當 this == APPROVED 時 allowed == null，然後 return allowed != null && allowed.contains(next) → null != null 為 false，直接回傳 false。這意味著對任何狀態 next，APPROVED.canTransitionTo(next) 恆為 false，REJECTED.canTransitionTo(next) 恆為 false，CANCELLED.canTransitionTo(next) 恆為 false。這是終止態（terminal state）的正確實作：不需要在 map 中為這些狀態加 {APPROVED, emptySet()}，缺少 key 本身就代表「沒有任何合法的後繼狀態」。

### Category

Application State Machine

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java

### Verified classes and methods

- ApplicationStatus.canTransitionTo()
- ApplicationStatus.ALLOWED_TRANSITIONS (private static)

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Understanding how a null-safe state machine handles terminal states requires careful code reading; tests attention to detail.

### Possible follow-up questions

- Is this implementation more or less readable than adding APPROVED → emptySet() to the map?
- Write a parameterized unit test that verifies all three terminal states reject every transition.
- What would @EnumSource(mode = EXCLUDE, names = {"INIT", "OTP_VERIFIED", ...}) look like in ApplicationTest?

### Verification status

- Verified


---

<a id="Q060"></a>

## Q060 — OtpRecord.verify() injects a Clock parameter rather than calling LocalDateTime.now() directly. How does this design appear in OtpCleanupScheduler, and why is Clock consistently used?

### 中文筆記

一致性的 Clock 使用：OtpRecord.verify() 接受 Clock clock 參數，呼叫 LocalDateTime.now(clock) 和 expiredAt.isBefore(LocalDateTime.now(clock))。OtpCleanupScheduler 注入 Clock clock bean，在 cleanupExpiredOtps() 中以 LocalDateTime.now(clock) 作為 cutoff 傳給 otpRepository.markExpiredBefore()。LocalDocumentStorageService 和 InMemoryCacheStore 也都注入 Clock。好處：測試中注入 Clock.fixed(Instant, ZoneId) 可以凍結時間，驗證「恰好在 expiry 前 1 秒」和「恰好在 expiry 後 1 秒」的邊界條件，不依賴系統時間，測試可重現。Clock bean 在 CommonConfig 中定義為 Clock.systemDefaultZone()，所有需要時間的地方用同一個 bean。

### Category

Application State Machine

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/config/CommonConfig.java

### Verified classes and methods

- OtpRecord.verify()
- OtpRecord.isExpired()
- OtpCleanupScheduler.cleanupExpiredOtps()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Clock injection is a well-known testability pattern; confirms the candidate understands why time-dependent code is notoriously hard to test without it.

### Possible follow-up questions

- Write the setup code for an OtpRecord test that verifies expiry using Clock.fixed().
- What timezone is Clock.systemDefaultZone() using? Is this configurable?
- Could a timezone mismatch between the application server and SQL Server cause OTP expiry bugs?

### Verification status

- Verified


---

<a id="Q061"></a>

## Q061 — All REST endpoints return ApiResponse<T> as a wrapper. What fields does this envelope contain, and what design decision does it represent?

### 中文筆記

ApiResponse<T> 欄位（record）：boolean success（成功/失敗旗標）、T data（payload，失敗時為 null）、String errorCode（ErrorCode.name()，成功時為 null）、String message（描述訊息）、List<FieldErrorDetail> fieldErrors（validation 錯誤時的欄位細節）、LocalDateTime timestamp（回應產生時間）。設計決策：統一信封（consistent envelope）讓 API client 可以靠 success 欄位快速判斷，不需要依賴 HTTP status code 的語意；錯誤時 errorCode 是 machine-readable 的字串（如 "INCOMPLETE_DOCUMENTS"），client 可以根據它做精確的錯誤處理邏輯。對比：純 HTTP status code 方案需要 client 解析 4xx 的 body 才能知道具體錯誤，ApiResponse 的 errorCode 更明確。

### Category

REST API Design and OpenAPI

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/response/FieldErrorDetail.java

### Verified classes and methods

- ApiResponse.success()
- ApiResponse.error()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Consistent API envelope design is a senior engineering concern; tests whether the candidate can explain the trade-offs.

### Possible follow-up questions

- What is the disadvantage of a consistent envelope approach compared to pure REST HTTP status codes?
- Why does ApiResponse include timestamp? Is this always useful?
- Should fieldErrors be non-null or null when there are no field errors?

### Verification status

- Verified


---

<a id="Q062"></a>

## Q062 — The API base path is /api/v1/. Why is the version in the URL, and what would a migration to /api/v2/ look like?

### 中文筆記

URL 版本化的理由：最常見的 REST API 版本化策略，對 client 透明，可以直接在 curl/browser 看到版本，CDN/load balancer 可以基於 path 路由。目前所有 @RequestMapping 都是 /api/v1/applications、/api/v1/otp 等，配合 @RestController。遷移到 v2 的選項：方案一（parallel controllers）：新增 v2 controller package，舊 v1 controllers 繼續存在一段時間（deprecation period）。方案二（路由層抽象）：shared service layer，v1 和 v2 controllers 各自映射不同的 DTO，底層 application service 共用。方案三（Spring RequestMappingHandlerMapping + 版本協商）：比較少見，複雜度高。目前 codebase 只有 v1，v2 migration 的 plan 在 20-maintenance-and-future-enhancement.md 中提及（JWT filter chain 走 /api/v2/**）。

### Category

REST API Design and OpenAPI

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

API versioning strategy is a standard backend engineering question; reveals whether the candidate has thought beyond the current state.

### Possible follow-up questions

- What are the alternatives to URL versioning (header versioning, content negotiation)?
- How would you deprecate a v1 endpoint without breaking existing clients?
- Could you have v1 and v2 of the same endpoint in one application simultaneously?

### Verification status

- Documentation-only


---

<a id="Q063"></a>

## Q063 — POST /api/v1/applications accepts an optional Idempotency-Key header. Why optional, and what happens when it is absent?

### 中文筆記

Optional 的設計：@RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey，header 缺失時 idempotencyKey 為 null。IdempotencyService.execute() 的邏輯：if (idempotencyKey == null || idempotencyKey.isBlank()) { return action.get(); }，直接執行業務邏輯，不經過 idempotency 處理。設計理由：向後相容性（不強制要求舊版 client 攜帶 header）；讓 API 在沒有 Redis 的環境（如某些 staging 配置）也能正常使用；在 dev 環境手動測試不需要每次帶 header。代價：沒有帶 header 的重複 POST 不受保護，會建立多筆 Application，client 必須自行保證重試時帶同樣的 key。

### Category

REST API Design and OpenAPI

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java

### Verified classes and methods

- ApplicationApiController.createApplication()
- IdempotencyService.execute()

### Execution flow

1. Controller receives optional Idempotency-Key header on create application
2. IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find()
3. On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock()
4. On hit with same hash, rebuild prior ResponseEntity; on hash conflict, return conflict error

### Why this may be asked

Optional idempotency keys are a deliberate API design choice with client responsibility implications; tests API contract thinking.

### Possible follow-up questions

- Should the Idempotency-Key be required in a production banking API? What is the risk if it is optional?
- What HTTP status does the API return if the same key is sent with a different request body?
- How would you document the idempotency behavior in OpenAPI/Swagger?

### Verification status

- Partially verified


---

<a id="Q064"></a>

## Q064 — StandardApiResponses is used as a meta-annotation on controller methods. What does it do, and why is it a better pattern than repeating @ApiResponse annotations?

### 中文筆記

@StandardApiResponses 是自訂的 composite annotation（@Target(ElementType.METHOD)），將常用的 Swagger/OpenAPI @ApiResponse 組合在一起（如 200、400、401、403、409、500 的標準文件說明）。優點：避免在每個 controller method 上重複相同的 6-7 個 @ApiResponse 注解，減少 boilerplate；修改標準回應說明時只需改 StandardApiResponses，不需要找遍所有 controller；讓 controller method 的注解更簡潔，focus 在業務語意（@Operation(summary = "Create application")），而非重複的文件模板。在 OpenAPI 文件中效果與逐個寫相同，但程式碼可維護性更高。

### Category

REST API Design and OpenAPI

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/config/StandardApiResponses.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java

### Verified classes and methods

- ApplicationApiController.createApplication()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Custom annotation composition is an underused Java technique; reveals whether the candidate knows how to reduce annotation boilerplate.

### Possible follow-up questions

- How does Java allow annotations to be composed into a meta-annotation?
- Is @StandardApiResponses a Spring annotation or a plain Java annotation?
- What retention policy must @StandardApiResponses have for it to work at runtime with Swagger?

### Verification status

- Verified


---

<a id="Q065"></a>

## Q065 — Document upload uses @RequestParam MultipartFile file rather than @RequestBody. Why?

### 中文筆記

MultipartFile 用 @RequestParam 而非 @RequestBody：@RequestBody 用於解析 JSON/XML 請求體，Content-Type 為 application/json；MultipartFile 是二進位/表單資料，Content-Type 必須為 multipart/form-data，由 Spring 的 MultipartResolver 解析，透過 @RequestParam 注入。在 /documents endpoint，同時接收 documentType（@RequestParam DocumentType，字串參數）和 file（@RequestParam MultipartFile，二進位內容），都透過 multipart form data 傳遞。若用 @RequestBody 接收文件，client 必須將文件 base64 encode 成 JSON，HTTP body 大幅膨脹，且 Spring 的標準 JSON message converter 不支援直接反序列化為 MultipartFile。

### Category

REST API Design and OpenAPI

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java

### Verified classes and methods

- ApplicationApiController.uploadDocument()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

@RequestParam vs @RequestBody is a common Spring MVC question; file upload specifics add practical depth.

### Possible follow-up questions

- What HTTP header does the client need to set when uploading a file to this endpoint?
- How would you limit the maximum upload file size in Spring Boot configuration?
- What is MockMultipartFile used for in ApplicationFlowIntegrationTest?

### Verification status

- Verified


---

<a id="Q066"></a>

## Q066 — The action endpoints use POST /api/v1/applications/{id}/actions/submit instead of PATCH /api/v1/applications/{id}. Why is this design preferable for workflow actions?

### 中文筆記

POST /actions/{actionName} 模式的優點：PATCH 方法的語意是「部分更新資源」，要求 client 傳入要修改的欄位；而 submit、cancel、approve 是業務操作（business command），不是欄位更新，語意上更接近 POST（執行一個動作）。PATCH 設計需要 client 傳入 {"status": "SUBMITTED"}，但這讓 client 可以嘗試傳入任意 status，server 必須額外驗證；POST /actions/submit 讓 API 語意明確，client 不能繞過業務規則直接設置 status。REST 純粹主義認為 PATCH 是更 RESTful 的，但業務驅動的 API 設計（Resource-Based Action）越來越被接受，符合 DDD 的 command 語意。Spring Petclinic、Stripe、Stripe API 等都採用類似的 action endpoint 模式。

### Category

REST API Design and OpenAPI

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

RESTful design philosophy for workflow actions is a classic interview topic; tests whether the candidate can articulate a design decision.

### Possible follow-up questions

- Is POST /actions/submit idempotent? Should it be?
- How would you document these action endpoints differently from CRUD endpoints in OpenAPI?
- What does the Stripe API use for similar scenarios?

### Verification status

- Documentation-only


---

<a id="Q067"></a>

## Q067 — ApplicationApiController.getApplication() returns ApiResponse<ApplicationDetailResponse> directly, not ResponseEntity<ApiResponse<...>>. Why does only the create endpoint use ResponseEntity?

### 中文筆記

ResponseEntity 的使用場景：createApplication() 用 ResponseEntity.status(HttpStatus.CREATED).body(...) 是因為需要設置 HTTP 201 狀態碼（資源被建立），也因為 IdempotencyService.execute() 的回傳型別就是 ResponseEntity<ApiResponse<T>>（需要重建 idempotency 快取中的 HTTP status）。getApplication() 直接回傳 ApiResponse<ApplicationDetailResponse>，Spring MVC 會自動以 HTTP 200 回應，因為查詢操作不需要自訂 status code，@ResponseBody 由 @RestController 隱含提供。這個設計遵循「只在需要時才用 ResponseEntity」原則：若 HTTP 200 就足夠，直接回傳 response body 讓 code 更簡潔。

### Category

REST API Design and OpenAPI

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java

### Verified classes and methods

- ApplicationApiController.createApplication()
- ApplicationApiController.getApplication()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

When to use ResponseEntity versus a direct return is a common Spring MVC question; tests understanding of the framework's response handling.

### Possible follow-up questions

- How does Spring MVC determine the HTTP status when you return a non-ResponseEntity from a @RestController?
- What @ResponseStatus annotation could replace ResponseEntity.status(CREATED) on the method?
- Could getApplication() ever need to return 204 No Content? When?

### Verification status

- Verified


---

<a id="Q068"></a>

## Q068 — The project uses native SQL queries (nativeQuery = true) in ApplicationJpaRepository for some report queries. What are the risks of native queries?

### 中文筆記

Native query 的風險：(1) 資料庫方言耦合：GROUP BY status、GROUP BY product_id 在 H2 和 SQL Server 語法差異小，但複雜查詢可能不相容（H2 MODE=MSSQLServer 只是部分相容），導致 H2 dev 環境通過但 SQL Server staging 失敗。(2) 型別安全缺失：List<Object[]> 回傳型別需要手動 cast（如 (String) row[0]、((Number) row[1]).longValue()），cast 錯誤在 runtime 才暴露，不在 compile-time。(3) Hibernate 不做 JPQL 改寫：無法自動分頁、filter，需要手動拼接 SQL。在此 project 中使用 native query 的理由：GROUP BY 和聚合查詢用 JPQL 比較繁瑣，report 查詢效能要求較高，native SQL 更直接。

### Category

REST API Design and OpenAPI

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java

### Verified classes and methods

- ApplicationJpaRepository

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Native query risks in a dual-database environment are a practical concern unique to this project's design.

### Possible follow-up questions

- How would you test these native queries against SQL Server in CI without a SQL Server instance?
- What is a Spring Data Projection interface, and how could it replace List<Object[]> here?
- Would a @NamedNativeQuery be preferable to inline nativeQuery = true?

### Verification status

- Verified


---

<a id="Q069"></a>

## Q069 — CreateApplicationRequest is a record with @Valid @NotNull ApplicantRequest applicant. What does @Valid on a nested field do?

### 中文筆記

@Valid 在巢狀欄位上的作用：觸發 cascade validation（級聯驗證）。@NotNull 確保 applicant 欄位本身不為 null；@Valid 確保 null 檢查通過後，進一步驗證 ApplicantRequest 內部的所有 constraint（ApplicantRequest.fullName 的 @NotBlank、dateOfBirth 的 @NotNull 等）。若只有 @NotNull 沒有 @Valid，即使 applicant 不為 null，其內部欄位仍不會被驗證，client 可以傳入 {"applicant": {}, "cardProductId": "TL-CLASSIC"} 而不報錯。同理 ApplicantRequest 中的 @Valid @NotNull AddressRequest address 再觸發 AddressRequest 的驗證，形成三層 cascade。

### Category

Validation and Exception Handling

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/CreateApplicationRequest.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/ApplicantRequest.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/AddressRequest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Cascade validation is a common Spring Boot validation mistake; candidates who never tested deeply nested DTOs often miss @Valid.

### Possible follow-up questions

- What would the MethodArgumentNotValidException error response look like for a missing fullName?
- How does GlobalExceptionHandler.handleValidationException() extract field-level errors?
- Is @Validated the same as @Valid? When would you use @Validated instead?

### Verification status

- Documentation-only


---

<a id="Q070"></a>

## Q070 — GlobalExceptionHandler catches AccessDeniedException. Why is catching Spring Security exceptions in a @RestControllerAdvice necessary?

### 中文筆記

必要性：Spring Security 的 AccessDeniedException 和 AuthenticationException 預設由 ExceptionTranslationFilter 攔截，轉發到 AccessDeniedHandler/AuthenticationEntryPoint。但 @RestControllerAdvice 的 @ExceptionHandler 只能捕捉 dispatcher servlet 層的 exception，而 security filter 在 dispatcher 之前執行。這裡 GlobalExceptionHandler 能捕捉到 AccessDeniedException，表示 exception 是從 controller 或 service 拋出的（例如 @PreAuthorize 失敗），不是從 filter 層。若是 URL-based 存取控制拒絕（filter 層），則走 CustomAccessDeniedHandler。兩個 handler 共存，確保不論 exception 從哪層拋出都有一致的 JSON 回應格式。

### Category

Validation and Exception Handling

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/CustomAccessDeniedHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- GlobalExceptionHandler.handleAccessDeniedException()
- CustomAccessDeniedHandler

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

The dual-handler pattern for security exceptions is subtle and reveals deep Spring Security knowledge.

### Possible follow-up questions

- What is the difference between AuthenticationException and AccessDeniedException?
- How does Spring Security decide whether to invoke the AuthenticationEntryPoint or AccessDeniedHandler?
- Would removing handleAccessDeniedException from GlobalExceptionHandler break anything?

### Verification status

- Verified


---

<a id="Q071"></a>

## Q071 — WorkflowException and BusinessException are two separate exception classes. When is each used, and how does GlobalExceptionHandler treat them differently?

### 中文筆記

區別：BusinessException：業務邏輯錯誤，帶有 ErrorCode enum（如 INCOMPLETE_DOCUMENTS、OTP_EXPIRED）和描述訊息，映射到多種 HTTP status（400、404、409 等）由 switch expression 決定。WorkflowException：狀態機轉換錯誤，語意更窄（不合法的狀態轉換），不帶 ErrorCode enum（因為所有狀態機錯誤都映射到同一個 INVALID_WORKFLOW_TRANSITION code）。GlobalExceptionHandler 的差異：handleBusinessException() 用 switch on ex.getErrorCode() 映射 HTTP status；handleWorkflowException() 固定回 HTTP 409 CONFLICT，使用 ErrorCode.INVALID_WORKFLOW_TRANSITION。分離設計讓 caller 可以 catch 特定類型，也讓 API 回應語意更清晰。

### Category

Validation and Exception Handling

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/exception/BusinessException.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/exception/WorkflowException.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java

### Verified classes and methods

- GlobalExceptionHandler.handleBusinessException()
- GlobalExceptionHandler.handleWorkflowException()

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Two exception types for two different semantic categories shows intentional design thinking; tests whether the candidate can articulate the distinction.

### Possible follow-up questions

- Why is HTTP 409 Conflict the right status for a workflow transition error?
- Should WorkflowException extend BusinessException? What are the pros and cons?
- What ErrorCode would you add if you needed a new category of business error?

### Verification status

- Verified


---

<a id="Q072"></a>

## Q072 — GlobalExceptionHandler truncates exception messages at 500 characters in the audit log path, but not in the API response. Is this correct?

### 中文筆記

現象：AuditAspect.truncateMessage(ex.getMessage()) 將 exception message 截斷至 500 字元，但 GlobalExceptionHandler 的 ApiResponse.error(code, ex.getMessage(), ...) 直接使用完整的 message。截斷在 audit log 的理由：audit log 的 detail 欄位可能有 VARCHAR 長度限制（資料庫 schema 設定），截斷避免 JPA/JDBC 的 DataException: Value too long for column。API response 中不截斷：回應給 client 的 message 應該完整，以便 client 理解錯誤。潛在問題：若 exception message 本身含有 PII 或 internal stack trace，完整 message 暴露給 API client 可能是安全風險。GlobalExceptionHandler.handleException() 捕捉 unhandled Exception 時，log.error("Unhandled exception", ex) 記錄 stack trace，但只回傳 SYSTEM_ERROR，不洩漏 internal detail——這是正確的做法。

### Category

Validation and Exception Handling

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java

### Verified classes and methods

- AuditAspect.truncateMessage() (private)
- GlobalExceptionHandler.handleBusinessException()
- GlobalExceptionHandler.handleException()

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Security consciousness around error message exposure is a production awareness question; tests whether the candidate thinks about what clients should and should not see.

### Possible follow-up questions

- What information in a BusinessException message could be a PII leak?
- How would you add rate limiting to error responses to prevent error enumeration attacks?
- Should the timestamp field in ApiResponse be removed to prevent information disclosure?

### Verification status

- Verified


---

<a id="Q073"></a>

## Q073 — @Auditable is a custom annotation with a single action() attribute. How is the annotation processor (AOP aspect) discovered at runtime?

### 中文筆記

運行時發現機制：@Auditable 有 @Retention(RetentionPolicy.RUNTIME) 確保 annotation 在 runtime 可被 reflection 讀取；AuditAspect 是 @Component，Spring 掃描到它；AuditAspect 有 @Aspect（AspectJ annotation），Spring AOP 子系統識別為切面；pointcut 是 @Around("@annotation(auditable)")，Spring AOP 在 proxy 中攔截所有帶 @Auditable 的方法呼叫，並注入具體的 Auditable 實例（可直接讀取 auditable.action()）。@EnableAspectJAutoProxy 由 Spring Boot AutoConfiguration 自動啟用。所以整個鏈：@EnableAspectJAutoProxy → 掃描 @Aspect → proxy 包裝目標 bean → 方法呼叫時執行 @Around advice。

### Category

Validation and Exception Handling

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/Auditable.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java

### Verified classes and methods

- AuditAspect.audit()

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Understanding how AOP and custom annotations connect requires knowing Spring internals; a frequent senior interview topic.

### Possible follow-up questions

- What is RetentionPolicy.CLASS versus RetentionPolicy.RUNTIME? Could @Auditable use CLASS?
- What happens if @Auditable is placed on a private method?
- How would Spring handle @Auditable if the bean is not proxied (e.g., when called via this.method())?

### Verification status

- Verified


---

<a id="Q074"></a>

## Q074 — What happens in AuditAspect when the audited method throws an exception? Is the audit log written?

### 中文筆記

exception 情況下的 audit 行為：AuditAspect.audit() 的 @Around 邏輯：try { Object result = joinPoint.proceed(); auditLogWriter.saveAsync(SUCCESS log); return result; } catch (Throwable ex) { auditLogWriter.saveAsync(FAILURE log); throw ex; } finally { AuditContext.clear(); }。也就是說：exception 拋出時，catch 區塊執行 auditLogWriter.saveAsync(FAILURE log)，記錄失敗的 audit log，然後 throw ex 重新拋出，讓 GlobalExceptionHandler 繼續處理。AuditContext.clear() 在 finally 確保清除，不論成功或失敗。這個設計保證了每個 @Auditable 操作都有一筆 audit log（成功或失敗），不會有漏記。resolveFailureAction() 還能為特定 action（如 OTP_VERIFY_SUCCESS）映射到不同的失敗 action（OTP_VERIFY_FAILED）。

### Category

Validation and Exception Handling

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java

### Verified classes and methods

- AuditAspect.audit()
- AuditAspect.resolveFailureAction() (private)
- AuditLogWriter.saveAsync()

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

The dual-log (success + failure) audit pattern is a correctness test that reveals whether the candidate understands exception flow in AOP.

### Possible follow-up questions

- What happens if auditLogWriter.saveAsync() itself throws an exception inside the catch block?
- Is AuditContext.clear() in finally important even when the method succeeds? Why?
- Could the audit write ever cause the business operation to appear to succeed when it failed?

### Verification status

- Verified


---

<a id="Q075"></a>

## Q075 — FieldErrorDetail is used for validation errors. What information does it carry, and how is it populated?

### 中文筆記

FieldErrorDetail 是一個 record（或 class）包含：field（失敗驗證的欄位名稱，如 "applicant.fullName"）、message（constraint message，如 "must not be blank"）。填入方式：GlobalExceptionHandler.handleValidationException() 接收 MethodArgumentNotValidException，呼叫 ex.getBindingResult().getFieldErrors()，每個 FieldError 映射為 new FieldErrorDetail(error.getField(), error.getDefaultMessage())，最後包在 ApiResponse.error(VALIDATION_FAILED, "Validation failed", fieldErrors) 中。巢狀欄位錯誤的 field 名稱是 dot-notation（applicant.mobile），不是扁平的 mobile，讓 client 可以定位到具體的巢狀欄位。

### Category

Validation and Exception Handling

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/response/FieldErrorDetail.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java

### Verified classes and methods

- GlobalExceptionHandler.handleValidationException()

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Structured validation error responses are a basic API quality requirement; tests knowledge of Spring's binding result API.

### Possible follow-up questions

- How does the dot-notation field path look for a failing constraint on applicant.address.city?
- What is the difference between error.getDefaultMessage() and error.getCode() for a constraint?
- Could you return the constraint annotation name (e.g., @NotBlank) in the error detail?

### Verification status

- Verified


---

<a id="Q076"></a>

## Q076 — AuditIpResolver is used to extract the client IP address. Why is this non-trivial, and what does the resolver need to handle?

### 中文筆記

IP 解析的複雜性：簡單的 request.getRemoteAddr() 只能取到直接連線的 IP（在 reverse proxy/load balancer 後面會是 proxy 的 IP，不是真實 client IP）。AuditIpResolver 需要優先讀取 HTTP header，依序嘗試：X-Forwarded-For（最常見，proxy 轉發鏈）、X-Real-IP（Nginx 直接設定）、Proxy-Client-IP、WL-Proxy-Client-IP 等，最後才 fallback 到 request.getRemoteAddr()。X-Forwarded-For 可能包含多個 IP（client, proxy1, proxy2），需要取第一個（最左邊）才是原始 client。安全考量：client 可以偽造這些 header，所以在真實 production 環境，應只信任來自受信任 proxy 的 X-Forwarded-For。此處用於 audit log，偽造的 IP 會被記錄，不影響業務邏輯安全性，但 audit 的可信度降低。

### Category

Validation and Exception Handling

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditIpResolver.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java

### Verified classes and methods

- AuditIpResolver.resolveClientIp()

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

IP resolution behind a reverse proxy is a production deployment detail that trips up engineers who test only locally.

### Possible follow-up questions

- What does X-Forwarded-For: 1.2.3.4, 10.0.0.1 mean, and which IP should you use?
- How would you protect against IP spoofing via X-Forwarded-For in a banking system?
- Does the Docker Compose setup affect IP resolution, and how?

### Verification status

- Verified


---

<a id="Q077"></a>

## Q077 — Spring Security BCryptPasswordEncoder is configured with strength 12. What does the strength parameter control, and what is the performance trade-off?

### 中文筆記

BCrypt strength 12：strength 參數（work factor）決定 bcrypt 的 cost，即 2^12 = 4096 次的 key schedule 迭代。CPU work factor：strength 每增加 1，計算時間約 ×2。在現代硬體上 strength 12 大約需要 100–300 ms 驗證一個密碼，strength 10（Spring 預設）約 25–75 ms。Security vs UX 取捨：較高的 strength 使 brute-force 攻擊更慢（攻擊者試 1000 個密碼需要 100–300 秒），但登入時每次驗證也需要 100–300 ms。對銀行應用場景（有嚴格的安全要求），strength 12 是合理的選擇。若用戶量大（1000 並發登入），每個登入請求占用 300 ms CPU 可能成為瓶頸。BCryptPasswordEncoder 是 Spring Security 的預設推薦，不使用 MD5/SHA1。

### Category

Spring Security

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- SecurityConfig.passwordEncoder()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

BCrypt work factor is a security engineering question with real performance implications; tests whether the candidate understands the trade-off.

### Possible follow-up questions

- What happens if you increase BCrypt strength from 12 to 14 in a live system?
- How does BCryptPasswordEncoder.matches() work? Does it require the same strength to verify?
- What is Argon2 and when would it be preferred over BCrypt?

### Verification status

- Verified


---

<a id="Q078"></a>

## Q078 — UserDetailsServiceImpl throws DisabledException when a user's isEnabled() returns false. How does Spring Security handle this, and what does the client receive?

### 中文筆記

處理流程：loadUserByUsername() 拋 DisabledException（Spring Security 的 AuthenticationException 子類別）。DaoAuthenticationProvider 捕捉到 DisabledException，傳給 AuthenticationManager，最終觸發 LoginFailureHandler.onAuthenticationFailure()。LoginFailureHandler 接收 AuthenticationException，將其轉換為 HTTP 401 JSON response（ApiResponse.error(UNAUTHORIZED, "Invalid username or password", null)）。注意：LoginFailureHandler 可能對所有 AuthenticationException 回傳相同的 generic message，不區分「帳號不存在」和「帳號被停用」，避免 user enumeration（攻擊者無法透過不同的錯誤訊息判斷帳號是否存在）。SecurityIntegrationTest.login_withDisabledUser_shouldReturn401Json() 驗證此行為。

### Category

Spring Security

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginFailureHandler.java
- sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java

### Verified classes and methods

- UserDetailsServiceImpl.loadUserByUsername()
- LoginFailureHandler.onAuthenticationFailure()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

User enumeration via distinct error messages is a subtle security concern; tests production security thinking.

### Possible follow-up questions

- What is user enumeration, and why is returning "Invalid username or password" for all failures safer?
- How does Spring Security differentiate between UsernameNotFoundException and DisabledException?
- Would you add account lockout after N failed attempts? Where in the code would that logic live?

### Verification status

- Verified


---

<a id="Q079"></a>

## Q079 — LoginSuccessHandler writes the audit log synchronously (auditLogRepository.save()) rather than using AuditLogWriter.saveAsync(). Is this intentional, and what is the risk?

### 中文筆記

觀察：LoginSuccessHandler.onAuthenticationSuccess() 直接呼叫 auditLogRepository.save(AuditLog.builder()...build())（同步），而其他業務操作透過 AuditAspect + AuditLogWriter.saveAsync() 非同步寫入。可能的理由：login 操作本身是在 Spring Security filter chain 中執行，不在 dispatcher servlet 的 @Async context 中，直接同步寫更可靠。風險：(1) 若 auditLogRepository.save() 失敗（DB 不可用），login 流程可能拋出 exception（雖然 handler 內可能有 try-catch），影響登入成功的回應；(2) 寫 audit log 占用了 login request 的回應時間。改善方式：將 audit log 寫入也抽到 AuditLogWriter.saveAsync()，login 的 audit 不應阻塞回應。此外，LoginSuccessHandler 還更新了 user.updateLastLoginAt(now)（synchronous DB write），這是更必要的同步操作。

### Category

Spring Security

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java

### Verified classes and methods

- LoginSuccessHandler.onAuthenticationSuccess()
- AuditLogWriter.saveAsync()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Inconsistency between sync and async audit writes is a subtle technical debt question that tests observational skills.

### Possible follow-up questions

- What would happen to the login response if the database is temporarily unavailable during the audit write?
- Should updateLastLoginAt() be synchronous or asynchronous? Does it matter for the user experience?
- How would you refactor LoginSuccessHandler to use AuditLogWriter.saveAsync() consistently?

### Verification status

- Verified


---

<a id="Q080"></a>

## Q080 — MdcLoggingFilter extends OncePerRequestFilter. What does OncePerRequestFilter guarantee, and why does it matter for MDC?

### 中文筆記

OncePerRequestFilter 的保證：在同一個 HTTP request 的生命週期中，doFilterInternal() 只被呼叫一次，即使 Spring 的 forward/include dispatch 也不會重複執行（標準 Filter 在 forward 時可能被再次呼叫）。對 MDC 的重要性：MdcLoggingFilter 在 doFilterInternal() 開頭設置 MDC.put(REQUEST_ID, UUID...)，finally 清除 MDC.clear()。若使用標準 Filter，forward dispatch 可能導致 doFilterInternal 再次被呼叫，覆寫 requestId（UUID 會不同），或在 finally 過早清除 MDC，使後續 log 沒有 correlation ID。OncePerRequestFilter 確保整個 request chain（包含 forward）使用同一個 requestId，所有 log 都能被關聯回同一個 request。

### Category

Spring Security

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java
- sp2-springboot/src/main/resources/logback-spring.xml

### Verified classes and methods

- MdcLoggingFilter.doFilterInternal()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

OncePerRequestFilter vs Filter is a common Spring question with real consequences for request-scoped state like MDC.

### Possible follow-up questions

- What log pattern does logback-spring.xml use to include the requestId and username MDC values?
- What happens to the MDC keys when an @Async method is called from within the filter chain?
- How would you propagate the MDC to a background thread created by @Async?

### Verification status

- Verified


---

<a id="Q081"></a>

## Q081 — CSRF is disabled for /api/** but enabled for web form endpoints. How is this configured, and is it safe?

### 中文筆記

配置方式：SecurityConfig 中 csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))，CSRF protection 對 /api/** 的請求不啟用。API 端點（/api/v1/applications、/api/v1/otp）是 stateless JSON API，client（如 browser SPA、mobile app）使用 fetch/axios 發送 JSON request，不是 browser form submit，CSRF 的 threat model 不完全適用（攻擊者無法偽造 CSRF request 並攜帶 JSON body，因為 browser 的 CORS policy 阻止跨域讀取 response）。Web form 端點（/login、Thymeleaf pages）使用 browser form submit，必須有 CSRF token 防止 cross-site form submission。安全疑慮：目前 applicant API（POST /api/v1/applications）是 permitAll（不需登入），攻擊者不能利用 CSRF 盜用 authenticated session 來建立申請。若 admin/reviewer API 的 session-based authentication 與 CSRF 豁免共存，則有 CSRF 攻擊風險。

### Category

Spring Security

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java
- sp2-springboot/docs/decisions/0006-session-over-jwt.md

### Verified classes and methods

- SecurityConfig.securityFilterChain()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

CSRF is often misunderstood; the interaction between CSRF disablement and session authentication on admin APIs is a real security question.

### Possible follow-up questions

- Could an attacker use CSRF to call POST /api/v1/admin/users from a malicious website?
- What is the SameSite cookie attribute, and how would it help here?
- How does the test in SecurityIntegrationTest pass CSRF? Does it use csrf() post-processor?

### Verification status

- Verified


---

<a id="Q082"></a>

## Q082 — LoginSuccessHandler uses LoginResponseMode.prefersJson() to decide between a JSON response and a redirect. What determines whether a request prefers JSON?

### 中文筆記

LoginResponseMode.prefersJson() 的判斷邏輯（推測，驗證需查看 source）：通常檢查 Accept header 是否包含 application/json（REST client 會帶這個 header），或 Content-Type 是否是 application/json/application/x-www-form-urlencoded（form login 是後者），或 X-Requested-With: XMLHttpRequest header（傳統 Ajax 請求）。Browser 直接訪問 login page 後 submit form：Accept: text/html，回傳 redirect。Frontend SPA 或 REST client 送 login request：Accept: application/json，回傳 JSON LoginResponse。這個 dual-mode 設計讓同一個 login endpoint 服務 browser form（重定向到 dashboard）和 REST API client（JSON 回應），避免為兩種用途維護兩個端點。

### Category

Spring Security

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/util/LoginResponseMode.java

### Verified classes and methods

- LoginSuccessHandler.onAuthenticationSuccess()
- LoginResponseMode.prefersJson()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Dual-mode response (JSON vs redirect) in form login is an interesting design problem that tests HTTP content negotiation knowledge.

### Possible follow-up questions

- What HTTP header would a browser send vs a curl client for the same login request?
- How does resolveRedirectUrl() decide where to redirect ADMIN vs REVIEWER users?
- What does SecurityIntegrationTest.login_withValidCredentials_shouldReturn200WithUserInfo() use to trigger the JSON path?

### Verification status

- Verified


---

<a id="Q083"></a>

## Q083 — UserDetailsServiceImpl.toSpringRole() maps "APPLICANT" to "ROLE_USER" instead of "ROLE_APPLICANT". Why?

### 中文筆記

Spring Security 的 role 慣例：hasRole("USER") 在 SpEL 中等同於 hasAuthority("ROLE_USER")。DaoAuthenticationProvider 期望 GrantedAuthority 以 ROLE_ 前綴，而 hasRole("X") 自動加前綴判斷。SecurityConfig 中用 hasRole("REVIEWER")、hasRole("ADMIN")，代碼儲存的 role string 是 "REVIEWER"、"ADMIN"。若直接用 "ROLE_APPLICANT" 但程式碼用 hasRole("APPLICANT")，就是 ROLE_APPLICANT mapping，這也可以。這裡選擇 "ROLE_USER" 的原因：applicant 是系統的一般使用者，ROLE_USER 是 Spring Security 慣用的普通用戶 role，語意上合理；也允許 anyRequest().authenticated() 對 REVIEWER/ADMIN 生效，而不特別提及 APPLICANT 這個 role（因為 applicant 的 API 都是 permitAll）。

### Category

Spring Security

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- UserDetailsServiceImpl.toSpringRole() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The ROLE_ prefix convention is a Spring Security stumbling block; tests knowledge of how roles map to authorities.

### Possible follow-up questions

- What is the difference between hasRole() and hasAuthority() in Spring Security SpEL?
- Why doesn't the SecurityConfig have a requestMatchers().hasAnyRole("USER") rule?
- How would @PreAuthorize("hasRole('APPLICANT')") behave if APPLICANT maps to ROLE_USER?

### Verification status

- Verified


---

<a id="Q084"></a>

## Q084 — SessionExpiredStrategy returns HTTP 401 with a specific message when a concurrent login invalidates the existing session. What user experience does this create?

### 中文筆記

用戶體驗：用戶 A 在電腦登入後，用手機也登入同一帳號（因為 maximumSessions(1)，手機登入成功後，電腦端的 session 被標記為 expired）。當電腦端的下一個請求到達時，Spring Security 的 ConcurrentSessionFilter 偵測到 session expired，呼叫 SessionExpiredStrategy.onExpiredSessionDetected()，回傳 HTTP 401 JSON：{"success": false, "errorCode": "UNAUTHORIZED", "message": "Session expired due to concurrent login"}。電腦端的 SPA/browser 接收到 401 後，應顯示提示（如「您的帳號已在其他地方登入，請重新登入」）並導向登入頁。這個設計讓 reviewer 和 admin 無法同時在多個裝置操作，符合銀行系統的安全要求（防止 session sharing 或未授權的多點登入）。

### Category

Spring Security

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/SessionExpiredStrategy.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- SessionExpiredStrategy.onExpiredSessionDetected()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Concurrent session control is a banking-specific security requirement; tests whether the candidate understands the use case and implementation.

### Possible follow-up questions

- What setting in SecurityConfig would allow up to 2 concurrent sessions instead of 1?
- Is the old session (computer) immediately invalidated, or does it expire on the next request?
- How would SessionRegistry be used to allow an admin to force-logout a specific user?

### Verification status

- Verified


---

<a id="Q085"></a>

## Q085 — Applicant-facing APIs (POST /api/v1/applications, POST /api/v1/otp/**) are permitAll. Is this a security risk, and what would you change for production?

### 中文筆記

安全風險：目前任何人可以不登入就建立 Application、發送 OTP，沒有任何 rate limiting、身份驗證或 bot 保護。風險項目：(1) OTP 濫用：攻擊者可以對任意手機號碼反覆發送 OTP，造成 SMS spam 和費用爆增；(2) 申請洪水：惡意大量建立 Application，耗盡資料庫空間；(3) 身份冒用：任何人可以用他人的手機號碼建立申請。生產環境應加入：OTP 發送頻率限制（如每個手機號碼每分鐘最多 1 次）；CAPTCHA 或 bot 偵測；applicant.nationalId 格式驗證在 API 層（目前可能只在 domain value object 層）；考慮要求申請人有基本身份（如先認證 email 或 phone）才能發起申請。ADR 0006 和 README 都明確承認 applicant endpoints 是 demo-safe 設定。

### Category

Spring Security

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java
- sp2-springboot/docs/decisions/0006-session-over-jwt.md

### Verified classes and methods

- SecurityConfig.securityFilterChain()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Production security hardening of permitAll endpoints is an honest admission of portfolio vs production scope; tests maturity of security thinking.

### Possible follow-up questions

- How would you implement OTP rate limiting with Redis without changing the domain layer?
- What Spring Security mechanism would you use for IP-based rate limiting?
- If you had to secure this in one week, what would be your priority order?

### Verification status

- Verified


---

<a id="Q086"></a>

## Q086 — CacheManagementService can evict product and parameter caches. How does this relate to InMemoryCacheStore and CachedCardProductRepository?

### 中文筆記

產品與系統參數快取走 InMemoryCacheStore；CachedCardProductRepository 以 @Primary 裝飾 CardProductRepository。CacheManagementService 提供管理端驅逐/刷新。快取是行程內記憶體，不是 Redis；Redis 僅用於冪等 store=redis。

### Category

In-Memory Cache

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java

### Verified classes and methods

- CacheManagementService
- InMemoryCacheStore
- CachedCardProductRepository

### Execution flow

1. Read path hits cached repository/service
2. Values stored in InMemoryCacheStore
3. Admin API or scheduler triggers eviction/refresh via CacheManagementService

### Why this may be asked

Forces precise Redis-versus-cache scoping, a common interview trap for this repo.

### Possible follow-up questions

- What does CacheRefreshScheduler do?
- Why is CachedCardProductRepository @Primary?
- What breaks if two app instances use only in-memory cache?

### Verification status

- Verified


---

<a id="Q087"></a>

## Q087 — Why was session-based auth chosen over JWT? Under what future circumstances would you add a JWT filter chain alongside the existing session chain?

### 中文筆記

選擇 session 的理由（ADR 0006）：Staff portal 是 Thymeleaf server-rendered（browser form login），session 提供即時登出和 maximumSessions(1) 控制，比 JWT 的 token invalidation 問題（JWT 無法立即撤銷）更適合這個場景。加入 JWT filter chain 的時機：當需要支援 mobile app 或第三方服務消費 TLBank API（不用 browser session）；API v2（/api/v2/**）可以新增第二個 SecurityFilterChain bean，設定 SessionCreationPolicy.STATELESS，加入 JWT BearerTokenAuthenticationFilter，而 /login、Thymeleaf admin 路徑繼續走 session chain。兩個 filter chain 可以並存（Spring Security 透過 @Order 決定優先級）。ADR 0006 明確提到這是 roadmap 項目。

### Category

Session Authentication vs JWT

### Difficulty

Advanced

### Verified source files

- sp2-springboot/docs/decisions/0006-session-over-jwt.md
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- SecurityConfig.securityFilterChain()
- SessionExpiredStrategy.onExpiredSessionDetected()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

JWT vs session is a classic backend interview topic; this question is particularly strong because TLBank has an ADR with explicit rationale.

### Possible follow-up questions

- What is the JWT token invalidation problem, and how do blocklists solve it?
- What is the @Order annotation's role when you have two SecurityFilterChain beans?
- How would refresh tokens work in a JWT setup for this system?

### Verification status

- Verified


---

<a id="Q088"></a>

## Q088 — The LoginSuccessHandler sets the session timeout in the response body (sessionExpiredAt) but relies on SERVER_SERVLET_SESSION_TIMEOUT for actual enforcement. What happens if these two values differ?

### 中文筆記

差異的影響：LoginSuccessHandler 硬編碼 SESSION_TIMEOUT_MINUTES = 30，計算並在 LoginResponse.sessionExpiredAt 中回傳「30 分鐘後的時間」告知 client session 過期時間。實際 session 超時由 SERVER_SERVLET_SESSION_TIMEOUT（Docker Compose environment 中設定 30m）或 spring.session.timeout 控制。若兩者不一致（如 response 說 30 分鐘，但 server 設定 60 分鐘）：client 以為 30 分鐘後要重新登入（可能提前 auto-refresh），但實際 session 還活著。更危險的情況：response 說 30 分鐘，server 設 5 分鐘，client 還沒到期就發現 401，用戶體驗差。修正方式：LoginSuccessHandler 應從 ServerProperties.getServlet().getSession().getTimeout() 讀取實際設定值，而非硬編碼。這是技術債。

### Category

Session Authentication vs JWT

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java
- .github/workflows/ci.yml

### Verified classes and methods

- SecurityConfig.securityFilterChain()
- SessionExpiredStrategy.onExpiredSessionDetected()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Hardcoded vs configured timeout is a technical debt and consistency question; reveals attention to configuration management.

### Possible follow-up questions

- How would you inject ServerProperties into LoginSuccessHandler to read the configured timeout?
- Could a @Value("${server.servlet.session.timeout:30m}") annotation solve this?
- What unit of time does SERVER_SERVLET_SESSION_TIMEOUT: 30m use?

### Verification status

- Verified


---

<a id="Q089"></a>

## Q089 — ApplicationEntity extends BaseEntity. What does BaseEntity provide, and how does JPA know to automatically set createdAt and updatedAt?

### 中文筆記

BaseEntity 提供：@CreatedDate 標記的 createdAt（LocalDateTime，updatable = false）和 @LastModifiedDate 標記的 updatedAt（每次更新都重設）。JPA 的自動設置機制：@MappedSuperclass（BaseEntity 的欄位映射給所有子類別的 table，不創建自己的 table）；@EntityListeners(AuditingEntityListener.class) 告訴 JPA 使用 Spring Data JPA 的 auditing listener；@EnableJpaAuditing（在某個 @Configuration 類別，如 JpaConfig 中啟用）；AuditingEntityListener 在 @PrePersist（insert 前）設置 createdAt 和 updatedAt，在 @PreUpdate（update 前）只更新 updatedAt。若缺少 @EnableJpaAuditing，@CreatedDate 和 @LastModifiedDate 不會被觸發，欄位保持 null。

### Category

JPA and Hibernate

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/entity/BaseEntity.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/config/JpaConfig.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

JPA auditing is a standard Spring Data feature; tests practical knowledge beyond basic CRUD.

### Possible follow-up questions

- What annotation enables JPA auditing in Spring Boot?
- Can @CreatedDate work without @EntityListeners? Why or why not?
- What happens to createdAt if you accidentally set updatable = true?

### Verification status

- Documentation-only


---

<a id="Q090"></a>

## Q090 — ApplicationEntity uses @Enumerated(EnumType.STRING) for the status field. Why not EnumType.ORDINAL?

### 中文筆記

EnumType.STRING vs ORDINAL：ORDINAL 儲存 enum 的 ordinal 值（0, 1, 2...），STRING 儲存 enum 名稱字串（"INIT"、"OTP_VERIFIED"...）。ORDINAL 的危險：若 enum 定義中插入一個新的常數（如在 INIT 和 OTP_VERIFIED 之間插入 EMAIL_VERIFIED），所有既有資料庫 ordinal 就會錯位（原來的 OTP_VERIFIED = 1 變成 EMAIL_VERIFIED），現有資料就讀錯了，且問題不在 compile time 發現。STRING 的保護：資料庫儲存 "OTP_VERIFIED" 字串，不受 enum 順序改變影響，只有在重命名 enum constant 時才需要 migration。銀行應用的申請狀態不應因程式碼重構而讀錯，所以 EnumType.STRING 是正確選擇。

### Category

JPA and Hibernate

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java

### Verified classes and methods

- ApplicationEntity

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

EnumType.ORDINAL vs STRING is a classic JPA gotcha with serious data corruption implications; reveals production awareness.

### Possible follow-up questions

- What would happen to existing data if EnumType.ORDINAL was used and a new status was inserted at position 0?
- Is there a way to use a custom string mapping (e.g., "OTP_VFD" for "OTP_VERIFIED") with JPA?
- How does Hibernate handle an unknown string value in the database that doesn't map to any enum constant?

### Verification status

- Verified


---

<a id="Q091"></a>

## Q091 — ApplicationJpaRepository has a method findByApplicationNo(String applicationNo). Why is there a applicationNo separate from the id primary key?

### 中文筆記

兩種 ID 的理由：id（BIGINT IDENTITY PRIMARY KEY）是資料庫的技術 ID（surrogate key），用於 JPA entity 的 primary key，外鍵關聯（workflow_histories.application_id）等內部 join。applicationNo（VARCHAR(30)，UNIQUE）是業務 ID（business key），格式如 APP-20260628170759-2980，暴露給外部世界（API response、URL path /api/v1/applications/APP-...、用戶看得到的申請編號）。分離的好處：(1) 技術 ID（BIGINT）不暴露給外部，防止 sequential enumeration（攻擊者不能猜測 id=1,2,3... 來存取他人申請）；(2) 業務 ID 有可讀格式（帶時間戳），用戶容易識別；(3) 可以跨 migration 更改技術 ID 生成策略而不影響 API contract。ApplicationId.generate() 生成帶時間戳的業務 ID。

### Category

JPA and Hibernate

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java

### Verified classes and methods

- ApplicationEntity
- ApplicationJpaRepository

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Surrogate vs business key is a fundamental data modeling question with security implications; reveals database design knowledge.

### Possible follow-up questions

- What is sequential enumeration, and why does exposing a BIGINT primary key enable it?
- How does ApplicationId.generate() create the business ID format?
- Could you use a UUID as the primary key instead? What are the trade-offs?

### Verification status

- Verified


---

<a id="Q092"></a>

## Q092 — ApplicationEntity maps workflowHistories and documents with FetchType.LAZY. What problem does this solve, and what new problem does it create?

### 中文筆記

LAZY 解決的問題：每次 findByApplicationNo() 加載 ApplicationEntity 時，若 workflowHistories 和 documents 是 EAGER，Hibernate 會自動 JOIN 加載所有子集合（即使 caller 只需要 application status），增加不必要的 DB 查詢和記憶體使用。LAZY 確保子集合只在第一次存取時才加載（lazy initialization）。引入的問題：LazyInitializationException（could not initialize proxy - no Session）：在 session 關閉後（transaction 結束後）存取 lazy 集合會拋此例外。在 ApplicationRepositoryImpl.toDomain() 中，需要存取 entity.getWorkflowHistories() 來建立 domain object，這必須在 JPA session 還活著（transaction 開啟）的情況下執行。@Transactional 的邊界必須涵蓋整個 repository method，確保 lazy load 在 session 存續期間完成。

### Category

JPA and Hibernate

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java

### Verified classes and methods

- ApplicationEntity
- ApplicationRepositoryImpl.toDomain() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

LazyInitializationException is one of the most common JPA runtime errors; tests whether the candidate understands when it occurs and how to prevent it.

### Possible follow-up questions

- What is the open-session-in-view pattern, and why is it generally discouraged?
- How would @EntityGraph help avoid lazy load issues for specific queries?
- What would happen if toDomain() were called outside a transaction boundary?

### Verification status

- Verified


---

<a id="Q093"></a>

## Q093 — @Modifying appears on OtpJpaRepository.markExpiredBefore(). What does @Modifying do, and what does @Transactional on the caller provide?

### 中文筆記

@Modifying 的作用：標記此查詢為 DML（UPDATE/DELETE），而非 SELECT。Spring Data JPA 的 @Query 預設假設是 SELECT，@Modifying 告訴它這是修改操作，返回受影響的行數（int）而非結果集。若沒有 @Modifying，執行 UPDATE/DELETE query 會拋 InvalidDataAccessApiUsageException。@Transactional 的角色：@Modifying 操作必須在 transaction 中執行，否則 JPA 拋錯。OtpCleanupScheduler.cleanupExpiredOtps() 標有 @Transactional，確保 markExpiredBefore() 在 transaction boundary 內。若 scheduler 沒有 @Transactional，需要由 repository 方法自己提供 transaction（可在 @Modifying method 上加 @Transactional）。clearAutomatically = true（可選）在 @Modifying 上清除 JPA first-level cache，避免 stale data 問題。

### Category

JPA and Hibernate

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java

### Verified classes and methods

- OtpJpaRepository
- OtpCleanupScheduler.cleanupExpiredOtps()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

@Modifying with JPQL UPDATE is a standard but commonly confused JPA topic; tests practical Spring Data knowledge.

### Possible follow-up questions

- What is clearAutomatically in @Modifying, and when would you set it to true?
- What return type should markExpiredBefore() have? Can it be void?
- If the @Transactional annotation were removed from cleanupExpiredOtps(), what exception would occur?

### Verification status

- Verified


---

<a id="Q094"></a>

## Q094 — @Transactional(readOnly = true) is used on query methods in the application service. What optimizations does Hibernate apply for read-only transactions?

### 中文筆記

readOnly = true 的 Hibernate 最佳化：(1) Hibernate 跳過 dirty checking（不追蹤 entity 變更，transaction commit 時不掃描所有 entity 是否有修改），節省 CPU；(2) Hibernate 可以提示底層 JDBC driver 使用唯讀模式（Connection.setReadOnly(true)），某些 driver 和資料庫會因此使用較低的 isolation level 或不獲取寫鎖；(3) Spring DataSourceTransactionManager 在某些 connection pool 配置下可以優化連線選擇（如從 read replica 取連線）。不是 magic read-only lock：readOnly = true 不阻止你呼叫 entityManager.persist()，但 dirty checking 跳過意味著即使修改了 entity，也不會 flush 到資料庫（除非顯式 flush）。在此 project 中：ApplicationAppService.getApplication() 有 @Transactional(readOnly = true)，適合只查詢、不修改的 use case。

### Category

JPA and Hibernate

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java

### Verified classes and methods

- ApplicationAppService.getApplication()
- UserDetailsServiceImpl.loadUserByUsername()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

readOnly = true is commonly used but rarely explained correctly; tests JPA/Hibernate depth.

### Possible follow-up questions

- Does @Transactional(readOnly = true) prevent a save() call from working?
- What is the relationship between readOnly = true and database read replicas?
- Would you put readOnly = true on a method that calls findById() and then calls entity.setName()?

### Verification status

- Verified


---

<a id="Q095"></a>

## Q095 — ApplicationJpaRepository uses both JPQL (@Query with entity names) and native SQL (nativeQuery = true). Why mix the two?

### 中文筆記

混用的理由：JPQL 查詢（SELECT COUNT(a) FROM ApplicationEntity a WHERE a.createdAt >= :start）：使用 JPA entity 名稱和欄位名稱，與底層 table/column 命名無關，若重命名 table 或欄位（加 @Column(name="...")），JPQL 不受影響，適合簡單的計數查詢。Native SQL 查詢（SELECT status, COUNT(*) FROM applications WHERE... GROUP BY status）：使用真實 table 和 column 名稱，適合聚合報表查詢（GROUP BY），特別是當 JPQL 的聚合語法比 native SQL 繁瑣時，或需要特定資料庫函數（SQL Server 特有語法）。取捨：JPQL 更 portable（換資料庫無需改），native SQL 更直接（複雜聚合更簡單）。報表類查詢傾向 native SQL，CRUD 類查詢傾向 JPQL，這是常見的 pattern。

### Category

JPA and Hibernate

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java

### Verified classes and methods

- ApplicationJpaRepository

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Knowing when to use JPQL vs native SQL is a practical JPA judgment call; tests whether the candidate can articulate the trade-off.

### Possible follow-up questions

- What would happen to the native SQL queries if the applications table were renamed?
- How would a Spring Data Projection interface change the return type of the native query?
- Could @NamedNativeQuery on the entity class improve maintainability?

### Verification status

- Verified


---

<a id="Q096"></a>

## Q096 — MaskingUtil masks national IDs, mobile numbers, emails, and names before they appear in API responses. Where exactly in the code is masking applied?

### 中文筆記

Masking 的應用點：ApplicationAppService.getApplication() 呼叫 toMaskedApplicant(application.getApplicant())，建立 MaskedApplicantResponse，其中 mobile = MaskingUtil.maskMobile(applicant.getMobileNumber().value())、nationalId = MaskingUtil.maskNationalId(applicant.getNationalId())、name = MaskingUtil.maskName(applicant.getFullName())，email 也類似。Domain 層的 MobileNumber.masked() 也提供 masking 方法，但 masking 邏輯主要在 application service 的 response mapping 階段執行。Domain aggregate Application.getApplicant() 返回的是未 masked 的真實資料，audit log 中記錄的也可能是 masked version（透過 AuditDetailBuilder）。注意：PII 只在 API response（outbound）被 mask，不在 log、audit log 的 detail 欄位中 mask（需確認 AuditDetailBuilder 是否也有 masking）。

### Category

Spring Security

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/util/MaskingUtil.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/MaskedApplicantResponse.java

### Verified classes and methods

- MaskingUtil.maskMobile()
- MaskingUtil.maskNationalId()
- ApplicationAppService.toMaskedApplicant() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

PII masking in financial applications is a compliance requirement; tests security-conscious API design thinking.

### Possible follow-up questions

- Is PII masked in the audit log detail field? What would need to change if it is not?
- What regulation (e.g., PDPA in Taiwan) requires masking of national ID numbers in responses?
- How would you test that masking is consistently applied across all endpoints?

### Verification status

- Verified


---

<a id="Q097"></a>

## Q097 — InMemoryCacheStore has a @Scheduled(fixedDelay = 60_000) method for cleanup. Why does the cache need a separate cleanup job?

### 中文筆記

需要 cleanup job 的原因：InMemoryCacheStore 使用 ConcurrentHashMap<String, CacheEntry<?>> 儲存快取，TTL 是用 CacheEntry.expiresAt 紀錄，過期後 entry 不會自動從 map 移除。get() 在取值時檢查過期並移除（lazy eviction），但若某個 key 從未被再次 get()，過期的 entry 會一直佔據記憶體。cleanupExpiredEntries() 每 60 秒掃描整個 map，移除所有過期的 entry，防止記憶體洩漏。為什麼不只靠 lazy eviction：card product 快取可能某些產品很久沒人查詢，但快取 entry 在 TTL 後仍佔記憶體；系統參數快取更新後舊 entry 也需要清除。store.remove(entry.getKey(), entry.getValue()) 用 value 比較做 CAS（compare-and-swap）安全移除，避免移除了 concurrent 更新的新 entry。

### Category

Performance and Scalability

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java

### Verified classes and methods

- InMemoryCacheStore.cleanupExpiredEntries()
- InMemoryCacheStore.get()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Cache memory management is a production concern; lazy eviction only versus proactive cleanup is a classic cache design question.

### Possible follow-up questions

- What is the CAS pattern, and how does store.remove(key, value) use it?
- What happens if cleanupExpiredEntries() takes longer than 60 seconds to complete?
- Is this scheduler safe in a multi-instance environment? What happens if two instances both clean up?

### Verification status

- Verified


---

<a id="Q098"></a>

## Q098 — CacheRefreshScheduler calls systemParameterService.refreshCache() on a schedule. What would happen if the database is temporarily unavailable during the refresh?

### 中文筆記

當資料庫不可用時的行為：CacheRefreshScheduler.refreshCaches() 的 try-catch-log 結構：若 systemParameterService.refreshCache() 呼叫底層的 SystemParameterRepository（JPA），資料庫不可用時拋 exception（DataAccessException 或 connection timeout）。catch 區塊：log.warn("[SCHEDULER] Cache refresh failed after {}ms: {}", elapsed, ex.getMessage(), ex)，記錄警告但不重拋 exception，scheduler 不會崩潰，下次 cron 觸發時再試。結果：當次 refresh 失敗，快取中的 system parameter 維持舊值（或維持 TTL 前的版本），直到下次 refresh 成功更新。業務影響：在資料庫恢復前，系統使用的參數可能是舊值（如 max upload size）。這個設計選擇了「可用性優先」（系統繼續運行，使用舊快取）而非「一致性優先」（停止服務直到快取更新）。

### Category

Performance and Scalability

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/CacheRefreshScheduler.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/parameter/service/SystemParameterService.java

### Verified classes and methods

- CacheRefreshScheduler.refreshCaches()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Error handling in scheduled tasks has availability vs consistency trade-offs; tests whether the candidate thinks about failure modes.

### Possible follow-up questions

- What is the stale data window if the scheduler runs every hour and the database is down for 2 hours?
- How would you add an alert (e.g., push a metric) when the cache refresh fails?
- Should the scheduler's cron frequency affect how long stale cache is acceptable?

### Verification status

- Verified


---

<a id="Q099"></a>

## Q099 — NotificationEventHandler catches all exceptions from notification calls and logs a warning instead of rethrowing. What is the design intent?

### 中文筆記

設計意圖（side-effect isolation）：通知（SMS/Email）是業務流程的副作用，不是核心業務操作。若 sendApplicationSubmittedNotification() 失敗（Mock sender 拋 exception、網路問題等），catch 後 log.warn(...) 繼續，不讓通知失敗影響申請提交的結果。這對應 00-project-overview.md 的問題陳述：「side-effect isolation：SMS/email failures must not roll back core workflow commits」。若不 catch：@EventListener 拋出 exception → Spring 的事件發布機制會將 exception 傳播回 ApplicationEventPublisher.publishEvent() 的呼叫者（ApplicationAppService.submitApplication()），可能導致整個業務 transaction rollback，申請提交失敗，但 DB 中狀態已改變（transaction 已 commit 或未 commit 取決於 @Transactional 邊界）。注意：通知失敗後沒有 retry 機制，這是已知的限制（ADR 0003/handbook 提到 Outbox Pattern 作為未來改善）。

### Category

Domain Events

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java
- sp2-springboot/docs/handbook/00-project-overview.md

### Verified classes and methods

- NotificationEventHandler.onApplicationSubmitted()
- NotificationEventHandler.onApplicationApproved()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Side-effect isolation is stated as a core design goal; tests whether the candidate can trace the intent from documentation to implementation.

### Possible follow-up questions

- What is the Outbox Pattern, and how would it guarantee notification delivery even after a crash?
- If notification failure is caught and logged, how would an operator know that a customer never received their SMS?
- Is the transaction that submitted the application already committed when NotificationEventHandler runs?

### Verification status

- Verified


---

<a id="Q100"></a>

## Q100 — ReviewEventHandler.onApplicationSubmitted() creates a ReviewCase without a try-catch. Why is this different from NotificationEventHandler?

### 中文筆記

兩者差異的設計理由：ReviewEventHandler 沒有 try-catch——ReviewCase 建立失敗時，exception 會傳播回 event publisher，可能導致 transaction rollback。這是有意為之：ReviewCase 的建立是申請提交的核心業務流程（沒有 ReviewCase，審核員看不到申請，業務流程中斷），屬於「必須成功」的操作，不能靜默忽略失敗。NotificationEventHandler 有 try-catch——通知是 best-effort 副作用，失敗不影響申請的業務狀態。這個設計區分了：core side effects（必須成功，ReviewCase 建立）和 best-effort side effects（可容忍失敗，通知）。潛在問題：若 ReviewEventHandler 失敗，整個 submitApplication() transaction 可能 rollback（取決於 @EventListener 的事務傳播行為），申請者看到提交失敗，但資料庫中申請狀態可能已更新，需要仔細分析 transaction boundary。

### Category

Domain Events

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java

### Verified classes and methods

- ReviewEventHandler.onApplicationSubmitted()
- NotificationEventHandler.onApplicationSubmitted()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The intentional asymmetry between the two event handlers is a subtle and important design decision; tests whether the candidate can explain the architectural rationale.

### Possible follow-up questions

- What is @TransactionalEventListener and how would it change the behaviour of ReviewEventHandler?
- What happens if reviewCaseRepository.save() throws a DataIntegrityViolationException?
- How would the Outbox Pattern resolve the transaction consistency issue between application submission and ReviewCase creation?

### Verification status

- Verified


---

<a id="Q101"></a>

## Q101 — ApplicationRepository is an interface in the domain package. Why does the domain layer define a repository interface that infrastructure then implements?

### 中文筆記

Domain 定義 interface 的理由（Dependency Inversion Principle）：若 domain layer 直接依賴 ApplicationJpaRepository，就等同於 domain 依賴 infrastructure（Spring Data JPA），形成向外的依賴，違反 Clean Architecture 依賴規則。相反的，domain 定義 ApplicationRepository interface（只描述業務需要的存取行為：findById(ApplicationId)、save(Application)、findByStatus(ApplicationStatus)），infrastructure 的 ApplicationRepositoryImpl 實作它。結果：domain 只依賴自己定義的 port，infrastructure 依賴 domain（實作其 port），dependency 方向正確。測試時可用 Mockito mock ApplicationRepository 而不需要啟動 JPA。ApplicationAppServiceTest 就是 @Mock ApplicationRepository 直接測試 service，沒有資料庫。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java
- sp2-springboot/src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

The repository-as-port pattern is foundational to Clean Architecture; tests whether the candidate understands the Dependency Inversion Principle in practice.

### Possible follow-up questions

- What methods does ApplicationRepository expose, and why are there so few?
- What would need to change if you replaced SQL Server with MongoDB?
- How does Spring know to inject ApplicationRepositoryImpl when ApplicationAppService asks for ApplicationRepository?

### Verification status

- Documentation-only


---

<a id="Q102"></a>

## Q102 — ApplicationRepositoryImpl.save() checks if an entity with the same applicationNo already exists before deciding to insert or update. Why not use JpaRepository.save() directly?

### 中文筆記

不直接用 JpaRepository.save() 的理由：JpaRepository.save() 的邏輯是：entity.getId() == null → INSERT，entity.getId() != null → merge（UPDATE）。問題：domain aggregate Application 不持有資料庫的 surrogate id（BIGINT），只有業務 applicationId（ApplicationId value object，對應 applicationNo 字串）。在 toDomain() 轉換後，domain object 不知道 JPA 的數字 id，因此無法直接用 JPA 的 id-based save 判斷是 INSERT 還是 UPDATE。ApplicationRepositoryImpl.save() 的解法：先 findByApplicationNo(application.getApplicationId().value())，找到存在的 entity 就 applyToEntity(existing, application) update，找不到就 toEntity(application) 新建，再 applicationJpaRepository.save(entity)。這是 domain id 與 surrogate key 分離設計的必然代價。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java

### Verified classes and methods

- ApplicationRepositoryImpl.save()
- ApplicationJpaRepository

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The upsert pattern using a business key rather than a surrogate key reveals understanding of the entity/aggregate impedance mismatch.

### Possible follow-up questions

- What is the risk of a race condition between findByApplicationNo() and save() in a concurrent environment?
- How does the UNIQUE constraint on application_no protect against concurrent inserts?
- Could you use JpaRepository.saveAndFlush() here instead of save()? When would you need to?

### Verification status

- Verified


---

<a id="Q103"></a>

## Q103 — OtpRepositoryImpl has findLatestPendingByMobile(). Why does it return the latest (most recent) OTP rather than any pending OTP?

### 中文筆記

「最新」而非「任意」的理由：sendOtp() 的流程是先取消現有 pending OTP，再建立新 OTP。但若取消動作在 DB 還沒 commit 或有 race condition，可能存在多筆 PENDING OTP。findTopByMobileAndStatusOrderByCreatedAtDesc() 取最新（最近建立）的 PENDING OTP，確保 verify 時驗證的是最後送出的 OTP code，不是舊的。若返回任意一筆，可能讓用戶拿舊碼驗證失敗（碰巧取到舊的）或拿到已過期的舊碼。Spring Data JPA 的 findTop...By...OrderBy...Desc 是 derived query，findTop 限制返回一筆，OrderByCreatedAtDesc 確保取最新的，不需要手寫 JPQL。這也與 sendOtp() 的設計搭配：send OTP 時先 cancel 舊的，verify OTP 時取最新的，確保一致性。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java

### Verified classes and methods

- OtpJpaRepository
- OtpAppService.sendOtp()

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

The "latest pending OTP" design shows understanding of concurrent state and data consistency; tests whether the candidate can trace data flow across layers.

### Possible follow-up questions

- What would happen if sendOtp() ran twice concurrently for the same mobile number?
- Could the index on otp_records(user_id, purpose) help the findLatestPendingByMobile() query?
- Is there a race window between cancelling the old OTP and saving the new one?

### Verification status

- Verified


---

<a id="Q104"></a>

## Q104 — ReviewCaseRepository.search() accepts a Pageable parameter. Should Pageable appear in the domain port interface? What are the alternatives?

### 中文筆記

爭議點：Pageable 是 Spring Data 的類別（org.springframework.data.domain.Pageable），若 domain port interface ReviewCaseRepository.search() 的參數是 Pageable，則 domain layer 隱性依賴了 Spring Data，違反 Clean Architecture 的依賴規則。ADR 0001 承認有此類 leak。替代方案：(1) 自訂 PageCriteria value object（offset, limit, sortBy, sortDirection），domain port 接受 PageCriteria；adapter 在 ReviewCaseRepositoryImpl 中將 PageCriteria 轉換為 Spring 的 PageRequest；(2) 讓 port 只有 findAll(ReviewCaseSearchCriteria) 返回完整 list，分頁邏輯在 application service 處理（不適合大資料量）。目前選擇 Pageable 是 pragmatic trade-off：Spring Data 的 Page<T> 提供 totalElements、totalPages 等分頁 metadata，自訂 PageCriteria 不省多少工，且分頁是「how to query」的 infrastructure 關心點，不是業務規則。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/repository/ReviewCaseRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md

### Verified classes and methods

- ReviewCaseRepository
- ReviewAppService.searchCases()

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

Pageable in domain ports is a known pragmatic compromise; tests whether the candidate can articulate the trade-off without pretending perfect purity.

### Possible follow-up questions

- What would a PageCriteria value object look like, and what fields would it need?
- Is using Page<T> from Spring Data as a return type the same level of leakage as Pageable input?
- How would you enforce a no-Spring-imports rule in the domain package using ArchUnit?

### Verification status

- Verified


---

<a id="Q105"></a>

## Q105 — ReviewCaseSearchCriteria is a separate class in the domain package. What does it contain, and why is it in the domain rather than the application layer?

### 中文筆記

ReviewCaseSearchCriteria 包含搜尋條件（推測：status（ReviewStatus）、assignedTo（username）、日期範圍等）。在 domain 而非 application 的理由：搜尋條件的欄位定義（可以按哪些業務欄位篩選 ReviewCase）是 domain 的語言，是「業務上的查詢維度」。application service 需要它來查詢，但它的欄位類型（ReviewStatus enum、String assignedTo）都是 domain types，若放在 application layer 就需要 domain layer 依賴 application layer 的 DTO，或者 application layer 重新定義相同的 criteria 再轉換。放在 domain 讓 port interface（ReviewCaseRepository.search(ReviewCaseSearchCriteria, Pageable)）可以直接使用，infrastructure adapter 也可以直接用它建構 JPA criteria query 或 dynamic query，不需要額外轉換層。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCaseSearchCriteria.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/repository/ReviewCaseRepository.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

The placement of search criteria classes tests understanding of what belongs in the domain versus application layer.

### Possible follow-up questions

- What fields does ReviewCaseSearchCriteria contain?
- How does ReviewCaseRepositoryImpl translate ReviewCaseSearchCriteria into a JPA query?
- If you added a free-text search field to ReviewCaseSearchCriteria, would that be a domain or infrastructure concern?

### Verification status

- Documentation-only


---

<a id="Q106"></a>

## Q106 — DocumentStorageService is defined in infrastructure.storage but is injected into ApplicationAppService. Does this violate the layer dependency rule?

### 中文筆記

層級依賴分析：ApplicationAppService 在 application layer，LocalDocumentStorageService（實作類別）在 infrastructure.storage，DocumentStorageService（interface）在 infrastructure.storage。問題：若 ApplicationAppService 直接 import LocalDocumentStorageService 就是 application 依賴 infrastructure 實作，違規。若 ApplicationAppService import DocumentStorageService interface，但 interface 在 infrastructure package，application layer 就依賴 infrastructure package，這也是 leak（interface 應在 domain 或 application layer 定義）。正確做法：DocumentStorageService interface 應放在 domain 的 port 位置（如 domain.application.storage 或 application.storage），由 infrastructure 實作。目前的設計是已知的不完美之處，interface 和 implementation 都在 infrastructure.storage package，是另一個 ADR 0001 提到的 pragmatic trade-off，在不影響業務可測試性的情況下接受的 leak。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/DocumentStorageService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Identifying subtle dependency violations in the codebase reveals careful code reading skills and architectural judgment.

### Possible follow-up questions

- Where should DocumentStorageService interface live to fix this violation?
- Does moving the interface to domain require any other change?
- How would you write an ArchUnit rule to detect this pattern?

### Verification status

- Documentation-only


---

<a id="Q107"></a>

## Q107 — Both OtpRepositoryImpl and ApplicationRepositoryImpl follow the same pattern: domain port → JPA repository → entity. Is there any code duplication, and how might it be reduced?

### 中文筆記

重複的 pattern：兩個 impl 都有 toDomain(Entity entity)、toEntity(Domain domain)、applyToEntity(Entity existing, Domain domain) 三個 mapping 方法，邏輯結構相似但具體欄位不同。潛在重複：若日後有 8-10 個 aggregate，每個都有相同三方法的 impl，維護成本上升。減少重複的選項：(1) MapStruct：定義 @Mapper interface，自動生成 mapping 程式碼，已在 pom.xml 的 classpath（ADR 0001 提到但未啟用），是已知的 technical debt；(2) 抽象 base class BaseRepositoryImpl<Domain, Entity, Id> 提供通用的 save 邏輯，子類別只需提供 mapping 方法（template method pattern）；(3) 接受目前的重複，因為每個 aggregate 的 mapping 複雜度不同（Application 有巢狀 Applicant、WorkflowHistory，OtpRecord 相對簡單），過度抽象可能降低可讀性。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpRepositoryImpl.java
- sp2-springboot/pom.xml

### Verified classes and methods

- ApplicationRepositoryImpl.toDomain() (private)
- OtpRepositoryImpl.toDomain() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Code duplication in repository adapters is a practical refactoring question that reveals familiarity with mapping tools and design patterns.

### Possible follow-up questions

- What MapStruct annotation would you write to map ApplicationEntity to Application?
- Is the template method pattern appropriate here, or would it make testing harder?
- At what point would you introduce MapStruct — how many aggregates is "too many to map manually"?

### Verification status

- Verified


---

<a id="Q108"></a>

## Q108 — CachedCardProductRepository is an adapter that decorates another CardProductRepository. How does Spring resolve which CardProductRepository to inject where?

### 中文筆記

Spring 的注入解析：有兩個 bean 實作 CardProductRepository：CardProductRepositoryImpl（JPA adapter）和 CachedCardProductRepository（cache decorator）。解析方式：(1) @Primary：若 CachedCardProductRepository 標有 @Primary，Spring 預設注入它；CardProductRepositoryImpl 在 CachedCardProductRepository 的 constructor 中透過 @Qualifier("cardProductRepositoryImpl") 明確指定注入未加 cache 的那個。(2) 或 @Qualifier：CachedCardProductRepository 明確 @Qualifier 指定包裝的 impl。這個 pattern 讓 application service（注入 CardProductRepository）不需要知道有幾層 decorator，Spring 自動處理組裝。若兩個 bean 都沒有 @Primary/@Qualifier 的消歧，Spring 會拋 NoUniqueBeanDefinitionException。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/product/CardProductRepositoryImpl.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Spring bean disambiguation with two implementations of the same interface is a practical Spring DI question.

### Possible follow-up questions

- What exception would Spring throw if neither @Primary nor @Qualifier disambiguated the two CardProductRepository beans?
- How would you add a third CardProductRepository implementation (e.g., an S3-backed version) without touching ApplicationAppService?
- Is the Decorator pattern the same as the Proxy pattern?

### Verification status

- Documentation-only


---

<a id="Q109"></a>

## Q109 — ReviewAppService publishes ApplicationApprovedEvent after approving a review case. Where in the service does this happen, and what triggers it?

### 中文筆記

發布時機：ReviewAppService.approveCase() 方法（或類似名稱）：(1) 讀取 ReviewCase；(2) 呼叫 reviewCase.approve(operator, remark)（aggregate 內部狀態轉換）；(3) 讀取相關的 Application；(4) 呼叫 application.approve(operator, remark)（Application aggregate 狀態轉換到 APPROVED）；(5) applicationRepository.save(application)；(6) reviewCaseRepository.save(reviewCase)；(7) eventPublisher.publishEvent(new ApplicationApprovedEvent(applicationId, mobile, email))。事件在所有 DB 操作完成後、transaction commit 前發布（Spring 的 ApplicationEventPublisher 同步發布，@EventListener 在同一 thread 執行）。NotificationEventHandler 捕捉 ApplicationApprovedEvent 發送通知，使用 try-catch 確保通知失敗不影響 transaction commit。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java

### Verified classes and methods

- ReviewAppService.approveCase()

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

Event publishing placement (before or after DB save, inside or outside transaction) is a subtle but important design question.

### Possible follow-up questions

- What is @TransactionalEventListener(phase = AFTER_COMMIT) and why might you use it here?
- Could publishing the event before save() cause a problem if the save fails?
- How would you verify in a test that the event was published when a case is approved?

### Verification status

- Verified


---

<a id="Q110"></a>

## Q110 — ReviewAppService calls both ApplicationRepository and ReviewCaseRepository in a single transaction. What are the consistency guarantees and risks?

### 中文筆記

單一 transaction 的保證：@Transactional 包裹整個 approveCase() 方法，Application.approve() 和 ReviewCase.approve() 的 DB 寫入在同一個 ACID transaction，若任何一個 save 失敗，整個 transaction rollback，兩個 aggregate 都不會部分更新。一致性風險：兩個 aggregate 的 approval 狀態必須同時提交（all-or-nothing），但業務上 Application.status = APPROVED 和 ReviewCase.status = APPROVED 理應同步，這符合設計。進階問題：若 ApplicationRepository.save() 成功但 ReviewCaseRepository.save() 失敗，transaction rollback 讓兩者都回滾——這是正確的。另一個風險：事件（ApplicationApprovedEvent）在 transaction 內發布，若 NotificationEventHandler 的 try-catch 沒有，notification 失敗可能影響 transaction。@TransactionalEventListener(phase = AFTER_COMMIT) 可讓 event 在 transaction commit 後才執行，更安全。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/repository/ReviewCaseRepository.java

### Verified classes and methods

- ReviewAppService.approveCase()

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

Cross-aggregate transactions are a DDD anti-pattern that sometimes has pragmatic justification; tests whether the candidate understands the trade-off.

### Possible follow-up questions

- Does updating two aggregates in one transaction violate DDD principles?
- What alternative design would avoid the cross-aggregate transaction?
- How would the Saga pattern change this design?

### Verification status

- Verified


---

<a id="Q111"></a>

## Q111 — H2 is configured with MODE=MSSQLServer in application-dev.yml. What SQL compatibility does this provide, and what are its limits?

### 中文筆記

MODE=MSSQLServer 提供的相容性：H2 模擬 SQL Server 的一些語法特性，如 ISNULL() 函數（SQL Server 的 null 替換）、TOP N 語法（H2 預設是 LIMIT N）、@@IDENTITY 函數、DATETIME2 資料型別（部分支援）。H2 connection URL：jdbc:h2:mem:tlbank_lending;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MSSQLServer。限制（已知不完全相容）：(1) GETDATE() 函數（SQL Server）vs CURRENT_TIMESTAMP（ANSI SQL，H2 支援）；(2) IDENTITY(1,1) 語法（SQL Server PRIMARY KEY 定義）vs GENERATED BY DEFAULT AS IDENTITY（H2）；(3) 複雜的 T-SQL 特性（TRY...CATCH、GOTO、OUTPUT clause）；(4) DATETIME2 精度差異。因此有兩個 Flyway migration 資料夾：db/migration（H2 相容語法）和 db/migration-sqlserver（原生 SQL Server 語法）。

### Category

SQL Server and H2

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/application-dev.yml
- sp2-springboot/src/main/resources/db/migration/V1__create_users.sql
- sp2-springboot/src/main/resources/db/migration-sqlserver/V1__create_users.sql

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

H2 compatibility mode is a pragmatic choice with known limits; tests whether the candidate understands when the dev environment diverges from production.

### Possible follow-up questions

- What would happen if you accidentally used GETDATE() in the H2 migration scripts?
- How do the two V1__create_users.sql files differ between H2 and SQL Server?
- What is the risk of writing a query that passes H2 tests but fails in SQL Server?

### Verification status

- Documentation-only


---

<a id="Q112"></a>

## Q112 — The H2 console is enabled in dev profile at /h2-console. How is it secured, and what risk does it pose?

### 中文筆記

H2 console 的安全性：在 SecurityConfig.securityFilterChain()，H2 console 路徑 /h2-console/** 是 permitAll()，不需要登入即可存取。同時，H2 console 使用 HTML iframe，若沒有關閉 X-Frame-Options，Spring Security 會因為 clickjacking 保護而阻擋 iframe 載入。SecurityConfig 中有：if (isDevProfile()) { headers.frameOptions(frame -> frame.disable()); }，只在 dev profile 關閉 X-Frame-Options，允許 H2 console 的 iframe 正常顯示。風險：H2 console 是一個完整的資料庫 SQL 操作介面，任何人在 dev 環境都可以存取、修改資料。在 staging/prod 必須確保：spring.h2.console.enabled=false（application-staging.yml 不啟用 H2）；SecurityConfig.isDevProfile() 確保只在 dev 關閉 frameOptions。

### Category

SQL Server and H2

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/application-dev.yml
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- SecurityConfig.securityFilterChain()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

H2 console security is a concrete example of environment-specific security configuration; tests attention to security-by-profile.

### Possible follow-up questions

- What would happen to H2 console in staging profile given the current configuration?
- What is clickjacking, and why does disabling X-Frame-Options matter for H2 console?
- How would you add a password to H2 console in dev for an additional layer of protection?

### Verification status

- Verified


---

<a id="Q113"></a>

## Q113 — H2 is configured with DB_CLOSE_DELAY=-1. What does this option do, and why is it necessary for tests?

### 中文筆記

DB_CLOSE_DELAY=-1：H2 in-memory 資料庫預設在最後一個 JDBC connection 關閉時自動刪除資料庫。DB_CLOSE_DELAY=-1 讓資料庫在 JVM 整個生命週期內保持存在，不因為 connection 關閉而消失。對測試的必要性：Spring Boot 在測試中可能使用 connection pool（HikariCP），connections 在 pool 中空閒時並未「關閉」（只是 idle），但若測試之間有 connection pool 的 idle timeout 或 pool reset，沒有 DB_CLOSE_DELAY=-1 的話，H2 資料庫可能被提前刪除，下一個測試啟動時資料庫是空的（Flyway migration 需要重跑，schema 消失）。DB_CLOSE_ON_EXIT=FALSE 更進一步確保 JVM 關閉時不自動刪除 H2 資料庫（允許測試 reporter 在 JVM 關閉前讀取最終狀態）。

### Category

SQL Server and H2

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/application-dev.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

H2 URL parameters are subtle but important for test stability; tests knowledge of in-memory database lifecycle.

### Possible follow-up questions

- What is DB_CLOSE_ON_EXIT=FALSE and how does it differ from DB_CLOSE_DELAY=-1?
- What would happen in tests if DB_CLOSE_DELAY was set to 30 (30 seconds)?
- How does @SpringBootTest reuse the application context across multiple test classes, and how does that interact with H2 lifecycle?

### Verification status

- Documentation-only


---

<a id="Q114"></a>

## Q114 — V3__create_applications.sql in the H2 migration sets status DEFAULT 'DRAFT', but ApplicationStatus enum has no DRAFT constant. Is this a bug?

### 中文筆記

分析：H2 migration V3__create_applications.sql：status VARCHAR(30) NOT NULL DEFAULT 'DRAFT'，但 ApplicationStatus enum 是：INIT, OTP_VERIFIED, DOCUMENT_UPLOADED, SUBMITTED, UNDER_REVIEW, APPROVED, REJECTED, CANCELLED，沒有 DRAFT。影響：DEFAULT 'DRAFT' 只在直接 INSERT 時不提供 status 欄位才生效。在程式碼中，ApplicationAppService.createApplication() 明確設定 status(ApplicationStatus.INIT)，ApplicationRepositoryImpl.toEntity() 將 ApplicationStatus.INIT 轉換到 entity 的 status 欄位，不會觸發資料庫 DEFAULT。若有人直接 INSERT applications 而不提供 status（如手動 SQL 插入），會在 DB 儲存 "DRAFT"，但 Hibernate 讀取時找不到對應的 ApplicationStatus.DRAFT，拋 exception。這是 schema 和 domain 不一致的技術債，可能是 schema 演化過程中的遺留（舊版叫 DRAFT，後改為 INIT），應修正為 DEFAULT 'INIT'。

### Category

SQL Server and H2

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/resources/db/migration/V3__create_applications.sql
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Schema-domain mismatch is a real technical debt question; tests whether the candidate notices inconsistencies between SQL and Java code.

### Possible follow-up questions

- How would you fix this inconsistency — in the SQL migration or in the domain enum?
- Would ddl-auto: validate catch this inconsistency? Why or why not?
- What Flyway migration would you write to fix the DEFAULT value?

### Verification status

- Documentation-only


---

<a id="Q115"></a>

## Q115 — BIGINT GENERATED BY DEFAULT AS IDENTITY is used in H2 migrations. How does the SQL Server migration express the same thing, and why do they differ?

### 中文筆記

H2 語法（H2 ANSI SQL）：id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY，這是 ISO SQL 標準的 identity column 語法，H2 在 MODE=MSSQLServer 下也支援它。SQL Server 語法（db/migration-sqlserver/V1__create_users.sql）：id BIGINT IDENTITY(1,1) PRIMARY KEY，這是 SQL Server T-SQL 的專有語法（IDENTITY(seed, increment)，通常是 IDENTITY(1,1) 表示從 1 開始，每次 +1）。為什麼不同：H2 雖在 MODE=MSSQLServer 下模擬一些 SQL Server 語法，但 IDENTITY(1,1) 和 GENERATED BY DEFAULT AS IDENTITY 的語法解析不完全相同。GENERATED BY DEFAULT AS IDENTITY 在 H2 中是可靠的；IDENTITY(1,1) 在 H2 MODE=MSSQLServer 中也可能支援，但兩個 migration 資料夾的維護就是為了處理這類 dialect 差異，確保每個環境都有最可靠的語法。

### Category

SQL Server and H2

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/db/migration/V1__create_users.sql
- sp2-springboot/src/main/resources/db/migration-sqlserver/V1__create_users.sql

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Understanding SQL dialect differences is a practical database engineering question for teams working with multiple databases.

### Possible follow-up questions

- What does IDENTITY(1,1) mean in SQL Server? Can you change the seed?
- Is there a way to reset the identity counter in SQL Server after deleting all rows?
- Would Liquibase be a better tool than Flyway for managing dual-database migrations?

### Verification status

- Documentation-only


---

<a id="Q116"></a>

## Q116 — The SQL Server migration adds extra indexes that are absent from the H2 migration (e.g., idx_users_username, idx_user_roles_user_id). Why?

### 中文筆記

H2 在 dev/test 環境的特性：H2 是 in-memory 資料庫，資料量極少（seed data 幾十筆），query 速度本身就很快，index 對效能的影響微乎其微。H2 中缺少 index 不影響開發體驗，但會減少 migration script 的維護負擔。SQL Server staging 的必要性：SQL Server 在真實資料量下（千筆、萬筆），沒有 index 的 username 欄位查詢（WHERE username = ?）是 full table scan，效能差。UserDetailsServiceImpl.loadUserByUsername() 在每次認證時都 findByUsername()，高並發登入時沒有 index 就是瓶頸。idx_users_username 在 SQL Server migration 中是為此而加。設計原則：H2 migration 重視 schema structure（table、constraints），SQL Server migration 在 structure 基礎上加 performance optimization（indexes）。

### Category

SQL Server and H2

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/db/migration/V1__create_users.sql
- sp2-springboot/src/main/resources/db/migration-sqlserver/V1__create_users.sql

### Verified classes and methods

- UserDetailsServiceImpl.loadUserByUsername()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Different optimization concerns for dev vs staging databases is a practical database operations question.

### Possible follow-up questions

- What query plan would SQL Server use for findByUsername() without the index?
- Should idx_users_username also be in the H2 migration for consistency?
- How would you identify which indexes are missing in the SQL Server migration versus the H2 migration?

### Verification status

- Verified


---

<a id="Q117"></a>

## Q117 — AuditLog resides in common.audit package, but it has a @Column(length = 45) for ipAddress. Why 45 characters?

### 中文筆記

45 字元的理由：IPv4 最長表示：255.255.255.255（15 字元）。IPv6 完整表示（full form）：2001:0db8:85a3:0000:0000:8a2e:0370:7334（39 字元）。IPv6 with embedded IPv4（mapped）：::ffff:255.255.255.255（最長約 45 字元）。因此 VARCHAR(45) 能涵蓋所有 IPv4 和 IPv6 地址格式，是 IP 地址儲存的慣用長度。AuditIpResolver 可能返回任何格式的 IP，VARCHAR(45) 是 safe upper bound。若只用 VARCHAR(15)（只存 IPv4），未來系統支援 IPv6 後會有資料截斷問題。這個細節顯示 schema 設計考慮了 IPv6 的未來需求。

### Category

SQL Server and H2

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLog.java
- sp2-springboot/src/main/resources/db/migration/V14__reshape_audit_logs_for_sprint9.sql

### Verified classes and methods

- AuditLog

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Domain knowledge in schema design (IP address length) is a concrete technical detail that reveals database engineering awareness.

### Possible follow-up questions

- What is the maximum length of an IPv6 address, and does 45 characters cover it?
- How does AuditIpResolver determine whether to return an IPv4 or IPv6 address?
- What would happen if length = 15 and a request came from an IPv6 address?

### Verification status

- Verified


---

<a id="Q118"></a>

## Q118 — V14__reshape_audit_logs_for_sprint9.sql starts with DROP TABLE IF EXISTS audit_logs followed by a CREATE TABLE. When is this approach acceptable?

### 中文筆記

DROP + CREATE 的適用場景：在開發迭代早期（sprint 9 之前的 audit_logs 表結構還在演化），決定完全重設 audit log schema（重命名欄位、移除舊欄位）。DROP TABLE IF EXISTS 後 CREATE TABLE 等同於重建：舊的 audit log 資料全部丟失。可接受的條件：(1) audit log 是不需要 migrate 的歷史資料（清空舊格式 audit log 可以接受，不影響業務資料）；(2) dev/staging 環境沒有需要保留的 audit 資料（portfolio 場景）；(3) V14 是在 V6 的 audit_logs 之後的改版，若 V6 → V14 之間的資料不重要。不可接受的場景：production 環境有幾百萬筆 audit log 需要保留；DROP TABLE 在 production 是破壞性操作，正確做法是 ALTER TABLE ADD COLUMN、ALTER TABLE DROP COLUMN（分階段）。這個 migration 展示了 portfolio 和 production 的重要差異：portfolio 可以 DROP，production 不能。

### Category

Flyway Migrations

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/db/migration/V14__reshape_audit_logs_for_sprint9.sql

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Application starts with active Spring profile
2. Flyway migrates scripts from profile-specific locations
3. Hibernate ddl-auto=validate checks entities against schema

### Why this may be asked

DROP TABLE in a Flyway migration is alarming in production; tests whether the candidate understands when destructive migrations are acceptable.

### Possible follow-up questions

- What would happen if you ran this migration against a production database with 5 million audit log rows?
- How would you rewrite V14 to preserve existing data while still changing the schema?
- What is Flyway's behavior if you try to re-run V14 after it has already been applied?

### Verification status

- Documentation-only


---

<a id="Q119"></a>

## Q119 — Flyway runs two separate migration locations for dev and staging. How does Flyway know which location to use in each environment?

### 中文筆記

Flyway 位置的設定：application-dev.yml：spring.flyway.locations: classpath:db/migration,classpath:db/dev-seed（H2 migration + seed data）。application-staging.yml / application-prod.yml：spring.flyway.locations: classpath:db/migration-sqlserver（SQL Server migration）。Spring Boot 的 profile 機制：SPRING_PROFILES_ACTIVE=staging 時，Spring 會載入 application.yml（base）和 application-staging.yml，後者的 spring.flyway.locations 覆蓋 base 設定。結果：dev 環境跑 db/migration（H2 相容語法），staging 環境跑 db/migration-sqlserver（SQL Server 語法）。dev-seed 只在 dev profile 加入，確保測試種子資料不會出現在 staging。Version numbering（V1, V2...）在兩個資料夾中保持同步，同一版本的 migration 在兩個環境實現相同的 schema change，只是 SQL 語法不同。

### Category

Flyway Migrations

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/application-dev.yml
- sp2-springboot/src/main/resources/application-staging.yml
- sp2-springboot/docs/decisions/0007-h2-vs-sqlserver.md

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Application starts with active Spring profile
2. Flyway migrates scripts from profile-specific locations
3. Hibernate ddl-auto=validate checks entities against schema

### Why this may be asked

Flyway multi-location configuration is a common interview question for teams managing multiple database dialects.

### Possible follow-up questions

- What happens if V8__add_user_last_login.sql exists in db/migration but not in db/migration-sqlserver?
- How does Flyway handle the dev-seed scripts — are they treated as migrations?
- What is the flyway_schema_history table, and what would you find there after migration?

### Verification status

- Documentation-only


---

<a id="Q120"></a>

## Q120 — V10__extend_system_parameters.sql drops a unique constraint and adds a new one. What risk does dropping a constraint mid-migration pose?

### 中文筆記

風險分析：V10 做了：ALTER TABLE system_parameters DROP CONSTRAINT uk_system_parameters_key（移除舊的 key-only unique constraint）→ 資料更新（group 欄位）→ 新增 CONSTRAINT uk_system_parameters_group_key UNIQUE (param_group, param_key)。風險：(1) 若中間的 UPDATE 步驟有錯誤（如 group 值設錯），新的複合 unique constraint 建立前，舊約束已經移除，資料庫有一個短暫的「無 unique key」窗口（在 DDL 層面，若 migration 在此時中斷，資料可能有重複的 param_key）；(2) Flyway migration 通常在 transaction 中執行，DDL rollback 的能力依資料庫而定（H2 支援 DDL rollback，但 SQL Server 的 DDL 在 explicit transaction 中也是可 rollback 的，但 ALTER TABLE 後的 implicit commit 取決於設定）；(3) 這個 migration 假設現有資料更新後不會違反新的複合 unique constraint，若有兩筆 (OTP, expiry.minutes) 的資料，添加 unique constraint 就會失敗。

### Category

Flyway Migrations

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/resources/db/migration/V10__extend_system_parameters.sql

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Application starts with active Spring profile
2. Flyway migrates scripts from profile-specific locations
3. Hibernate ddl-auto=validate checks entities against schema

### Why this may be asked

Constraint manipulation in migrations is a production risk topic; tests awareness of migration safety.

### Possible follow-up questions

- How would you verify before running V10 that no duplicate (param_group, param_key) combinations exist?
- What does Flyway's outOfOrder configuration option do, and when would you need it?
- Should you back up the system_parameters table before running V10 in production?

### Verification status

- Documentation-only


---

<a id="Q121"></a>

## Q121 — Flyway has a flyway_schema_history table. What information does it contain, and how does it prevent running the same migration twice?

### 中文筆記

flyway_schema_history 的內容：每一筆執行過的 migration 記錄：installed_rank（執行順序）、version（如 "1"、"2"）、description（migration 說明）、type（SQL）、script（檔案名）、checksum（CRC32 雜湊）、installed_by（用戶）、installed_on（時間）、execution_time（毫秒）、success（boolean）。防止重複執行的機制：Flyway 啟動時讀取 flyway_schema_history，找出所有已成功執行的 migration（success = TRUE），對尚未執行的 migration（classpath 上有但 history 中無）才執行。Checksum 驗證：若 V3__create_applications.sql 已執行過（在 history 中），再次啟動時 Flyway 重新計算 checksum，若與 history 中的不符，拋 FlywayValidationError: Checksum mismatch——這防止了「偷偷修改已執行的 migration」。

### Category

Flyway Migrations

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/resources/db/migration/V1__create_users.sql
- sp2-springboot/src/main/resources/application.yml
- sp2-springboot/src/main/resources/application-dev.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Application starts with active Spring profile
2. Flyway migrates scripts from profile-specific locations
3. Hibernate ddl-auto=validate checks entities against schema

### Why this may be asked

Flyway schema history is fundamental to understanding database migration tooling; tests baseline knowledge.

### Possible follow-up questions

- What happens if you delete a row from flyway_schema_history and restart the application?
- When would you use flyway.repair command?
- What is the difference between flyway.clean and rolling back a migration?

### Verification status

- Documentation-only


---

<a id="Q122"></a>

## Q122 — dev-seed scripts (V100__seed_test_data.sql, V101__add_user_136628.sql) use version numbers in the 100s. Why are they versioned so much higher than the schema migrations?

### 中文筆記

High version number 的設計意圖：schema migration 版本從 V1 到 V14，使用 1-99 範圍。Seed data migration 從 V100 開始，確保：(1) seed data 永遠在 schema migration 之後執行（V100 > V14，Flyway 按版本號升序執行）；(2) 未來繼續加 schema migration（V15、V16...）也不會與 seed data 號碼衝突，直到達到 V100 還有很大空間；(3) 視覺上清晰區分「schema DDL」（1-99）和「dev data DML」（100+）。若 seed data 用 V15，和 schema migration 夾雜，容易誤認為是 schema change，也難以確定 seed data 在所有需要的 schema 建好之後才執行。在 staging/prod 不加入 dev-seed 到 Flyway locations，所以 V100 系列完全不在 staging/prod 執行。

### Category

Flyway Migrations

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/resources/db/dev-seed/V100__seed_test_data.sql
- sp2-springboot/src/main/resources/db/dev-seed/V101__add_user_136628.sql

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Application starts with active Spring profile
2. Flyway migrates scripts from profile-specific locations
3. Hibernate ddl-auto=validate checks entities against schema

### Why this may be asked

Migration versioning strategy shows planning ahead; tests whether the candidate thinks about maintainability.

### Possible follow-up questions

- What would happen if you accidentally included db/dev-seed in the staging Flyway locations?
- Could you use flyway.placeholders to make seed data environment-aware?
- Why is V101__add_user_136628.sql a separate migration from V100__seed_test_data.sql?

### Verification status

- Documentation-only


---

<a id="Q123"></a>

## Q123 — What happens if a developer changes an already-applied Flyway migration script by fixing a typo? Will the application still start?

### 中文筆記

結果：應用程式啟動時 Flyway 執行 validate（預設行為）：計算修改後的 migration 檔案的 checksum，與 flyway_schema_history 中記錄的原始 checksum 比較，兩者不符，Flyway 拋 FlywayValidationError: Migration checksum mismatch for migration version X，應用程式啟動失敗。修復選項：(1) 若是 dev 環境且不需要保留資料，執行 flyway clean（刪除所有表）然後重啟（重新跑所有 migration）；(2) 執行 flyway repair（更新 flyway_schema_history 中的 checksum 為當前檔案的 checksum）——謹慎使用，它承認了「已執行過的 migration 被修改」；(3) 建立新 migration（V4b 或 V15）來修正 schema，不修改已執行的 V4；(4) 設定 spring.flyway.validate-on-migrate=false（強烈不推薦 production）。正確做法：永遠不修改已 apply 的 migration，新需求用新 migration 版本。

### Category

Flyway Migrations

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/db/migration/V1__create_users.sql
- sp2-springboot/src/main/resources/application.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Application starts with active Spring profile
2. Flyway migrates scripts from profile-specific locations
3. Hibernate ddl-auto=validate checks entities against schema

### Why this may be asked

FlywayValidationError from editing applied migrations is a common developer mistake; tests operational awareness.

### Possible follow-up questions

- What is flyway.repair and when is it safe to use?
- How does Flyway calculate the checksum for a migration file?
- What is the flyway.outOfOrder property and when would you enable it?

### Verification status

- Documentation-only


---

<a id="Q124"></a>

## Q124 — Both V8__add_user_last_login.sql and V9__add_user_business_id.sql are single-column ALTER TABLE ADD COLUMN migrations. Why is each change its own migration rather than combining them?

### 中文筆記

獨立 migration 的好處：(1) 可回溯性（auditability）：每個 migration 對應一個明確的功能需求（last login tracking 是 sprint 8 的需求，business user id 是 sprint 9 的需求），獨立版本號讓 git history + flyway history 可以精確追蹤「何時、為什麼」加了這個欄位；(2) 細粒度的失敗隔離：若 V9 加欄位失敗，V8 不受影響；若合在一起，一個欄位加失敗可能讓另一個也 rollback（在有 transaction 的情況下），或造成部分 schema change（沒有 transaction DDL 的資料庫）；(3) 版本對應 sprint：V8 對應 sprint 8，V9 對應 sprint 9，讓 schema history 與開發迭代對齊，team 可以用 Flyway history 判斷 staging 環境「跑到哪個 sprint 的 schema」；(4) 若需要告知 DBA 審查，小的獨立 migration 比大批次的 ALTER 更容易 review。

### Category

Flyway Migrations

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/resources/db/migration/V8__add_user_last_login.sql
- sp2-springboot/src/main/resources/db/migration/V9__add_user_business_id.sql

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Migration granularity is a database change management practice question that reveals engineering discipline.

### Possible follow-up questions

- Is there a performance cost to running many small migrations vs fewer large ones?
- What is the maximum safe size for a single migration in production?
- How does the sprint-aligned versioning help when multiple developers are working in parallel?

### Verification status

- Documentation-only


---

<a id="Q125"></a>

## Q125 — What would happen if a team member ran mvn clean verify with the H2 migration scripts, but the application-dev.yml was not the active profile?

### 中文筆記

活躍 profile 的影響：mvn clean verify 執行測試時，test 類別（如 ApplicationFlowIntegrationTest）有 @ActiveProfiles("dev")，明確指定使用 dev profile。若沒有 @ActiveProfiles 的測試，Spring Boot test 預設使用 default profile（不加入任何 profile-specific yml），則 application.yml（base）生效，沒有 datasource 設定（或 datasource 設到 SQL Server URL），測試可能找不到資料庫而失敗。在 src/test/resources/application-dev.yml（test resource）中也有配置確保測試使用 H2。結論：測試設計有 @ActiveProfiles("dev") 保護，確保 H2 被使用。若移除 @ActiveProfiles，且 src/test/resources 中沒有對應設定，測試可能嘗試連 SQL Server（不可用）而失敗。

### Category

Flyway Migrations

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java
- sp2-springboot/src/test/resources/application-dev.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Application starts with active Spring profile
2. Flyway migrates scripts from profile-specific locations
3. Hibernate ddl-auto=validate checks entities against schema

### Why this may be asked

Profile management in tests is a common operational confusion; tests whether the candidate understands how profile activation works in the test lifecycle.

### Possible follow-up questions

- What is src/test/resources/application-dev.yml used for, and how does it differ from src/main/resources/application-dev.yml?
- How would you run only integration tests that use the staging SQL Server profile in CI?
- What is @TestPropertySource and when would you use it instead of @ActiveProfiles?

### Verification status

- Documentation-only


---

<a id="Q126"></a>

## Q126 — The audit log schema was redesigned in V14. Why was DROP TABLE IF EXISTS used instead of ALTER TABLE? What are the migration lifecycle consequences?

### 中文筆記

選擇 DROP + CREATE 的原因：V6 的 audit_logs schema（entity_type、entity_id、action、performed_by、details）與 V14 的新 schema（username、action、ip_address、result、detail）有根本性不同（column set 幾乎全部不同），ALTER TABLE 需要多步驟：ADD COLUMN username、ADD COLUMN ip_address、ADD COLUMN result、DROP COLUMN entity_type、DROP COLUMN entity_id、DROP COLUMN performed_by——比 DROP + CREATE 繁瑣，且需要處理 NOT NULL 欄位的 default value 問題。Migration lifecycle 後果：(1) Flyway checksum 記錄 V14 的 DROP + CREATE，未來無法修改此腳本；(2) 所有在 V6 和 V14 之間建立的 audit log 在 V14 執行時永久消失；(3) 若在 staging 環境先跑 V6-V13，再跑 V14，需要接受歷史 audit log 清除；(4) 若未來 V15 參考了 audit_logs table，V14 的 CREATE TABLE 保證 schema 正確。在 dev/portfolio 環境這是合理的。

### Category

Flyway Migrations

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/resources/db/migration/V6__create_audit_logs.sql
- sp2-springboot/src/main/resources/db/migration/V14__reshape_audit_logs_for_sprint9.sql

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Application starts with active Spring profile
2. Flyway migrates scripts from profile-specific locations
3. Hibernate ddl-auto=validate checks entities against schema

### Why this may be asked

This is a nuanced migration strategy question; reveals whether the candidate can reason about data loss, schema evolution, and production safety.

### Possible follow-up questions

- How would you write V14 as a zero-data-loss migration for a production database?
- What does flyway.validateOnMigrate=false do, and should you ever set it to false?
- If V14 fails midway in SQL Server (which doesn't support transactional DDL in all scenarios), what state would the database be in?

### Verification status

- Documentation-only


---

<a id="Q127"></a>

## Q127 — ApplicationAppService.createApplication() has @Transactional but getApplication() has @Transactional(readOnly = true). Why does even a read-only query need a transaction?

### 中文筆記

Read-only 也需要 transaction 的原因：(1) JPA Lazy Loading：getApplication() 讀取 ApplicationEntity 後呼叫 entity.getWorkflowHistories()（lazy collection），若沒有 open session，就拋 LazyInitializationException；@Transactional 確保整個方法執行期間 Hibernate session 保持 open，lazy load 可以在 session 內完成；(2) Transaction isolation：@Transactional(readOnly = true) 給資料庫 hint，可用較低的 isolation level（如 READ COMMITTED），某些資料庫在 read-only transaction 下可以路由到 read replica；(3) Hibernate first-level cache（session cache）：在 transaction 內，同一 entity 的多次查詢只打一次 DB（identity map），read-only transaction 可以共享此 cache。若沒有 @Transactional，每個 JPA 操作自成一個 transaction（auto-commit），lazy collection 在第一個 transaction（取 ApplicationEntity）結束後 session 就關閉了，第二次存取 workflowHistories 沒有 session。

### Category

Transactions

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java

### Verified classes and methods

- ApplicationAppService.getApplication()
- ApplicationEntity

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The interplay between @Transactional(readOnly = true) and lazy loading is a frequently confused topic; tests JPA transaction management depth.

### Possible follow-up questions

- What is open-session-in-view and why is it considered an anti-pattern?
- Does @Transactional(readOnly = true) propagate to a called service method with @Transactional?
- How would @EntityGraph or JOIN FETCH eliminate the need for @Transactional in a simple read method?

### Verification status

- Verified


---

<a id="Q128"></a>

## Q128 — AuditLogWriter.saveAsync() uses PROPAGATION_REQUIRES_NEW. What does this mean for the surrounding transaction?

### 中文筆記

PROPAGATION_REQUIRES_NEW：requiresNewTransaction = new TransactionTemplate(transactionManager) 設定 PROPAGATION_REQUIRES_NEW，執行時暫停（suspend）當前 thread 上的任何活躍 transaction，開始一個全新的獨立 transaction 來執行 auditLogRepository.save(auditLog)，完成後 commit 新 transaction，然後恢復被暫停的原始 transaction。業務意義：audit log 的寫入與業務操作的 transaction 隔離。若業務 transaction rollback（如申請提交失敗），audit log 已在獨立 transaction 中 committed，不會隨業務 rollback 一起消失。這確保了：即使業務操作失敗，失敗的 audit log（result = "FAILURE"）仍被保留，audit trail 完整。若使用預設的 PROPAGATION_REQUIRED，audit log 與業務在同一 transaction，business rollback 時 audit log 也會 rollback，失去審計記錄。

### Category

Transactions

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java

### Verified classes and methods

- AuditLogWriter.saveAsync()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

PROPAGATION_REQUIRES_NEW for audit logging is a classic design pattern with clear business rationale; tests transaction propagation knowledge.

### Possible follow-up questions

- What is the difference between PROPAGATION_REQUIRES_NEW and PROPAGATION_NESTED?
- Could PROPAGATION_REQUIRES_NEW in saveAsync() cause a deadlock? Under what conditions?
- What happens if the saveAsync() thread's new transaction also fails?

### Verification status

- Verified


---

<a id="Q129"></a>

## Q129 — OtpCleanupScheduler.cleanupExpiredOtps() is annotated with @Transactional. What would happen without it?

### 中文筆記

沒有 @Transactional 的後果：otpRepository.markExpiredBefore(cutoff) 最終呼叫 OtpJpaRepository.markExpiredBefore()，這是 @Modifying + @Query（UPDATE 語句）。@Modifying 的執行需要一個活躍的 JPA transaction。若沒有 @Transactional 在 scheduler 方法上，Spring Data JPA 的 repository method 在沒有 outer transaction 的情況下，根據 default propagation（PROPAGATION_REQUIRED），會建立自己的 transaction 並在方法結束後 commit。實際上這在多數情況下仍可工作，但有微妙差異：(1) JPA 的 auto-transaction 管理不如明確聲明的 @Transactional 清晰；(2) 若 markExpiredBefore() 的實作使用了 JPA session 的 first-level cache（entity manager），沒有 outer transaction 時 session 的 lifecycle 更短，某些 cache 行為不同；(3) 明確聲明 @Transactional 讓 log（@Slf4j）和 transaction 邊界清晰，try-catch 也明確包裹整個 scheduler 邏輯。

### Category

Transactions

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java

### Verified classes and methods

- OtpCleanupScheduler.cleanupExpiredOtps()
- OtpJpaRepository

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Transaction boundaries on scheduled tasks is a frequently missed concern; tests awareness of where transactions originate.

### Possible follow-up questions

- If @Transactional is on the scheduler and the @Modifying update fails, does the scheduler's try-catch handle it?
- What is @Transactional's default isolation level in Spring?
- Could a long-running cleanupExpiredOtps() hold a database lock that blocks other operations?

### Verification status

- Verified


---

<a id="Q130"></a>

## Q130 — ReviewAppService.approveCase() calls applicationRepository.save() and reviewCaseRepository.save(). What happens to the domain event if the second save fails?

### 中文筆記

失敗場景：假設 applicationRepository.save(application) 成功（Application 狀態改為 APPROVED），但 reviewCaseRepository.save(reviewCase) 拋 exception：(1) @Transactional 的 rollback：exception 傳播出 approveCase() 方法，Spring 的 TransactionInterceptor 偵測到 exception，觸發 rollback，兩個 save 都撤銷，DB 狀態回到 approve 之前——這是正確的 ACID 行為；(2) 事件：eventPublisher.publishEvent(ApplicationApprovedEvent) 在 exception 之前已執行，NotificationEventHandler 已在同一 thread 處理（Spring 同步事件），若通知已發出（mock SMS log 已寫），但 DB transaction rollback，業務上出現「通知已發出但申請未真正 approve」的不一致。這正是 @TransactionalEventListener(phase = AFTER_COMMIT) 可以解決的問題——等 transaction commit 成功後才發送通知，若 rollback 則通知不發出。

### Category

Transactions

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java

### Verified classes and methods

- ReviewAppService.approveCase()

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

Event-before-commit inconsistency is a subtle but critical production concern; tests advanced transaction and event management knowledge.

### Possible follow-up questions

- What is @TransactionalEventListener and which TransactionPhase would you use?
- If you switch to AFTER_COMMIT, what happens if the notification service itself throws an exception?
- How does the Outbox Pattern eliminate this event-before-commit inconsistency entirely?

### Verification status

- Verified


---

<a id="Q131"></a>

## Q131 — @Transactional is on the application service methods, not on the domain methods. Why this placement?

### 中文筆記

@Transactional 在 application service 的理由：(1) Transaction 是 use case 的 concern：一個 use case（如 submitApplication()）需要在單一 ACID transaction 中完成多個 DB 操作（save Application、save OTP、emit event）；use case 的範圍就是 transaction 的範圍，由 application service 控制；(2) Domain 純粹性：domain aggregate 方法（application.submit()）是純業務邏輯，不應知道「我是否在 transaction 中」；加了 @Transactional 就讓 domain 依賴 Spring，違反 clean architecture；(3) Spring AOP proxy 限制：@Transactional 需要 Spring proxy，domain objects 通常不是 Spring bean（Application 是 plain Java object，不是 @Service），proxy 機制不適用於 new 出來的 domain objects；(4) 可測試性：domain unit test 無需 transaction 管理，application service test 可以 mock repository，integration test 才需要真實 transaction。

### Category

Transactions

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- ApplicationAppService.createApplication()
- Application.submit()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Transaction placement in Clean Architecture is a foundational design question; tests whether the candidate understands layer responsibilities.

### Possible follow-up questions

- Can @Transactional on a Spring bean call a domain method that modifies state outside the transaction?
- What propagation behavior does @Transactional use by default?
- If Application were a Spring @Service bean, would putting @Transactional on it be appropriate?

### Verification status

- Verified


---

<a id="Q132"></a>

## Q132 — IdempotencyService.execute() acquires a lock with tryAcquireLock() and always releases it in finally. Why is finally critical here?

### 中文筆記

finally 的必要性：lock 在 try 區塊取得（idempotencyStore.tryAcquireLock(lockKey, LOCK_TTL_SECONDS)），業務邏輯（action.get()）在 try 內執行，finally 確保 idempotencyStore.releaseLock(lockKey) 不論業務邏輯是否拋 exception 都執行。若沒有 finally：若 action.get() 拋 exception（如 DB 錯誤），lock 不會被釋放，lockKey 在 Redis 中的 TTL（30 秒）到期前，任何相同 idempotency key 的重試都會得到「A request with this Idempotency-Key is already in progress」的錯誤，即使前一個請求已失敗。LOCK_TTL_SECONDS = 30 作為 safety net：即使 releaseLock() 自身也失敗（Redis 不可用），lock 最多 30 秒後自動過期，不會永久鎖住。這是分散式 lock 的標準 acquire-try-finally-release pattern。

### Category

Redis and Idempotency

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java

### Verified classes and methods

- IdempotencyService.execute()
- RedisIdempotencyStore.tryAcquireLock()
- RedisIdempotencyStore.releaseLock()

### Execution flow

1. Controller receives optional Idempotency-Key header on create application
2. IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find()
3. On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock()
4. On hit with same hash, rebuild prior ResponseEntity; on hash conflict, return conflict error

### Why this may be asked

try/finally in distributed lock patterns is critical production knowledge; tests whether the candidate understands what happens when the action throws.

### Possible follow-up questions

- What is the risk of a very long-running action.get() combined with a 30-second lock TTL?
- How does redisTemplate.opsForValue().setIfAbsent(lockKey, "1", Duration) implement SETNX semantics?
- What is Lua scripting in Redis, and why would it make lock/unlock more atomic?

### Verification status

- Verified


---

<a id="Q133"></a>

## Q133 — ReportAppService can emit Excel or PDF. Which generators are used, and what is a known DRY debt?

### 中文筆記

ReportAppService 依格式呼叫 ExcelReportGenerator（Apache POI）或 PdfReportGenerator（iText）。維護文件指出兩者有重複的百分比格式化邏輯，屬可接受的小債，新增第三種格式前再抽取共用工具即可。

### Category

Reports (Excel and PDF)

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/PdfReportGenerator.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- ReportAppService
- ExcelReportGenerator
- PdfReportGenerator

### Execution flow

1. Admin calls report API with date and format
2. ReportDataService aggregates counts
3. ExcelReportGenerator or PdfReportGenerator returns bytes

### Why this may be asked

Grounds reporting stack knowledge in actual classes and documented debt.

### Possible follow-up questions

- Which role can call report APIs?
- Does report generation run asynchronously today?
- What data service feeds daily statistics?

### Verification status

- Verified


---

<a id="Q134"></a>

## Q134 — InMemoryIdempotencyStore is used for tests and when Redis is unavailable. How does its TTL enforcement differ from Redis?

### 中文筆記

TTL 實現差異：RedisIdempotencyStore.save(key, entry, ttlSeconds)：呼叫 redisTemplate.opsForValue().set(key, json, Duration.ofSeconds(ttlSeconds))，TTL 由 Redis 服務器在指定時間後自動刪除 key，完全 server-side，不需要 JVM 做任何事。InMemoryIdempotencyStore.save(key, entry, ttlSeconds)：entries.put(key, new TimedEntry(entry, Instant.now().plusSeconds(ttlSeconds)))，TTL 是 TimedEntry.expiresAt，過期判斷在 find() 時（timed.expiresAt().isBefore(Instant.now())）做 lazy eviction，過期後 entry 在下次被 find() 時才被移除（或由 cleanupExpiredEntries() 主動清除）。差異：Redis 的過期是 exact（服務器計時），JVM 記憶體的過期是 lazy（需要被查詢才清除）；Redis TTL 對 JVM restart 有效（資料在 Redis 中），in-memory TTL 在 JVM 重啟後消失。

### Category

Redis and Idempotency

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/InMemoryIdempotencyStore.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java

### Verified classes and methods

- InMemoryIdempotencyStore.find()
- InMemoryIdempotencyStore.save()

### Execution flow

1. Controller receives optional Idempotency-Key header on create application
2. IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find()
3. On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock()
4. On hit with same hash, rebuild prior ResponseEntity; on hash conflict, return conflict error

### Why this may be asked

Understanding the implementation difference between in-memory and Redis TTL is a nuanced distributed systems question.

### Possible follow-up questions

- Could two threads in the InMemoryIdempotencyStore see different TTL expiry results for the same key?
- Is the InMemoryIdempotencyStore safe to use in a multi-instance deployment?
- What would happen if the JVM clock was adjusted backward by NTP while idempotency entries were stored?

### Verification status

- Verified


---

<a id="Q135"></a>

## Q135 — OtpAppService.sendOtp() stores the actual OTP code in AuditContext.put("otpCode", otpCode). Is this a security concern?

### 中文筆記

安全疑慮：AuditContext.put("otpCode", otpCode) 將 OTP 明文寫入 audit context，AuditAspect 在 mergeDetail() 中會把 AuditContext.buildSuffix()（包含 "otpCode=123456"）加入 audit log 的 detail 欄位，audit log 儲存在資料庫的 audit_logs.detail VARCHAR(500)。問題：OTP 是一次性密碼，若 audit log 記錄了 OTP 明文，有資料庫存取權限的人（DBA、admin）可以查看 OTP，理論上可以重放 OTP（在有效期內）。正確做法：不應記錄 OTP 明文，應記錄 masked 版本（otpCode=1****6）或只記錄「OTP generated for mobile: 0912****78」。此處是 portfolio 場景的 security gap，實際 production 應移除或遮蔽 OTP 的 audit 記錄。AuditContext 作為 ThreadLocal 的使用是 OK 的，問題是 what is stored。

### Category

OTP Flow

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditContext.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java

### Verified classes and methods

- OtpAppService.sendOtp()
- AuditContext.put()
- AuditAspect.mergeDetail() (private)

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Storing credentials (including OTPs) in audit logs is a real security vulnerability; tests whether the candidate notices security-sensitive code patterns.

### Possible follow-up questions

- How would you mask the OTP code in the audit context before logging it?
- Should OTP codes ever appear in application logs (not audit logs) via log.info()?
- What is the PDPA/GDPR implication of storing OTP codes in a database audit log?

### Verification status

- Verified


---

<a id="Q136"></a>

## Q136 — OtpRecord.verify() throws exceptions rather than returning a detailed failure result. Given VerifyResult only has SUCCESS, is the exception-based approach appropriate here?

### 中文筆記

目前設計：OtpRecord.verify() 對三種失敗情況拋 exception：OTP_EXPIRED（BusinessException）、OTP_RETRY_EXCEEDED（BusinessException）、OTP_MISMATCH（BusinessException）；只有成功時返回 VerifyResult.SUCCESS。合理性分析：拋 exception 讓 OTP 驗證失敗自動觸發 audit 的 FAILURE log（AuditAspect catch block 記錄失敗）；若用 result type，OtpAppService 需要手動判斷是 SUCCESS/FAILURE，並手動記錄 audit，邏輯分散。Exception 的三種類型對應不同的 ErrorCode，GlobalExceptionHandler 映射到正確的 HTTP status（OTP 相關通常是 400）。缺點：VerifyResult 只有 SUCCESS 顯得多餘，可以改成 void 方法（成功就 return，失敗就 throw）。若日後需要更多成功結果細分（如 VERIFIED_WITH_GRACE_PERIOD），VerifyResult 才有擴展空間。

### Category

OTP Flow

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/VerifyResult.java

### Verified classes and methods

- OtpRecord.verify()
- VerifyResult

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Exception vs result type is a design philosophy question with practical trade-offs in this specific context.

### Possible follow-up questions

- What would change if verify() were void and threw exceptions for all failures?
- How does the exception-based approach interact with AuditAspect's catch block?
- If you added VERIFIED_NEAR_EXPIRY to VerifyResult, how would OtpAppService handle it differently?

### Verification status

- Verified


---

<a id="Q137"></a>

## Q137 — OtpAppService.sendOtp() cancels any existing PENDING OTP before creating a new one. What race condition could still occur?

### 中文筆記

殘留的 race condition：兩個並發的 sendOtp() 呼叫（同一 mobile number）：(1) Thread A 查詢 findLatestPendingByMobile("0912345678") → 返回 OTP-1（PENDING）；(2) Thread B 查詢 findLatestPendingByMobile("0912345678") → 也返回 OTP-1（PENDING，因為 A 還沒 cancel）；(3) Thread A cancel OTP-1，save 新 OTP-2；(4) Thread B cancel OTP-1（已被 A cancel，cancel() 再次設定 CANCELLED，otpRepository.save(existing) 不報錯），save 新 OTP-3；結果：同一手機有兩筆 PENDING OTP（OTP-2 和 OTP-3）。findLatestPendingByMobile() 取最新的（OTP-3），用戶收到 OTP-3 但 OTP-2 也是 PENDING 狀態，可用 OTP-2 驗證（若在 TTL 內）——這是 bypass retry limit 的潛在漏洞。真正的 fix 需要資料庫層面的 lock（SELECT ... FOR UPDATE）或 unique constraint 確保一次只有一筆 PENDING OTP。

### Category

OTP Flow

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpRepositoryImpl.java

### Verified classes and methods

- OtpAppService.sendOtp()
- OtpJpaRepository

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Concurrent OTP generation race conditions are a real security issue in production banking systems; tests concurrent programming awareness.

### Possible follow-up questions

- How would a SELECT ... FOR UPDATE (pessimistic lock) prevent this race?
- Could a unique database constraint on (mobile, status) where status = 'PENDING' help?
- What is a partial index in PostgreSQL, and how would it solve this problem differently from SQL Server?

### Verification status

- Verified


---

<a id="Q138"></a>

## Q138 — OtpRecord.retryCount is incremented directly on the domain object without a save() call inside verify(). How does the updated retryCount get persisted?

### 中文筆記

持久化的路徑：OtpRecord.verify() 執行 retryCount++，這修改了 domain object 的 in-memory 狀態；但 OtpRecord 不是 JPA entity，它只是一個 plain Java object（@Getter、@Builder），修改 retryCount 不觸發 JPA dirty checking。OtpAppService.verifyOtp() 在 otpRecord.verify(command.otpCode(), maxRetry, clock) 之後，呼叫 otpRepository.save(otpRecord) 顯式 save。OtpRepositoryImpl.save() 的邏輯：otpRecord.getOtpId() != null（已存在的 OTP），找到對應的 OtpRecordEntity，呼叫 applyToEntity(existing, otp) 更新 entity 的所有欄位（包含更新後的 retryCount 和 status），然後 otpJpaRepository.save(entity)。這個「修改 domain object 後顯式 save」的 pattern 是 Clean Architecture repository 的標準做法，不依賴 JPA 的 dirty checking magic。

### Category

OTP Flow

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpRepositoryImpl.java

### Verified classes and methods

- OtpRecord.verify()
- OtpAppService.verifyOtp()
- OtpRepositoryImpl.save()

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Understanding why Clean Architecture repository adapters require explicit saves (not JPA dirty checking) is a key implementation detail.

### Possible follow-up questions

- What would happen if otpRepository.save(otpRecord) was accidentally omitted after verify()?
- How is this different from the JPA @Entity pattern where dirty checking handles persistence?
- If verify() is called and throws OTP_MISMATCH, does retryCount still get saved?

### Verification status

- Verified


---

<a id="Q139"></a>

## Q139 — OtpAppService uses SecureRandom for OTP code generation. Why SecureRandom over Random?

### 中文筆記

SecureRandom vs Random 的差異：java.util.Random 是偽隨機數生成器（PRNG），使用可預測的算法（linear congruential generator），若攻擊者知道 seed（或猜測 seed），可以預測後續所有的「隨機」數值。SecureRandom 使用密碼學上安全的隨機數生成算法（如 /dev/urandom、OS entropy source），不可預測，即使知道大量之前的 OTP code 也無法預測下一個。OTP 的安全需求：OTP 是身份驗證憑據，若可預測，攻擊者可以「猜測」OTP 而非暴力測試（受 retry limit 限制）。用 Random 的 6 位數 OTP 只有 1,000,000 種可能，且可預測，安全性極低。JVM 的 SecureRandom 是一個 static final 常數，避免每次呼叫重新實例化（SecureRandom 的初始化有 entropy collection 成本，reuse 是效能最佳實踐）。

### Category

OTP Flow

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java

### Verified classes and methods

- OtpAppService
- OtpAppService.generateOtpCode() (private)

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

SecureRandom vs Random for authentication credentials is a basic security knowledge question.

### Possible follow-up questions

- Is there a performance cost to SecureRandom? How does it compare to ThreadLocalRandom?
- Should SecureRandom be a singleton? Why is it static final in this class?
- What is entropy in the context of random number generation?

### Verification status

- Verified


---

<a id="Q140"></a>

## Q140 — OtpRecord.markExpired() is called inside verify() when the OTP is expired. Why transition to EXPIRED status inside the verify method rather than the cleanup scheduler?

### 中文筆記

在 verify 時即時 mark expired 的理由：(1) 即時回饋：用戶呼叫 verify 時，若 OTP 過期，呼叫 markExpired()（status = EXPIRED），後續 otpRepository.save(otpRecord) 立即更新 DB，資料庫中的狀態即刻反映「此 OTP 已嘗試使用但已過期」；(2) 狀態一致性：PENDING → EXPIRED（使用者嘗試驗證時觸發）vs. PENDING → EXPIRED（cleanup scheduler 按時觸發），兩路都能讓 OTP 進入 EXPIRED 狀態，確保 PENDING 狀態只代表「有效且未使用」的 OTP；(3) 降低 findLatestPendingByMobile() 的干擾：若過期 OTP 留在 PENDING，cleanup scheduler 可能延遲清除，findLatestPendingByMobile() 可能返回過期的 OTP，讓用戶看到「已過期」的錯誤而非「沒有 OTP」；即時 mark expired 讓後續的 findLatestPendingByMobile() 不再返回它。

### Category

OTP Flow

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java

### Verified classes and methods

- OtpRecord.verify()
- OtpRecord.markExpired()
- OtpCleanupScheduler.cleanupExpiredOtps()

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

The interaction between on-demand state changes and scheduled cleanup reveals understanding of system-wide consistency.

### Possible follow-up questions

- If markExpired() is called in verify() but the save() fails, what is the OTP's status in the database?
- Could the OtpCleanupScheduler and verify() both try to update the same OTP simultaneously? What is the risk?
- Would it be acceptable to skip markExpired() in verify() and rely only on the scheduler?

### Verification status

- Verified


---

<a id="Q141"></a>

## Q141 — SystemParameterService.getValue() uses the cache-aside pattern. Walk me through a cache miss and a cache hit.

### 中文筆記

Cache miss 流程：getValue("OTP", "expire_minutes") → cacheStore.get("sys_param:OTP:expire_minutes") → cache 沒有 → log.debug("Cache MISS...") → systemParameterRepository.findByGroupAndKey("OTP", "expire_minutes") → 找到 SystemParameter → 取 paramValue（如 "5"） → cacheStore.put(cacheKey, "5", cacheTtlProvider.getTtlSeconds()) → return "5"。Cache hit 流程：同樣的 getValue("OTP", "expire_minutes") → cacheStore.get("sys_param:OTP:expire_minutes") → cache 有 → log.debug("Cache HIT...") → 直接 return cached value "5"，不碰 DB。CacheTtlProvider.getTtlSeconds() 提供 TTL（可能從 system parameter 或 config 讀取）。若 system parameter 值在 DB 更新，cache 中的舊值直到 TTL 過期後才被清除，或 admin 手動觸發 evict，或 CacheRefreshScheduler 定期清除。

### Category

In-Memory Cache

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/parameter/service/SystemParameterService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CacheKeys.java

### Verified classes and methods

- SystemParameterService.getValue()
- InMemoryCacheStore.get()
- InMemoryCacheStore.put()

### Execution flow

1. Read path goes through CachedCardProductRepository or SystemParameterService cache
2. InMemoryCacheStore stores values in-process with TTL
3. Admin CacheManagementService can evict/refresh; CacheRefreshScheduler refreshes periodically

### Why this may be asked

Cache-aside pattern is a fundamental caching strategy; tests whether the candidate can trace through a concrete implementation.

### Possible follow-up questions

- What is the difference between cache-aside, read-through, and write-through caching?
- What is a cache stampede, and could it happen in SystemParameterService.getValue()?
- How does CacheTtlProvider determine the TTL, and where is it configured?

### Verification status

- Verified


---

<a id="Q142"></a>

## Q142 — InMemoryCacheStore is ConcurrentHashMap-backed. What thread-safety guarantees does this provide, and what gaps remain?

### 中文筆記

ConcurrentHashMap 的保證：get()/put()/remove() 操作是原子的，多執行緒並發存取不會拋 ConcurrentModificationException；segment-level locking（Java 8+ 使用 CAS + synchronized on bucket）允許高並發讀寫。cleanupExpiredEntries() 使用 store.remove(entry.getKey(), entry.getValue()) 這個原子的 compare-and-swap remove，只有在 value 未被其他 thread 修改時才刪除，避免刪除了 concurrent put 的新 entry。殘留的 thread-safety gap：cache miss → DB 查詢 → put 的整個序列不是原子的。兩個 thread 同時 cache miss，都查 DB，都 put，最後的 put 覆蓋前一個——但因為兩個都是 get 同一個 system parameter，put 的值相同，沒有問題（idempotent）。cleanupExpiredEntries() 迭代 entrySet 時，concurrent put/remove 在 ConcurrentHashMap 下不拋 exception（weakly consistent iterator），但可能看到舊版本。InMemoryCacheStoreTest.concurrentAccess_shouldBeThreadSafe() 用 200 個 parallel stream 驗證了這個行為。

### Category

In-Memory Cache

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java
- sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStoreTest.java

### Verified classes and methods

- InMemoryCacheStoreTest

### Execution flow

1. Read path goes through CachedCardProductRepository or SystemParameterService cache
2. InMemoryCacheStore stores values in-process with TTL
3. Admin CacheManagementService can evict/refresh; CacheRefreshScheduler refreshes periodically

### Why this may be asked

Thread-safety guarantees and gaps in ConcurrentHashMap is a classic Java concurrency question applied to a real cache implementation.

### Possible follow-up questions

- What is the difference between ConcurrentHashMap and Collections.synchronizedMap(new HashMap())?
- What is a weakly consistent iterator in ConcurrentHashMap?
- How would you add a read-through cache to make cache misses serve only one DB query despite concurrent misses?

### Verification status

- Verified


---

<a id="Q143"></a>

## Q143 — CacheStore interface is in infrastructure.cache, not in domain or application. Is this placement correct?

### 中文筆記

位置的評估：CacheStore interface 在 infrastructure.cache，而使用它的 SystemParameterService 在 application layer。這表示 application layer 依賴 infrastructure.cache（import 了 infrastructure package 的 interface），違反了 Clean Architecture 依賴規則（application 不應依賴 infrastructure）。正確位置：CacheStore interface 應在 application layer（或 domain layer 若快取是業務規則），由 InMemoryCacheStore 在 infrastructure 實作。類比：IdempotencyStore interface 在 infrastructure.idempotency，同樣被 application layer 的 IdempotencyService 使用，也有相同的 leak。這是 ADR 0001 提到的已知不完美，接受 interface 留在 infrastructure package 的 pragmatic 決定，因為它們是「技術能力」而非「業務能力」。

### Category

In-Memory Cache

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CacheStore.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/parameter/service/SystemParameterService.java
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md

### Verified classes and methods

- InMemoryCacheStore
- CachedCardProductRepository
- CacheStore

### Execution flow

1. Read path goes through CachedCardProductRepository or SystemParameterService cache
2. InMemoryCacheStore stores values in-process with TTL
3. Admin CacheManagementService can evict/refresh; CacheRefreshScheduler refreshes periodically

### Why this may be asked

Asking whether an interface is correctly placed tests the ability to apply Clean Architecture principles to concrete code, not just theory.

### Possible follow-up questions

- Where should CacheStore be moved to fully comply with the dependency rule?
- Would moving CacheStore to application.cache require any other changes?
- How would ArchUnit detect this dependency violation automatically in CI?

### Verification status

- Verified


---

<a id="Q144"></a>

## Q144 — The cache does not use Spring Cache (@Cacheable). What are the trade-offs of using a custom CacheStore over Spring's built-in caching abstraction?

### 中文筆記

自訂 CacheStore vs Spring Cache（@Cacheable）的取捨：Spring Cache 的優點：@Cacheable(cacheNames = "cardProducts") 只需加注解，自動代理 method 呼叫，支援多種 backend（EHCache、Caffeine、Redis Spring Cache）統一切換，@CacheEvict、@CachePut 提供聲明式 cache management。自訂 CacheStore 的優點：可精確控制 cache entry 的 TTL（put(key, value, ttlSeconds)），Spring Cache 的 TTL 設定依 backend 而不同（Caffeine 可設，Redis 需 RedisCacheConfiguration）；更容易測試（mock CacheStore interface，不需要 @SpringBootTest 激活 cache proxy）；CacheManagementService 可以暴露 cache stats（keys、hit/miss）API，Spring Cache 不直接提供這個；在不同 profile 下 InMemoryCacheStore（dev/test）和未來 RedisCacheStore（prod）可以透過 Spring DI 切換，無需改變業務程式碼。

### Category

In-Memory Cache

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CacheStore.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java

### Verified classes and methods

- InMemoryCacheStore
- CachedCardProductRepository
- CacheStore

### Execution flow

1. Read path goes through CachedCardProductRepository or SystemParameterService cache
2. InMemoryCacheStore stores values in-process with TTL
3. Admin CacheManagementService can evict/refresh; CacheRefreshScheduler refreshes periodically

### Why this may be asked

Custom vs Spring Cache abstraction is a design decision question that reveals framework knowledge and critical thinking.

### Possible follow-up questions

- What annotation-based Spring caching would replace SystemParameterService.getValue() cache logic?
- How does Caffeine's TTL configuration compare to the custom CacheStore.put() TTL parameter?
- What would you need to add to support cache statistics in @Cacheable?

### Verification status

- Verified


---

<a id="Q145"></a>

## Q145 — ReviewCase.transitionTo() uses a series of if checks rather than a map-based approach like ApplicationStatus. Why might the review state machine be implemented differently?

### 中文筆記

兩種實作方式的對比：ApplicationStatus（enum-based map）：Map<ApplicationStatus, Set<ApplicationStatus>> ALLOWED_TRANSITIONS 在 enum 常數中定義所有合法轉換，canTransitionTo() 做 map lookup，擴展性好（加新狀態只改 map），但邏輯分散在 enum 和 aggregate 兩處。ReviewCase.transitionTo()（if-else 邏輯）：直接在方法內 if (PENDING && UNDER_REVIEW) { ok } if (UNDER_REVIEW && (APPROVED || REJECTED)) { ok } else { throw }，邏輯集中在一個方法，容易理解和修改，適合小型狀態機（ReviewStatus 只有 PENDING、UNDER_REVIEW、APPROVED、REJECTED、可能 CANCELLED 共 4-5 個狀態）。選擇 if-else 的理由：ReviewCase 的 transition 邏輯簡單（線性路徑），map-based 方法顯得 over-engineered；ApplicationStatus 有 8 個狀態和多個分叉路徑（INIT 可以到 OTP_VERIFIED 或 CANCELLED），map-based 更合適。設計選擇反映複雜度比例。

### Category

Credit Review Workflow

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java

### Verified classes and methods

- ReviewCase.transitionTo() (private)
- ApplicationStatus.ALLOWED_TRANSITIONS (private static)

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Comparing two state machine implementations in the same codebase reveals code reading skills and design judgment.

### Possible follow-up questions

- If ReviewStatus added a RETURNED_FOR_MORE_INFO status, how would you extend the if-else approach vs the map approach?
- Is the if-else approach in ReviewCase.transitionTo() easily unit-testable?
- What design pattern is the map-based approach associated with?

### Verification status

- Verified


---

<a id="Q146"></a>

## Q146 — ReviewCase has reviewedAt field but Application does not have a corresponding reviewedAt. Why?

### 中文筆記

reviewedAt 在 ReviewCase 的理由：reviewedAt 記錄「審核員何時完成審核決定」（approve 或 reject 的時間），這是審核流程的業務時間點，屬於 ReviewCase 的生命週期事件。Application 有 submittedAt（申請人提交的時間），代表申請流程的關鍵時間點。為什麼 Application 沒有 reviewedAt：Application 代表申請人的申請，從申請人視角只需知道 submittedAt；審核結果（approved/rejected）透過 Application.status 和 WorkflowHistory（哪個 operator 在哪個時間轉換了狀態）可以推斷，不需要獨立的 reviewedAt。若要查詢「審核員何時完成審核」，應查 ReviewCase.reviewedAt 或 WorkflowHistory（在 Application aggregate 的 history 中也記錄了 UNDER_REVIEW → APPROVED 的 operatedAt）。兩個 aggregate 各自記錄自己關心的業務時間點，符合邊界設計。

### Category

Credit Review Workflow

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- ReviewCase
- Application

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

Understanding why certain data lives in one aggregate vs another tests domain modeling knowledge.

### Possible follow-up questions

- How would you query "which applications were reviewed within 2 business days of submission"?
- What information would WorkflowHistory provide that ReviewCase.reviewedAt does not?
- Should ReviewCase also have a submittedAt field (the time the application was submitted)?

### Verification status

- Verified


---

<a id="Q147"></a>

## Q147 — ReviewCase has a remarks list (List<ReviewRemark>). What are the business rules for adding remarks, and how are they enforced?

### 中文筆記

業務規則與執行方式：ReviewCase.addRemark(String content, String operator) 呼叫 addRemarkInternal(content, operator)，新增一筆 ReviewRemark（reviewCaseId、content、operator、createdAt）到 remarks list。規則執行：addRemark() 沒有狀態限制（任何 status 都可以加 remark）。startReview() 內部呼叫 addRemarkInternal("Review started", operator)（自動記錄）。approve() 和 reject() 也各自呼叫 addRemarkInternal(remark, operator)（審核決定的理由）。addRemarkInternal() 是 private，外部只能透過 addRemark()、startReview()、approve()、reject() 加入 remark。constraint：review_remarks table 的 remark VARCHAR(1000)，若 reviewer 提交超過 1000 字元的 remark，@Column 或 DB constraint 會截斷或拋 exception。業務規則的完整性：目前沒有「只有 UNDER_REVIEW 狀態才能 addRemark」的限制，APPROVED 後仍可加備註，這是業務允許的（事後補充說明）。

### Category

Credit Review Workflow

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewRemark.java
- sp2-springboot/src/main/resources/db/migration/V5__create_review_cases.sql

### Verified classes and methods

- ReviewCase.addRemark()
- ReviewCase.addRemarkInternal() (private)

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

Business rule enforcement in aggregate methods is a DDD tactical question; tests whether the candidate can trace rules from domain to schema.

### Possible follow-up questions

- What prevents a remark from being added to a CANCELLED review case?
- How does ReviewRemark differ from WorkflowHistory in Application?
- What would you add to enforce a maximum remark length at the domain level rather than relying on the database?

### Verification status

- Verified


---

<a id="Q148"></a>

## Q148 — ReviewAppService.searchCases() returns PageResponse<ReviewCaseSummaryResponse>. What is the purpose of PageResponse versus returning Page<T> directly?

### 中文筆記

自訂 PageResponse vs Spring Data 的 Page<T> 的理由：Spring Data 的 Page<T> 是 org.springframework.data.domain.Page，若直接從 application service 返回 Page<ReviewCaseSummaryResponse>，controller 就依賴 Spring Data 類型，且 Page<T> 的 JSON 序列化（Jackson）不如預期——預設會序列化大量 metadata 欄位（pageable、last、first、empty、sort 等），對 API client 不友好。自訂 PageResponse<T> 只包含：content（list）、pageNumber、pageSize、totalElements、totalPages，清晰地定義了 API contract，不洩漏 Spring Data 的實作細節。好處：(1) API response schema 穩定，換 Spring Data 版本不影響 API；(2) Controller 不需要知道 Page<T>；(3) API 文件（OpenAPI）中 PageResponse 的 schema 更簡潔。

### Category

Credit Review Workflow

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/response/PageResponse.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java

### Verified classes and methods

- ReviewAppService.searchCases()

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

Custom pagination wrappers are an API design choice with framework coupling implications; tests design judgment.

### Possible follow-up questions

- What does Page<T>.getContent() return vs PageResponse.content?
- Would returning Page<ReviewCaseSummaryResponse> directly from the controller work? What would the JSON look like?
- How does ReviewApiControllerTest verify the pagination response structure?

### Verification status

- Verified


---

<a id="Q149"></a>

## Q149 — AuditAspectTest uses a custom @TestConfiguration to replace the async executor with a synchronous one. Why?

### 中文筆記

問題背景：AuditLogWriter.saveAsync() 是 @Async 方法，在默認的 async thread pool 中執行。測試中，主 test thread 呼叫 auditableTestService.success(applicationId) 後，audit log 的寫入在另一個 thread 非同步執行，主 test thread 不知道什麼時候 audit log 才寫入 DB。若直接 assertThat(auditLogRepository.findAll())...，可能在 async 執行前就查，結果為空，test fail。解決方案：@TestConfiguration 中定義一個 @Primary Executor bean，這個 bean 是 synchronous（execute 直接 run Runnable，不用另開 thread），這樣 @Async 注解調用時改用 synchronous executor，saveAsync() 在主 test thread 同步執行完成，再查詢 audit log 就能找到剛寫入的記錄。或者，test 有 awaitSuccessLog() 方法（基於 polling），等待 audit log 寫入後再查（不需要換 executor，但需要忙等）。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java

### Verified classes and methods

- AuditAspectTest
- AuditLogWriter.saveAsync()

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

Testing async code correctly is a common challenge; tests whether the candidate knows how to handle async in tests without introducing flakiness.

### Possible follow-up questions

- What is Awaitility library and how would it help test async behavior without replacing the executor?
- Would @DirtiesContext be needed if you replace the executor in @TestConfiguration?
- What is the difference between @TestConfiguration and @Configuration in a test context?

### Verification status

- Verified


---

<a id="Q150"></a>

## Q150 — ApplicationFlowIntegrationTest directly modifies the database using JdbcTemplate to set the OTP code. Why is this necessary, and what does it reveal about the test architecture?

### 中文筆記

直接修改 DB 的必要性：OtpAppService.sendOtp() 使用 SecureRandom 生成隨機 OTP，測試無法預知這個 random code。為了測試 OTP verify 流程，測試需要知道 OTP 值。解決方案：setOtpCodeInDatabase(MOBILE, TEST_OTP) 用 JdbcTemplate 直接更新 otp_records 表，把 OTP code 設定為已知值（如 "123456"），然後用這個值呼叫 verify endpoint。這個方法的意義：(1) 測試不 mock 任何 Spring bean，是真正的 end-to-end integration test（通過 MockMvc → Controller → Service → Repository → H2 DB）；(2) 直接 DB manipulation 是 integration test 的常見做法，當 mock 難以注入特定條件時（SecureRandom 的結果），直接修改 DB state 是實用的替代方案；(3) 揭示了測試需要「infrastructure bypass」來設置特定測試條件，若 OtpAppService.generateOtpCode() 可注入或替換，就不需要 DB 直接操作。

### Category

Integration Testing

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java

### Verified classes and methods

- ApplicationFlowIntegrationTest.setOtpCodeInDatabase() (private)
- OtpAppService

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Direct database manipulation in integration tests is a practical technique that reveals test design thinking and awareness of testability.

### Possible follow-up questions

- How would you refactor OtpAppService to make OTP generation injectable/mockable without breaking production behavior?
- What is @Sql annotation and how does it differ from using JdbcTemplate in tests?
- Could a @MockBean OtpCodeGenerator be a cleaner way to control OTP values in tests?

### Verification status

- Verified


---

<a id="Q151"></a>

## Q151 — ApplicationAppServiceTest uses @ExtendWith(MockitoExtension.class) without @SpringBootTest. What does this choice reveal about test design?

### 中文筆記

@ExtendWith(MockitoExtension.class) 的意義：只啟動 Mockito 的 JUnit 5 extension，不啟動 Spring context，所有依賴都是 @Mock 或 @InjectMocks，測試以毫秒級速度執行。@InjectMocks ApplicationAppService 配合 @Mock ApplicationRepository、@Mock CardProductRepository、@Mock DocumentStorageService、@Mock ApplicationEventPublisher：完全隔離 application service 的邏輯，任何依賴的行為都由 when/verify 控制，不受資料庫、Redis、Spring Security 影響。這個選擇體現的設計原則：application service 是純業務協調邏輯，可以在沒有 Spring 的情況下被測試；若 service 需要 Spring context 才能測試，說明它依賴了 Spring bean（如直接呼叫 @Autowired 的 JPA repository），設計上有問題。@BeforeEach 手動 new ApplicationAppService(...) 確保建構子注入（不依賴 field injection）的可測試性。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

Choosing the right test base class reveals testing philosophy; candidates who always use @SpringBootTest don't understand the cost.

### Possible follow-up questions

- What is the startup time difference between @ExtendWith(MockitoExtension.class) and @SpringBootTest?
- When would you add @SpringBootTest to ApplicationAppServiceTest?
- Why is constructor injection (@RequiredArgsConstructor) better for testability than field injection (@Autowired)?

### Verification status

- Documentation-only


---

<a id="Q152"></a>

## Q152 — OtpAppServiceTest uses ArgumentCaptor<OtpRecord> to verify what was saved. When is ArgumentCaptor preferable to verify(mock).method(expectedValue)?

### 中文筆記

ArgumentCaptor vs 直接比較的場景：當方法傳入的參數是一個新建立的複雜物件（如 OtpRecord.builder()...build()），這個物件在呼叫端動態建構，不能在測試中預先建立完全相同的實例（OtpRecord 沒有覆寫 equals()），verify(mock, times(1)).save(expectedOtpRecord) 就會用 Object.equals()（參考相等），必然失敗。ArgumentCaptor.forClass(OtpRecord.class) 在 verify 時捕獲傳入的 OtpRecord 實例，之後對 captor.getValue() 做個別欄位 assertion（如 assertThat(captor.getValue().getMobile()).isEqualTo("0912345678")），明確驗證每個業務欄位而非整個物件相等。何時用直接比較：當參數是字串、原始型別、有 value-based equals() 的物件（如 ApplicationId.of("APP-...")）時，直接 verify(mock).findById(ApplicationId.of("APP-...")) 更簡潔。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/application/otp/OtpAppServiceTest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

ArgumentCaptor vs direct matcher is a practical Mockito question that tests deep understanding of object equality in testing.

### Possible follow-up questions

- What would happen if you added @EqualsAndHashCode to OtpRecord? Would direct comparison then work?
- What is Mockito's argThat() matcher and when is it better than ArgumentCaptor?
- How would you verify that retryCount = 0 was set when a new OTP was created?

### Verification status

- Documentation-only


---

<a id="Q153"></a>

## Q153 — LocalDocumentStorageServiceTest uses @TempDir. What does this JUnit 5 annotation provide, and why is it better than creating a test directory manually?

### 中文筆記

@TempDir 的機制：JUnit 5 在每個測試方法（或 test class，取決於 annotation 位置）執行前，自動建立一個臨時目錄（OS temp 資料夾中），測試執行完成後自動刪除，無論測試 pass 或 fail。優點：(1) 不留垃圾：手動 Files.createTempDirectory() + Files.delete() 需要在 @AfterEach 清除，若測試拋 exception，@AfterEach 可能不執行，temp 目錄殘留；@TempDir 保證清除；(2) 隔離：每個測試拿到獨立的 temp 目錄，不同測試不相互干擾（並行測試也安全）；(3) 簡潔：一個 annotation 代替 @BeforeEach/@AfterEach 的 setup/teardown 邏輯。在 LocalDocumentStorageServiceTest 中，tempDir.toString() 注入為 basePath（透過 ReflectionTestUtils.setField），storageService 在測試隔離目錄中寫入文件，測試後自動清除，不影響真實 uploads 目錄。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageServiceTest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

@TempDir is a JUnit 5 best practice for filesystem tests; candidates who use @BeforeEach to create directories often forget cleanup.

### Possible follow-up questions

- What is ReflectionTestUtils.setField() and why is it used instead of a setter method?
- What scope does @TempDir at the field level have — per method or per class?
- Would @TempDir work if the test class was @Nested?

### Verification status

- Documentation-only


---

<a id="Q154"></a>

## Q154 — SystemParameterServiceCacheTest tests the cache-aside pattern independently from SystemParameterServiceTest. Why are there two test classes for the same service?

### 中文筆記

分離測試類別的理由：SystemParameterServiceTest 測試業務邏輯（getValue() 找到 / 找不到 parameter → 拋 exception；updateParameter() 更新 DB）。SystemParameterServiceCacheTest 專門測試 cache 行為（第一次呼叫 cache miss → 查 DB → put cache；第二次呼叫 cache hit → 不查 DB）。分離的好處：(1) 測試意圖更清晰，閱讀 SystemParameterServiceCacheTest 就知道是在驗證 caching contract；(2) 若 cache 邏輯有 bug，SystemParameterServiceCacheTest 的 failure 立即定位到 cache 問題，不需要在大型 test file 中找；(3) Mockito 的 when(cacheStore.get(CACHE_KEY)).thenReturn(Optional.empty()).thenReturn(Optional.of("5")) 需要設定 multi-invocation stub，放在 cache test 比較自然，不干擾主業務邏輯的 test；(4) 遵循「一個測試只驗證一個 concern」的原則。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/application/parameter/SystemParameterServiceTest.java
- sp2-springboot/src/test/java/com/tlbank/lending/application/parameter/SystemParameterServiceCacheTest.java

### Verified classes and methods

- SystemParameterServiceCacheTest

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

Separation of concerns in test classes is a test design quality question; tests whether the candidate values test clarity.

### Possible follow-up questions

- How does when(mock.method()).thenReturn(value1).thenReturn(value2) work for consecutive calls?
- What is the lenient() qualifier in Mockito and why is it used in setUp()?
- How many times does systemParameterRepository.findByGroupAndKey() get called in the cache-hit scenario?

### Verification status

- Verified


---

<a id="Q155"></a>

## Q155 — ExcelReportGeneratorTest creates an XSSFWorkbook from the generated bytes to verify the sheet structure. What does this test strategy reveal about testing output formats?

### 中文筆記

測試策略的啟示：ExcelReportGeneratorTest 不只驗證「方法不拋 exception」，也驗證 output 的結構（workbook.getNumberOfSheets() == 2、sheet name 是 "Summary" 和 "By Product"）和內容（所有 ApplicationStatus 值都出現在 Summary sheet）。使用 Apache POI 本身解析 output：new XSSFWorkbook(new ByteArrayInputStream(content)) 讀回剛生成的 bytes，直接操作 Sheet、Row、Cell，是「round-trip testing」——用同一個工具驗證自己的 output。這揭示了測試輸出格式（Excel、PDF）的關鍵策略：不要只 assert content.length > 0（太弱，任何垃圾 bytes 都會通過），應解析並驗證業務相關的 content（correct sheet names、expected data rows、status names present）。PdfReportGenerator 可以用 iText 的 PdfReader 做類似驗證，但 PDF 內容提取較複雜，實際測試中可能只驗證 PDF metadata。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/report/ExcelReportGeneratorTest.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java

### Verified classes and methods

- ExcelReportGeneratorTest
- ExcelReportGenerator.generateDailyStatistics()

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

Testing binary output formats requires a specific strategy; tests whether the candidate knows how to assert on structured outputs.

### Possible follow-up questions

- How would you test PdfReportGenerator without parsing the PDF bytes in detail?
- What is a snapshot test and could it work for Excel report verification?
- What happens in the test if a new ApplicationStatus is added to the enum?

### Verification status

- Verified


---

<a id="Q156"></a>

## Q156 — ReviewApiControllerTest uses @Sql annotation to insert test data instead of JdbcTemplate. What are the trade-offs?

### 中文筆記

@Sql vs JdbcTemplate 的取捨：@Sql(statements = {"INSERT INTO..."}) 是 Spring Test 的 annotation，在 test method 執行前（或後）自動執行 SQL 語句，完成後可自動 rollback（若 @Transactional 在 test 上）。優點：SQL 語句宣告式地與 test method 關聯，不需要在 @BeforeEach 或 setUp() 中分散寫 JdbcTemplate 呼叫；@Sql(executionPhase = AFTER_TEST_METHOD) 可清除測試資料；可以用外部 SQL file（@Sql("/test-data.sql")）提高複用性。JdbcTemplate 的優點：可以在 @BeforeEach 中動態構建 SQL（帶 Java variable）；可以在 test method 中讀取插入後的 ID（jdbcTemplate.queryForObject(..., Long.class)）；更靈活的錯誤處理。ApplicationFlowIntegrationTest 需要 OTP code 的動態值，所以用 JdbcTemplate；ReviewApiControllerTest 需要固定的靜態測試資料，所以用 @Sql。兩種方法各有場景，不互斥。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/presentation/api/v1/ReviewApiControllerTest.java
- sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

@Sql vs JdbcTemplate is a practical test data setup question that reveals awareness of Spring Test features.

### Possible follow-up questions

- Does @Sql roll back the inserted data after the test? What is the default behavior?
- Can @Sql be placed at the class level to apply to all test methods?
- What is @SqlConfig and when would you need it?

### Verification status

- Documentation-only


---

<a id="Q157"></a>

## Q157 — JaCoCo is configured in pom.xml with several <excludes>. What classes are excluded and why?

### 中文筆記

排除的類別清單（pom.xml）：**/config/**（Spring 配置類，如 SecurityConfig、AsyncConfig，主要是 @Bean 方法的 Spring plumbing，業務邏輯少，測試 coverage 意義低）；**/dto/**（request/response DTO records，純資料容器，無業務邏輯，100% coverage 無意義）；**/*Application.class（Spring Boot 主類，main() 方法由 Spring 執行，不適合 unit test coverage）；**/entity/**（JPA entity 類，只有 getter/setter/Lombok，無業務邏輯）；**/*Embeddable.class（如 ApplicantEmbeddable、AddressEmbeddable，純 JPA mapping，無業務邏輯）。排除的理由：這些類別若不排除，會拉低 coverage 百分比（因為 Spring Boot 啟動時執行 config 類，integration test 才會覆蓋，unit test 不覆蓋），使 coverage report 難以聚焦在真正重要的業務邏輯層。排除後，coverage report 更能反映 domain、application service、infrastructure 的測試完整度。

### Category

JaCoCo

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/pom.xml

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — build/test tooling question

### Why this may be asked

Understanding JaCoCo exclusion strategy shows familiarity with test metrics; candidates who blindly chase 100% coverage often include meaningless classes.

### Possible follow-up questions

- What JaCoCo goal generates the HTML report, and where is it located after mvn verify?
- Should you set a minimum coverage threshold in JaCoCo? What percentage is reasonable for this project?
- How does jacoco:prepare-agent work and why is it executed in the test-compile phase?

### Verification status

- Documentation-only


---

<a id="Q158"></a>

## Q158 — JaCoCo reports are generated with mvn verify but the CI pipeline runs mvn clean verify too. What is the difference between verify and test in Maven lifecycle?

### 中文筆記

Maven lifecycle 的差異：mvn test：執行到 test phase（compile → test-compile → test），只跑 unit tests，不生成 JAR，不執行 JaCoCo report goal。mvn verify：執行到 verify phase（...test → package → integration-test → verify），執行所有 test（unit + integration），生成 JAR（package phase），觸發 JaCoCo 的 report goal（綁定在 verify phase）。CI pipeline 用 mvn clean verify：clean 刪除 target/ 目錄（確保 fresh build，避免 stale artifacts），verify 完整執行所有 lifecycle phase 包含測試和 JaCoCo report。本地快速迭代可用 mvn test -pl sp2-springboot 只跑 unit tests；提交前用 mvn verify 確保 coverage report 和 JAR 都 ok。mvn package -DskipTests（Dockerfile 用的）：跳過測試，只打 JAR，比 mvn verify 快。

### Category

JaCoCo

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/pom.xml
- .github/workflows/ci.yml
- sp2-springboot/docker/app/Dockerfile

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — build/test tooling question

### Why this may be asked

Maven lifecycle phases are foundational knowledge; candidates who don't know when coverage is generated often run wrong commands.

### Possible follow-up questions

- Why does the Dockerfile use -DskipTests when building the production image?
- What is the difference between mvn package and mvn install?
- How would you add a JaCoCo minimum coverage gate that fails the CI build?

### Verification status

- Documentation-only


---

<a id="Q159"></a>

## Q159 — JaCoCo coverage data is written to target/jacoco.exec. What is this file, and how does it relate to the HTML report?

### 中文筆記

jacoco.exec 的性質：binary 格式的 coverage data 檔案，由 JaCoCo agent 在測試執行期間生成，記錄哪些 bytecode instruction 被執行（instrumented bytecode），哪些 branch 被覆蓋。生成過程：jacoco:prepare-agent goal 在 test-compile phase 設定 JVM agent（-javaagent:jacoco-agent.jar），測試執行時 agent 記錄 coverage，寫入 target/jacoco.exec。HTML report 的生成：jacoco:report goal 讀取 target/jacoco.exec + compiled classes（target/classes/）+ source files（src/main/java/），生成 target/site/jacoco/index.html（可讀的 HTML report）。多個 exec 檔案可以 merge（jacoco:merge），適合 module 分離的 multi-module project。CI pipeline 在 mvn clean verify 中同時執行 test 和 report，開發者可以在本地 open target/site/jacoco/index.html 查看 coverage。

### Category

JaCoCo

### Difficulty

Basic

### Verified source files

- sp2-springboot/pom.xml

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — build/test tooling question

### Why this may be asked

Understanding JaCoCo's two-phase process (agent collection then report generation) is a common gap in developer knowledge.

### Possible follow-up questions

- Why must JaCoCo prepare-agent run before tests, not after?
- What happens if you run mvn jacoco:report without running tests first?
- Is the jacoco.exec file committed to git? Should it be?

### Verification status

- Documentation-only


---

<a id="Q160"></a>

## Q160 — The CI pipeline runs mvn verify which includes JaCoCo, but there is no coverage threshold enforcement. What would you add to gate the pipeline on coverage?

### 中文筆記

加入 coverage threshold 的方法：在 pom.xml 的 JaCoCo plugin 中新增一個 check execution：<goal>check</goal> 綁定到 verify phase，配置 <rules><rule><limits><limit><counter>LINE</counter><value>COVEREDRATIO</value><minimum>0.70</minimum></limit></limits></rule></rules>，表示 line coverage 低於 70% 時 build fail。目前沒有 threshold 的影響：CI 通過即使某個 domain class 的 coverage 是 0%，開發者可能不知道。加入 threshold 的考量：(1) 剛開始設定合適的 minimum（如 70%）需要評估當前 coverage 作為 baseline；(2) 過高的 threshold（如 95%）可能讓 CI 難以通過，迫使寫無意義的測試；(3) counter 可以選 LINE、BRANCH、INSTRUCTION、METHOD——LINE 最常見，BRANCH 更嚴格（驗證 if/else 兩條路徑都測試）。ADR 0004 提到 Trivy 目前也只是 report-only（exit-code: 0），非 blocking，顯示目前 pipeline 傾向「學習/可見性優先」而非「強制門檻」。

### Category

JaCoCo

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/pom.xml
- .github/workflows/ci.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — build/test tooling question

### Why this may be asked

Coverage gating is a CI best practice decision with real trade-offs; tests whether the candidate can design a quality gate.

### Possible follow-up questions

- What is the difference between line coverage, branch coverage, and instruction coverage?
- At what coverage threshold would you set a warning vs a failure?
- How would you exclude generated code (like Lombok) from JaCoCo's measurement?

### Verification status

- Documentation-only


---

<a id="Q161"></a>

## Q161 — MockEmailSender is activated with @ConditionalOnProperty(name = "tlbank.notification.mode", havingValue = "mock", matchIfMissing = true). What does matchIfMissing = true mean?

### 中文筆記

matchIfMissing = true：若 application.yml 或任何 active profile 的配置中沒有 tlbank.notification.mode 屬性，則 @ConditionalOnProperty 的條件視為 matched（即條件成立），bean 被創建。效果：沒有配置 tlbank.notification.mode 的環境（dev test、新環境）自動使用 MockEmailSender；若要切換為真實 sender，必須明確配置 tlbank.notification.mode=real（或對應的 havingValue），不需要配置 mock 的環境預設安全（不送真實 email）。安全設計：matchIfMissing = true 讓「mock 是 default」，真實 sender 需要明確啟用，這是 fail-safe 設計——遺忘設定寧可用 mock 也不要意外發送真實 SMS/email。若反過來（matchIfMissing = false，且 property 未設），mock bean 不啟動，也沒有真實 sender（NoUniqueBeanDefinitionException 或注入失敗）。

### Category

Notifications (Mock)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockEmailSender.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java

### Verified classes and methods

- NotificationEventHandler
- MockSmsSender
- MockEmailSender

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

matchIfMissing is a subtle but important Spring conditional annotation behavior; tests awareness of safe defaults in bean configuration.

### Possible follow-up questions

- How would you add a real email sender and ensure it only activates in production?
- What happens if both MockEmailSender and a RealEmailSender are on the classpath with no @Primary?
- Is there a way to test with @ConditionalOnProperty without changing application.yml?

### Verification status

- Verified


---

<a id="Q162"></a>

## Q162 — NotificationServiceImpl has sendSmsSafely() and sendEmailSafely() private methods. What do these methods do, and why wrap the sender call?

### 中文筆記

sendSmsSafely() / sendEmailSafely() 的作用（推測基於 code 結構）：這些 private helper 方法包裝 smsSender.send() / emailSender.send() 呼叫，加入 try-catch 防止 notification 的 exception 傳播到 NotificationServiceImpl 的 caller（NotificationEventHandler），並記錄 log.warn() 以便 operator 知道通知失敗。雙層 exception 保護：第一層在 NotificationServiceImpl.sendSmsSafely()（service 內部），第二層在 NotificationEventHandler.onApplicationApproved()（event handler 外層 try-catch）。為什麼要在 service 層 wrap：NotificationServiceImpl 是可被直接呼叫的 service（如 OtpAppService.sendOtp() 直接呼叫 notificationService.sendOtpNotification()），若不在 service 層 catch，OTP 通知失敗就會影響 sendOtp() 的 transaction。Event handler 的 try-catch 只保護 event-triggered 呼叫，不保護直接呼叫路徑。

### Category

Notifications (Mock)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/notification/service/NotificationServiceImpl.java
- sp2-springboot/src/test/java/com/tlbank/lending/application/notification/NotificationServiceImplTest.java

### Verified classes and methods

- NotificationServiceImpl.sendSmsSafely() (private)
- NotificationServiceImpl.sendEmailSafely() (private)

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

Exception safety in notification services is a core design requirement for banking systems; tests layered exception handling understanding.

### Possible follow-up questions

- What does NotificationServiceImplTest verify when the sender throws an exception?
- Is sendSmsSafely() the right place to log the failure, or should it be in the event handler?
- How would you add retry logic to SMS sending without blocking the request thread?

### Verification status

- Verified


---

<a id="Q163"></a>

## Q163 — NotificationTemplate uses @UtilityClass from Lombok. What does @UtilityClass provide, and why is it preferred over a plain class with static methods?

### 中文筆記

@UtilityClass 的作用：Lombok 在編譯時將類別的 constructor 改為 private（防止實例化）、在 class 宣告加上 final（防止繼承）、並將所有方法變為 static（若原本不是 static）。效果等同於手動寫：public final class NotificationTemplate { private NotificationTemplate() { throw new UnsupportedOperationException(); } public static String formatOtpSms(...) {...} }。比 plain class with static methods 好的原因：(1) 若開發者忘記將 utility class 設為 final 或 private constructor，工具類別可能被繼承或實例化；(2) Lombok 生成的 throw new UnsupportedOperationException() 在 private constructor 中確保反射也無法實例化；(3) 宣告意圖更明確（一看 @UtilityClass 就知道這是工具類）。@UtilityClass 不適合需要繼承或 Spring 管理的類別，只適合純靜態方法的工具集合。

### Category

Notifications (Mock)

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/NotificationTemplate.java

### Verified classes and methods

- NotificationEventHandler
- MockSmsSender
- MockEmailSender

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

@UtilityClass is a Lombok feature that demonstrates careful design intention; tests practical Java/Lombok knowledge.

### Possible follow-up questions

- What error would you get if you tried to call new NotificationTemplate()?
- How does @UtilityClass differ from @Value in Lombok?
- Is NotificationTemplate thread-safe? Why?

### Verification status

- Verified


---

<a id="Q164"></a>

## Q164 — OTP notification sends both SMS and email for the same OTP. What is the business reason, and what happens if only one succeeds?

### 中文筆記

雙通道通知的業務理由：確保用戶以至少一種方式收到 OTP（redundancy）。若 SMS 網路問題，用戶可從 email 取得 OTP；若 email 被誤判為垃圾郵件，SMS 是 backup。sendOtpNotification() 的邏輯：sendSmsSafely(mobile, formatOtpSms(...)) 和 sendEmailSafely("notifications@tlbank.local", OTP_EMAIL_SUBJECT, formatOtpEmailBody(...)) 各自 try-catch，兩個呼叫互相獨立，SMS 失敗不影響 email 發送，反之亦然。問題：目前 email 的 recipient 是 "notifications@tlbank.local"（一個假地址），不是 applicant 的 email——這意味著 email 通知實際上沒有送到用戶（只是 mock log）。真實生產應改為 applicant.getEmail() 或 SendOtpCommand.email()，但目前 SendOtpCommand 可能沒有傳 email 欄位。這是功能上的 gap，反映 portfolio 範圍的限制。

### Category

Notifications (Mock)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/notification/service/NotificationServiceImpl.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java

### Verified classes and methods

- NotificationServiceImpl.sendOtpNotification()

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Noticing that the OTP email goes to a hardcoded address is a code review observation that reveals attention to detail.

### Possible follow-up questions

- How would you change SendOtpCommand to also carry the applicant's email address?
- What is the risk of sending OTPs via email rather than SMS from a security perspective?
- How would you test that both SMS and email are attempted even when one fails?

### Verification status

- Verified


---

<a id="Q165"></a>

## Q165 — MockSmsSender and MockEmailSender only log the message. What would a real integration look like, and what configuration would be needed?

### 中文筆記

真實整合的架構：SMS：接入 Twilio、AWS SNS、or Taiwan local providers（中華電信簡訊 API、遠傳 API）。需要：API key/secret（透過 secrets manager 注入），SmsSender interface 的 real implementation（如 TwilioSmsSender），HTTP client（RestTemplate 或 WebClient）發送 POST request 到 Twilio API，@ConditionalOnProperty(name = "tlbank.notification.mode", havingValue = "real") 啟動真實 sender。Email：JavaMail（spring-boot-starter-mail）或 SendGrid API。需要：SMTP server config（spring.mail.host/username/password）或 SendGrid API key，EmailSender real implementation（如 JavaMailEmailSender），配置 spring.mail.*。切換機制：目前 ConditionalOnProperty(matchIfMissing = true) 讓 mock 是 default，加入 real sender 只需定義新 bean + 配置 tlbank.notification.mode=real，NotificationServiceImpl 不需要任何改動（依賴 SmsSender/EmailSender interface）。

### Category

Notifications (Mock)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/SmsSender.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/EmailSender.java

### Verified classes and methods

- NotificationEventHandler
- MockSmsSender
- MockEmailSender

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

The path from mock to real integration tests understanding of ports and adapter extensibility.

### Possible follow-up questions

- What Spring configuration would you add for SMTP email sending?
- How would you handle SMS delivery receipts (callback webhooks from Twilio) in this architecture?
- What is the cost implication of using a real SMS provider in a portfolio project?

### Verification status

- Verified


---

<a id="Q166"></a>

## Q166 — NotificationTemplate hardcodes English message strings. What would internationalization (i18n) require?

### 中文筆記

目前的問題：OTP_SMS = "【TLBank】Your OTP: {0}. Valid {1} min. Keep it secret."、SUBMIT_EMAIL_BODY = "Dear customer,\n\n..." 都是 hardcoded 英文。國際化需求：(1) Spring MessageSource：將模板改為 messages.properties（英文）、messages_zh_TW.properties（繁體中文），透過 messageSource.getMessage(code, args, locale) 取得對應語言的訊息；(2) 用戶的 Locale：SendOtpCommand 需要帶入 applicant 的偏好語言，或從 ApplicationContext（LocaleContextHolder）取得 request 的 locale；(3) MessageFormat：NotificationTemplate 用 MessageFormat.format(template, args) 格式化，需改為 messageSource.getMessage(key, args, locale)；(4) 【TLBank】 SMS prefix 已是中文 bracket，但 body 是英文，本身就不一致。台灣的 fintech 申請系統應優先支援繁體中文介面。

### Category

Notifications (Mock)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/NotificationTemplate.java

### Verified classes and methods

- NotificationEventHandler
- MockSmsSender
- MockEmailSender

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

i18n is a production concern that's often omitted in portfolios; tests forward-thinking design awareness.

### Possible follow-up questions

- What Spring class would you use to externalize message templates into locale-specific properties files?
- How would MessageFormat.format("{0} minutes", 5) behave with different locales?
- Is there a risk of character encoding issues when sending Chinese characters via SMS?

### Verification status

- Verified


---

<a id="Q167"></a>

## Q167 — SchedulingConfig and SchedulerConfig both have @EnableScheduling. The project explicitly documents this as a known bug. Why doesn't having two @EnableScheduling annotations break anything?

### 中文筆記

為何不 break：@EnableScheduling 是 @Configuration + @Import(SchedulingConfiguration.class) 的組合，SchedulingConfiguration 是 Spring 的 internal class，向 BeanDefinitionRegistry 注冊 ScheduledAnnotationBeanPostProcessor。若重複 @Import 同一個 class，Spring 的 @Import mechanism 對 idempotent registrations 做 de-duplication：同一個 BeanDefinitionRegistry 中，已經存在的 bean definition 不會被重複注冊（第二個 @EnableScheduling 的 import 發現 ScheduledAnnotationBeanPostProcessor 已存在，直接跳過）。結果：功能上完全正常，只有 @EnableScheduling 重複的 code smell（混淆讀者）。已知 bug 的修復：刪除 SchedulerConfig.java 或 SchedulingConfig.java 的其中一個，留下一個即可。這個 bug 也是很好的面試誠信點：文件明確承認 open bug #3，不掩蓋。

### Category

Schedulers

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulerConfig.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- OtpCleanupScheduler
- CacheRefreshScheduler
- DailyStatisticsScheduler
- SchedulingConfig
- SchedulerConfig

### Execution flow

1. Spring @Scheduled triggers scheduler component on cron from tlbank.scheduler.*
2. Scheduler calls repository/service (OTP cleanup, cache refresh, or daily stats)
3. Optional admin manual run via SchedulerApiController

### Why this may be asked

A documented known bug is an opportunity to test both technical knowledge and honesty; tests understanding of Spring import idempotency.

### Possible follow-up questions

- How would you decide which of the two config classes to delete?
- Does this create any performance overhead at startup?
- What ArchUnit rule could detect duplicate @EnableScheduling annotations?

### Verification status

- Verified


---

<a id="Q168"></a>

## Q168 — OtpCleanupScheduler uses @Scheduled(cron = "${tlbank.scheduler.otp-cleanup.cron}"). What is the advantage of externalized cron expressions over hardcoded ones?

### 中文筆記

外部化 cron 的優點：(1) 環境差異：dev 環境可以設定 cron: "0 */1 * * * *"（每分鐘）加速測試 OTP 過期清除；production 環境設定 cron: "0 0 */2 * * *"（每兩小時），不需要改 code，只改 config；(2) 緊急調整：若 scheduler 在某個時段佔用資源影響效能，ops 團隊可以修改 config 並重啟，不需要開發者 release；(3) 測試靈活性：application-dev.yml 和 application.yml 可以設不同的 cron，CI 用的 profile 不啟動 scheduler（設 cron: "-" 停用）；(4) 避免魔法數字：code 中看到 @Scheduled(cron = "0 */1 * * * *") 需要讀者懂 cron 語法才知道頻率；外部化後 YAML 中有更易讀的說明 comment。"${property:-default}" Spring EL 也支援 inline default（若 property 不存在就用 default），但這個 project 要求 config 必須提供。

### Category

Schedulers

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java
- sp2-springboot/src/main/resources/application-dev.yml

### Verified classes and methods

- OtpCleanupScheduler.cleanupExpiredOtps()

### Execution flow

1. Spring @Scheduled triggers scheduler component on cron from tlbank.scheduler.*
2. Scheduler calls repository/service (OTP cleanup, cache refresh, or daily stats)
3. Optional admin manual run via SchedulerApiController

### Why this may be asked

Externalizing cron expressions is a configuration best practice; tests operational awareness.

### Possible follow-up questions

- What does @Scheduled(cron = "-") do in Spring?
- How would you disable a scheduler entirely in the test environment without deleting the bean?
- What is a cron expression for "run at 3 AM every day"?

### Verification status

- Verified


---

<a id="Q169"></a>

## Q169 — DailyStatisticsScheduler has a public generateDailyStatistics(LocalDate date) overload in addition to the @Scheduled method. Why?

### 中文筆記

公開 overload 的設計理由：@Scheduled 方法通常是 void 且無參數（Spring Scheduling 要求），但測試和手動觸發需要能傳入特定日期（如「重新生成昨天的統計」）。generateDailyStatistics() 的無參版（@Scheduled 觸發）呼叫 generateDailyStatistics(LocalDate.now(clock).minusDays(1))（昨天的統計）。公開的 generateDailyStatistics(LocalDate date) 版本：(1) 允許 SchedulerApiController（管理員 API）手動觸發特定日期的統計重新生成；(2) 允許 DailyStatisticsSchedulerTest 直接傳入固定日期測試統計生成邏輯，不需要等待 cron trigger 或 mock 整個 scheduler；(3) 讓 @Scheduled 方法保持乾淨（只有 log + 呼叫 overload），實際邏輯在 overload 中，可被獨立測試。這是「thin scheduled wrapper + testable core method」的 scheduler 設計 pattern。

### Category

Schedulers

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/DailyStatisticsScheduler.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/SchedulerApiController.java

### Verified classes and methods

- DailyStatisticsScheduler.generateDailyStatistics()

### Execution flow

1. Spring @Scheduled triggers scheduler component on cron from tlbank.scheduler.*
2. Scheduler calls repository/service (OTP cleanup, cache refresh, or daily stats)
3. Optional admin manual run via SchedulerApiController

### Why this may be asked

The thin-wrapper scheduler pattern is a clean design for testable scheduled jobs; tests whether the candidate recognizes the intent.

### Possible follow-up questions

- How would SchedulerApiController call the overloaded method to regenerate stats for a specific date?
- Would making the @Scheduled method private be appropriate? What would change?
- How would you unit test generateDailyStatistics(LocalDate) without starting a Spring context?

### Verification status

- Verified


---

<a id="Q170"></a>

## Q170 — All three schedulers (OtpCleanupScheduler, CacheRefreshScheduler, DailyStatisticsScheduler) catch all exceptions and log warnings. Is this uniform pattern correct, or should some failures be treated differently?

### 中文筆記

統一 try-catch-log 的評估：OtpCleanupScheduler：PENDING OTP 沒有被標記 EXPIRED 的影響是 stale OTP 可被繼續使用（安全問題），失敗應告警而非靜默。目前 log.warn 可能不夠（應是 log.error，並觸發 alert）。CacheRefreshScheduler：cache 更新失敗讓系統用舊 cache，短期影響是 admin 改的 parameter 沒有立即生效，業務影響較小，log.warn 可接受。DailyStatisticsScheduler：統計生成失敗只是 admin report 看不到最新數據，不影響核心業務，log.warn 合理。改進建議：OtpCleanupScheduler 的失敗應 log.error（error 級別 log 通常接入告警系統）；三個 scheduler 的失敗都應推送 metrics（如 Micrometer counter 記錄 scheduler failure count），讓 Prometheus/Grafana alert 而非靠人工看 log。「所有 exception 都 warn」是 portfolio 的簡化，生產環境應根據業務影響差異化處理。

### Category

Schedulers

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/CacheRefreshScheduler.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/DailyStatisticsScheduler.java

### Verified classes and methods

- OtpCleanupScheduler
- CacheRefreshScheduler
- DailyStatisticsScheduler
- SchedulingConfig
- SchedulerConfig

### Execution flow

1. Spring @Scheduled triggers scheduler component on cron from tlbank.scheduler.*
2. Scheduler calls repository/service (OTP cleanup, cache refresh, or daily stats)
3. Optional admin manual run via SchedulerApiController

### Why this may be asked

Evaluating whether uniform error handling is appropriate reveals production maturity; tests critical thinking about error severity.

### Possible follow-up questions

- What Micrometer metric would you add to count OTP cleanup failures?
- How would you set up a PagerDuty alert when OtpCleanupScheduler fails three consecutive times?
- Should scheduler failures ever automatically retry? How would you implement retries?

### Verification status

- Verified


---

<a id="Q171"></a>

## Q171 — ReportAppService.generateDailyStatisticsReport() generates the report on the request thread. What is the scalability problem with this design?

### 中文筆記

在 request thread 生成 report 的問題：generateDailyStatisticsReport() 是一個 HTTP request handler（ReportApiController 呼叫它）。生成 Excel/PDF report 需要：DB query（reportDataService.buildDailyStatistics(date) 聚合查詢）+ Apache POI 或 iText 處理（CPU-intensive byte array 生成）。問題：(1) 長時間占用 request thread（Tomcat thread pool），若有 10 個並發 report 請求，10 個 threads 被 block，降低整個應用的並發處理能力；(2) HTTP request timeout：若報表 DB 查詢慢（數萬筆申請的聚合），client 可能因 timeout 而失敗；(3) 記憶體壓力：Apache POI 的 XSSFWorkbook 將整個 Excel 內容放在記憶體（SXSSF streaming 版本可以改善）。解決方向：(1) 非同步 report generation：admin 發出 report request → 後台 async 任務生成 → 完成後通知或 poll endpoint；(2) 預先排程：DailyStatisticsScheduler 每天生成後存入 DB 或 file storage，admin 直接下載快取的 report；(3) SXSSF 降低記憶體。

### Category

Reports (Excel and PDF)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- ReportAppService.generateDailyStatisticsReport()

### Execution flow

1. Admin calls report API with date and format
2. ReportDataService aggregates counts
3. ExcelReportGenerator or PdfReportGenerator returns bytes

### Why this may be asked

Report generation on the request thread is a well-known scalability anti-pattern; tests production systems thinking.

### Possible follow-up questions

- What is SXSSFWorkbook and how does it reduce memory usage for large Excel reports?
- How would you implement async report generation with polling in this Spring Boot app?
- What would be the timeout for the HTTP response if report generation takes 45 seconds?

### Verification status

- Verified


---

<a id="Q172"></a>

## Q172 — ExcelReportGenerator and PdfReportGenerator both duplicate percentage-formatting logic. The project documents this as known technical debt (#6). What is the risk of leaving it?

### 中文筆記

重複 percentage-formatting 邏輯的風險：目前兩個 generator 各自有計算百分比的代碼（(statusCount / totalApplications) * 100）。風險：(1) Bug duplication：若 percentage 計算有 bug（如除以零 edge case 沒處理），需要在兩個地方修；(2) Inconsistency drift：若 Excel 的格式是 "25.5%" 但 PDF 是 "25.50%"（不同小數位），兩個 generator 各自演化，同一數據在兩份報告中顯示不同，造成用戶困惑；(3) Future third format：若加入 CSV report generator，需要第三次複製。修復方式：提取 ReportFormatter.formatPercentage(long count, long total) utility method（或在 DailyStatisticsData record 中加入 computed property），兩個 generator 都呼叫它，確保格式一致。ADR 建議等到「需要第三種格式時再重構」，避免 premature extraction。

### Category

Reports (Excel and PDF)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/PdfReportGenerator.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Admin calls report API with date and format
2. ReportDataService aggregates counts
3. ExcelReportGenerator or PdfReportGenerator returns bytes

### Why this may be asked

Code duplication across output generators is a concrete technical debt discussion; tests whether the candidate can assess risk and propose targeted fixes.

### Possible follow-up questions

- How would you write a test to detect if the two generators produce different percentages for the same data?
- What refactoring technique would you apply — extract method, extract class, or move to DailyStatisticsData?
- When is DRY principle more important than waiting for the third occurrence to justify extraction?

### Verification status

- Documentation-only


---

<a id="Q173"></a>

## Q173 — ReportAppService.getFileName() generates file names like daily-statistics-20240101.xlsx. What concerns arise if two users request the same report simultaneously?

### 中文筆記

同時請求的問題：getFileName(LocalDate.of(2024, 1, 1), EXCEL) → "daily-statistics-20240101.xlsx"，兩個請求生成同名文件。目前的實作：report 以 byte[] 回傳（return excelReportGenerator.generateDailyStatistics(data) → byte[]），不寫入磁碟，直接在 HTTP response 中 stream out（Content-Disposition: attachment; filename=daily-statistics-20240101.xlsx）。若是 in-memory 回傳：兩個並發請求各自獨立計算、獨立的 ByteArrayOutputStream，不共享狀態，同名文件名不造成 conflict（filename 只出現在 HTTP header）。若未來改為先寫檔案再提供下載：兩個 requests 可能同時寫 /app/reports/daily-statistics-20240101.xlsx，後寫者覆蓋前者，或造成 partial read。目前的 in-memory design 避免了此問題，是這個設計的隱性好處。

### Category

Reports (Excel and PDF)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java

### Verified classes and methods

- ReportAppService.getFileName()
- ReportAppService.generateDailyStatisticsReport()

### Execution flow

1. Admin calls report API with date and format
2. ReportDataService aggregates counts
3. ExcelReportGenerator or PdfReportGenerator returns bytes

### Why this may be asked

Concurrent report requests and file naming conflicts reveal production thinking about shared resources.

### Possible follow-up questions

- How would you add a timestamp to the filename to ensure uniqueness even with a persistent storage backend?
- What HTTP response headers would the controller set to trigger a browser download with the correct filename?
- How would you cache the generated report to avoid regenerating it within the same day?

### Verification status

- Verified


---

<a id="Q174"></a>

## Q174 — PdfReportGenerator uses iText 7. What licensing concern should be raised in a production deployment?

### 中文筆記

iText 7 的授權問題：iText 7 在 AGPL（Affero GNU Public License）下發布。AGPL 的要求：若你在 SaaS 或 web application 中使用 iText 7（用戶通過網路使用系統），你必須將整個應用程式原始碼（包含所有 dependencies）以 AGPL 授權開源。對商業應用的影響：TLBank 是 portfolio 專案，無商業部署，AGPL 不影響；但若真正的銀行系統採用 iText 7 而不想開源程式碼，必須購買 iText 的商業授權（iText 7 Proprietary License）。替代方案：Apache PDFBox（Apache 2.0 license，完全開源友好）、OpenPDF（LGPL/MPL，允許 linking 而不需要開源 application code）。pom.xml 中使用 iText 7 是 portfolio 常見選擇，面試時應主動提出這個 caveat 以顯示商業意識。

### Category

Reports (Excel and PDF)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/pom.xml
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/PdfReportGenerator.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Admin calls report API with date and format
2. ReportDataService aggregates counts
3. ExcelReportGenerator or PdfReportGenerator returns bytes

### Why this may be asked

License awareness in open-source dependencies is a production engineering concern; distinguishes engineers who think about production from those who only think about functionality.

### Possible follow-up questions

- What is the difference between GPL and AGPL? Why is AGPL more restrictive for SaaS?
- If you needed to switch from iText 7 to Apache PDFBox, what would change in PdfReportGenerator?
- How would you perform a license audit of all dependencies in this Maven project?

### Verification status

- Documentation-only


---

<a id="Q175"></a>

## Q175 — DailyStatisticsData is a record. What fields does it carry, and why is it a record rather than a @Builder class?

### 中文筆記

DailyStatisticsData 欄位（基於使用點推測）：LocalDate reportDate、long totalApplications、Map<ApplicationStatus, Long> statusCounts（各狀態的申請數）、Map<String, Long> productCounts（各產品的申請數）——所有欄位在建立後不需要修改。record 的適用理由：Report data 是「計算結果的載體」（DTO/value object），一旦從 DB 聚合計算完成就不再改變，immutable data 是 record 的天然場景；record 自動提供 equals()、hashCode()、toString()（對 report data 的測試 assertion 有用）；record 的 canonical constructor 確保所有欄位在建立時提供，不允許部分初始化（不像 builder 可以漏 set 某個欄位）。@Builder 更適合有多個 optional fields 的 command/request 物件，對 immutable data transfer 物件 record 更語意化。

### Category

Reports (Excel and PDF)

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/DailyStatisticsData.java
- sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/report/ExcelReportGeneratorTest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Admin calls report API with date and format
2. ReportDataService aggregates counts
3. ExcelReportGenerator or PdfReportGenerator returns bytes

### Why this may be asked

Choosing between record and @Builder for data transfer objects reveals Java design sense.

### Possible follow-up questions

- What is the difference between a Java record and a Lombok @Value class?
- How does DailyStatisticsData get populated from the native SQL query results (List<Object[]>)?
- Can you add a computed method to a record — for example, to calculate a total percentage?

### Verification status

- Documentation-only


---

<a id="Q176"></a>

## Q176 — ReportFormat is an enum used to select Excel vs PDF generation. Why is an enum better than a String parameter "EXCEL" or "PDF"?

### 中文筆記

enum vs String 的優點：(1) Compile-time safety：if (request.format() == ReportFormat.EXCEL) 如果有 typo（ReportFormat.EXEL），compile error；if (format.equals("EXEL")) 只在 runtime 才發現（永遠 false，silent bug）；(2) 自文件化：ReportFormat.EXCEL 比 "EXCEL" 明確，IDE 提示所有可用值；(3) 有限集合：enum 限定只有 EXCEL 和 PDF 是合法值，String 允許任意輸入（需額外驗證）；(4) switch 的 exhaustiveness：switch (format) { case EXCEL -> ... case PDF -> ... } 若新增 CSV，compiler 或 IDE 提醒未覆蓋的 case；String 的 switch 沒有這個保護；(5) Jackson 序列化：@RequestParam ReportFormat format 讓 Spring 的 converter 自動驗證 request 的 format 參數是否是合法的 enum 值，非法值自動 400 Bad Request。

### Category

Reports (Excel and PDF)

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportFormat.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java

### Verified classes and methods

- ReportAppService.generateDailyStatisticsReport()

### Execution flow

1. Admin calls report API with date and format
2. ReportDataService aggregates counts
3. ExcelReportGenerator or PdfReportGenerator returns bytes

### Why this may be asked

Enum over String for bounded value sets is a foundational type safety question.

### Possible follow-up questions

- How does Spring MVC handle an invalid format=CSV query parameter against the ReportFormat enum?
- How would you add CSV as a third report format? What files would you need to change?
- What is the difference between @RequestParam ReportFormat format and @RequestParam String format when the client sends format=pdf in lowercase?

### Verification status

- Verified


---

<a id="Q177"></a>

## Q177 — LocalDocumentStorageService.store() validates the file before storing it. Why is validation in the store() method rather than only in the controller's @Valid?

### 中文筆記

在 store() 中驗證的理由（Defence in depth）：(1) @Valid 在 controller 只驗證 request DTO 的欄位（如 @NotBlank、@Size），MultipartFile 的 extension 和 size 不能用 Bean Validation annotation 直接驗證；(2) DocumentStorageService 是一個 port，可能被多個 caller 使用（直接 REST、Thymeleaf web controller、batch processing），每個 caller 都需要 validation，集中在 store() 確保不論從哪個路徑呼叫都有保護；(3) 業務規則（max upload size 從 SystemParameterService 動態讀取、extension allowlist）屬於業務邏輯，應在 service/domain 層執行，不應只靠 controller 層的 Spring MVC validation；(4) Unit test：LocalDocumentStorageServiceTest 可以單獨測試 validate 行為，不需要 HTTP request context。Thymeleaf web controller 也透過 store() 上傳文件，與 REST API 共享同一組驗證。

### Category

File Upload and Storage

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java

### Verified classes and methods

- LocalDocumentStorageService.validate()
- LocalDocumentStorageService.store()

### Execution flow

1. Upload API accepts multipart file
2. ApplicationAppService delegates to LocalDocumentStorageService
3. Metadata saved with application; bytes stored under configured base-path

### Why this may be asked

Defense in depth validation strategy is a security best practice; tests whether the candidate understands layered validation.

### Possible follow-up questions

- What happens if validate() is called separately before store()? Could the file change between the two calls?
- How would you add virus scanning to the document upload flow without changing the DocumentStorageService port?
- What is the difference between client-side validation and server-side validation in this context?

### Verification status

- Verified


---

<a id="Q178"></a>

## Q178 — LocalDocumentStorageService uses @PostConstruct initUploadDirectory(). What risk does this mitigate?

### 中文筆記

@PostConstruct initUploadDirectory() 的作用：Spring 完成 dependency injection 後、bean 可以被使用前，立即呼叫 initUploadDirectory()，執行 Files.createDirectories(resolvedBasePath)（確保 upload 根目錄存在）。緩解的風險：(1) 啟動即失敗：若 basePath（${tlbank.upload.base-path}）配置的目錄不存在且無法建立（如磁碟空間不足、權限問題），@PostConstruct 拋 IllegalStateException，應用程式在啟動時失敗，而不是在第一個 upload request 時才發現；fail-fast 比 fail-late 更容易 debug；(2) Directory path normalization：Paths.get(basePath).toAbsolutePath().normalize() 將相對路徑解析為絕對路徑（${user.dir}/uploads/dev → /home/user/project/uploads/dev），避免後續路徑拼接的潛在問題。目錄不預先建立的後果：Files.copy(file.getInputStream(), targetFile, REPLACE_EXISTING) 若 parent directory 不存在，拋 NoSuchFileException，只在第一個 upload 時報錯。

### Category

File Upload and Storage

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java

### Verified classes and methods

- LocalDocumentStorageService

### Execution flow

1. Upload API accepts multipart file
2. ApplicationAppService delegates to LocalDocumentStorageService
3. Metadata saved with application; bytes stored under configured base-path

### Why this may be asked

@PostConstruct for fail-fast initialization is a production stability pattern; tests knowledge of Spring bean lifecycle hooks.

### Possible follow-up questions

- What is the lifecycle order of @PostConstruct relative to @Autowired and constructor injection?
- What other type of initialization would you put in @PostConstruct besides directory creation?
- How would LocalDocumentStorageServiceTest handle @PostConstruct — does it get called in tests?

### Verification status

- Verified


---

<a id="Q179"></a>

## Q179 — Files are stored at {basePath}/{applicationId}/{documentType}_{timestamp}.{extension}. What security problem could arise from trusting documentType directly in the file path?

### 中文筆記

Path traversal 的風險：若 DocumentType 是一個 enum（NATIONAL_ID、INCOME_PROOF、RESIDENCE_PROOF），且 file name 使用 documentType.name()，不存在 path traversal——enum 的 .name() 返回的是 Java identifier（字母數字下劃線），不含 / 或 ..。但若 documentType 改為 String 輸入（non-enum），且直接放入路徑：/uploads/APP-001/../../etc/passwd 類型的 path traversal 就成為問題。目前的防禦：@RequestParam DocumentType documentType（enum type）讓 Spring 自動 reject 非法值（400 Bad Request），再加上 documentType.name() 只輸出合法 Java identifier。applicationId 也是透過 business key format（APP-YYYYMMDD-xxxx），但若 applicationId 接受 ../../admin 這樣的值，targetDir = resolvedBasePath.resolve(applicationId) 可能導致 path traversal。resolvedBasePath 的 normalize() 可以部分緩解，但應加入顯式 path validation（確認 targetDir 是 resolvedBasePath 的 sub-path）。

### Category

File Upload and Storage

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentType.java

### Verified classes and methods

- LocalDocumentStorageService.store()

### Execution flow

1. Upload API accepts multipart file
2. ApplicationAppService delegates to LocalDocumentStorageService
3. Metadata saved with application; bytes stored under configured base-path

### Why this may be asked

Path traversal is a classic file upload security vulnerability; tests security awareness in file handling code.

### Possible follow-up questions

- How would you validate that targetDir is still under resolvedBasePath after resolving the applicationId?
- What is the OWASP recommendation for handling uploaded file names?
- What does StandardCopyOption.REPLACE_EXISTING do, and is it safe for document re-upload?

### Verification status

- Verified


---

<a id="Q180"></a>

## Q180 — LocalDocumentStorageService.validate() reads the max file size from SystemParameterService on every validation call. What is the performance trade-off?

### 中文筆記

性能分析：systemParameterService.getIntValue("UPLOAD", "max.size.mb", 10) 在每次 validate() 呼叫時執行（每次文件上傳都會呼叫）。SystemParameterService.getIntValue() 先查 cache（InMemoryCacheStore），cache hit 不查 DB；cache miss 才查 DB 並放入 cache。一般情況（正常運行，cache 有值）：只是一個 ConcurrentHashMap.get() + Optional.map() 操作，overhead 可忽略。極端情況（cache miss，TTL 過期後）：DB query 才發生，但 max file size 是不常變化的 parameter，cache hit rate 應該很高。設計合理性：動態讀取 max size 的好處是 admin 可以在不重啟應用的情況下調整上傳限制（改 DB 值，等 cache TTL 到期，或手動 evict cache），比硬編碼或 @Value 注入更靈活。若要優化：在 LocalDocumentStorageService 中 cache maxSizeMb（@Value 注入）作為 last resort fallback，但目前的 SystemParameterService cache 已足夠。

### Category

File Upload and Storage

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/parameter/service/SystemParameterService.java

### Verified classes and methods

- LocalDocumentStorageService.validate()

### Execution flow

1. Upload API accepts multipart file
2. ApplicationAppService delegates to LocalDocumentStorageService
3. Metadata saved with application; bytes stored under configured base-path

### Why this may be asked

Dynamic configuration lookup in a hot code path is a performance design question with cache trade-off implications.

### Possible follow-up questions

- What would happen to all upload validations if SystemParameterService threw an exception?
- How would you benchmark whether the cache lookup adds measurable latency to file upload?
- Is there a race condition between an admin lowering the max file size and an in-flight upload?

### Verification status

- Verified


---

<a id="Q181"></a>

## Q181 — ApplicationDocumentEntity stores files as file system paths rather than BLOBs in the database. What are the trade-offs of this design?

### 中文筆記

Filesystem path vs BLOB 的取捨：Filesystem path（目前設計）優點：(1) DB 大小不受文件大小影響，DB 保持輕量；(2) 文件讀取不占 DB connection pool 和 RAM（直接讀 filesystem）；(3) 備份策略可以獨立（DB dump + filesystem sync 分開處理）；缺點：(1) 文件和 DB 記錄可以不同步（檔案存在但 DB 記錄刪除，或 DB 記錄存在但文件已刪除）；(2) 水平擴展時多個 app instance 無法共享 local filesystem（需要 NFS 或 S3）；(3) 文件列表必須讀 DB，文件內容讀 filesystem，需要兩個 I/O。BLOB 設計優點：強一致性（文件和 record 在同一個 ACID transaction）；缺點：DB 膨脹、查詢慢、備份大。Portfolio 選擇 filesystem path 的正確理由：符合 production 最佳實踐（S3 object storage 也是 path/URL 方式）；local filesystem 在 single instance staging 夠用；Hexagonal port 允許未來換成 S3 adapter（LocalDocumentStorageService → S3DocumentStorageService）。

### Category

File Upload and Storage

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationDocumentEntity.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java

### Verified classes and methods

- ApplicationDocumentEntity

### Execution flow

1. Upload API accepts multipart file
2. ApplicationAppService delegates to LocalDocumentStorageService
3. Metadata saved with application; bytes stored under configured base-path

### Why this may be asked

File storage design decisions have production scalability implications; tests data architecture thinking.

### Possible follow-up questions

- What would you check to verify that a file path stored in the database actually corresponds to an existing file?
- How would an S3 URL look in filePath if you switched to object storage?
- What happens to uploaded files if the Docker app-uploads volume is not mounted?

### Verification status

- Verified


---

<a id="Q182"></a>

## Q182 — Uploaded files are stored in {applicationId}/{documentType}_{timestamp}.{extension}. What happens if a reviewer tries to download the same document twice?

### 中文筆記

重複下載的分析：REPLACE_EXISTING（Files.copy(..., StandardCopyOption.REPLACE_EXISTING)）：若同一 documentType + 同一秒內的 timestamp 上傳兩次，file name 相同，後者覆蓋前者，path 在 DB 中被更新。若在不同時間上傳：NATIONAL_ID_20260614162447.pdf 和 NATIONAL_ID_20260614162555.pdf 是兩個不同 path，兩個 ApplicationDocumentEntity 記錄（同一 application，同一 documentType，不同 path）。Download 的問題：目前沒有看到 download endpoint（GET /api/v1/applications/{id}/documents/{documentType}）的實作，可能 reviewer 透過 Thymeleaf UI 查看 uploaded file，或 document download 是 not implemented feature。若要讓 reviewer 下載：需要一個 endpoint 根據 applicationId + documentType 找到最新 file path，讀取 filesystem，stream 回 HTTP response（InputStreamResource，Content-Disposition: attachment）。目前文件列表是在 ApplicationDetailResponse.documentInfos 中顯示 path，但 path 對 user 直接暴露是 security concern（leak 了 filesystem 結構）。

### Category

File Upload and Storage

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/DocumentInfoResponse.java

### Verified classes and methods

- LocalDocumentStorageService.store()

### Execution flow

1. Upload API accepts multipart file
2. ApplicationAppService delegates to LocalDocumentStorageService
3. Metadata saved with application; bytes stored under configured base-path

### Why this may be asked

Document re-upload and download scenarios are a complete feature analysis question; tests end-to-end thinking.

### Possible follow-up questions

- What HTTP security header would prevent the browser from caching a sensitive downloaded document?
- How would you sign a download URL so that only authenticated reviewers can access it?
- Should the file system path be returned to API clients at all?

### Verification status

- Verified


---

<a id="Q183"></a>

## Q183 — The Dockerfile uses a two-stage build. What are the benefits, and what specific images are used for each stage?

### 中文筆記

兩階段 build 的好處：第一階段（builder）：FROM eclipse-temurin:17-jdk AS builder，包含完整 JDK（javac、maven 工具）+ 所有 build artifacts，編譯和打包 JAR。第二階段（runtime）：FROM eclipse-temurin:17-jre AS runtime，只包含 JRE（執行 .class 不需要 javac、jlink 等工具），COPY --from=builder /workspace/target/*.jar app.jar 複製 JAR。好處：(1) 大幅縮小最終 image 大小（JDK 約 500MB，JRE 約 180MB）；(2) 安全性：JDK 的 javac、jarsigner 等工具不在 runtime image 中，減少攻擊面；(3) Maven dependencies 和 source code 不在 runtime image 中（只有 JAR），不洩漏 source。特殊選擇：eclipse-temurin:17-jdk（Eclipse Temurin = Adoptium project 的 OpenJDK distribution，非 Oracle JDK，license 友好）；非 Alpine base（Alpine 的 musl libc 可能與某些 Java library 不相容，Temurin on Debian/Ubuntu 更穩定）。

### Category

Docker and Docker Compose

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docker/app/Dockerfile

### Verified classes and methods

- Documentation-level question

### Execution flow

1. docker compose up builds/runs SQL Server and app services
2. App container uses staging profile and SQL Server migrations
3. Healthcheck hits actuator endpoint (currently auth-gated)

### Why this may be asked

Multi-stage Docker builds are a standard best practice; tests whether the candidate understands container optimization.

### Possible follow-up questions

- What is the difference between JDK and JRE? Why is JRE sufficient for the runtime stage?
- What is Eclipse Temurin (Adoptium), and why use it over openjdk:17?
- How much smaller is the runtime image compared to building in a single stage?

### Verification status

- Documentation-only


---

<a id="Q184"></a>

## Q184 — The Dockerfile adds a non-root user tlbank for the runtime stage. Why is running as root in a container a security risk?

### 中文筆記

非 root user 的安全理由：若 container 以 root（UID 0）執行：(1) 容器逃逸（container escape）：若攻擊者透過應用程式漏洞（如 RCE）在 container 內執行任意命令，以 root 執行的攻擊者有最大的容器內權限，更容易利用 kernel 漏洞逃逸到 host；(2) Volume mount 的 host 目錄：app-uploads:/app/uploads mount 到 host 的 Docker volume，root 在 container 中修改文件，在 host 也是以 root 權限修改，影響 host filesystem；(3) 最小權限原則（Principle of Least Privilege）：應用程式只需讀寫 /app/uploads 和 /app/logs，不需要 root。目前的設定：RUN groupadd -r tlbank && useradd -r -g tlbank -s /usr/sbin/nologin tlbank（system user，無 home directory，無 login shell）；USER tlbank 讓 ENTRYPOINT java ... -jar app.jar 以 tlbank user 執行。VOLUME /app/uploads 和 VOLUME /app/logs 讓 Docker 管理這些目錄的 mount，tlbank user 需要這些目錄的 write 權限。

### Category

Docker and Docker Compose

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docker/app/Dockerfile

### Verified classes and methods

- Documentation-level question

### Execution flow

1. docker compose up builds/runs SQL Server and app services
2. App container uses staging profile and SQL Server migrations
3. Healthcheck hits actuator endpoint (currently auth-gated)

### Why this may be asked

Running as non-root in containers is a security best practice that's often missing in portfolios; tests container security awareness.

### Possible follow-up questions

- What is container escape, and how does running as non-root mitigate the risk?
- What VOLUME instruction does, and what is the relationship between VOLUME and Docker named volumes?
- How would you verify what user the running container is using?

### Verification status

- Documentation-only


---

<a id="Q185"></a>

## Q185 — The ENTRYPOINT in the Dockerfile includes -XX:+UseContainerSupport and -XX:MaxRAMPercentage=75.0. What do these JVM flags do?

### 中文筆記

JVM flags 的作用：-XX:+UseContainerSupport（Java 10+ 預設 enabled，Java 8u191+ 加入）：讓 JVM 尊重 container 的 cgroup 記憶體限制（--memory flag），而不是讀取 host 的總記憶體。沒有這個 flag（舊 JDK 8 預設）：JVM 看到 host 的 64GB RAM，設定 heap 為 16GB，但 Docker container 的限制是 512MB，JVM 嘗試分配超出 container 限制的 heap，被 OS OOM killer 殺死。-XX:MaxRAMPercentage=75.0：讓 JVM 使用 container 分配記憶體的最多 75%（-XX:MaxRAM × 75%）作為最大 heap。若 container --memory=1g（1GB），heap 最大 750MB，留 250MB 給 metaspace、NIO buffers、OS processes 等 off-heap 需求。-Djava.security.egd=file:/dev/./urandom：加速 SecureRandom 初始化（避免 Linux blocking entropy source /dev/random 導致啟動緩慢），使用非阻塞的 /dev/urandom。

### Category

Docker and Docker Compose

### Difficulty

Advanced

### Verified source files

- sp2-springboot/docker/app/Dockerfile

### Verified classes and methods

- Documentation-level question

### Execution flow

1. docker compose up builds/runs SQL Server and app services
2. App container uses staging profile and SQL Server migrations
3. Healthcheck hits actuator endpoint (currently auth-gated)

### Why this may be asked

Container-aware JVM flags are critical for production deployment; engineers who don't know them are likely to have OOM issues.

### Possible follow-up questions

- What would happen if UseContainerSupport was missing and the container had a 512MB memory limit?
- What is the remaining 25% of container memory used for if Java takes 75%?
- When would you use -XX:MaxRAMPercentage vs -Xmx directly?

### Verification status

- Documentation-only


---

<a id="Q186"></a>

## Q186 — docker-compose.yml has a db-init service that runs SQL scripts and then exits. Why is a separate init container needed rather than running scripts in sqlserver or app?

### 中文筆記

獨立 db-init container 的設計理由：sqlserver container：Microsoft SQL Server image 的 entrypoint 是 SQL Server 服務本身（sqlservr），不能同時執行初始化 SQL script（啟動時只做 SQL Server 啟動，沒有 lifecycle hook 支援 custom SQL）。app container（Spring Boot）：由 Flyway 負責 schema migration，但 Flyway 在 TlbankLendingApplication 啟動時才執行，此時資料庫（tlbank）必須已存在；若資料庫不存在，Flyway 連線失敗。db-init 的職責：等待 sqlserver healthy，執行 01-init-database.sql（CREATE DATABASE tlbank IF NOT EXISTS）、02-create-app-user.sql（建立 app 用戶和授權），然後 restart: "no" 自動退出。app 的 depends_on.db-init.condition: service_completed_successfully 確保資料庫初始化完成後 Spring Boot 才啟動，Flyway 才執行 schema migration。三步驟：SQL Server 啟動 → db-init 建庫 → app 跑 Flyway migration。

### Category

Docker and Docker Compose

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docker-compose.yml
- sp2-springboot/docker/sqlserver/init/01-init-database.sql
- sp2-springboot/docker/sqlserver/init/02-create-app-user.sql

### Verified classes and methods

- Documentation-level question

### Execution flow

1. docker compose up builds/runs SQL Server and app services
2. App container uses staging profile and SQL Server migrations
3. Healthcheck hits actuator endpoint (currently auth-gated)

### Why this may be asked

Init container pattern in Docker Compose is a common orchestration question that tests understanding of service startup ordering.

### Possible follow-up questions

- What is condition: service_completed_successfully vs condition: service_healthy?
- Why does the db-init container use restart: "no"?
- How would Kubernetes handle this init container pattern differently from Docker Compose?

### Verification status

- Documentation-only


---

<a id="Q187"></a>

## Q187 — The sqlserver service in docker-compose.yml has a healthcheck that runs sqlcmd -Q "SELECT 1". Why is this specific query used for health checking?

### 中文筆記

SELECT 1 作為 healthcheck 的理由：(1) 極輕量：執行一條不掃描任何 table 的常數 query，幾乎沒有 CPU 和 I/O 消耗，不影響資料庫效能；(2) 驗證連線能力：sqlcmd -S localhost -U sa -P "..." -C -Q "SELECT 1" 完整模擬應用程式的連線路徑（hostname、port、credentials、SSL context），只有成功返回才代表 SQL Server 真正 ready 接受連線；(3) 跨版本相容：SELECT 1 在所有 SQL Server 版本都有效；(4) 退出碼：sqlcmd 成功回傳 exit code 0，失敗（SQL Server 未就緒、認證失敗）回傳非 0，Docker Compose 的 healthcheck 以 exit code 判斷 healthy/unhealthy。-C flag（trust server certificate）：避免 sqlcmd 因 SSL certificate 驗證失敗而拒絕連線（開發環境使用自簽憑證）。start_period: 40s：給 SQL Server 40 秒的啟動寬限期（SQL Server 啟動慢），在此期間 healthcheck 失敗不計入 retries。

### Category

Docker and Docker Compose

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docker-compose.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. docker compose up builds/runs SQL Server and app services
2. App container uses staging profile and SQL Server migrations
3. Healthcheck hits actuator endpoint (currently auth-gated)

### Why this may be asked

SELECT 1 for SQL Server health checks is a specific and common pattern; tests database operations knowledge.

### Possible follow-up questions

- What is start_period in Docker Compose healthcheck, and why is it set to 40 seconds for SQL Server?
- What would happen if start_period was too short and SQL Server took 60 seconds to start?
- Could Spring Boot's Actuator /actuator/health/db endpoint be used for the SQL Server healthcheck?

### Verification status

- Documentation-only


---

<a id="Q188"></a>

## Q188 — The app service in docker-compose.yml uses depends_on with condition: service_healthy for sqlserver. What problem would occur without this?

### 中文筆記

沒有 service_healthy 的問題：若 depends_on.sqlserver 只有 condition: service_started（預設），Docker Compose 在 sqlserver container 的 process 啟動後立即啟動 app container（不等 SQL Server ready 接受 connection）。SQL Server 啟動需要 30-60 秒（初始化 system databases、binding port 等），在此期間 Spring Boot 的 Flyway 嘗試連線 SQL Server，得到 connection refused，Application.run() 拋 exception，container 退出（restart = no 或 on-failure 觸發 restart loop）。condition: service_healthy 搭配 sqlserver 的 healthcheck（SELECT 1 成功），確保 SQL Server 完全 ready 後才啟動 app。同理 depends_on.db-init.condition: service_completed_successfully 確保資料庫 tlbank 建立完成後，Flyway migration 才能找到 target DB。三層 startup 依賴：sqlserver healthy → db-init complete → app start。

### Category

Docker and Docker Compose

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docker-compose.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. docker compose up builds/runs SQL Server and app services
2. App container uses staging profile and SQL Server migrations
3. Healthcheck hits actuator endpoint (currently auth-gated)

### Why this may be asked

depends_on with health conditions is a common Docker Compose issue that causes race conditions; tests practical container orchestration knowledge.

### Possible follow-up questions

- How does condition: service_completed_successfully differ from condition: service_healthy?
- What would happen if you removed the app container's healthcheck — would depends_on still work?
- In Kubernetes, what mechanism replaces Docker Compose's condition: service_healthy?

### Verification status

- Documentation-only


---

<a id="Q189"></a>

## Q189 — The CI pipeline's deploy-staging job writes a docker-compose.yml file dynamically rather than using the one in the repository. Why?

### 中文筆記

動態生成 docker-compose.yml 的理由：CI pipeline 在 self-hosted macOS runner 上執行，deploy 時需要將 GHCR image（ghcr.io/.../tlbank-backend:latest）注入 docker-compose.yml 的 app.image。若直接用 repository 中的 docker-compose.yml，它的 app service 用 build: context: .（本地 build），不是 GHCR pull image——staging 的目的是驗證已 build 的 Docker image，不是重新 build。動態生成還允許：注入 MSSQL_SA_PASSWORD（GitHub secret）到 docker-compose.yml 的 environment section，不將 secret 提交到 git；動態設定 IMAGE_NAME（lowercase GHCR path）；每次 deploy 都生成最新的 compose file，確保配置與當前 CI run 的 image 對應。Repository 中的 docker-compose.yml 是 local dev 用（build: context: .），staging deploy 用 CI 生成的版本（image: ghcr.io/...）。

### Category

Docker and Docker Compose

### Difficulty

Advanced

### Verified source files

- .github/workflows/ci.yml
- sp2-springboot/docker-compose.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. docker compose up builds/runs SQL Server and app services
2. App container uses staging profile and SQL Server migrations
3. Healthcheck hits actuator endpoint (currently auth-gated)

### Why this may be asked

The distinction between a dev compose file and a deploy compose file is a mature CI/CD design decision.

### Possible follow-up questions

- What is the risk of generating the compose file dynamically if the template has a syntax error?
- Could you use Docker Compose override files to avoid duplicating the whole compose file?
- How does the IMAGE_NAME lowercase conversion work in the CI step?

### Verification status

- Documentation-only


---

<a id="Q190"></a>

## Q190 — Named volumes (sqlserver-data, app-uploads, app-logs) are used in Docker Compose. What is the difference between named volumes and bind mounts, and why are named volumes chosen here?

### 中文筆記

Named volume vs bind mount：Named volume（sqlserver-data:/var/opt/mssql）：Docker 自行管理 volume 位置（通常在 /var/lib/docker/volumes/），container-to-host 隔離，跨 container restart 持久化，docker compose down 不刪除 volume（只刪 container），docker compose down -v 才刪除。Bind mount（/host/path:/container/path）：直接掛載 host 的指定目錄，開發時可以實時看到 container 寫入的文件，但 host path 必須存在且有適當權限。選擇 Named volume 的理由：(1) Portability：docker-compose.yml 不含 host-specific path（./data/sqlserver 在 Windows 和 Mac 路徑格式不同），任何環境都能 docker compose up；(2) DB data safety：sqlserver-data 在 docker compose down（不加 -v）後仍保留，不需要每次重啟都跑 Flyway 重新建 schema；(3) app-uploads 和 app-logs 儲存用戶上傳的文件和 log，需要跨 container restart 持久化；Named volume 讓 Docker 管理 ownership，避免 permission 問題（tlbank user vs host user）。

### Category

Docker and Docker Compose

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docker-compose.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. docker compose up builds/runs SQL Server and app services
2. App container uses staging profile and SQL Server migrations
3. Healthcheck hits actuator endpoint (currently auth-gated)

### Why this may be asked

Understanding named volumes vs bind mounts is a fundamental Docker knowledge question for backend engineers.

### Possible follow-up questions

- How would you inspect the contents of the sqlserver-data named volume from outside the container?
- What happens to SQL Server data if you run docker compose down -v?
- When would you use a bind mount instead of a named volume for app-logs?

### Verification status

- Documentation-only


---

<a id="Q191"></a>

## Q191 — The CI pipeline has 5 jobs with needs dependencies. Draw the dependency graph and identify the critical path.

### 中文筆記

5 job 依賴圖：build-test → code-quality（needs: build-test）→ 無後繼（僅止於此）；build-test → dependency-scan（needs: build-test）→ 無後繼；build-test + code-quality + dependency-scan → build-and-push-image（needs: [build-test, code-quality, dependency-scan]）→ deploy-staging（needs: build-and-push-image，且只在 workflow_dispatch 執行）。並行：code-quality 和 dependency-scan 在 build-test 完成後並行執行。Critical path（最長路徑）：build-test → code-quality 或 dependency-scan（兩者並行，取較慢的）→ build-and-push-image → deploy-staging。若 code-quality 耗時 3 分鐘、dependency-scan 耗時 5 分鐘（Trivy 較慢），critical path 是 build-test + dependency-scan + build-and-push-image + deploy-staging。code-quality 不在 critical path（只要它比 dependency-scan 快就不影響總時間）。

### Category

GitHub Actions CI/CD

### Difficulty

Intermediate

### Verified source files

- .github/workflows/ci.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Push/PR triggers CI jobs in .github/workflows/ci.yml
2. build-test runs mvn clean verify; later jobs scan and may push GHCR image
3. deploy-staging runs only on workflow_dispatch against self-hosted macOS runner

### Why this may be asked

Understanding pipeline dependency graphs and critical paths is a DevOps engineering skill; tests CI/CD architecture knowledge.

### Possible follow-up questions

- What would happen if code-quality failed but dependency-scan succeeded?
- How would you parallelize further to reduce the total pipeline time?
- Why does deploy-staging need build-and-push-image rather than just build-test?

### Verification status

- Documentation-only


---

<a id="Q192"></a>

## Q192 — SchedulerConfig and SchedulingConfig both declare @EnableScheduling. What is the impact, and what does the maintenance doc recommend?

### 中文筆記

common.config 下 SchedulingConfig 與 SchedulerConfig 皆標註 @EnableScheduling。功能上排程仍可運作，但屬重複設定。維護文件將其列為技術債：應刪除其中一個組態類別，保留單一啟用點。

### Category

Schedulers

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulerConfig.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- SchedulingConfig
- SchedulerConfig

### Execution flow

1. Spring bootstraps configuration classes under common.config
2. Either SchedulingConfig or SchedulerConfig enables scheduling
3. OtpCleanupScheduler / CacheRefreshScheduler / DailyStatisticsScheduler register @Scheduled methods

### Why this may be asked

Checks whether the candidate reads known debt instead of assuming every duplicate annotation is a runtime bug.

### Possible follow-up questions

- Which schedulers are registered today?
- How are cron expressions externalized?
- Would duplicate @EnableScheduling cause jobs to run twice?

### Verification status

- Verified


---

<a id="Q193"></a>

## Q193 — Maven dependencies are resolved with mvn -B -q dependency:resolve before the Trivy scan. Why is this step necessary?

### 中文筆記

預先解析 dependency 的必要性：Trivy 的 scan-type: fs（filesystem scan）掃描 sp2-springboot/ 目錄，偵測 pom.xml 中的 dependency，並查詢 CVE 資料庫。問題：若 Trivy 在掃描時嘗試自己解析 Maven dependency（或觸發 Maven wrapper 下載），可能向 Maven Central 發送大量請求，達到 Maven Central 的 rate limit（HTTP 429），Trivy scan 因此失敗。解法：mvn -B -q dependency:resolve 預先把所有 dependencies 下載到 ~/.m2 local repository（CI 有 Maven cache，來自 actions/setup-java 的 cache: maven），Trivy 掃描時所有 JAR 都在本地 cache，不觸發遠端請求，避免 429 限流。-B（batch mode，關閉 interactive 提示）、-q（quiet，減少 log 輸出）讓這個步驟快速靜默執行。此 step 是 CI 工程的細節優化，體現了解決實際 CI 問題的能力。

### Category

GitHub Actions CI/CD

### Difficulty

Advanced

### Verified source files

- .github/workflows/ci.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Push/PR triggers CI jobs in .github/workflows/ci.yml
2. build-test runs mvn clean verify; later jobs scan and may push GHCR image
3. deploy-staging runs only on workflow_dispatch against self-hosted macOS runner

### Why this may be asked

The reason behind pre-resolving Maven dependencies before Trivy is a specific and non-obvious CI engineering decision; tests attention to CI configuration detail.

### Possible follow-up questions

- What is HTTP 429 Too Many Requests and how would it affect the Trivy scan without this step?
- How does actions/setup-java's cache: maven work, and what does it cache?
- Could you skip this step if using scan-type: image instead of scan-type: fs?

### Verification status

- Documentation-only


---

<a id="Q194"></a>

## Q194 — Trivy uses exit-code: 0 (report-only mode) and ADR 0004 explicitly notes this as a known limitation. What would change to make Trivy a blocking gate?

### 中文筆記

exit-code: 0（report-only）vs exit-code: 1（blocking）：目前：Trivy 掃描後以 table 格式列出 HIGH/CRITICAL vulnerability，但 GitHub Actions step 以 exit code 0 成功結束，後續 jobs 繼續執行，develop 不受阻。改為 blocking（exit-code: 1）的後果：若掃描到 HIGH/CRITICAL CVE 且 CVE 已有 fix，step fail，build-and-push-image 不執行，開發者必須先升級 dependency 才能 merge。需要的前置工作：(1) 建立 baseline：先掃描一次，接受所有現有的 CVE（建立 .trivyignore 或 Trivy policy 豁免清單），確保從 baseline 開始 exit-code: 1 不立即破壞 CI；(2) Dependency 更新策略：pom.xml 的 dependencies 需要定期 mvn versions:display-dependency-updates 檢查更新；(3) ignore-unfixed: true 已設定，只要沒有 fix 的 CVE 不 block CI，降低 noise。ADR 0004 明確標記為 future improvement（「make Trivy blocking when the vulnerability baseline is acceptable」）。

### Category

GitHub Actions CI/CD

### Difficulty

Intermediate

### Verified source files

- .github/workflows/ci.yml
- sp2-springboot/docs/decisions/0004-use-github-actions.md

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Push/PR triggers CI jobs in .github/workflows/ci.yml
2. build-test runs mvn clean verify; later jobs scan and may push GHCR image
3. deploy-staging runs only on workflow_dispatch against self-hosted macOS runner

### Why this may be asked

Non-blocking security scans are a deliberate trade-off during early development; tests understanding of security gate maturity progression.

### Possible follow-up questions

- What is a .trivyignore file, and when would you use one?
- What is ignore-unfixed: true in Trivy and why is it set?
- How would you notify the team of HIGH findings without blocking the pipeline?

### Verification status

- Documentation-only


---

<a id="Q195"></a>

## Q195 — The build-test job uses actions/setup-java@v5 with cache: maven. How does the Maven cache work in GitHub Actions, and what is its effect on build speed?

### 中文筆記

Maven cache 的工作原理：actions/setup-java@v5 的 cache: maven 選項：在 job 結束時，將 ~/.m2/repository（下載的 Maven dependencies 和 plugins）打包 + 上傳到 GitHub Actions cache storage（以 cache-dependency-path: pom.xml 的 checksum 作為 cache key）。下次相同 pom.xml 的 build job 啟動時，在 setup-java 步驟 restore cache 到 ~/.m2/repository，Maven build 直接使用 cached dependencies，不需要從 Maven Central 重新下載。效果：pom.xml 不變時，build 速度顯著提升（跳過 dependency download，可節省 30 秒到 2 分鐘）；pom.xml 改變（新增 dependency）時，cache key 不同，無 cache，重新下載。cache-dependency-path: ${{ env.APP_DIR }}/pom.xml 確保 cache 以 sp2-springboot/pom.xml 的 checksum 計算（monorepo 有多個子目錄，精確指定正確的 pom.xml）。

### Category

GitHub Actions CI/CD

### Difficulty

Intermediate

### Verified source files

- .github/workflows/ci.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Push/PR triggers CI jobs in .github/workflows/ci.yml
2. build-test runs mvn clean verify; later jobs scan and may push GHCR image
3. deploy-staging runs only on workflow_dispatch against self-hosted macOS runner

### Why this may be asked

Understanding how CI caching works is a practical DevOps question; engineers who don't configure caching have unnecessarily slow pipelines.

### Possible follow-up questions

- What happens if two jobs run in parallel and both try to restore and save the Maven cache?
- How would you debug a CI build where cache: maven seems not to be working?
- What is the maximum cache size in GitHub Actions, and what happens when it's exceeded?

### Verification status

- Documentation-only


---

<a id="Q196"></a>

## Q196 — The deploy-staging job constructs GHCR credentials as a base64-encoded string in config.json rather than using docker login. Why?

### 中文筆記

繞過 docker login 的原因：macOS 的 Docker Desktop 預設使用 Keychain（macOS Credential Store）存儲 Docker credentials（~/.docker/config.json 有 "credsStore": "desktop"）。當 GitHub Actions self-hosted runner 在 macOS 上以 service 執行時，它的 Keychain context 和用戶的 interactive session 不同，docker login ghcr.io 嘗試寫入 Keychain 可能失敗（權限拒絕或 UI 彈出 Keychain 解鎖提示）。解決方案：設定 DOCKER_CONFIG=${{ runner.temp }}/.docker（覆蓋 Docker config 路徑），在這個隔離目錄中直接寫入 config.json（base64 encoded username:token），讓 Docker 使用這個 temporary config 而不觸及系統 Keychain。Keychain isolation 是 macOS self-hosted runner 上 Docker auth 的特殊問題，在 Linux runner 上不存在（Docker 直接用 ~/.docker/config.json 無 Keychain）。ADR 0004 提到「outbound-only self-hosted runner」，這個 Keychain bypass 是支撐該決策的實作細節。

### Category

GitHub Actions CI/CD

### Difficulty

Advanced

### Verified source files

- .github/workflows/ci.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

The macOS Keychain issue with Docker on self-hosted runners is a real-world problem that requires specific knowledge to solve.

### Possible follow-up questions

- What is DOCKER_CONFIG environment variable and how does Docker use it?
- Why is base64 encoding used for Docker credentials in config.json?
- What security risk exists in storing credentials in a temp directory on the runner machine?

### Verification status

- Documentation-only


---

<a id="Q197"></a>

## Q197 — ci.yml and terraform.yml are separate workflow files. What is the advantage of splitting workflows across multiple files?

### 中文筆記

分開 workflow 的優點：(1) 獨立觸發：ci.yml 在 sp2-springboot/** 變更時觸發；terraform.yml 在 infra/** 變更時觸發；markdown.yml 在 .md 文件變更時觸發。若合在一個 workflow.yml，每次任何文件改動都觸發所有 jobs，浪費 CI quota；(2) 獨立配置：每個 workflow 有自己的 permissions、env、jobs 設定，不相互干擾；(3) 清晰的責任分離：查看 CI 狀態時，GitHub UI 顯示三個獨立的 workflow run，每個對應一個關注點（code verify、IaC check、docs lint），失敗時立即知道是哪個 workflow；(4) 可以獨立 disable：若暫時不需要 markdown lint，只需停用 markdown.yml 而不影響 CI/CD；(5) ADR 0004 的設計原則：Terraform validation 是 IaC concern，獨立於應用程式 CI，不應 block 應用程式 build。

### Category

GitHub Actions CI/CD

### Difficulty

Basic

### Verified source files

- .github/workflows/ci.yml
- .github/workflows/terraform.yml
- .github/workflows/markdown.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Push/PR triggers CI jobs in .github/workflows/ci.yml
2. build-test runs mvn clean verify; later jobs scan and may push GHCR image
3. deploy-staging runs only on workflow_dispatch against self-hosted macOS runner

### Why this may be asked

Workflow file organization is a DevOps practice question; tests understanding of separation of concerns in CI/CD.

### Possible follow-up questions

- How would you share common steps (like actions/checkout and setup-java) across multiple workflow files?
- What is a reusable workflow in GitHub Actions, and when would you use one?
- What is the difference between workflow_call and workflow_dispatch?

### Verification status

- Documentation-only


---

<a id="Q198"></a>

## Q198 — The terraform.yml workflow runs terraform fmt -check -recursive before terraform validate. What does format checking add to a CI pipeline?

### 中文筆記

terraform fmt -check 在 CI 的意義：terraform fmt 是 Terraform 的標準格式化工具（類似 gofmt 或 prettier），對齊縮排、括號間距、argument 順序等。-check flag：不修改文件，只驗證文件是否已格式化；若未格式化，terraform fmt -check 返回非 0 exit code，CI step fail。-recursive：遞迴檢查 infra/local/ 下所有 .tf 文件。在 CI 中加入的好處：(1) 強制統一程式碼風格：任何 PR 若 .tf 文件沒有先在本地執行 terraform fmt，CI 立即 fail，reviewer 不需要在 review 時指出格式問題；(2) 預防 diff 噪音：格式不統一的 main.tf 在下次有人執行 terraform fmt 後會產生大量 whitespace diff，pollute git history；(3) 低成本 gate：terraform fmt -check 執行極快（不需要 provider init 或雲端連線）。

### Category

Terraform (Local)

### Difficulty

Basic

### Verified source files

- .github/workflows/terraform.yml
- infra/local/main.tf

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — local Terraform validate/plan workflow; no cloud apply in this repo

### Why this may be asked

Format checking in CI is a code quality practice; tests understanding of infrastructure code quality gates.

### Possible follow-up questions

- What command would a developer run locally before pushing to avoid terraform fmt -check failures?
- What is terraform validate and how does it differ from terraform fmt -check?
- What would terraform plan output for the local provider, and why is it still useful to run in CI?

### Verification status

- Documentation-only


---

<a id="Q199"></a>

## Q199 — infra/local/main.tf uses the hashicorp/local provider to generate a markdown file. How does this demonstrate Terraform IaC workflow without incurring cloud costs?

### 中文筆記

Local provider 的教學目的：hashicorp/local provider 讓 Terraform 管理本地文件（local_file resource 生成 generated-staging-env.md）。透過這個不需要 cloud credentials、不需要費用的 provider，可以完整演練 Terraform 的 state/plan/apply 工作流程：terraform init：下載 hashicorp/local provider binary（registry.terraform.io/hashicorp/local ~> 2.5）；terraform plan：計算「what will change」（生成 markdown 文件）；terraform apply：真正執行（在 infra/local/generated-staging-env.md 寫入內容）；terraform.tfstate：Terraform 記錄已管理的 resource（markdown 文件的 checksum 等），commit 到 git 作為 state file；outputs.tf：output "backend_url" 等輸出值，模擬雲端 infra 的 output（如 ALB URL）。加入 AWS provider 的 evolution path：把 local_file 換成 aws_ecs_service、aws_rds_instance 等，state file 從 local 移到 S3 backend，其他工作流程完全相同。這讓面試官看到對 Terraform workflow 的理解，而非只是「我知道 Terraform 是什麼」。

### Category

Terraform (Local)

### Difficulty

Intermediate

### Verified source files

- infra/local/main.tf
- infra/local/variables.tf
- infra/local/outputs.tf
- infra/local/terraform.tfstate

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — local Terraform validate/plan workflow; no cloud apply in this repo

### Why this may be asked

Local provider as a learning scaffold is the honest explanation of the current Terraform setup; tests whether the candidate can articulate its purpose.

### Possible follow-up questions

- What is Terraform state, and why is it committed to git for the local provider but should NOT be for cloud providers?
- What would need to change in main.tf to provision an actual AWS ECS task instead of a markdown file?
- What is a Terraform backend, and what S3 backend configuration would you add for remote state?

### Verification status

- Documentation-only


---

<a id="Q200"></a>

## Q200 — The .cursor/rules.md file instructs Cursor AI to "always read every document under /docs before generating code" and "never generate code outside the current Sprint." What does this reveal about the development methodology?

### 中文筆記

Cursor rules 揭示的開發方法論：(1) Spec-driven development：「always read every document under /docs」確保 Cursor 在生成 code 之前理解 SDD、ADR、架構圖，以設計文件作為 ground truth，而非直接生成 CRUD；(2) Sprint-bounded development：「never generate code outside the current Sprint」限制實作範圍，避免過度工程與超出需求的功能；(3) Architecture preservation：「do not change architecture unless requested」防止 Clean Architecture 被改成 transaction script 或 active record；(4) SOLID principles：「follow SOLID」約束設計選擇；(5) Documentation sync：「always update documentation after implementation」避免 code 與 docs drift。整體流程是 spec first、工具輔助、人工審查，而非直接接受產生結果。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Advanced

### Verified source files

- sp2-springboot/.cursor/rules.md
- sp2-springboot/docs/design/19-cursor-implementation-roadmap.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Checks whether the candidate can explain a spec-first workflow with tool-assisted coding, sprint scope limits, and human review against ADRs and tests.

### Possible follow-up questions

- How did you verify that Cursor-generated code matched the spec before merging?
- What would you do if Cursor generated code that violated the Clean Architecture dependency rule?
- What is the difference between using AI to "generate code" vs using AI to "assist code review"?

### Verification status

- Documentation-only


---

<a id="Q201"></a>

## Q201 — AuditDetailBuilder runs a final regex pass to redact password=, otpCode=, and nationalId= substrings even after type-specific extraction. Why is this defense-in-depth necessary?

### 中文筆記

雙重防護的必要性：第一層是 type-specific extraction（SendOtpCommand 用 maskMobile()、VerifyOtpCommand 用 maskMobile()），對已知型別提供精確的脫敏。但「防禦縱深」需要假設第一層可能失效：(1) 未來有開發者在新的 @Auditable 方法中傳入含有 password 或 nationalId 欄位的自訂物件，未更新 AuditDetailBuilder 的 type-specific 處理；(2) 某個物件的 toString() 意外包含敏感欄位名稱（如 Lombok 的 @ToString 預設包含所有欄位）；(3) 傳入的 String 參數本身包含 nationalId=A123456789。最終 regex sanitize() 正則表達式掃描整個 detail string，若發現 password=...、otpCode=...、nationalId=...（大小寫不敏感），將值替換為 ***。這個設計來自一個原則：PII 洩漏到 audit log 是不可逆的風險，應在最後輸出點設置通用防線，即使前面所有層都已脫敏。

### Category

Security and Production Risks

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditDetailBuilder.java
- sp2-springboot/docs/design/11-audit-logging.md

### Verified classes and methods

- AuditDetailBuilder.buildDetail()
- AuditDetailBuilder.sanitize() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Defense-in-depth data sanitization is a mature security pattern; the candidate should be able to explain why one layer is not enough.

### Possible follow-up questions

- What regular expression would match nationalId=A123456789 case-insensitively?
- How would you test that the sanitize() method catches an unexpected case?
- What is the risk if AuditDetailBuilder throws an exception — does it block the business operation?

### Verification status

- Verified


---

<a id="Q202"></a>

## Q202 — The LoginFailureHandler records USER_LOGIN_FAILED audit logs with the username from the request parameter. What security concern does this create?

### 中文筆記

安全問題：request.getParameter("username") 取得的是 HTTP POST form 的 username 欄位值，這是 untrusted user input。若用戶提交 username=admin'; DROP TABLE users;--（SQL injection 嘗試）或超長字串，這個值會被直接存入 audit_logs.username（VARCHAR(100)）。風險分析：(1) 資料庫 truncation：若 username 超過 100 字元，JDBC 可能拋 DataException 或 SQL Server 自動截斷，影響 audit log 寫入；(2) Log injection：若 username 包含換行符（\n、\r\n），日誌系統可能被欺騙解讀為多條 log entries；(3) PII 洩漏：若用戶輸入的是 email 地址或手機號碼（而非 username），這些 PII 被原始存入 audit log。緩解措施：截斷 username 到合理長度（50 字元）、移除控制字元、或對非字母數字字元做 sanitization 後再存 audit log。LoginSuccessHandler 從 authentication.getName() 取 username（已通過認證，是 DB 中的 valid username），沒有這個問題，兩者不一致。

### Category

Security and Production Risks

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginFailureHandler.java

### Verified classes and methods

- LoginFailureHandler.onAuthenticationFailure()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Unsanitized user input in audit logs is a subtle security issue; tests whether the candidate notices the difference between trusted and untrusted input.

### Possible follow-up questions

- How would you truncate the username to 50 characters safely before storing it?
- What is log injection and how would you prevent it?
- How does LoginSuccessHandler avoid this problem by using authentication.getName()?

### Verification status

- Verified


---

<a id="Q203"></a>

## Q203 — The security design document explicitly states that GET /api/v1/applications/** is permitAll(). What is the risk of allowing unauthenticated read access to application details?

### 中文筆記

permitAll() 的風險：任何人可以呼叫 GET /api/v1/applications/{applicationId} 取得申請明細（包含 masked 但仍可識別的個人資訊）。風險項目：(1) ID enumeration：applicationId 格式是 APP-YYYYMMDDHHMMSS-XXXX，時間戳部分可預測，攻擊者可以在特定時段枚舉 applicationId，不需要猜測隨機 UUID；(2) PII 曝光：即使 mobile 和 nationalId 有 mask（0912****78），仍洩漏申請人存在、何時申請、選擇哪種卡片產品；(3) Business intelligence 洩漏：攻擊者可統計每天有多少申請（從 timestamp 推斷），了解銀行業務規模。設計理由：permitAll() 是為了讓申請人查詢自己的申請進度（不需要帳號）。改善方向：用 short-lived signed URL（類似 S3 presigned URL）、或在申請建立後返回一個 access token 讓申請人保存，query 時需提供此 token 作為身份驗證。

### Category

Security and Production Risks

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java
- sp2-springboot/docs/design/07-security-design.md

### Verified classes and methods

- SecurityConfig.securityFilterChain()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Public read access on sensitive resources is a real production security gap; tests whether the candidate identifies authorization gaps.

### Possible follow-up questions

- How would a signed URL approach work for applicant self-service query?
- What is the OWASP Broken Object Level Authorization (BOLA) vulnerability, and does this design have it?
- Would requiring a query parameter secret token be sufficient protection?

### Verification status

- Verified


---

<a id="Q204"></a>

## Q204 — The .env.example file documents APP_OTP_EXPIRE_MINUTES and APP_OTP_MAX_RETRY as environment variables, but the code reads them from system_parameters via SystemParameterService. This is documented as technical debt #5. What is the confusion, and why does it matter?

### 中文筆記

問題的本質：.env.example 列出 APP_OTP_EXPIRE_MINUTES=5 和 APP_OTP_MAX_RETRY=3，暗示 docker-compose.yml 將這些設定注入 app service 的環境變數，Spring Boot 透過 @Value 或 property binding 讀取。實際：OtpAppService.sendOtp() 呼叫 systemParameterService.getIntValue("OTP", "expire_minutes", 5)，從 system_parameters DB table 讀取，沒有 @Value("${app.otp.expire.minutes}") 的 property binding。結果：operator 看到 .env.example 修改了 APP_OTP_EXPIRE_MINUTES=10，重啟 Docker Compose，但 OTP 仍然 5 分鐘過期——因為程式根本不讀那個環境變數。這個不一致讓 ops 困惑，也讓後人誤以為設定正確卻沒效果。修復選項：(1) 在 .env.example 移除 APP_OTP_* 說明，說明應透過 admin UI 的 system_parameters 調整；(2) 在 OtpAppService 加入 @Value binding 作為 DB config 的 override，但這會讓兩個 config 來源共存（更複雜）。

### Category

Security and Production Risks

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/.env.example
- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- OtpAppService.sendOtp()

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Configuration source confusion is a real operational risk; tests whether the candidate can trace where a value actually comes from.

### Possible follow-up questions

- What would happen if an operator changed APP_OTP_EXPIRE_MINUTES=1 in .env expecting 1-minute OTPs?
- How would you add a @Value binding as a fallback to the SystemParameterService lookup?
- What is the principle of "single source of truth" for configuration?

### Verification status

- Verified


---

<a id="Q205"></a>

## Q205 — There is no rate limiting on POST /api/v1/otp/actions/send. What attack does this enable, and how would you add rate limiting with Redis without modifying the domain layer?

### 中文筆記

無速率限制的攻擊：攻擊者可以對任意手機號碼重複呼叫 POST /api/v1/otp/actions/send（permitAll()，不需要登入），每次觸發 MockSmsSender 的 log（若換成真實 SMS API，就是真實費用）。攻擊向量：(1) SMS bombing：每秒發送 100 個 OTP 到目標手機（mobile: "0912345678"），目標手機收到 100 條 SMS，造成騷擾；(2) 費用攻擊：若 SMS provider 按量計費，攻擊可以讓平台承擔鉅額 SMS 費用；(3) DOS：OtpRecord 每次 sendOtp 都取消舊的 + 寫入新的，高頻呼叫對 DB 造成壓力。加入 Redis rate limiting 的方式（不改 domain layer）：在 OtpApiController 或 OtpAppService 中注入 Redis，用 StringRedisTemplate：rate_limit:otp:{mobile}，INCR + EXPIRE（滑動窗口），若計數超過閾值（如 1 分鐘 3 次）拋 BusinessException(OTP_RATE_LIMIT_EXCEEDED) → HTTP 429；完全在 application/infrastructure layer 處理，domain 的 OtpRecord 不知道有 rate limit 的存在。

### Category

Security and Production Risks

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- OtpAppService.sendOtp()

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

OTP rate limiting is a critical real-world banking security requirement; tests whether the candidate can design a solution that respects architecture layers.

### Possible follow-up questions

- What is the difference between a fixed window and a sliding window rate limiter?
- How would you implement rate limiting with INCR + EXPIRE in Redis?
- Where in the architecture (controller, service, or infrastructure) would you place the rate limiting check?

### Verification status

- Verified


---

<a id="Q206"></a>

## Q206 — Passwords in CreateUserRequest are validated only with @Size(min = 8). What password policy improvements would be needed for production?

### 中文筆記

目前的驗證：@Size(min = 8) 只確保密碼長度至少 8 字元，允許 "12345678" 或 "aaaaaaaa" 等 trivially weak passwords。生產環境需要的改進：(1) 複雜度規則：至少 1 個大寫字母、1 個小寫字母、1 個數字、1 個特殊字元（如 @$!%*?&）；(2) 最大長度限制（如 128 字元）防止長密碼 BCrypt 耗時攻擊（BCrypt 對超長密碼的 cost 更高）；(3) 常用密碼黑名單（如 admin, password, 12345678 等）；(4) 與 username 不同（不能用 username 作為密碼）；(5) 密碼輪換政策（強制 90 天更換）。實作選項：Bean Validation 自訂 @PasswordStrength annotation + ConstraintValidator；或 OWASP Passay library（提供 LengthRule、CharacterRule、UsernameRule 等）。@Size 是 MVP，文件（20-maintenance-and-future-enhancement.md §3.1）明確列出「configurable password policy」為 future enhancement。

### Category

Security and Production Risks

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md
- sp2-springboot/src/main/java/com/tlbank/lending/application/user/service/UserAppService.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Password policy is a basic security requirement that portfolios often overlook; tests security maturity.

### Possible follow-up questions

- What is OWASP Passay and how would you integrate it with Spring's @Valid mechanism?
- At what layer should password complexity validation live — controller, application service, or domain?
- How would you implement a password history check to prevent reuse?

### Verification status

- Documentation-only


---

<a id="Q207"></a>

## Q207 — The Swagger UI is permitAll() with no authentication. What information could an attacker extract from it, and how would you restrict access in production?

### 中文筆記

Swagger UI 洩漏的資訊：/v3/api-docs/** 提供完整的 OpenAPI JSON spec，包含：所有 endpoint 路徑（/api/v1/admin/users、/api/v1/review/cases 等）；request/response schema（DTO 欄位名稱、型別、validation 規則）；ErrorCode enum 值（可用來構建更精確的攻擊）；API examples（@Schema(example = "A123456789")）。攻擊者可以用這些資訊：發現未知的 admin endpoint（reconnaissance）；了解 request 格式構建精確的 fuzzing payload；發現 API 版本（/api/v1）→ 嘗試 /api/v2 猜測未文件化的 endpoint。生產環境的限制方式：(1) springdoc.api-docs.enabled=false + springdoc.swagger-ui.enabled=false 在 application-prod.yml；(2) 或加 IP 白名單（只允許辦公室 IP 存取 Swagger）；(3) 或在 SecurityConfig 將 Swagger 路徑改為 authenticated() 甚至 hasRole("ADMIN")。目前 SecurityConfig 有 dev profile 的 isDevProfile() 條件，可以擴展此邏輯到 Swagger。

### Category

Security and Production Risks

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/config/SwaggerConfig.java

### Verified classes and methods

- SecurityConfig.securityFilterChain()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Swagger security in production is a common oversight; tests whether the candidate understands information disclosure risks.

### Possible follow-up questions

- What is the difference between disabling Swagger at the springdoc level vs restricting it at the security layer?
- If Swagger is behind authentication, how would external API consumers discover the API contract?
- What is the OpenAPI security scheme and how would you document the session-based auth in Swagger?

### Verification status

- Verified


---

<a id="Q208"></a>

## Q208 — LocalDocumentStorageService uses Files.copy() with StandardCopyOption.REPLACE_EXISTING. What security concern does REPLACE_EXISTING introduce?

### 中文筆記

REPLACE_EXISTING 的安全問題：若兩個 requests 同時上傳同一 documentType + 同一時間戳（極少但可能的 race），後者會覆蓋前者。更實質的問題：若攻擊者知道另一個申請人的 applicationId 和 documentType，且他的 applicationId 對應的路徑（basePath/APP-xxx/）可以被推算，理論上可以上傳一個文件覆蓋合法申請人的文件——但前提是攻擊者需要知道完整的 applicationId（帶時間戳的業務 key），且 POST /api/v1/applications/{id}/documents 目前是 permitAll()，任何人都能 POST。這意味著一個知道 applicationId 的人可以替另一人上傳/覆蓋文件（偽造文件攻擊）。緩解方式：(1) 驗證呼叫者是否是該申請的申請人（目前沒有這個驗證，applicant flow 是匿名的）；(2) 使用 ATOMIC_MOVE + CREATE_NEW 避免 race condition；(3) 為每個 upload 生成 UUID 文件名而非 timestamp，讓 overwrite 更難發生。

### Category

Security and Production Risks

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- LocalDocumentStorageService.store()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The combination of permitAll() uploads and REPLACE_EXISTING creates a document substitution attack vector; tests composite security thinking.

### Possible follow-up questions

- How would you link document upload authorization to the application's OTP verification status?
- Would using UUIDs for filenames mitigate this risk? What would remain vulnerable?
- What is the principle of "access control at every operation" (not just at login)?

### Verification status

- Verified


---

<a id="Q209"></a>

## Q209 — Report generation and review queue search both run directly against the primary database. What performance problem could arise under load, and what is the architectural fix?

### 中文筆記

主資料庫壓力問題：ReportAppService.generateDailyStatisticsReport() 執行聚合查詢（GROUP BY status、GROUP BY product_id，native SQL），在 applications table 做 full table scan 或 index range scan；ReviewAppService.searchCases() 做分頁查詢 + 多表 join（review_cases + applications + card_products）。若這些查詢與正常的 OLTP 操作（申請建立、OTP 驗證）競爭同一個 SQL Server，長時間運行的 report query 可能 lock table（若使用 NOLOCK hint 以外的 isolation level），或只是消耗大量 CPU/I/O 讓 OLTP 變慢。架構修復選項：(1) Read replica（SQL Server Always On 或 AWS RDS read replica）：report 和 search 查詢路由到 read replica，OLTP 打 primary；Spring 的 AbstractRoutingDataSource 可以根據 @Transactional(readOnly = true) 路由；(2) CQRS：建立獨立的 read model（summary table，每次 Application status 改變就更新 summary row），report query 只查 summary；(3) 排程生成（DailyStatisticsScheduler 已有此設計意圖）：預計算結果存入 DB，admin 下載已生成的 report。

### Category

Performance and Scalability

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java
- sp2-springboot/docs/handbook/06-system-design-handbook.md

### Verified classes and methods

- ReportAppService.generateDailyStatisticsReport()
- ReviewAppService.searchCases()

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

OLTP vs OLAP workload separation is a classic database scaling question; tests whether the candidate understands read/write separation.

### Possible follow-up questions

- What is CQRS and how would it apply to the reporting use case?
- What is AbstractRoutingDataSource and how does it enable read/write separation?
- How would a pre-computed statistics table differ from the current native SQL aggregation query?

### Verification status

- Verified


---

<a id="Q210"></a>

## Q210 — Application.getWorkflowHistories() returns List<WorkflowHistory> which grows unboundedly. What performance concern does this create for long-running applications?

### 中文筆記

無界 list 的性能問題：每次狀態轉換都向 workflowHistories 添加一筆 WorkflowHistory。一個正常流程的申請有 4-5 筆 history（INIT → OTP_VERIFIED → DOCUMENT_UPLOADED → SUBMITTED → UNDER_REVIEW → APPROVED）。但若業務流程延長（如系統故障、重複嘗試），history 可以更多。主要性能問題：ApplicationRepositoryImpl.toDomain() 呼叫時，JPA 必須 load 所有的 WorkflowHistoryEntity（FetchType.LAZY，但在 toDomain() 執行時 session 存在，所有 history 都被 load）→ 每次 findById() 都帶回所有 history（N+1 問題的變體：單個 Application 帶回所有 history rows）。對於大量查詢（review queue 每頁 20 個 application），每個 application 都 load 全部 history，DB 查詢量 = 20 × history 數量。解決方案：(1) 在 ApplicationDetailResponse 只顯示最近 N 筆 history；(2) 在 list/summary view 不 load history（ApplicationSummaryResponse 不含 history）；(3) 使用 @BatchSize 或 @EntityGraph 優化 lazy load。目前 ApplicationSummaryResponse 不含 history，問題主要在 getApplication() 的 detail view。

### Category

Performance and Scalability

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java

### Verified classes and methods

- Application
- ApplicationRepositoryImpl.toDomain() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Unbounded collections in aggregates are a common DDD + JPA performance issue; tests whether the candidate thinks about data growth.

### Possible follow-up questions

- What is the N+1 problem and does ReviewAppService.searchCases() suffer from it?
- How would you use @BatchSize on workflowHistories to reduce query count?
- Should ApplicationSummaryResponse ever include workflow history?

### Verification status

- Verified


---

<a id="Q211"></a>

## Q211 — The scheduler pool size is spring.task.scheduling.pool.size: 3. What would happen if all three schedulers run at the same time and all three threads are occupied?

### 中文筆記

Thread pool saturation 的後果：Spring Scheduling 使用 ThreadPoolTaskScheduler，pool size = 3 表示最多 3 個 scheduler task 並發執行。三個 scheduler：OtpCleanupScheduler（每分鐘）、CacheRefreshScheduler（每 6 小時）、DailyStatisticsScheduler（每日 01:00）。若時間上重疊（如每 6 小時的整點 + OTP cleanup 每分鐘）：pool size = 3 理論上夠用，因為 DailyStatisticsScheduler 和 CacheRefreshScheduler 不可能同時在 pool 中（daily 01:00 和 6-hourly 不會剛好 overlap），只有 OtpCleanupScheduler 每分鐘觸發。但若 OtpCleanupScheduler 某次執行特別慢（DB timeout，嘗試持有 retry 達 5 分鐘），此時 CacheRefreshScheduler 觸發，pool 中有 1 個長跑 + 1 個新啟動，pool 有容量；若還有 DailyStatisticsScheduler 觸發，pool 佔 3 個，下一個 OtpCleanupScheduler trigger 被 queue 等待。問題：若某個 scheduler 長時間不釋放 thread，後續觸發會 queue 但不執行，但不會拋 exception，日誌中只會看到 schedule 延遲。Pool size 3 對目前 3 個 job 是合理的。

### Category

Performance and Scalability

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/resources/application.yml
- sp2-springboot/docs/design/13-scheduler-design.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Thread pool sizing for schedulers is a concurrency question with real production implications; tests understanding of scheduler thread management.

### Possible follow-up questions

- What happens to a @Scheduled trigger that fires while the previous execution is still running?
- How would you detect if a scheduler is being blocked by a long-running predecessor?
- When would you increase pool.size to 5?

### Verification status

- Documentation-only


---

<a id="Q212"></a>

## Q212 — CachedCardProductRepository caches the entire product list under card_products:all. What happens to memory when the card product catalog grows to thousands of entries?

### 中文筆記

記憶體問題分析：card_products:all 緩存整個 List<CardProduct>，包含所有 enabled 的卡片產品及其 List<ProductFeature>（假設每個 CardProduct 帶 product features）。目前 portfolio 場景：幾個 test card products，記憶體佔用可忽略。若產品數量成長到 1000+ 個（企業銀行有多種卡片產品線）：每個 CardProduct + ProductFeature 集合可能佔 2-10 KB，1000 個 = 2-10 MB in InMemoryCacheStore。CacheRefreshScheduler 每 6 小時 evict + lazy repopulate，期間記憶體使用峰值 = 舊 list + 新 list（gc 還沒回收舊的）。更好的設計：分頁查詢（不緩存全部）、或只緩存 enabled 的 top N 個產品、或按 productType 分組緩存。card_product:{productId} 的個別 product 緩存（CacheKeys.cardProductKey(productId)）更適合大型 catalog：只緩存被查詢過的 product（hot entries），不預加載全部。

### Category

Performance and Scalability

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CacheKeys.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java

### Verified classes and methods

- CachedCardProductRepository
- CacheKeys

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Cache memory sizing with growing data is a practical scalability concern; tests whether the candidate thinks about cache growth scenarios.

### Possible follow-up questions

- How would you limit the maximum number of entries in InMemoryCacheStore?
- When should you switch from "cache everything" to "cache on demand"?
- What is a WeakReference cache, and would it help here?

### Verification status

- Verified


---

<a id="Q213"></a>

## Q213 — ApplicationFlowIntegrationTest uses @SpringBootTest which starts the full Spring context. How long does this test suite take, and what would you do to make it faster?

### 中文筆記

Spring context 啟動成本：@SpringBootTest 每次需要啟動完整 Spring context（所有 beans、security filter chain、JPA、Flyway migration 執行）。第一個 @SpringBootTest class 啟動耗時可能 10-30 秒。Spring Test 的 context caching 機制：相同 @SpringBootTest configuration（相同 properties、profile、@Import）的多個 test class 共享同一個 ApplicationContext，不重複啟動。目前有多個 @SpringBootTest @ActiveProfiles("dev") test class（ApplicationFlowIntegrationTest、ReviewFlowIntegrationTest、SecurityIntegrationTest、ApplicationIdempotencyIntegrationTest、ReviewApiControllerTest、ApplicationWebControllerTest）——若它們的 context configuration 完全相同，共享一個 context（只啟動一次）。加速的方式：(1) @DirtiesContext 會強制重建 context（要避免，除非必要）；(2) 將 domain/application unit tests 改為 @ExtendWith(MockitoExtension.class)（已做，速度最快）；(3) 讓 @SpringBootTest test class 的 context configuration 完全一致（相同 @ActiveProfiles），確保 context caching 生效；(4) 若加入 @Sql 或 @TestConfiguration，注意 context cache key 的影響。

### Category

Performance and Scalability

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java
- sp2-springboot/docs/design/16-testing-strategy.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Test suite performance is a CI efficiency concern; tests understanding of Spring Test context caching.

### Possible follow-up questions

- How does Spring Test determine whether two test classes can share a ApplicationContext?
- What is @DirtiesContext and when would you use it despite the performance cost?
- What is the test pyramid, and how does TLBank's test suite reflect it?

### Verification status

- Documentation-only


---

<a id="Q214"></a>

## Q214 — ReviewAppService.toSummary() calls findApplicationOrThrow() for each ReviewCase in a page result. What N+1 problem does this create?

### 中文筆記

N+1 問題的存在：searchCases() 執行分頁查詢返回 Page<ReviewCase>（假設 page size = 20，執行 1 個 query 取 20 個 ReviewCase）。content.stream().map(this::toSummary).toList() 對每個 ReviewCase 呼叫 toSummary(reviewCase)，toSummary() 呼叫 findApplicationOrThrow(reviewCase.getApplicationId()) → applicationRepository.findById(applicationId) → 1 個 DB query。20 個 ReviewCase → 20 個 findById query → 共 21 次 DB 查詢（1 個分頁 + 20 個 by-id），這是 classic N+1。額外問題：toSummary() 還呼叫 cardProductRepository.findById(application.getCardProductId())，但 CachedCardProductRepository 緩存了產品，所以這個查詢是 cache hit，不打 DB。修復 N+1：在 ReviewCaseRepository.search() 的 JPA query 中用 JOIN FETCH applications ON review_cases.application_no = applications.application_no，一個 query 取回所有需要的資料；或在 ReviewCaseRepositoryImpl 批量 findAllById(applicationIds) 取所有 application。

### Category

Performance and Scalability

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java

### Verified classes and methods

- ReviewAppService.searchCases()
- ReviewAppService.toSummary() (private)

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

N+1 queries in list/page operations is one of the most common JPA performance issues; tests whether the candidate identifies it.

### Possible follow-up questions

- How would you rewrite searchCases() to avoid N+1?
- What is a JOIN FETCH in JPQL and when does it cause a Cartesian product problem?
- Why does the cardProductRepository.findById() call in toSummary() not cause N+1?

### Verification status

- Verified


---

<a id="Q215"></a>

## Q215 — InMemoryCacheStore stores cached values in the JVM heap. What happens to cache size and application memory during a long period without restarts?

### 中文筆記

長時間運行的記憶體行為：InMemoryCacheStore 使用 ConcurrentHashMap<String, CacheEntry<?>> 儲存 cache。清除機制：(1) 讀取時 lazy eviction（get() 發現 expired 就移除）；(2) @Scheduled(fixedDelay = 60_000) 每分鐘掃描移除 expired entries。若 key 空間有限（system parameters 數量固定、card products 數量有限），map 大小有上界，長時間運行不會無限成長。但若 CacheStore 被誤用（如有人把每個 OTP 也放進 cache，key 包含 mobile number），key 空間可能是 unbounded，TTL 到期才被 lazy eviction 或 cleanup 清除。最惡情況：high traffic，大量短 TTL entries 被快速寫入，cleanup job 每 60 秒才跑，在這 60 秒內 map 可能持有數千個 expired-but-not-yet-cleaned entries，增加 GC pressure。目前 cache 只儲存 system_parameters 和 card_products（有限的 key 空間），不存在此問題。若未來擴展 cache 用途需要注意。

### Category

Performance and Scalability

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java

### Verified classes and methods

- InMemoryCacheStore.cleanupExpiredEntries()
- InMemoryCacheStore.put()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Memory management in long-running caches is a production operations question; tests whether the candidate understands JVM heap growth risks.

### Possible follow-up questions

- How would you add a maximum entry count (maxSize) to InMemoryCacheStore?
- What is the Guava Cache / Caffeine LoadingCache and how would it replace InMemoryCacheStore?
- What JVM flag would you add to monitor heap usage over time?

### Verification status

- Verified


---

<a id="Q216"></a>

## Q216 — LocalDocumentStorageService stores files to local disk with no disk quota enforcement. What production risk does this introduce?

### 中文筆記

磁碟空間風險：每次上傳文件，Files.copy() 寫入 {basePath}/{applicationId}/{documentType}_{timestamp}.{ext}，無上限。風險：(1) 磁碟空間耗盡：多個申請人各上傳多份文件，加上 app-logs volume 的 log 累積，Docker named volume 可能填滿 host 磁碟，導致 Files.copy() 拋 IOException，應用程式無法接受新上傳；(2) LocalDocumentStorageService.validate() 只驗證單個文件大小（max.size.mb），不驗證整個 applicationId 目錄的總大小，也不驗證整個 uploads 目錄的剩餘空間；(3) 若系統崩潰或 Docker volume 損壞，文件沒有備份。緩解措施：(1) 驗證 Files.getFileStore(resolvedBasePath).getUsableSpace() 在寫入前確保有足夠空間；(2) 設定 per-application 的文件數量上限（如最多上傳 5 份文件）；(3) 替換為 S3（無限容量，自動備份，按使用付費）。

### Category

Performance and Scalability

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java

### Verified classes and methods

- LocalDocumentStorageService.store()
- LocalDocumentStorageService.validate()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Disk space management in file upload services is a production operations gap; tests production awareness beyond the happy path.

### Possible follow-up questions

- How would you implement a per-application file count limit in the domain layer?
- What is the Java API for checking available disk space before writing?
- How would S3 change the operational risk profile for document storage?

### Verification status

- Verified


---

<a id="Q217"></a>

## Q217 — Logback is configured with profile-specific appenders: console-only for dev, file + error-file for staging/prod. Why is this separation important?

### 中文筆記

Profile-specific logging 設計的理由：Dev（console only）：開發時直接看 terminal 輸出最方便，彩色高亮（%clr(...)）讓 DEBUG/INFO/WARN/ERROR 視覺上好區分，無需 log file；log 不需要 retain，每次重啟 terminal 清空。Staging/prod（file + error file）：(1) 持久化：app.log 記錄 INFO 以上的所有 log，rolling by day（${APP_NAME}.%d{yyyy-MM-dd}.log），保留 30 天，ops 可事後查詢；(2) 獨立 error log：app-error.log 只記錄 ERROR 級別（ThresholdFilter），保留 90 天，告警系統可監控這個 smaller file；(3) 無 %clr ANSI escape code：生產 log 避免 ANSI color code 污染（log aggregator 如 ELK 可能顯示 \e[32m 等字元）；(4) 結構化 log：FILE_PATTERN 包含 [%X{requestId}] 和 [%X{username}]（MDC），便於按 request correlation ID 過濾。<springProfile name="staging | prod"> 讓一個 logback-spring.xml 覆蓋所有環境，不需要多份 logback 文件。

### Category

Observability and Logging

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/logback-spring.xml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Request hits filter chain; MdcLoggingFilter populates MDC
2. Controllers/services log with correlation fields
3. Filter clears MDC after request completes

### Why this may be asked

Profile-based logging configuration is a standard production practice; tests whether the candidate understands operational logging requirements.

### Possible follow-up questions

- What is a ThresholdFilter in Logback and how does it work?
- Why is maxHistory: 30 for general logs but maxHistory: 90 for error logs?
- What is structured logging (JSON format), and how would you add it alongside the current pattern-based format?

### Verification status

- Documentation-only


---

<a id="Q218"></a>

## Q218 — MDC is populated with requestId and username by MdcLoggingFilter. How would you trace a single request across multiple log lines in a log file?

### 中文筆記

MDC 的 tracing 機制：requestId（8-char UUID prefix）是 per-request 唯一 ID，所有在該 request lifecycle 中產生的 log（controller、service、domain、infrastructure）都帶有相同的 requestId。username 讓每條 log 都知道是哪個 user 操作的。實際 tracing 步驟：在 log file 中用 grep：grep "\[abc12345\]" app.log 取出所有 requestId = abc12345 的 log lines，即可看到一個 request 從 controller → service → repository → response 的完整執行路徑。在 staging 環境 Docker named volume app-logs：docker exec tlbank-app cat /app/logs/tlbank-lending.log | grep "abc12345" 或 docker cp tlbank-app:/app/logs/tlbank-lending.log . 後在本機 grep。ELK stack 整合：若 log aggregator（Logstash）解析 log 的 [requestId] pattern，可以在 Kibana 的 Discover 中 requestId: abc12345 快速找到所有相關 log。限制：@Async 方法（AuditLogWriter.saveAsync()）在不同 thread 執行，MDC 不自動傳遞，async thread 中的 log 沒有 requestId，需要手動傳遞 MDC（MDC.put 在 async thread 開始時）。

### Category

Observability and Logging

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java
- sp2-springboot/src/main/resources/logback-spring.xml

### Verified classes and methods

- MdcLoggingFilter.doFilterInternal()

### Execution flow

1. Request hits filter chain; MdcLoggingFilter populates MDC
2. Controllers/services log with correlation fields
3. Filter clears MDC after request completes

### Why this may be asked

Request correlation in logs is a core observability pattern; tests practical debugging knowledge.

### Possible follow-up questions

- Why doesn't AuditLogWriter.saveAsync() have the same requestId in its logs?
- How would you propagate MDC from the main thread to an @Async thread?
- What is the difference between MDC (Mapped Diagnostic Context) and NDC (Nested Diagnostic Context) in SLF4J?

### Verification status

- Verified


---

<a id="Q219"></a>

## Q219 — Spring Actuator is configured with a healthcheck endpoint. What would you add to the Actuator configuration to make it more useful for production monitoring?

### 中文筆記

目前 Actuator 配置：/actuator/health 返回 {"status": "UP"}，主要用於 Docker Compose healthcheck 和 liveness probe。生產監控需要增加：(1) management.endpoints.web.exposure.include: health,info,metrics：暴露 /actuator/metrics（JVM 記憶體、thread count、HTTP request count/latency），讓 Prometheus 或 CloudWatch 可以 scrape；(2) management.endpoint.health.show-details: when_authorized：對 ADMIN 角色顯示 DB 連線 health、disk space（DiskSpaceHealthIndicator）、Redis ping（RedisHealthIndicator）；(3) 自訂 health indicator：CacheHealthIndicator 顯示 cache entry count；(4) info endpoint：management.info.env.enabled: true + info.app.version: @project.version@（從 Maven pom 注入版本號）讓 ops 知道 staging 跑的是哪個 build；(5) Micrometer + Prometheus：在 pom.xml 加 micrometer-registry-prometheus，/actuator/prometheus 暴露 metrics scrape endpoint。目前 Actuator 只在 Docker Compose healthcheck 中用到，未充分利用。

### Category

Observability and Logging

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/application.yml
- sp2-springboot/docker-compose.yml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Request hits filter chain; MdcLoggingFilter populates MDC
2. Controllers/services log with correlation fields
3. Filter clears MDC after request completes

### Why this may be asked

Actuator configuration for production monitoring is a practical DevOps question; tests awareness of the Actuator's capabilities beyond basic health.

### Possible follow-up questions

- What is Micrometer and how does it relate to Spring Boot Actuator?
- What is a HealthIndicator and how would you write a custom one for Redis connectivity?
- Should /actuator/env (which shows all environment variables) be exposed in production?

### Verification status

- Documentation-only


---

<a id="Q220"></a>

## Q220 — The audit log has action, result, username, ipAddress, and detail fields but no sessionId or correlationId. What observability capability does this omit?

### 中文筆記

缺少 sessionId / correlationId 的影響：audit_logs 現有欄位可以回答：「誰做了什麼操作（action）」「IP 是多少」「成功還是失敗」。無法回答：「這個 action 是在哪個 HTTP session 中執行的」——若用戶在一個 session 中執行了多次操作（OTP 送出 3 次 → 驗證 → 上傳 → 提交），無法從 audit log 把這些操作串連成「一個 session 的完整軌跡」；無法偵測「同一個 session 中連續失敗 5 次 OTP」（需要 session-level grouping）。缺少 requestId（MDC 的 correlation ID）：無法把 audit log entry 和 application log 的 request trace 關聯（知道 audit entry 發生在哪個 HTTP request）。改善方案：加入 session_id 欄位（從 Spring Security session 取）；或加入 request_id（從 MDC 的 requestId 取，在 AuditAspect 中 MDC.get("requestId")），讓 audit log 可以關聯到 application log 的 trace。

### Category

Observability and Logging

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLog.java
- sp2-springboot/docs/design/11-audit-logging.md

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Request hits filter chain; MdcLoggingFilter populates MDC
2. Controllers/services log with correlation fields
3. Filter clears MDC after request completes

### Why this may be asked

Audit log completeness for forensic analysis is a compliance and security question; tests whether the candidate thinks about post-incident investigation.

### Possible follow-up questions

- How would you add requestId from MDC to every audit log entry in AuditAspect?
- What regulation (e.g., SOX, PCI DSS) requires audit logs to include session identifiers?
- How long should audit logs be retained, and how would you implement automated archiving?

### Verification status

- Documentation-only


---

<a id="Q221"></a>

## Q221 — Log rotation is configured for 30 days (general) and 90 days (error). These values are hardcoded in logback-spring.xml. What is the operational problem with hardcoded retention?

### 中文筆記

Hardcoded retention 的問題：<maxHistory>30</maxHistory> 和 <maxHistory>90</maxHistory> 直接寫在 logback-spring.xml，無法透過環境變數或 Spring property 覆蓋（logback-spring.xml 可以用 <springProperty> 讀取 Spring properties，但目前 maxHistory 沒有這樣做）。問題：(1) 合規需求：某些金融機構 regulation 要求 audit log 保留 7 年，error log 保留 1 年；若要修改，需要改 code + commit + deploy；(2) 環境差異：dev 環境可能只需要 7 天，prod 需要 365 天；hardcode 讓兩個環境用相同 retention；(3) 磁碟容量：staging 的 Mac 磁碟空間有限，90 天的 error log 可能佔用大量空間。修復方式：使用 <springProperty scope="context" name="LOG_MAX_HISTORY" source="logging.file.max-history" defaultValue="30"/> 讓 maxHistory 可從 application.yml 配置；各 profile 設不同的 logging.file.max-history。

### Category

Observability and Logging

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/resources/logback-spring.xml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Request hits filter chain; MdcLoggingFilter populates MDC
2. Controllers/services log with correlation fields
3. Filter clears MDC after request completes

### Why this may be asked

Configurable log retention is an operational concern; tests whether the candidate identifies hardcoded operational parameters.

### Possible follow-up questions

- How would you use <springProperty> in Logback to read values from application.yml?
- What is totalSizeCap in Logback's TimeBasedRollingPolicy and why is it useful?
- How would you implement log archiving to S3 after 30 days while keeping the last 30 days on disk?

### Verification status

- Documentation-only


---

<a id="Q222"></a>

## Q222 — There are no application performance metrics (e.g., OTP verify latency, review queue size). What would you add first, and using what tool?

### 中文筆記

最高優先的 metrics（業務影響優先）：(1) OTP verify success/failure rate：直接衡量申請人轉換率，failure rate 高可能表示 OTP 過期設定不合理或 SMS 延遲；(2) 申請狀態分布：SUBMITTED 但 UNDER_REVIEW 的數量（review 積壓），若長期高，表示審核員不夠或 assignment 有問題；(3) HTTP request latency by endpoint（p50/p95/p99）：POST /api/v1/applications 的 p95 latency 是用戶體驗的直接指標；(4) Cache hit/miss rate：若 system_parameters 的 cache hit rate 低，說明 TTL 設定不合理或 refreshCache() 過於頻繁。工具選擇：micrometer-registry-prometheus（pom.xml 加 dependency）+ io.micrometer.core.instrument.MeterRegistry 注入到需要打點的地方。Spring Boot Actuator 自動整合 Micrometer，/actuator/prometheus 暴露 Prometheus scrape endpoint；Prometheus + Grafana 完整監控 stack。最簡單的起步：Counter（OTP verify 成功/失敗計數）+ Timer（HTTP request duration，Spring Boot Actuator 自動加 http.server.requests timer）。

### Category

Observability and Logging

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/pom.xml
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Metrics prioritization for a business application reveals whether the candidate thinks about business impact, not just technical metrics.

### Possible follow-up questions

- What is the difference between Micrometer's Counter, Gauge, Timer, and DistributionSummary?
- How would you add a custom Gauge for the current size of the review queue?
- What is Prometheus pull model vs push model?

### Verification status

- Documentation-only


---

<a id="Q223"></a>

## Q223 — ApplicationCancelledEvent and OtpGeneratedEvent are defined in the domain but never published. What is the impact of having unused code, and how should this technical debt be resolved?

### 中文筆記

未使用 domain event 的影響：(1) 誤導讀者：看到 ApplicationCancelledEvent 以為 cancel 操作會觸發 event（通知相關方），實際上 ApplicationAppService.cancelApplication() 只改 Application 狀態，沒有 eventPublisher.publishEvent(new ApplicationCancelledEvent(...))；(2) Test coverage gap：ApplicationCancelledEvent 沒有 producer，測試無法驗證 cancel 的 downstream effects（應該通知申請人申請已取消，但通知沒有觸發）；(3) Technical debt 累積：未使用的 class 讓 codebase 產生 cognitive overhead，後人需要額外確認「這個 class 是有計劃實作還是已廢棄」。修復方式（ADR 建議）：(1) Wire cancel event：在 ApplicationAppService.cancelApplication() 加入 eventPublisher.publishEvent(new ApplicationCancelledEvent(...))，NotificationEventHandler 新增 onApplicationCancelled() 發送取消通知；(2) OtpGeneratedEvent：決定是否需要 event（OTP 的 notification 目前由 OtpAppService 直接呼叫 notificationService.sendOtpNotification()，不走 event），若不需要就刪除，並在 ADR 中記錄決策。

### Category

Technical Debt and Known Limitations

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationCancelledEvent.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/OtpGeneratedEvent.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Unused code in a designed system is a documentation of intent vs. reality gap; tests honest self-assessment.

### Possible follow-up questions

- Would you delete unused events or keep them? Under what condition would you keep them?
- How would you write a test to verify that ApplicationCancelledEvent is published when an application is cancelled?
- What is "dead code" vs "code written for future use" and how do you distinguish them in a PR review?

### Verification status

- Documentation-only


---

<a id="Q224"></a>

## Q224 — No Testcontainers-based test path exercises real SQL Server (Known Limitation #12). What bugs could this let through to staging?

### 中文筆記

H2-only testing 的漏洞：目前所有 @SpringBootTest 測試對 H2（MODE=MSSQLServer），不對真正的 SQL Server 2022。可能讓 bugs 滑過的情況：(1) Native query dialect 差異：ApplicationJpaRepository.countByStatusAndCreatedAtBetween() 的 native SQL 在 H2 通過但在 SQL Server 可能因 DATETIME2 vs TIMESTAMP 的函數差異、TOP vs LIMIT 語法失敗；(2) IDENTITY vs GENERATED BY DEFAULT AS IDENTITY：H2 migration 用後者，SQL Server 用前者，若有 migration 語法不一致（如 V14 的 DROP TABLE IF EXISTS），在 SQL Server 可能行為不同；(3) 大小寫敏感性：SQL Server 的 collation 預設 case-insensitive（CI），H2 預設 case-sensitive，findByUsername("Admin") 在 H2 找不到 "admin"，但在 SQL Server（CI collation）找得到；(4) NOLOCK hint：若 SQL Server query 需要 WITH (NOLOCK)，H2 不支援該語法。Testcontainers 修復：在 pom.xml 加 testcontainers-mssql，建立 @Tag("integration-sqlserver") test set，CI 有獨立 job 用 SQL Server container 執行。

### Category

Technical Debt and Known Limitations

### Difficulty

Advanced

### Verified source files

- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md
- sp2-springboot/docs/design/16-testing-strategy.md
- sp2-springboot/pom.xml

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

The H2-only testing gap is a critical technical debt with real production risk; tests whether the candidate can articulate specific failure scenarios.

### Possible follow-up questions

- What is Testcontainers and how would you add a SQL Server test container?
- How would you structure the CI pipeline to run H2 tests fast and SQL Server tests in a separate slower job?
- What is @DataJpaTest with @AutoConfigureTestDatabase(replace = NONE) and when would you use it?

### Verification status

- Documentation-only


---

<a id="Q225"></a>

## Q225 — The WorkflowDomainService.validateTransition() method simply delegates to ApplicationStatus.canTransitionTo(). Is this domain service necessary?

### 中文筆記

是否有存在必要的分析：WorkflowDomainService.validateTransition(from, to) 的實作只有一行：if (!from.canTransitionTo(to)) { throw new WorkflowException(...) }，完全等同於直接呼叫 from.canTransitionTo(to) 後手動拋 exception。問題：(1) 過度設計：domain service 通常封裝跨 aggregate 或跨 value object 的業務邏輯，但這個方法的邏輯已經在 ApplicationStatus enum 的 canTransitionTo() 中，re-wrap 一層不增加任何業務價值；(2) Spring leak：@Service 在 WorkflowDomainService 讓 domain 依賴 Spring（ADR 0001 承認的 impurity），而這個 service 的實際用途很小；(3) 呼叫者（Application.transitionTo()）通常直接呼叫 status.canTransitionTo(next) 而非透過 service。可能的保留理由：若未來有跨 aggregate 的 transition 驗證（如「申請人只能有一個 ACTIVE 申請」），這個 service 是放置此類邏輯的正確位置。目前的薄封裝是 premature abstraction 的例子，但保留它不增加維護成本，且 ADR 0002 提到是「domain service 是 DDD-lite 的一部分」。

### Category

Technical Debt and Known Limitations

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md

### Verified classes and methods

- WorkflowDomainService.validateTransition()
- ApplicationStatus.canTransitionTo()

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Recognizing over-engineering in a codebase is a senior engineering skill; tests critical code assessment ability.

### Possible follow-up questions

- What business logic would justify the existence of WorkflowDomainService beyond a simple delegation?
- What is the "rule of three" in refactoring and how would it apply to removing this service?
- If you kept WorkflowDomainService, how would you make it useful now?

### Verification status

- Verified


---

<a id="Q226"></a>

## Q226 — ExcelReportGenerator and PdfReportGenerator duplicate percentage-formatting logic. Write a concrete example of how you would extract and test this shared utility.

### 中文筆記

具體重構方案：建立 ReportFormatterUtil（@UtilityClass）：public static String formatPercentage(long count, long total)，邏輯是：若 total == 0 return "0.0%"（避免 division by zero）；否則 String.format("%.1f%%", (double) count / total * 100)（保留 1 位小數）。ExcelReportGenerator 和 PdfReportGenerator 都改為呼叫 ReportFormatterUtil.formatPercentage(count, total) 而非各自計算。測試：ReportFormatterUtilTest（純 JUnit 5，無 Spring context）：formatPercentage(0, 0) → "0.0%"；formatPercentage(10, 100) → "10.0%"；formatPercentage(1, 3) → "33.3%"；formatPercentage(100, 100) → "100.0%"。這個重構的優點：(1) bug fix 一處就修好兩個 generator；(2) ExcelReportGeneratorTest 和 PdfReportGenerator 的測試不再重複驗證 percentage 格式（ReportFormatterUtilTest 負責），符合 DRY 原則。

### Category

Technical Debt and Known Limitations

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/PdfReportGenerator.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

This is a live coding/refactoring question about a specific known debt; tests the candidate's ability to propose concrete solutions.

### Possible follow-up questions

- Where in the package structure would ReportFormatterUtil live?
- Would you also extract the duplicate table header generation into a shared method?
- When would you use Lombok's @UtilityClass vs a static inner class for this utility?

### Verification status

- Documentation-only


---

<a id="Q227"></a>

## Q227 — ReviewEventHandler.onApplicationSubmitted() runs within the same transaction as the triggering submitApplication() call (Known Limitation #8). Show the exact call chain from ApplicationApiController.submit() to the database.

### 中文筆記

完整 call chain：(1) ApplicationApiController.submitApplication(applicationId) → (2) ApplicationAppService.submitApplication(applicationId) (@Transactional → transaction T1 開始）→ (3) application.submit("APPLICANT") (aggregate state change) → (4) applicationRepository.save(application) (INSERT/UPDATE SQL in T1) → (5) eventPublisher.publishEvent(new ApplicationSubmittedEvent(...))（Spring 同步 event，在 T1 thread 執行）→ (6) ReviewEventHandler.onApplicationSubmitted(event) (@EventListener，預設 PROPAGATION_REQUIRED，繼承 T1） → (7) ReviewCase reviewCase = ReviewCase.createFor(applicationId) → (8) reviewCaseRepository.save(reviewCase) (INSERT SQL in T1) → (9) NotificationEventHandler.onApplicationSubmitted(event) (@EventListener，也在 T1）→ (10) notificationService.sendApplicationSubmittedNotification(...) → (11) MockSmsSender.send() (log only)。若 step 8 失敗（DataIntegrityViolationException）：T1 整體 rollback，application status 也沒有被更新，submit() 對用戶的 API response 是 500 error。若 step 11 拋 exception 且 NotificationEventHandler 沒有 try-catch：T1 rollback（但實際 NotificationEventHandler 有 try-catch，所以安全）。

### Category

Technical Debt and Known Limitations

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- ApplicationAppService.submitApplication()
- ReviewEventHandler.onApplicationSubmitted()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Tracing a transaction boundary through an event handler is a senior Java/Spring question that tests deep understanding of Spring transaction propagation.

### Possible follow-up questions

- What is @TransactionalEventListener(phase = AFTER_COMMIT) and how does it change this flow?
- If reviewCaseRepository.save() fails inside ReviewEventHandler, what state is the application in?
- How does the Outbox Pattern decouple the review case creation from the submission transaction?

### Verification status

- Verified


---

<a id="Q228"></a>

## Q228 — InMemoryCacheStore is noted as a scalability limitation (Known Limitation #11). Write the interface contract for a RedisCacheStore that could replace it without any change to SystemParameterService.

### 中文筆記

RedisCacheStore 的 interface contract：必須實作 CacheStore 的所有方法：<V> Optional<V> get(String key)：stringRedisTemplate.opsForValue().get(PREFIX + key)，反序列化 JSON → Optional；<V> void put(String key, V value, long ttlSeconds)：stringRedisTemplate.opsForValue().set(PREFIX + key, serialize(value), Duration.ofSeconds(ttlSeconds))；void evict(String key)：stringRedisTemplate.delete(PREFIX + key)；void evictAll()：Set<String> keys = stringRedisTemplate.keys(PREFIX + "*"); stringRedisTemplate.delete(keys)（注意：keys() 在 production Redis 是危險的 O(N) 操作，應改用 SCAN）；Set<String> keys()：用 SCAN 迭代所有 key。Spring bean 切換：在 CacheConfig 中加 @ConditionalOnProperty(name = "tlbank.cache.store", havingValue = "redis") 在 RedisCacheStore 上，@ConditionalOnProperty(... matchIfMissing = true) 在 InMemoryCacheStore 上。SystemParameterService 不改任何一行——這正是 Ports and Adapters 設計的收益。（校正：現況 Redis 僅用於冪等存放；快取為 InMemoryCacheStore，Session 在 JVM 記憶體。）

### Category

Technical Debt and Known Limitations

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CacheStore.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Writing a new adapter for an existing port interface is the core Hexagonal Architecture skill; this is a live design/code question.

### Possible follow-up questions

- Why is Redis KEYS pattern dangerous in production and what should you use instead?
- How would you handle serialization of generic <V> in RedisCacheStore?
- What @Conditional mechanism would you use to switch between InMemoryCacheStore and RedisCacheStore?

### Verification status

- Documentation-only


---

<a id="Q229"></a>

## Q229 — The project has no circuit breaker for external calls. In the current codebase, which calls would benefit most from a circuit breaker pattern?

### 中文筆記

需要 circuit breaker 的呼叫（按優先順序）：(1) Redis 呼叫（RedisIdempotencyStore）：Redis 不可用時，IdempotencyService.tryAcquireLock() 阻塞或拋 exception，影響所有創建申請的 request。目前的降級：idempotency key 是 optional，若 Redis 完全不可用可以 fallback 到無 idempotency（直接執行 action），但目前沒有實作此 fallback；(2) 真實 SMS/Email sender（生產環境）：若 Twilio 或 SMTP server 不可用，NotificationServiceImpl 的 try-catch 已有部分防護，但 TCP timeout 期間 request thread 被 block；(3) 真實 KYC/信評 API（未來）：若加入外部信評服務，明確需要 circuit breaker；(4) SQL Server 連線（若使用 connection pool）：HikariCP 自身有 connection timeout，但沒有 circuit breaker 語意。工具選擇：Resilience4j（Spring Boot starter 可用）：@CircuitBreaker(name = "redis", fallbackMethod = "fallbackCreateWithoutIdempotency")。目前 mock notification 不需要，但真實 SMS provider 必需。

### Category

Technical Debt and Known Limitations

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/notification/service/NotificationServiceImpl.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Circuit breakers are a resilience pattern for distributed systems; tests production readiness thinking.

### Possible follow-up questions

- What is the difference between a circuit breaker and a retry mechanism?
- What Resilience4j annotation would you add to RedisIdempotencyStore.tryAcquireLock()?
- What is the HALF_OPEN state in a circuit breaker?

### Verification status

- Documentation-only


---

<a id="Q230"></a>

## Q230 — If you had to estimate the total time to make this system production-ready (excluding cloud infrastructure cost), what would be the top 5 gaps and rough effort estimates?

### 中文筆記

Top 5 gaps（按 effort 估計）：(1) Real external integrations（SMS、Email）— 2-3 days：替換 MockSmsSender/MockEmailSender 為 Twilio/SendGrid，需要設定 credentials、測試 sandbox、處理 webhook 回調；(2) OTP rate limiting 與 applicant endpoint 授權 — 3-5 days：Redis rate limiter（per-mobile）、applicant identity binding（OTP verified → session token for self-service query）；(3) Session externalization（Spring Session + Redis）— 1-2 days：pom.xml 加 spring-session-data-redis，application.yml 設 spring.session.*，測試 maximumSessions(1) 在多 pod 環境；(4) Testcontainers SQL Server 測試 — 3-5 days：加 testcontainers-mssql，驗證所有 native SQL 在真實 SQL Server 通過，修復發現的 dialect 差異；(5) Password policy + Swagger lockdown + audit log completeness — 2-3 days：OWASP Passay、Swagger prod disable、audit log 加 requestId。總估計：10-18 working days，不計雲端基礎設施架設。生產就緒的另一個維度：security review、penetration testing、DR plan，這些不是 code change，需要獨立時間。

### Category

Technical Debt and Known Limitations

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Production readiness estimation tests engineering maturity and honest self-assessment; the best candidates quantify gaps with effort estimates.

### Possible follow-up questions

- Which of the 5 gaps is a blocker for first production traffic?
- How would you prioritize these if you had only 2 weeks?
- What would you NOT implement in the first production release, and why?

### Verification status

- Documentation-only


---

<a id="Q231"></a>

## Q231 — The system design handbook identifies "split read models for reports" as a scalability step. What does a read model look like for the daily statistics report?

### 中文筆記

Daily statistics read model 的設計：目前：DailyStatisticsScheduler 每日 01:00 執行聚合 query（GROUP BY status、GROUP BY product_id），結果只 log 不儲存。Read model 方案：建立 daily_statistics table：report_date DATE、status VARCHAR(30)、count BIGINT、PRIMARY KEY (report_date, status)；daily_product_stats table：report_date DATE、product_id BIGINT、count BIGINT。更新機制：DailyStatisticsScheduler 在每日 01:00 計算並 UPSERT 這些表（MERGE INTO daily_statistics...），而非只 log；ReportAppService.generateDailyStatisticsReport() 改為 query daily_statistics 而非 applications table 的聚合，read 極快（幾行 by primary key），不再是 full table scan。好處：report endpoint 的 latency 從「聚合查詢 N 秒」降為「index lookup 毫秒」；不再競爭 OLTP table；report data 不受 OLTP transaction isolation level 影響（昨天的數據是固定的）。這就是 CQRS 的 write model（applications table）→ read model（daily_statistics table）分離。

### Category

System Design Evolution

### Difficulty

Advanced

### Verified source files

- sp2-springboot/docs/handbook/06-system-design-handbook.md
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/DailyStatisticsScheduler.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java

### Verified classes and methods

- DailyStatisticsScheduler.generateDailyStatistics()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Read model design is a CQRS concept question applied to a concrete use case; tests system design thinking.

### Possible follow-up questions

- What happens if DailyStatisticsScheduler fails — how do you ensure the read model is consistent?
- How would you handle timezone in the report_date column?
- What is eventual consistency and does the read model approach introduce it?

### Verification status

- Verified


---

<a id="Q232"></a>

## Q232 — If the credit review team grew from 2 to 20 reviewers, what changes to ReviewCase assignment logic would be needed?

### 中文筆記

Current state：ReviewCase.assign(String username) 允許手動指定 reviewer。沒有自動分配邏輯，沒有 workload balancing，沒有 specialization routing。20 個 reviewer 的需求：(1) Workload balancing：新 case 自動分配給 pending case 數量最少的 reviewer（round-robin 或 least-loaded）；ReviewCaseRepository.countByAssignedToAndStatusIn(username, PENDING, UNDER_REVIEW) 取各 reviewer 的 load；(2) Specialization routing：某些 reviewer 負責高額度申請，某些負責特定卡種；需要在 User 或 ReviewerProfile 中記錄 specialization，分配邏輯考慮 CardProduct 類型；(3) Reassignment：reviewer 請假時需要批量 reassign（admin 操作）；(4) SLA tracking：case 在 PENDING 超過 N 小時沒被 assign，觸發 alert；需要加 created_at → assigned_at 的時間差計算。Architecture impact：auto-assignment 邏輯是 domain service（ReviewCaseAssignmentService）的好候選，封裝「哪個 reviewer 應該處理這個 case」的業務規則；或作為 ApplicationSubmittedEvent 的消費者，在 review case 建立後非同步執行 assignment。

### Category

System Design Evolution

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java
- sp2-springboot/docs/handbook/06-system-design-handbook.md

### Verified classes and methods

- ReviewCase.assign()

### Execution flow

1. Reviewer calls review API (start/approve/reject/search)
2. ReviewAppService orchestrates ReviewCase and Application status changes
3. Domain events may trigger NotificationEventHandler (mock SMS/email)

### Why this may be asked

Scaling a manual process to an automated one is a classic system design growth question.

### Possible follow-up questions

- Where would you implement auto-assignment logic — in the domain service, application service, or infrastructure?
- How would you handle the case where all 20 reviewers are at capacity?
- What event would trigger auto-assignment in an event-driven design?

### Verification status

- Verified


---

<a id="Q233"></a>

## Q233 — The system design handbook says "extract services only when team or SLO boundaries demand it." What would be the first microservice to extract from TLBank, and how?

### 中文筆記

最先提取的 microservice：最佳候選是 Notification Service，理由：(1) 它已是自然接縫（NotificationEventHandler 是 event consumer，domain 不知道通知存在）；(2) 通知有獨立的 SLO（SMS 送達率、延遲）和獨立的技術棧（Twilio SDK）；(3) 通知的 failure 不應影響申請核心業務（已有 try-catch 隔離），提取後隔離更清晰；(4) 通知是 read-heavy（delivery status）+ write-once（send notification），可以用輕量 DB（NoSQL 或簡單 SQL）。提取步驟：(1) 在現有 monolith 中定義 NotificationCommand message schema（JSON）；(2) 在 NotificationEventHandler 中改為向 Kafka topic notification-commands 發 message；(3) 建立獨立的 Notification Service（Spring Boot），subscribe notification-commands topic，呼叫 Twilio/SendGrid；(4) 雙跑期間（strangler fig）：同時有 monolith 的 mock notification 和新 service，逐步切換；(5) 刪除 monolith 中的 NotificationServiceImpl 和 MockSmsSender。

### Category

System Design Evolution

### Difficulty

Advanced

### Verified source files

- sp2-springboot/docs/handbook/06-system-design-handbook.md
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/notification/service/NotificationServiceImpl.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Microservice extraction strategy is a senior system design question; tests understanding of strangler fig pattern and event-driven decoupling.

### Possible follow-up questions

- What is the Strangler Fig pattern and how does it apply here?
- How would you handle message ordering for notifications (e.g., "submitted" must arrive before "approved")?
- What would change in the domain model when transitioning to event-driven communication?

### Verification status

- Documentation-only


---

<a id="Q234"></a>

## Q234 — MdcLoggingFilter puts request correlation fields into MDC. What is logged, and what is still missing for production observability?

### 中文筆記

MdcLoggingFilter 在請求進入時寫入 MDC（如 requestId、username），供 Logback pattern 輸出。現況以單機日誌為主，維護文件指出後續才會引入 OpenTelemetry 分散式追蹤與 Micrometer 指標；不可把現況說成已具備完整 observability stack。

### Category

Observability and Logging

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java
- sp2-springboot/src/main/resources/logback-spring.xml
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- MdcLoggingFilter
- SecurityConfig.securityFilterChain()

### Execution flow

1. Request hits filter chain; MdcLoggingFilter populates MDC
2. Controllers/services log with correlation fields
3. Filter clears MDC after request completes

### Why this may be asked

Checks honest scoping of logging versus full observability platforms.

### Possible follow-up questions

- Where is the filter registered in the security chain?
- What fields appear in the console pattern?
- What would OpenTelemetry add beyond MDC?

### Verification status

- Verified


---

<a id="Q235"></a>

## Q235 — If the OTP flow needed to support TOTP (Google Authenticator) in addition to SMS OTP, how would you extend the domain model?

### 中文筆記

TOTP 擴展的 domain model 設計：目前 OtpRecord 儲存的是 SMS OTP（6 位數字，server 生成，有 expiry，有 retry count）。TOTP 的差異：secret key 儲存在 user 端（Google Authenticator app），server 只儲存共享 secret（users.totp_secret）；沒有 server 送出 code（不需要 SMS）；驗證邏輯：server 計算當前時間窗口的 TOTP code（基於 shared secret + timestamp），比對用戶輸入。設計選項：(1) Extend OtpPurpose enum 加入 TOTP_VERIFICATION，但 OtpRecord 的欄位（otpCode、retryCount、expiredAt）對 TOTP 不適用（TOTP code 不儲存在 server）；(2) 引入 OtpStrategy port：SmsOtpStrategy（目前的行為）和 TotpStrategy（驗證時查 users.totp_secret，不儲存 OtpRecord），application service 根據 OtpPurpose / user preference 選擇 strategy；(3) 新建 TotpRecord aggregate（儲存 shared secret、is_enabled、setup_at），OtpRecord 只管 SMS OTP。方案 2 較適合，因為保持了 OtpRecord 的職責單一（SMS OTP），不在同一 aggregate 混入 TOTP 邏輯。

### Category

System Design Evolution

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpPurpose.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Extending a domain model for a new authentication mechanism tests DDD design skills and Open/Closed Principle awareness.

### Possible follow-up questions

- What is TOTP and what RFC defines it?
- How would you implement a TOTP setup flow (QR code display, backup codes)?
- How would the test for TOTP verification differ from the test for SMS OTP verification?

### Verification status

- Documentation-only


---

<a id="Q236"></a>

## Q236 — How would you design a multi-card application feature where one applicant can apply for multiple card products simultaneously?

### 中文筆記

目前的限制：Application aggregate 的 cardProductId 是單一值，一個 Application 只對應一個 CardProduct。多卡申請的 domain 設計選項：(1) 多個獨立 Application aggregate（每張卡一個 application）：最簡單，申請人同時提交多個 POST /api/v1/applications，各自走獨立的 OTP → 文件 → 提交流程。問題：用戶需要為每張卡重複上傳相同文件（不友好），文件共享需要設計；(2) Application 包含 List<CardProductApplication>（multi-product aggregate）：一次申請可以包含多個產品選擇，文件共享，but aggregate 複雜度上升，state machine 需要重新設計（整體申請狀態 vs 各 product 狀態）；(3) ApplicationGroup aggregate：grouping 多個獨立 Application，共享文件，但各 application 有獨立審核流程。推薦：方案 1（多個獨立 Application）+ 文件引用（DocumentInfo 包含指向已上傳文件的 reference 而非重複上傳）是最 pragmatic 的，符合 CQRS 思想。方案 2 引入最多複雜度，只在申請人 UX 體驗要求極高時值得。

### Category

System Design Evolution

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java

### Verified classes and methods

- Application

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Extending an aggregate to support a new business requirement tests domain modeling skills.

### Possible follow-up questions

- How would the state machine change if one card application in a group is rejected but another is approved?
- How would you reuse uploaded documents across multiple applications?
- Would you create a new aggregate ApplicationBundle or extend the existing Application?

### Verification status

- Verified


---

<a id="Q237"></a>

## Q237 — The system design handbook proposes OpenTelemetry for distributed tracing. How would MdcLoggingFilter be extended to propagate a trace context across service boundaries?

### 中文筆記

OpenTelemetry trace propagation 的設計：目前 MdcLoggingFilter 生成一個 8-char requestId（純本地，只在當前 JVM 可見）。OpenTelemetry 的擴展：(1) 加入 io.opentelemetry:opentelemetry-spring-boot-starter + micrometer-tracing-bridge-otel；(2) Spring Boot 的 Micrometer Tracing 會自動在每個 HTTP request 建立 Span，並注入 traceId、spanId 到 MDC（key traceId、spanId），logback 的 pattern 可以直接用 %X{traceId}；(3) 若拆分出 Notification Service（Kafka 消費者），Kafka message header 攜帶 trace context（traceparent header，W3C Trace Context 格式），Notification Service 的 consumer 從 header 提取 trace context，繼續 same trace；(4) Thymeleaf 頁面可在 HTML response 中注入 traceId，讓 browser 報錯時 developer 可以查 log。目前的 8-char UUID 可以保留（面向用戶的 error reference），OpenTelemetry trace ID（32 hex chars）用於 infrastructure tracing，兩者共存在 MDC。

### Category

System Design Evolution

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java
- sp2-springboot/docs/handbook/06-system-design-handbook.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Distributed tracing integration in a Java service is a relevant DevOps engineering question.

### Possible follow-up questions

- What is W3C Trace Context and how does traceparent header propagate between services?
- How does Micrometer Tracing differ from directly using the OpenTelemetry API?
- What is a Span versus a Trace?

### Verification status

- Documentation-only


---

<a id="Q238"></a>

## Q238 — If TLBank needed to support a real-time notification to reviewers when a new application enters the review queue, what would you add?

### 中文筆記

Real-time notification 的設計選項：目前：reviewer 需要手動 refresh /review/cases 查看新 case。需求：SUBMITTED → ReviewCase 建立後，connected reviewer 立即收到提示（如 badge count 更新或 toast notification）。技術選項：(1) Server-Sent Events（SSE）：後端 GET /api/v1/review/events（text/event-stream）推送新 case 通知給 reviewer browser；Spring 支援 SseEmitter；連接斷開時 browser 自動重連；輕量，單向 push；(2) WebSocket（Spring @EnableWebSocketMessageBroker）：雙向通信，更複雜；適合 collaborative editing，對純通知 SSE 更合適；(3) Short polling：reviewer browser 每 30 秒 GET /api/v1/review/cases/count，比較上次的數量——最簡單，但有延遲和不必要的 request；(4) ApplicationSubmittedEvent 觸發 SSE push：ReviewEventHandler 在建立 ReviewCase 後，透過 SseEmitter repository 向所有 connected REVIEWER 推送 event。這個設計的 scalability：SSE 連接是 long-lived HTTP 連接，多個 reviewer 連接需要 server 維持。多 instance 時需要透過 Redis Pub/Sub 廣播 event 到所有 instance。

### Category

System Design Evolution

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java
- sp2-springboot/docs/handbook/06-system-design-handbook.md

### Verified classes and methods

- ReviewEventHandler.onApplicationSubmitted()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Real-time notification design is a common system design add-on question; tests knowledge of push vs pull patterns.

### Possible follow-up questions

- What is the difference between SSE and WebSocket?
- How would multiple application instances coordinate SSE pushes using Redis Pub/Sub?
- What happens to an SSE connection when the application restarts?

### Verification status

- Verified


---

<a id="Q239"></a>

## Q239 — Tell me about a time you had to make a design decision without all the information you needed. How does TLBank reflect this?

### 中文筆記

TLBank 中的不完全資訊決策：最典型的例子是 ADR 0005（Terraform local provider）和 ADR 0006（session over JWT）。在 ADR 0006 中，決策時不完全知道「未來是否需要 mobile app 支援」（mobile 需要 JWT），但做了 session-based 的決定並在 ADR 中記錄「若未來需要 mobile，加第二個 JWT filter chain」。這反映了「做最小合理決策，保留擴展空間」的思路，而非「預先設計所有可能的 use case」。另一個例子是 Terraform local provider：在不確定是否有雲端帳號和預算的情況下，先用 local provider 熟悉 Terraform workflow，記錄「when ready, replace with AWS provider」。這些決策的共同特徵：(1) 明確記錄當下的 constraint（為什麼現在這樣做）；(2) 記錄 alternative 和 trade-off；(3) 設計時保留 seam（port interface、ADR 的 future improvement section），讓 future context 下的換向成本低。

### Category

Behavioral Questions Based on the Project

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/decisions/0006-session-over-jwt.md
- sp2-springboot/docs/decisions/0005-use-terraform-local.md
- sp2-springboot/docs/decisions/0004-use-github-actions.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Behavioral questions reveal decision-making style; this one is best answered with specific examples from the project's ADRs.

### Possible follow-up questions

- What would you have done differently if you had to start TLBank over today?
- How did you handle uncertainty about whether @Transactional on ReviewEventHandler was the right choice?
- When is it appropriate to defer a decision vs. make an imperfect one now?

### Verification status

- Documentation-only


---

<a id="Q240"></a>

## Q240 — You mentioned using Cursor as an AI coding assistant. How did you ensure the generated code was correct and aligned with the design?

### 中文筆記

輔助開發的品質保證：三個機制：(1) Spec-before-code：sprint 開始前先讀 SDD，掌握 Hexagonal 與 state machine 約束，才能判斷 Cursor 生成的 code 是否合規；.cursor/rules.md 要求「Always read every document under /docs before generating code」；(2) Test-based acceptance：生成結果須通過測試，例如 ApplicationTest 的 state machine 測試、ApplicationAppServiceTest 的 mock 測試；若 transitionTo() 缺少正確 exception，測試失敗即為信號；(3) ADR-guided review：新增 port interface 等變更須對照 ADR 0001——domain 是否引入 Spring import、port 是否在正確 package、是否違反單一職責。輔助工具只加速實作，設計決策仍由開發者掌握。

### Category

Behavioral Questions Based on the Project

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/.cursor/rules.md
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md
- sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

AI tool usage in development is increasingly relevant; this question tests responsible AI development practices.

### Possible follow-up questions

- What type of code did you find Cursor best at generating, and where did it consistently make mistakes?
- How would you explain AI-assisted development to a skeptical colleague?
- What would you never let an AI tool write without manual review?

### Verification status

- Documentation-only


---

<a id="Q241"></a>

## Q241 — How do you approach learning a new technology? Walk me through how you learned Terraform for this project.

### 中文筆記

學習策略（以 Terraform 為例）：這個 project 的 Terraform 學習是典型的「limited-scope, documented progress」方式：(1) 目標先行：先定義「我需要知道 state/plan/apply workflow，以便未來能說明 IaC 實踐」——不是「學會 Terraform 全部」；(2) 最小可學習單位：選擇 hashicorp/local provider（不需要雲端帳號、零費用），讓自己能在本地環境走完完整 Terraform workflow，而非跳過到複雜的 AWS 配置；(3) 記錄決策：ADR 0005 明確寫下「目前是 local-only，學習 state/plan/apply workflow before cloud spend」，不虛報能力；(4) 誠實的 scope boundary：在 LinkedIn / 面試中不說「熟悉 Terraform」，而是「學習了 Terraform IaC workflow，使用 local provider 練習 state management，計劃下一步加入 AWS ECS skeleton」；(5) Incremental 推進：下一步是在 Terraform 中加入 AWS provider skeleton（aws_ecs_task_definition），讓 terraform plan 能通過，才能說「實際操作了 AWS 資源的 IaC」。

### Category

Behavioral Questions Based on the Project

### Difficulty

Basic

### Verified source files

- infra/local/main.tf
- sp2-springboot/docs/decisions/0005-use-terraform-local.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Learning agility is one of Trend Micro's stated values; how you learn new tech reveals your engineering growth trajectory.

### Possible follow-up questions

- What resource (documentation, course, mentor) did you find most useful for learning Terraform?
- How long did it take you to get a working terraform apply for the first time?
- What would the next sprint of Terraform learning look like for you?

### Verification status

- Documentation-only


---

<a id="Q242"></a>

## Q242 — If a senior engineer reviewed this codebase, what criticism would you expect, and how would you respond?

### 中文筆記

預期的 criticism（誠實評估）：(1) 「CacheStore / IdempotencyStore interface 在 infrastructure package，不是 Clean Architecture」→ 回應：ADR 0001 承認這是已知 trade-off，計劃在下一個 refactoring sprint 移動到 application layer，並會加 ArchUnit 測試確保不再 leak；(2) 「兩個 @EnableScheduling class」→ 回應：Known Limitation #3，down for next sprint cleanup，目前功能無影響；(3) 「No Testcontainers」→ 回應：Known Limitation #12，計劃加入 SQL Server Testcontainers 階段的 CI job，但有時間成本；(4) 「permitAll() 在 applicant endpoint 是 security risk」→ 回應：完全同意，是 portfolio scope 的 deliberate simplification；production 需要 applicant identity binding 或 short-lived token，已記錄在 future enhancement 中；(5) 「Report generation on request thread」→ 回應：知道 scalability 問題，DailyStatisticsScheduler 的 overload 方法已是第一步，async generation 是 roadmap。關鍵態度：不防禦，承認問題，說明為什麼這樣決定，展示已有 plan。

### Category

Behavioral Questions Based on the Project

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Self-awareness and ability to receive feedback is a key team collaboration indicator.

### Possible follow-up questions

- What criticism would you NOT accept, and why?
- If a reviewer pushed back on the session vs JWT decision, how would you defend it?
- What is the difference between defending a decision and being defensive?

### Verification status

- Documentation-only


---

<a id="Q243"></a>

## Q243 — Walk me through how you would demo TLBank to a technical interviewer in 10 minutes.

### 中文筆記

10 分鐘 demo 腳本：(1) 30 秒 pitch（畫 state machine）：「這是一個信用卡申請流程的後端，從 INIT 到 APPROVED 有明確的 state machine，我來 show 你幾個最有意思的工程決策」；(2) 開啟 ApplicationStatus.java（1 分鐘）：「domain layer 的 state machine，完全沒有 Spring，用 Map.of(EnumSet) 定義轉換規則，canTransitionTo(null) 的防禦性 check，讓測試跑起來只要 JDK」→ 跑 ApplicationTest；(3) 開啟 ApplicationApiController.java 的 createApplication()（2 分鐘）：「Idempotency-Key 是可選的，透過 IdempotencyService.execute() 包裝，内部用 Redis SETNX，24 小時 TTL，相同 key + 相同 body 返回快取的 201，相同 key + 不同 body 返回 409」→ 開 Swagger UI show endpoint；(4) 開啟 SecurityConfig.java（2 分鐘）：「為什麼 session 不用 JWT，maximumSessions(1) 的 concurrent session control，CSRF 對 /api/** 豁免」；(5) 開啟 CI pipeline .github/workflows/ci.yml（2 分鐘）：「5 個 job，Trivy report-only，自動 build image 推 GHCR，手動 deploy 到 local Mac」；(6) 開啟 ADR 0001（1 分鐘）：「每個重要決策都有文件，包括 known trade-offs，這是維護 engineering honesty 的方式」；(7) 問 interviewer 想深入哪個點（1 分鐘）。

### Category

Behavioral Questions Based on the Project

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/decisions/0001-use-clean-architecture.md
- .github/workflows/ci.yml
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Ability to explain a complex system concisely is a core engineering communication skill; the structure of the demo reveals how you prioritize.

### Possible follow-up questions

- What would you show if the interviewer said "skip the state machine, show me the security"?
- How would you adjust the demo for a non-technical interviewer vs a principal engineer?
- What part of the demo would you cut if you only had 5 minutes?

### Verification status

- Documentation-only


---

<a id="Q244"></a>

## Q244 — You transitioned from a non-technical background. What was the hardest technical concept to understand, and how did you work through it?

### 中文筆記

轉職背景的技術學習挑戰（以 TLBank 為例）：最難的概念之一是「Clean Architecture 的依賴方向」——理解為什麼 Application.java 不能 import @Entity 或 @Service 花了相當時間。從「這只是 annotation 有什麼差」到「因為 annotation 讓 domain 依賴 Spring，測試就必須啟動 Spring context，一旦 domain 有了 Spring 依賴，就無法在 plain JUnit 測試中驗證業務邏輯」，這個連結是靠跑 ApplicationTest（pure JUnit，啟動幾毫秒）和比較同樣的測試若 Application.java 有 @Entity 需要 @SpringBootTest 的差異，親眼驗證才真的理解。工作方式：「程式碼比教材更快教我」——看到測試速度的差異比閱讀理論文章更有說服力。另一個難點是 JPA 的 lazy loading 和 transaction boundary——透過故意觸發 LazyInitializationException，再在 findById() 的 @Transactional 上下手，親眼看到 exception 消失，才真正理解 session lifecycle。

### Category

Behavioral Questions Based on the Project

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Learning trajectory and problem-solving approach are key indicators for career changers; tests self-awareness.

### Possible follow-up questions

- What resource would you recommend to someone just starting to learn Java backend engineering?
- What surprised you most about software engineering compared to your previous career?
- How do you know when you understand something well enough to move on?

### Verification status

- Documentation-only


---

<a id="Q245"></a>

## Q245 — How did you decide on the test structure and naming convention in this project?

### 中文筆記

測試設計決策的依據：(1) 命名 convention：methodUnderTest_condition_expectedOutcome（如 verifyOtp_whenExpired_shouldThrowOtpExpiredException）——選擇這個 convention 是因為在讀 test name 時就能理解測試的 input condition 和 expected behavior，不需要打開測試 body；(2) 分層結構（Test Pyramid）：domain unit test 只用 JUnit 5（ApplicationTest、OtpRecordTest）→ application service test 用 JUnit 5 + Mockito（ApplicationAppServiceTest）→ integration test 用 @SpringBootTest（ApplicationFlowIntegrationTest）——每層測試的範圍和 setup 成本都有明確的邊界；(3) Clock injection pattern（FIXED_CLOCK = Clock.fixed(...)）：統一在所有需要時間的 test class 使用 Clock.fixed，確保 expiry 測試是 deterministic（不是「等 5 分鐘讓 OTP 過期」）；(4) 一個 behavior per test method（Single Responsibility Principle 應用到測試）：submit_whenDocumentsIncomplete_shouldThrowBusinessException 和 submit_whenDocumentUploaded_shouldSetSubmittedAt 是兩個獨立的 test，而非一個 test 驗證多個行為；(5) 測試分佈參考 16-testing-strategy.md §2。

### Category

Behavioral Questions Based on the Project

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/design/16-testing-strategy.md
- sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Test structure decisions reveal engineering discipline and team collaboration readiness.

### Possible follow-up questions

- What do you do when a test is flaky (sometimes passes, sometimes fails)?
- How many tests is "enough" for a new feature?
- What would you change about the current test suite if you had one week to improve it?

### Verification status

- Documentation-only


---

<a id="Q246"></a>

## Q246 — A reviewer saw this project and asked: "Why not just use Spring's built-in @Transactional state management instead of a custom state machine?" How do you answer?

### 中文筆記

回應策略：先理解 reviewer 的 concern，再解釋設計選擇。@Transactional 的 state management 意義：可能是指「在 DB 中直接 UPDATE status，不用在 domain 中 validate transition，讓 DB constraint 保護 consistency」。這個方式的問題：(1) 業務規則分散：「SUBMITTED 不可以直接 CANCELLED」這個規則在哪裡執行？若在 service 層用 if (status == SUBMITTED) throw，每個 service method 都要重複這個 check；若在 DB 用 trigger 或 check constraint，規則不可讀且難測試；(2) Domain 不可測試：沒有 state machine，Application 的狀態轉換邏輯散落在 services，domain unit test 無法驗證業務規則，每次改規則都需要整合測試；(3) 隱性 contract：Application.submit() 的呼叫者不知道前置條件是 status 必須是 DOCUMENT_UPLOADED，讀 code 才能發現。State machine 的好處在這裡很清楚：ApplicationStatus.ALLOWED_TRANSITIONS 是業務規則的 single source of truth，可以被 pure Java unit test 完整驗證，ApplicationTest.submit_whenDocumentsIncomplete_shouldThrowBusinessException 不需要 DB 或 Spring。

### Category

Behavioral Questions Based on the Project

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java
- sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationStatusTest.java

### Verified classes and methods

- ApplicationStatus.ALLOWED_TRANSITIONS (private static)

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Defending design decisions under challenge tests confidence and depth of understanding.

### Possible follow-up questions

- What would you lose if the state machine was in a DB state transition table instead?
- When would a DB-constraint-based state machine be preferable?
- What is the Spring State Machine project and when would you use it instead of a hand-coded one?

### Verification status

- Verified


---

<a id="Q247"></a>

## Q247 — How do you explain the ApplicationId design to someone who asks "why not just use a String?"

### 中文筆記

解釋 typed ID 的 value prop（非技術觀眾）：「想像你有三個方法：findApplicationById(String id) 、findReviewCaseById(String id) 、findCardProductById(String id)。若這三個都接受 String，編譯器不會阻止你這樣寫：reviewCaseService.approve(application.getId())——你傳了 applicationId 到本應收 reviewCaseId 的地方，程式碼可以 compile，但在 runtime 才會發現 'review case not found'。」「現在改成 ApplicationId、ReviewCaseId、CardProductId 三個不同的型別：reviewCaseService.approve(application.getApplicationId()) 在 compile 時就報錯——因為 approve() 接受 ReviewCaseId 而你傳了 ApplicationId，型別不符。」技術觀眾的補充：record 實現讓 value object 具有 value-based equality（兩個 ApplicationId.of("APP-001") equals），不是 reference equality，在 Map<ApplicationId, Application> 中作為 key 正確工作。這是「用型別系統代替 runtime check 的 early failure 設計」，符合 Fail Fast 原則。

### Category

Behavioral Questions Based on the Project

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Explaining technical decisions to different audiences is a communication skill; tests the ability to translate design rationale.

### Possible follow-up questions

- How would you explain Hexagonal Architecture to a non-technical manager?
- What is Primitive Obsession and how do you explain it to a junior developer?
- What are the limits of the typed ID approach?

### Verification status

- Documentation-only


---

<a id="Q248"></a>

## Q248 — If you were given this project and one week to improve it, what would you do?

### 中文筆記

一週改善計畫（優先序）：Day 1-2（Security）：加 OTP rate limiting（Redis INCR + EXPIRE），關閉 Swagger 在 non-dev profile，加 @PreAuthorize 在 applicant-facing endpoints 確認呼叫者是 OTP 已驗證的申請人（或留下 design note 說明這需要 bigger refactor）。Day 3（Architecture cleanup）：刪除 SchedulerConfig.java（保留 SchedulingConfig.java），移動 CacheStore 和 IdempotencyStore interface 到 application package，加 ArchUnit 測試確保 domain 不依賴 infrastructure。Day 4（Testing）：加 Testcontainers SQL Server test configuration（先作為 optional tag，不 block main CI），驗證所有 native query 在 real SQL Server 通過。Day 5（Observability）：加 micrometer-registry-prometheus，把 OTP verify success/failure 和 HTTP request latency expose 出去，更新 logback-spring.xml 讓 log retention 可 configure。不會動的事：不改 Terraform（需要 AWS account 和 budget decision）；不改 session → JWT（is a bigger architectural decision）；不改 notification（需要外部 vendor decision）。

### Category

Behavioral Questions Based on the Project

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Prioritization under constraints is a key engineering judgment skill; tests how the candidate balances security, architecture, and testing concerns.

### Possible follow-up questions

- What would you NOT do in that one week, and why?
- How would you communicate the improvements to your tech lead after the week?
- What metrics would you track to verify the improvements made a difference?

### Verification status

- Documentation-only


---

<a id="Q249"></a>

## Q249 — How does maintaining a side project differ from working on a production system, and what would you do differently if this were a real bank product?

### 中文筆記

Side project vs production 的差異（TLBank 具體對比）：Security：Portfolio 的 applicant endpoints 是 permitAll()（可接受），production 需要 identity binding（不可接受）；mock SMS sender 在 portfolio 沒問題，production 需要 Twilio + delivery receipts。Observability：Portfolio 只有 Logback file，production 需要 ELK 或 CloudWatch + Prometheus + alerting + on-call rotation。Deployment：Portfolio 是本機 Mac 手動 deploy，production 需要 HA（多 AZ）、滾動更新、健康狀態 gate + rollback plan。Data：Portfolio 無 DR（disaster recovery），production 需要 SQL Server backup、point-in-time recovery、RTO/RPO 定義。Testing：Portfolio 無 SQL Server integration test，production 有完整 Testcontainers + load test。若這是真正的銀行產品，還會加：Security audit + penetration testing、PCI DSS / PDPA compliance review、Code signing + SLSA supply chain security、Change management process（every production deploy has an approval gate + rollback plan）、24×7 on-call support process。最重要的差異：在 production，「known limitation」不是 roadmap item，是 blocker，必須在 go-live 前解決。

### Category

Behavioral Questions Based on the Project

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md
- sp2-springboot/docs/handbook/00-project-overview.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Distinguishing portfolio scope from production requirements shows engineering maturity.

### Possible follow-up questions

- What is PCI DSS and which parts of TLBank would it affect?
- How would you approach a security audit for this codebase?
- What would your rollback plan look like for a bad deployment?

### Verification status

- Documentation-only


---

<a id="Q250"></a>

## Q250 — SecurityIntegrationTest covers login and authorization. Which behaviors should a candidate expect it to assert?

### 中文筆記

SecurityIntegrationTest 以 MockMvc 驗證登入成功/失敗、角色授權與工作階段相關行為，密碼對應 dev seed 的測試常數。此測試鎖定 SecurityConfig 與 handler 合約，屬於安全回歸的主要自動化防線。

### Category

Spring Security

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java

### Verified classes and methods

- SecurityConfig.securityFilterChain()
- LoginSuccessHandler
- LoginFailureHandler

### Execution flow

1. Test boots Spring Security test context
2. MockMvc posts form login and secured URLs
3. Assertions check status codes and JSON error contracts

### Why this may be asked

Links security design answers to the actual automated proof in the repo.

### Possible follow-up questions

- How are roles mapped from user_roles?
- What does maximumSessions(1) do on concurrent login?
- Why are some applicant APIs permitAll?

### Verification status

- Verified


---

<a id="Q251"></a>

## Q251 — ApplicationId.generate() uses ThreadLocalRandom rather than SecureRandom. Is this safe, and why the different choice from OTP generation?

### 中文筆記

ThreadLocalRandom vs SecureRandom 的選擇：ApplicationId.generate() 使用 ThreadLocalRandom.current().nextInt(1000, 10000) 生成 4 位隨機後綴（1000–9999），加上 14 位時間戳（yyyyMMddHHmmss）。OtpAppService.generateOtpCode() 使用 SecureRandom 生成 6 位 OTP。差異的合理性：ApplicationId 是業務識別碼（APP-20260614162447-7823），目的是「在同一時間戳內產生不同的 applicationId 避免衝突」，不是密碼學用途；攻擊者猜出 applicationId 的後 4 位也只能查詢已知的 applicationId（且目前 GET /api/v1/applications/{id} 是 permitAll()）。OTP 的用途完全不同：若可預測，攻擊者可以「猜」OTP 繞過身份驗證，必須密碼學安全。ThreadLocalRandom 的好處：高性能（每個 thread 有獨立的 state，無 contention），比 SecureRandom 快幾個數量級，適合高頻 ID 生成。唯一隱患：若兩個 request 在完全相同的一秒內觸達（同一個 timestamp），後綴重疊（1/9000 機率），DB 的 UNIQUE (application_no) constraint 會 catch 並拋 DataIntegrityViolationException，caller 需要 retry。

### Category

Domain-Driven Design

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java

### Verified classes and methods

- ApplicationId.generate()
- OtpAppService

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Choosing the right randomness source for different use cases tests whether the candidate understands cryptographic vs. non-cryptographic randomness requirements.

### Possible follow-up questions

- What is the collision probability for two ApplicationIds generated in the same second?
- How does ThreadLocalRandom avoid thread contention compared to new Random()?
- What exception would ApplicationAppService.createApplication() get if there is a collision?

### Verification status

- Verified


---

<a id="Q252"></a>

## Q252 — MobileNumber validates with the pattern ^09\d{8}$. What does this enforce, and what real phone numbers would it reject?

### 中文筆記

^09\d{8}$ 強制規則：以 09 開頭（台灣手機號碼前綴）+ 後 8 位數字，共 10 位。合法範例：0912345678、0987654321、0900000000。會被拒絕的情況：(1) 固網電話（02-12345678，04-12345678）；(2) 國碼前綴（+886-912345678，886912345678）；(3) 有連字符的格式（0912-345-678）；(4) 國際手機號碼（非台灣）。業務範圍：TLBank 是台灣的銀行 platform，僅限台灣手機號碼是合理的業務決定。驗證在 MobileNumber value object 的 compact constructor 中執行（throw new IllegalArgumentException），在 domain 層就拒絕，不依賴 controller 的 @Valid。ApplicantRequest.mobile 是 @NotBlank String，但 String → MobileNumber 的轉換（在 ApplicationAppService.toApplicant() 中呼叫 MobileNumber.of(request.mobile())）才做 pattern 驗證。這意味著 controller 只做 @NotBlank，實際 format 驗證在 application service 轉換時執行，若失敗拋 IllegalArgumentException，需要 GlobalExceptionHandler 處理。

### Category

Validation and Exception Handling

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/MobileNumber.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java

### Verified classes and methods

- ApplicationAppService.toApplicant() (private)
- MobileNumber.of()

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Value object validation in the domain layer is a DDD question; understanding what gets rejected reveals attention to business rules.

### Possible follow-up questions

- What HTTP status should the API return if MobileNumber.of() throws IllegalArgumentException?
- How would you extend the validation to support international phone numbers?
- Why is the pattern validated in the domain value object rather than only in @Pattern on the request DTO?

### Verification status

- Verified


---

<a id="Q253"></a>

## Q253 — Address is a record with four fields validated in its compact constructor. Why does Address validate each field individually rather than as a group?

### 中文筆記

個別驗證的理由：Address 的 compact constructor 對 city、district、street、zipCode 各自執行 if (field == null || field.isBlank()) throw new IllegalArgumentException(...) 的獨立驗證。好處：(1) 具體的錯誤訊息：IllegalArgumentException("District must not be blank") 告訴 caller 是哪個欄位的問題，而非 generic 的 "Address is invalid"；(2) Fail fast：第一個無效欄位就拋出，不繼續驗證後面的欄位（無效 city 就不再驗 district）；(3) 業務意圖清晰：每個欄位的驗證是獨立的業務規則，分別記錄它們自己的 constraint。缺點：若 city 是 null 且 district 也是 null，呼叫端只看到第一個錯誤，需要修正後重送才看到第二個。在 Web API 場景，@Valid @NotBlank 可以一次回傳所有 field errors（FieldErrorDetail list）；但 domain value object 的角色是保護業務完整性（不需要呈現完整的 validation summary），fail fast 是正確的選擇。

### Category

Domain-Driven Design

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Address.java
- sp2-springboot/src/test/java/com/tlbank/lending/domain/application/AddressTest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Validation design in domain value objects reveals understanding of fail-fast vs. collect-all-errors patterns.

### Possible follow-up questions

- What is the difference between fail-fast validation and collect-all validation?
- How would you write an AddressTest that verifies the district error message specifically?
- Should Address validate zipCode format (e.g., 5-digit Taiwan postal code)?

### Verification status

- Documentation-only


---

<a id="Q254"></a>

## Q254 — Applicant is a record with a field of type MobileNumber (itself a record). What is the advantage of composing domain value objects this way?

### 中文筆記

Value object 組合的優點：Applicant 的 mobile 欄位是 MobileNumber 型別（而非 String），email 欄位是 Email 型別（而非 String）。優點：(1) Type-safety composition：在任何接受 Applicant 的方法中，applicant.mobile() 永遠是一個已驗證的台灣手機號碼，不可能是 "invalid"；若是 String 欄位，每個接受 Applicant.mobile 的 caller 都需要自己驗證；(2) 業務行為封裝：MobileNumber.masked() 方法可以在任何持有 MobileNumber 的地方呼叫（applicant.mobile().masked()），不需要在 ApplicationAppService 中重複 MaskingUtil.maskMobile(applicant.mobile())；(3) 語意明確：applicant.mobile() 的型別簽名就說明「這是一個有效的台灣手機號」，比 applicant.mobile() 返回 String 需要讀文件才知道是否已驗證；(4) Testability：ApplicationTest.sampleApplicant() 用 MobileNumber.of("0912345678") 建立，測試建立時就驗證，不需要額外 assert。

### Category

Domain-Driven Design

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Applicant.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/MobileNumber.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Email.java

### Verified classes and methods

- MobileNumber.masked()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Composing domain value objects is a DDD best practice; tests understanding of rich domain models vs. anemic data containers.

### Possible follow-up questions

- How does ApplicationAppService.submitApplication() get the applicant's mobile for the event?
- What would change if Applicant.mobile were String instead of MobileNumber?
- What is an anemic domain model, and does Applicant avoid it?

### Verification status

- Verified


---

<a id="Q255"></a>

## Q255 — DocumentInfo is a record with storagePath as a plain String. Should storagePath be a typed value object like StoragePath, and when would that be justified?

### 中文筆記

storagePath 是否應該型別化的評估：目前：StoragePath 是 String（如 APP-001/NATIONAL_ID_20260614162447.pdf）。型別化的好處：(1) 防止 path 和 other strings 混用（Primitive Obsession）；(2) 可在 StoragePath.of(value) 中驗證 path 格式（不含 ..，relative path only）；(3) StoragePath.toAbsolute(basePath) 可以封裝路徑拼接邏輯。不型別化的理由（目前的合理性）：storagePath 是 infrastructure concern（文件系統的相對路徑），domain 不需要了解 path 的結構；若未來換成 S3，storagePath 變成 S3 URI（s3://bucket/APP-001/...）或 presigned URL，domain 只需儲存「how to retrieve this document」的 opaque reference。型別化 StoragePath 會讓 domain 對 storage topology 有更多 assumption。合理結論：對於 storage path，保持 String 是合理的；若需要 domain-level path validation（如防止 ..），在 DocumentInfo 的 compact constructor 中加 if (storagePath.contains("..")) throw 更 pragmatic，不需要建立新的 value object type。

### Category

Domain-Driven Design

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentInfo.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

When to type vs. when to use String is a nuanced DDD design question; forces the candidate to articulate trade-offs.

### Possible follow-up questions

- What is the "Primitive Obsession" anti-pattern, and does DocumentInfo.storagePath qualify?
- At what point does creating a value object become over-engineering?
- How would DocumentInfo.storagePath change if you moved from local storage to S3?

### Verification status

- Documentation-only


---

<a id="Q256"></a>

## Q256 — ApplicationId.generate() is called inside ApplicationAppService.createApplication(), not inside the Application aggregate. Is this the right placement?

### 中文筆記

ID 生成位置的分析：在 ApplicationAppService.createApplication() 中：ApplicationId id = ApplicationId.generate()（或 ApplicationId.generate() 直接作為 Application.builder().applicationId(ApplicationId.generate()).build() 的參數）。為什麼在 service 而非在 aggregate factory：(1) Application.builder() 是 Lombok builder，不是工廠方法，呼叫 ApplicationAppService 的職責就是 orchestrate「建立一個 Application」這個 use case，生成 ID 是 orchestration 的一部分；(2) 若 ID 生成在 Application static factory 方法（如 Application.create(Applicant, CardProductId)），aggregate 自帶 ThreadLocalRandom 依賴，這在某些 DDD 純粹主義觀點中屬於 aggregate 知道太多 infrastructure concern（ID 生成策略）；(3) 測試中，ApplicationTest 用 ApplicationId.of("APP-20250607120000-1234")（hardcoded id），不呼叫 generate()，domain test 不依賴 random ID，可重現。無論哪種選擇都可以辯護，關鍵是一致性：若所有 aggregate 的 ID 都在 service 層生成，保持這個 pattern；若部分在 aggregate 生成，就有 inconsistency。

### Category

Domain-Driven Design

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java

### Verified classes and methods

- ApplicationAppService.createApplication()
- ApplicationId.generate()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

ID generation placement is a subtle DDD design question with no single right answer; tests the candidate's ability to reason about trade-offs.

### Possible follow-up questions

- What is a domain factory method and how would it apply here?
- How do other aggregates (ReviewCase, User) generate their IDs?
- If ID generation used a database sequence instead of ThreadLocalRandom, where would the placement change?

### Verification status

- Verified


---

<a id="Q257"></a>

## Q257 — WorkflowHistory has a fromStatus field that could be null for the very first transition from INIT. Is this correctly modeled?

### 中文筆記

fromStatus 為 null 的情況分析：Application 的第一次狀態轉換是 verifyOtp() → INIT → OTP_VERIFIED，此時 fromStatus = INIT（不是 null）。實際上每次呼叫 transitionTo(next, operator, remark) 都有 fromStatus = this.status（當前狀態），而 Application 在 builder 中設 status(ApplicationStatus.INIT)，所以第一次轉換的 fromStatus 是 INIT，不是 null。但資料庫 schema（V3__create_applications.sql 中 workflow_histories.from_status VARCHAR(30)，沒有 NOT NULL）允許 null。若 Application 建立後立刻被 load 然後 save() 而沒有狀態轉換，workflowHistories 是空的，不會有 null fromStatus 的 row。WorkflowHistoryEntity.fromStatus 是 @Enumerated(EnumType.STRING) 的 ApplicationStatus 型別，若資料庫中存 null，Hibernate 會讀出 null，但 WorkflowHistory.fromStatus 欄位是 nullable（@Getter + @Builder，Lombok 不加 non-null）。現有程式碼不可能產生 null fromStatus 的 history row，但 schema 允許 null 是多餘的靈活性（或遺留自早期設計）。

### Category

JPA and Hibernate

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/WorkflowHistory.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/WorkflowHistoryEntity.java
- sp2-springboot/src/main/resources/db/migration/V3__create_applications.sql

### Verified classes and methods

- Application.transitionTo() (private)
- WorkflowHistoryEntity

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Nullable fields in domain objects vs. schema is a data modeling question that tests attention to schema design.

### Possible follow-up questions

- Should workflow_histories.from_status be NOT NULL? What would need to change?
- How would you write a migration to add NOT NULL to from_status for existing data?
- What is the purpose of fromStatus for audit purposes — could you derive it from the previous history row?

### Verification status

- Verified


---

<a id="Q258"></a>

## Q258 — WorkflowHistoryEntity has a @ManyToOne(fetch = FetchType.LAZY) back-reference to ApplicationEntity. What JPA issue can arise from this?

### 中文筆記

@ManyToOne(fetch = LAZY) 的 JPA 問題：WorkflowHistoryEntity 有 @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "application_id") ApplicationEntity application，即每個 history 記錄都有一個 lazy reference 回到其所屬的 Application entity。當 ApplicationRepositoryImpl.toDomain() 從 ApplicationEntity.workflowHistories 取 list 並轉換：迭代 workflowHistoryEntity 時，若 code 嘗試存取 historyEntity.getApplication()（即反向導航回 ApplicationEntity），就會觸發 lazy load，在 session 存活時沒問題，但在 session 關閉後觸發 LazyInitializationException。目前 ApplicationRepositoryImpl.toDomain() 只從 history entity 取 fromStatus、toStatus、operator、comment、actionAt，不存取 application 欄位，所以沒有問題。但如果日後有開發者在 toDomain() 中加 historyEntity.getApplication().getApplicationNo()，就會在 session 關閉後踩到 lazy load exception。防禦方式：在 WorkflowHistoryEntity 上不暴露 getApplication() 的 public getter，或在 mapping code 中明確 document 不應呼叫反向導航。

### Category

JPA and Hibernate

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/WorkflowHistoryEntity.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java

### Verified classes and methods

- WorkflowHistoryEntity
- ApplicationRepositoryImpl.toDomain() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Bi-directional JPA relationships and their lazy loading pitfalls are a common JPA issue; tests careful code reading.

### Possible follow-up questions

- What is CascadeType.ALL on ApplicationEntity.workflowHistories, and how does it interact with the history deletion?
- How would @BatchSize(size = 20) on workflowHistories help when loading 20 Applications in a list?
- Could you remove the @ManyToOne back-reference entirely? What would break?

### Verification status

- Verified


---

<a id="Q259"></a>

## Q259 — UserEntity uses @ElementCollection(fetch = FetchType.EAGER) for roles, while ApplicationEntity uses @OneToMany(fetch = FetchType.LAZY). Explain the different design choices.

### 中文筆記

@ElementCollection EAGER vs @OneToMany LAZY：UserEntity.roles（Set<String>）使用 @ElementCollection(fetch = FetchType.EAGER)：(1) 角色集合很小（通常 1-3 個 role），EAGER load 不增加顯著 overhead；(2) 每次 Spring Security loadUserByUsername() 都需要 roles（UserDetailsServiceImpl 立即呼叫 user.getRoles()），若是 LAZY，就要確保在 session 存活時存取，使用 EAGER 更安全；(3) @ElementCollection 比 @OneToMany 更簡單（role 是 value，不是 entity，不需要自己的 ID）。ApplicationEntity.workflowHistories、.documents（@OneToMany LAZY）：(1) History 和 documents 可能有幾十筆，EAGER load 會讓每次查詢 Application 都 JOIN 大量子集合；(2) 許多查詢（如 list 申請、status 查詢）不需要 history，LAZY 可以避免不必要的 DB I/O；(3) Mapping code（toDomain()）在 transaction 內存取 lazy collection，安全。核心原則：EAGER 適合小型、始終需要的集合；LAZY 適合大型、按需存取的集合。

### Category

JPA and Hibernate

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java

### Verified classes and methods

- UserEntity
- ApplicationEntity

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Choosing between EAGER and LAZY fetch type is a fundamental JPA design decision with performance implications.

### Possible follow-up questions

- What is the N+1 problem that @OneToMany(EAGER) can cause in a list query?
- What is @ElementCollection and when is it preferable to @OneToMany?
- Could you use @ElementCollection(fetch = LAZY) for roles? When would that cause a bug?

### Verification status

- Verified


---

<a id="Q260"></a>

## Q260 — UserEntity uses a String userId as @Id (the business key USR-xxxxxxxx) rather than a surrogate BIGINT. Why is this different from other entities like ApplicationEntity?

### 中文筆記

UserEntity 使用 String 作為 @Id 的特殊性：其他 entity（ApplicationEntity、OtpRecordEntity、WorkflowHistoryEntity）都使用 BIGINT IDENTITY surrogate key 作為 @Id，並有獨立的 business key（如 applicationNo）。UserEntity 的 @Id 是 String userId（格式 USR-xxxxxxxx），這讓 @Id 直接是業務 key。影響：(1) Hibernate ID 生成：@GeneratedValue 不適用於 String @Id（預設 SEQUENCE/IDENTITY 是 numeric），UserId.generate() 在 application code 中生成，再 set 到 entity，不依賴 DB 生成；(2) FK 引用：WorkflowHistoryEntity 的 @JoinColumn(name = "action_by") 和其他引用 users.id 的 FK——users table 有兩個 ID：業務 user_id VARCHAR(50) 和內部 id BIGINT (IDENTITY)。UserEntity 把兩個都 map（@Id String userId + @Column(insertable = false, updatable = false) Long internalId），FK 關係可能指向 users.id（BIGINT），所以 @JoinColumn 需要 referencedColumnName = "id" 而非 user_id。

### Category

JPA and Hibernate

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/user/UserId.java

### Verified classes and methods

- UserEntity

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Mixed ID strategy (String @Id vs BIGINT @Id) across entities is a nuanced JPA design observation.

### Possible follow-up questions

- Why does UserEntity have both userId (String) and internalId (BIGINT)?
- How does Hibernate know not to generate userId from a sequence when it's the @Id?
- What would break if you removed internalId from UserEntity?

### Verification status

- Verified


---

<a id="Q261"></a>

## Q261 — LoginResponseMode.prefersJson() defaults to true when the Accept header is null, blank, or */*. Why is JSON the safer default?

### 中文筆記

JSON 為預設的理由：HTTP 的 Accept: */* 表示「任何格式都可以接受」，但實際上不同 client 的 behavior：(1) curl：預設發送 Accept: */*，期望 JSON 回應（REST API 測試場景）；(2) Swagger UI / Postman：發送 Accept: application/json，明確要 JSON；(3) Browser form submit：Accept: text/html,application/xhtml+xml,...，明確包含 text/html——這是 browser 的 default，會進入 redirect path；(4) Spring Boot test 的 MockMvc：預設 Accept: */*，SecurityIntegrationTest 需要 JSON response，prefersJson() 返回 true 確保測試可以 jsonPath("$.success") assert。若默認 redirect（false）：curl 用戶 login 會被重定向到 /admin/users（HTML page），不是 JSON，breaking change for API clients。JSON 是更安全的 default 因為：大多數非 browser client 期望 JSON，redirect 對它們沒有意義（HTTP client 可能不 follow redirect）。Accept: text/html 的 branch：只有真正的 browser（window.location redirect 有意義）才發送這個 header。

### Category

Spring Security

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/security/util/LoginResponseMode.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java

### Verified classes and methods

- LoginResponseMode.prefersJson()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

HTTP content negotiation for dual-mode responses is a practical Spring Security question; tests HTTP protocol awareness.

### Possible follow-up questions

- What HTTP status code does a browser redirect return — 302 or 303?
- How does the Postman tool set the Accept header by default?
- Why does Accept: */* mean "any format" but JSON is still the correct choice here?

### Verification status

- Verified


---

<a id="Q262"></a>

## Q262 — CardProduct is modeled as a domain class with List<ProductFeature>. Is it an aggregate root, and what boundary does it enforce?

### 中文筆記

CardProduct 的 aggregate root 評估：CardProduct 有 CardProductId productId（業務 ID），List<ProductFeature> features（子集合），boolean enabled（狀態）。作為 aggregate root 的特徵：有 ID → 可以是 aggregate root；features 是 CardProduct 的子集合（product feature 的生命週期依附於 product），只透過 CardProduct 存取，不直接 findById(featureId) → aggregate boundary 正確。它的業務不變式：CardProduct 確保 enabled 的 product 才能被申請（ApplicationAppService 有 .filter(CardProduct::isEnabled)）；features 只能在 product context 中管理（不透過獨立 repository 直接操作 feature）。限制：CardProduct 沒有業務方法（enable()、disable()、addFeature() 等）——如果 admin 需要 enable/disable 產品，目前是直接在 DB 改 status 欄位還是透過 domain method？若沒有 domain method，aggregate root 就只是一個 data container，接近 anemic model。若有 CardProduct.enable() 方法（this.enabled = true），才是完整的 aggregate。

### Category

Domain-Driven Design

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/product/CardProduct.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/product/ProductFeature.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Evaluating aggregate root boundaries is a DDD competency question; testing whether CardProduct truly qualifies reveals depth of understanding.

### Possible follow-up questions

- Should admin enable/disable a product through a domain method on CardProduct or through direct DB update?
- Is ProductFeature an entity or a value object?
- How would you model a "product retirement" feature where old products are no longer available but existing applications can reference them?

### Verification status

- Documentation-only


---

<a id="Q263"></a>

## Q263 — CacheManagementService.getStats() estimates memory using String.valueOf(value).length() * 2L. How accurate is this estimate, and what does * 2L account for?

### 中文筆記

記憶體估算的準確性：estimateEntryBytes(key) = key.length() * 2L + String.valueOf(value).length() * 2L。* 2L 的理由：Java String 在 JVM 中以 UTF-16 編碼儲存（每個 char 佔 2 bytes），所以 length() × 2 近似字元的 raw byte 大小。不準確之處：(1) JVM 的 Compact Strings（Java 9+）：若 String 只含 Latin-1 字元，JVM 用 byte[] 儲存（1 byte/char）而非 char[]，實際記憶體可能少一半；(2) String object header：JVM 中每個 String 物件有 header（~12-16 bytes）、byte[] 物件 header、length field，這些 overhead 沒有算入；(3) ConcurrentHashMap.Entry overhead：map 的 node、key-value pair reference 等 overhead 沒有計算；(4) CacheEntry record overhead：CacheEntry(V value, LocalDateTime expiresAt) 的物件 overhead 也沒算。結論：這是一個 rough estimate，用於 admin dashboard 顯示「大約幾 KB」的數量級，不是精確的 heap 分析。如果需要精確的記憶體測量，應使用 java.lang.instrument.Instrumentation（ObjectSizeCalculator）或 JVM profiler。

### Category

Performance and Scalability

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java

### Verified classes and methods

- CacheManagementService.estimateEntryBytes() (private)
- CacheManagementService.getStats()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Memory estimation logic reveals whether the candidate understands JVM memory layout; * 2L is a clue to Java String internals.

### Possible follow-up questions

- What is Java Compact Strings (introduced in Java 9) and how does it affect this estimate?
- How would you get an accurate object size in Java without a profiler?
- Is the inaccuracy in this estimate a problem for the intended use case?

### Verification status

- Verified


---

<a id="Q264"></a>

## Q264 — AuditContext uses ThreadLocal<Map<String, String>> with LinkedHashMap::new. Why LinkedHashMap rather than HashMap?

### 中文筆記

選擇 LinkedHashMap 的理由：LinkedHashMap 維護插入順序（insertion order），而 HashMap 不保證 iteration order。在 AuditContext.buildSuffix() 中，entries.entrySet().stream().map(entry -> key + "=" + value).collect(joining(", ")) 會按插入順序輸出 key-value pairs。若使用 HashMap：audit detail 的 key 順序每次可能不同（取決於 hash 計算），如 mobile=0912****78, applicationId=APP-001 和 applicationId=APP-001, mobile=0912****78 兩種順序都可能出現，讓 audit log 不一致，也讓測試 assertThat(detail).isEqualTo("applicationId=APP-001, mobile=0912****78") 不穩定（flaky）。LinkedHashMap 確保：先 put("applicationId", ...) 再 put("mobile", ...) 的 audit log 永遠是 applicationId=APP-001, mobile=0912****78，可讀且可測試。ThreadLocal.withInitial(LinkedHashMap::new) 每個 thread 的 context 是獨立的 LinkedHashMap 實例，不共享 state，thread-safe。

### Category

Audit Logging and AOP

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditContext.java

### Verified classes and methods

- AuditContext
- AuditContext.buildSuffix()

### Execution flow

1. Annotated method executes under @Auditable
2. AuditAspect intercepts and builds audit fields
3. AuditDetailBuilder sanitizes detail before persistence

### Why this may be asked

LinkedHashMap vs HashMap choice for an ordered map is a subtle Java collections question; tests attention to implementation detail.

### Possible follow-up questions

- What is ThreadLocal.withInitial() and how does it differ from new ThreadLocal()?
- What is the risk of not calling AuditContext.clear() in the finally block?
- How does ThreadLocal behave in a thread pool where threads are reused?

### Verification status

- Verified


---

<a id="Q265"></a>

## Q265 — ErrorCode is a flat enum without grouping (e.g., no OTP_* group vs APPLICATION_* group). What is the advantage and risk of this design?

### 中文筆記

Flat enum 的評估：優點：(1) 簡單直觀：ErrorCode.OTP_EXPIRED 比 OtpErrorCode.EXPIRED 更少的 class，不需要 namespace；(2) Switch statement：GlobalExceptionHandler 的 switch 可以覆蓋所有 error codes 在一個 place；(3) 單一 serialization：API response 的 errorCode 是 ErrorCode.name()，client 只需處理一個 namespace 的字串；(4) 一目了然：所有可能的 error code 列在一個 enum，看一個地方就夠。風險：(1) Namespace pollution：隨著系統成長，enum 可能有幾十個 member，OTP/Application/Review/User 的 error 混在一起，難以識別；(2) 跨 team 衝突：若多個 team 都在 ErrorCode 加新成員，merge conflict 頻繁；(3) 重複語意：NOT_FOUND vs APPLICATION_NOT_FOUND vs REVIEW_CASE_NOT_FOUND 都是 "not found" 的變體，可以統一；或者反過來，NOT_FOUND 太模糊，APPLICATION_NOT_FOUND 更具體。目前 18 個 error code 的規模，flat enum 完全合理，不需要分組。

### Category

REST API Design and OpenAPI

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java

### Verified classes and methods

- GlobalExceptionHandler.handleBusinessException()

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

API error code design is a practical question about API contract management; tests design judgment at scale.

### Possible follow-up questions

- How would you group error codes if they grew to 100 members?
- What is the difference between NOT_FOUND and APPLICATION_NOT_FOUND from the API client's perspective?
- How does IDEMPOTENCY_KEY_CONFLICT differ from IDEMPOTENCY_KEY_IN_PROGRESS?

### Verification status

- Verified


---

<a id="Q266"></a>

## Q266 — OtpPurpose is a single-value enum with only APPLICATION_VERIFICATION. Why use an enum for a single value?

### 中文筆記

Single-value enum 的設計意圖：OtpPurpose 目前只有 APPLICATION_VERIFICATION 一個值，但定義為 enum 而非 boolean flag 或 String constant。理由：(1) 擴展性：未來可能加入 PASSWORD_RESET（用戶忘記密碼）、ACCOUNT_BINDING（綁定新手機）、HIGH_VALUE_TRANSACTION（大額交易確認），enum 比 String 更安全（compile-time exhaustiveness check）；(2) 型別安全：OtpRecord.purpose（OtpPurpose）和 SendOtpCommand.purpose（OtpPurpose）型別明確，不是 String "APPLICATION_VERIFICATION"，防止 typo；(3) DB 儲存：otp_records.purpose VARCHAR(30) 儲存 "APPLICATION_VERIFICATION"，未來加新 value 只需加 enum constant + 不需要 DB migration（只要 VARCHAR 夠長）；(4) 文件化：enum 是 self-documenting，看到 OtpPurpose 就知道這個系統 OTP 有哪些用途，比 String constant 更 discoverables。Single-value enum 是常見的「設計為未來而非現在」的技術，符合 YAGNI 和 Open-Closed 的平衡。

### Category

Domain-Driven Design

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpPurpose.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java

### Verified classes and methods

- OtpPurpose

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Single-value enum design reveals forward-thinking API design philosophy; tests awareness of the Open/Closed Principle.

### Possible follow-up questions

- What would need to change to add PASSWORD_RESET as a second OTP purpose?
- Is this an example of YAGNI ("You Aren't Gonna Need It") violation or good extensibility design?
- How would the OTP schema change if different purposes had different expiry durations?

### Verification status

- Verified


---

<a id="Q267"></a>

## Q267 — ApproveCaseCommand is a record with reviewCaseId, remark, and operator. Who sets the operator field and how is it secured?

### 中文筆記

operator 欄位的設置路徑：ReviewApiController.approveCase() 收到 HTTP request 後，從 Spring Security context 取出當前 authenticated user：SecurityContextHolder.getContext().getAuthentication().getName() 或 @AuthenticationPrincipal AuthenticatedUser user，得到 username。Controller 建立 ApproveCaseCommand(reviewCaseId, request.remark(), username)，username 就是 operator。安全性：/api/v1/review/** 需要 hasAnyRole("REVIEWER", "ADMIN")（SecurityConfig 的 URL 規則 + method-level @PreAuthorize），未登入的 request 不能到達 approveCase() endpoint；登入後的 username 來自 Spring Security（已被驗證的 UserDetails.getUsername()），不是來自 request body（client 不能偽造 operator）。若 operator 來自 request body（@RequestBody ApproveRequest.operator），任何 reviewer 可以填入別人的 username，偽造操作者身份。從 Security context 取 username 確保 operator 是真實的 authenticated user。

### Category

Spring Security

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ApproveCaseCommand.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ReviewApiController.java

### Verified classes and methods

- SecurityConfig.securityFilterChain()
- SecurityConfig.passwordEncoder()
- LoginSuccessHandler.onAuthenticationSuccess()

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Where the authenticated username comes from and why it cannot be user-supplied is a security correctness question.

### Possible follow-up questions

- What is @AuthenticationPrincipal and how does it simplify getting the current user?
- What happens if a REVIEWER tries to approve their own application?
- How would audit logging capture who approved the case?

### Verification status

- Verified


---

<a id="Q268"></a>

## Q268 — RedisIdempotencyStore is activated by @ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis"). What happens when this property is absent — which IdempotencyStore bean is active?

### 中文筆記

Bean 啟動邏輯分析：RedisIdempotencyStore：@ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis")，沒有 matchIfMissing = true，所以若 property 不存在 → bean 不啟動。InMemoryIdempotencyStore（或 InMemoryIdempotencyStore）：需要查看其 @Conditional 設定。若 in-memory store 有 @ConditionalOnProperty(..., matchIfMissing = true)，則 property 不存在時 in-memory store 是 default。application-dev.yml 設定：tlbank.idempotency.store: redis，表示 dev 環境啟用 Redis store（需要 Redis 在 localhost:6379 可用）。若沒有這個 property（如純測試環境沒有設定），in-memory store 生效，IdempotencyService 使用 in-memory 實作。這個設計讓：需要 Redis 的環境明確 opt-in（設定 property）；不需要 Redis 的環境（如純 unit test）自動使用 in-memory，不需要啟動 Redis container。與 MockSmsSender 的 matchIfMissing = true 類似，但方向相反：SMS mock 是 safe default（沒設定 → mock），idempotency 也是 safe default（沒設定 → in-memory）。

### Category

Redis and Idempotency

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java
- sp2-springboot/src/main/resources/application-dev.yml

### Verified classes and methods

- IdempotencyService.execute()
- IdempotencyStore.tryAcquireLock()
- IdempotencyStore.releaseLock()
- RedisIdempotencyStore
- InMemoryIdempotencyStore

### Execution flow

1. Controller receives optional Idempotency-Key header on create application
2. IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find()
3. On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock()
4. On hit with same hash, rebuild prior ResponseEntity; on hash conflict, return conflict error

### Why this may be asked

@ConditionalOnProperty with and without matchIfMissing is a Spring conditional configuration question.

### Possible follow-up questions

- What exception would occur if neither RedisIdempotencyStore nor InMemoryIdempotencyStore was active?
- How would you verify in a test that InMemoryIdempotencyStore is the active bean?
- Could you use @Profile("dev") instead of @ConditionalOnProperty for the same effect?

### Verification status

- Verified


---

<a id="Q269"></a>

## Q269 — IdempotencyService.rebuildResponse() uses raw type ApiResponse.class in Jackson deserialization. What is the type erasure problem, and what does @SuppressWarnings("unchecked") indicate?

### 中文筆記

Type erasure 的問題：objectMapper.readValue(entry.responseBody(), ApiResponse.class) 只告訴 Jackson「反序列化為 ApiResponse」，但 ApiResponse<T> 的泛型參數 T 在 runtime 因 type erasure 而消失（JVM 不知道 T 是什麼）。結果：data 欄位會被反序列化為 LinkedHashMap（Jackson 的預設 Object 反序列化），不是 ApplicationSummaryResponse。@SuppressWarnings("unchecked")：告訴編譯器「我知道這個 unchecked cast 有風險（ApiResponse.class → ApiResponse<T>），我願意承擔責任」。實際影響：若 cached response 的 data 欄位被作為 ApiResponse<ApplicationSummaryResponse> 使用，而 data 實際是 LinkedHashMap，在後續 code 嘗試存取 response.getBody().data() 的 ApplicationSummaryResponse 特有方法（如 .applicationId()），就會拋 ClassCastException。在目前 usage 中：rebuildResponse() 的結果直接返回給 HTTP client（Jackson 再序列化為 JSON），LinkedHashMap 序列化的 JSON 結果與 ApplicationSummaryResponse 相同，所以在 HTTP response 層面沒有可見的問題，但在 Java code 中嘗試 downcast 就會出錯。

### Category

Java 17 Features

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java

### Verified classes and methods

- IdempotencyService.rebuildResponse() (private)

### Execution flow

1. Controller receives optional Idempotency-Key header on create application
2. IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find()
3. On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock()
4. On hit with same hash, rebuild prior ResponseEntity; on hash conflict, return conflict error

### Why this may be asked

Type erasure and generic deserialization is a common Java generic trap; tests understanding of JVM type erasure.

### Possible follow-up questions

- How would you use TypeReference<ApiResponse<ApplicationSummaryResponse>> to solve this?
- What is a ClassCastException at runtime from type erasure vs. compile-time type safety?
- In what scenario would the current implementation fail with a ClassCastException?

### Verification status

- Verified


---

<a id="Q270"></a>

## Q270 — User.roles is a Set<Role> where Role is an enum, but UserEntity.roles is Set<String>. How is the mapping handled, and what is the risk of this gap?

### 中文筆記

Domain/Infrastructure 的 roles 型別 gap：Domain User.roles（Set<Role>）：Role 是 enum（ROLE_ADMIN、ROLE_REVIEWER、ROLE_USER）。Infrastructure UserEntity.roles（Set<String>）：@ElementCollection + @Column(name = "role") 儲存 String（如 "ADMIN"、"REVIEWER"、"APPLICANT"）。Mapping 路徑：UserDetailsServiceImpl.loadUserByUsername() 用 UserEntity.getRoles()（Set<String>）傳給 toSpringRole(String role) 做 Spring authority 轉換，不走 User domain aggregate。若有 UserRepository → UserRepositoryImpl.toDomain() 將 UserEntity.roles（Set<String>）轉成 User.roles（Set<Role>）：需要 Role.valueOf(roleString) 或 switch 轉換，若 DB 中有 "INVALID_ROLE" 字串，Role.valueOf() 拋 IllegalArgumentException。風險：DB 中的 role string 和 Java enum name 不一致時（如 DB 是 "APPLICANT" 但 Role enum 沒有 APPLICANT，只有 ROLE_USER），mapping 邏輯必須有轉換表（"APPLICANT" → Role.ROLE_USER），若未處理則 UserRepository 拋 exception，用戶無法登入。

### Category

Repository Pattern (Ports and Adapters)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/user/User.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/user/Role.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java

### Verified classes and methods

- User
- UserEntity
- UserDetailsServiceImpl.toSpringRole() (private)

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Type mismatch between domain and infrastructure model is a common Clean Architecture impedance question.

### Possible follow-up questions

- What is the role of UserDetailsServiceImpl.toSpringRole() in bridging this gap?
- How would you add a new role SUPERVISOR and ensure all layers (domain, infrastructure, Spring Security) stay consistent?
- Should UserEntity.roles use @Enumerated(EnumType.STRING) on a Set<Role> instead?

### Verification status

- Verified


---

<a id="Q271"></a>

## Q271 — What is the purpose of the User.updateLastLoginAt() method on the domain aggregate, and does it violate any DDD principle?

### 中文筆記

updateLastLoginAt() 的存在理由：LoginSuccessHandler.onAuthenticationSuccess() 呼叫 userJpaRepository.findByUsername(username) 取 UserEntity，然後直接呼叫 user.updateLastLoginAt(now)，再 userJpaRepository.save(user)。這裡的 user 是 UserEntity，不是 domain User aggregate。User domain aggregate 也有 lastLoginAt（@Getter）但 LoginSuccessHandler 沒有透過 domain User aggregate 來更新——它直接操作 JPA entity。分析：在 LoginSuccessHandler 中，更新 lastLoginAt 發生在 Spring Security filter chain 中，UserAppService 沒有對應的 updateLastLogin(username) use case，所以沒有透過 domain aggregate 路徑。若嚴格遵守 DDD，應該有 UserAppService.recordLogin(userId) → user.recordLastLoginAt(now) → userRepository.save(user) 的完整路徑。目前的實作是「security concern（記錄最後登入時間）直接操作 JPA entity」的 pragmatic shortcut，繞過了 domain aggregate，是已知的架構 impurity，但在 security filter chain context 中的合理 trade-off。

### Category

Domain-Driven Design

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/user/User.java
- sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java

### Verified classes and methods

- UserEntity.updateLastLoginAt()
- User

### Execution flow

1. Browser or client posts credentials to form login processing URL
2. DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder
3. LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect

### Why this may be asked

Direct JPA entity manipulation inside a security handler is an architectural impurity question; tests critical reading of domain/infrastructure boundaries.

### Possible follow-up questions

- What would the correct Clean Architecture path for updateLastLoginAt look like?
- Is it acceptable to bypass the domain aggregate for a non-business-critical field like lastLoginAt?
- How would you add a test to verify that lastLoginAt is updated on login?

### Verification status

- Verified


---

<a id="Q272"></a>

## Q272 — The OtpAppService.sendOtp() method cancels any existing PENDING OTP and creates a new one in a single @Transactional. What is the transaction isolation implication?

### 中文筆記

Transaction isolation 的影響：sendOtp() 的 @Transactional 預設 isolation level 是 READ_COMMITTED（Spring 的 default，繼承 DB driver 設定；SQL Server 的 default 也是 READ_COMMITTED）。操作序列：findLatestPendingByMobile() → cancel existing → save(existing) → create new → save(newOtp)——這些操作在同一個 transaction T1 中。若另一個 transaction T2 在 T1 commit 前也做 findLatestPendingByMobile()：在 READ_COMMITTED 下，T2 看不到 T1 尚未 commit 的 cancel 操作（T1 的 UPDATE 是 uncommitted），T2 也找到「原始 PENDING OTP」並嘗試 cancel + create。這正是前面提到的 race condition（Q137）。解決方式：升高 isolation level 到 SERIALIZABLE（資源成本高）；或用 pessimistic lock：SELECT ... FOR UPDATE NOWAIT 讓第一個 transaction lock 住 OTP record，第二個 transaction 立即失敗；或 Redis distributed lock。目前的 @Transactional 提供 ACID 內的 atomic cancel+create，但不能防止並發 race。

### Category

Transactions

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java

### Verified classes and methods

- OtpAppService.sendOtp()

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

Transaction isolation and concurrent OTP generation is an advanced concurrency question with real security implications.

### Possible follow-up questions

- What is pessimistic locking and what JPA annotation would you use for it here?
- What is the SERIALIZABLE isolation level and what does it prevent?
- How would a Redis distributed lock on {mobile}:otp-send prevent the race without changing the DB transaction?

### Verification status

- Verified


---

<a id="Q273"></a>

## Q273 — ReviewCase uses if-else in transitionTo(), but ApplicationStatus uses a map. Write a parameterized JUnit test to exhaustively verify ReviewCase.transitionTo() transitions.

### 中文筆記

Exhaustive parameterized test 的設計：ReviewCaseTest 需要驗證所有合法的 transition 和所有非法的 transition：
java@ParameterizedTest
@MethodSource("legalTransitions")
void transitionTo_legal_shouldSucceed(ReviewStatus from, ReviewStatus next) {
 ReviewCase rc = buildReviewCase(from);
 assertThatCode(() -> rc.startReview("op")).doesNotThrowAnyException();
 // or approve / reject depending on transition
}

@ParameterizedTest
@CsvSource({
 "PENDING, APPROVED", // skip UNDER_REVIEW
 "PENDING, REJECTED", // skip UNDER_REVIEW
 "UNDER_REVIEW, PENDING",// no backward
 "APPROVED, UNDER_REVIEW",// terminal
 "APPROVED, REJECTED" // terminal
})
void transitionTo_illegal_shouldThrowWorkflowException(ReviewStatus from, String nextStr) {
 ReviewStatus next = ReviewStatus.valueOf(nextStr);
 ReviewCase rc = buildReviewCase(from);
 assertThatThrownBy(() -> rc.transitionTo_viaPublicMethod(next))
 .isInstanceOf(WorkflowException.class);
}
實際測試策略：ReviewCase.transitionTo() 是 private，不能直接呼叫；需要透過 public method（startReview()、approve()、reject()）間接測試。@EnumSource(ReviewStatus.class) + @EnumSource(ReviewStatus.class) 兩個 @ParameterizedTest 組合：對每個 from status，嘗試所有 to status，只有合法的不拋 exception。這種設計等同於 ApplicationStatusTest.canTransitionTo_allIllegalTransitions_shouldReturnFalse() 的風格。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java
- sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationStatusTest.java

### Verified classes and methods

- ReviewCase.transitionTo() (private)

### Execution flow

1. Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...)
2. Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo()
3. Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory

### Why this may be asked

Writing exhaustive parameterized tests for state machines is a practical testing skill question.

### Possible follow-up questions

- How does @MethodSource differ from @CsvSource in parameterized tests?
- How does ApplicationStatusTest test all transitions without duplicating the transition table?
- What test coverage does exhaustive state machine testing give you that happy-path tests miss?

### Verification status

- Verified


---

<a id="Q274"></a>

## Q274 — cancelApplication() in ApplicationAppService does not publish a domain event, but submitApplication() does. Is this inconsistency intentional?

### 中文筆記

已記錄的不一致（Known Limitation #7）：ApplicationCancelledEvent 在 domain event package 中定義（domain/event/ApplicationCancelledEvent.java），但 cancelApplication() 在 ApplicationAppService 中呼叫 application.cancel(operator, reason) 後只 save，沒有 eventPublisher.publishEvent(new ApplicationCancelledEvent(...))。對比：submitApplication() 發布 ApplicationSubmittedEvent；ReviewAppService.approveCase() 發布 ApplicationApprovedEvent；rejectCase() 發布 ApplicationRejectedEvent。業務影響：(1) 申請人在取消申請後，不會收到取消確認的 SMS/Email（NotificationEventHandler 沒有 onApplicationCancelled() handler，即使有也不會被觸發）；(2) 若有 downstream 系統需要知道申請被取消（如積分系統），無法透過 event 感知。是否刻意：ADR 和 20-maintenance 明確說這是 open issue，不是設計決策，是 sprint roadmap 中遺漏的實作。修復只需兩行：eventPublisher.publishEvent(new ApplicationCancelledEvent(applicationId, reason, LocalDateTime.now()))，並在 NotificationEventHandler 加 onApplicationCancelled() handler。

### Category

Domain Events

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationCancelledEvent.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- ApplicationAppService.cancelApplication()
- ApplicationAppService.submitApplication()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Missing event publication is a specific known technical debt question; tests whether the candidate can trace complete event flows.

### Possible follow-up questions

- Write the two lines of code needed to publish ApplicationCancelledEvent in cancelApplication().
- What @EventListener method would you add to NotificationEventHandler for the cancel event?
- Should cancel notification be a try-catch pattern (best-effort) or a required operation?

### Verification status

- Verified


---

<a id="Q275"></a>

## Q275 — The Applicant value object's compact constructor only validates fullName. Why are nationalId, dateOfBirth, and address not validated at the domain layer?

### 中文筆記

不在 domain 驗證的原因分析：Applicant's compact constructor：只檢查 fullName != null && !blank；nationalId、dateOfBirth 沒有 domain-level validation；mobile 是 MobileNumber 型別（已在 MobileNumber 驗證）；email 是 Email 型別（已在 Email 驗證）；address 是 Address 型別（已在 Address 驗證）。未驗證的合理性：(1) nationalId 格式（台灣身分證：1 個字母 + 9 個數字）是 infrastructure/regulatory concern，不同客群可能有不同 ID 格式（外國人居留證格式不同）；domain 若硬編碼 ^[A-Z][12]\d{8}$ 就綁死了台灣身分證格式；(2) dateOfBirth：最低年齡（如 20 歲）是業務規則，但目前沒有明確的 domain business rule 文件，是有意保留 flexibility 還是 oversight？若要驗證，Applicant 需要 Clock 依賴，讓 value object 依賴時間，稍微複雜；(3) ApplicantRequest 有 @NotBlank String nationalId，controller 層的 @Valid 做基本 not-blank check，避免空值進 domain。結論：部分驗證在 DTO（controller），部分在 value object（MobileNumber、Email、Address），部分尚未實作（nationalId format）。

### Category

Validation and Exception Handling

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Applicant.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/ApplicantRequest.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Partial validation at different layers is a deliberate vs. accidental design question that reveals the candidate's critical reading skills.

### Possible follow-up questions

- How would you add Taiwan national ID format validation without hardcoding a Taiwan-specific pattern in the core domain?
- Should dateOfBirth have a minimum age validation? At which layer?
- What is the risk of moving all validation to the DTO layer (controller) and removing domain-level validation?

### Verification status

- Documentation-only


---

<a id="Q276"></a>

## Q276 — The ApplicationSummaryResponse and ApplicationDetailResponse are in the application.application.service package rather than application.dto. Is this placement consistent with the rest of the codebase?

### 中文筆記

Response DTO 位置的一致性分析：CreateApplicationRequest、CancelApplicationRequest 等 request DTO 在 application.dto.request；DocumentUploadResponse 等 response DTO 在 application.dto.response。但 ApplicationSummaryResponse、ApplicationDetailResponse、WorkflowHistoryResponse、MaskedApplicantResponse 在 application.application.service（與 ApplicationAppService 同一個 package）。可能的設計意圖：讓 response 類別和生產它們的 service 在同一個 package（高內聚），減少 cross-package import；ApplicationSummaryResponse 只由 ApplicationAppService 使用，放在 service package 減少 public API surface。不一致的影響：若其他 service 也需要 ApplicationSummaryResponse（如 ReviewAppService 在 summary view 中使用），就需要跨 package import，這個設計的 cohesion 假設就失效了。實際上 ReviewAppService.toSummary() 使用了自己的 ReviewCaseSummaryResponse（在 application.review.service），兩個 service 的 response 分開，cohesion 策略在 review module 是一致的。可以算 domain-module-based packaging（每個 module 有自己的 request/response），不是全局的 dto package。

### Category

Clean / Hexagonal Architecture

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationSummaryResponse.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/dto/response/DocumentUploadResponse.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Inconsistent packaging is a code organization observation that tests attention to structure; candidates may notice this without being told.

### Possible follow-up questions

- Which packaging style do you prefer — module-based or layer-based? What are the trade-offs?
- Would you enforce a consistent packaging style with a code convention document or an automated check?
- What is the Package by Feature pattern and does TLBank follow it?

### Verification status

- Documentation-only


---

<a id="Q277"></a>

## Q277 — The @Auditable annotation's action() attribute uses AuditAction.OTP_VERIFY_SUCCESS as the name even for failed verifications. How does AuditAspect remap this for failures?

### 中文筆記

Action remapping 機制（resolveFailureAction()）：OtpAppService.verifyOtp() 有 @Auditable(action = AuditAction.OTP_VERIFY_SUCCESS)。當 OTP 驗證失敗（OtpRecord.verify() 拋 exception），AuditAspect 的 catch 區塊執行：AuditAction failureAction = resolveFailureAction(auditable.action())，若 action == OTP_VERIFY_SUCCESS → return OTP_VERIFY_FAILED（AuditDetailBuilder 或 AuditAspect 中有這個 mapping）；auditLogWriter.saveAsync(AuditLog{action = OTP_VERIFY_FAILED, result = "FAILURE", detail = ex.getMessage()})。設計理由：若 annotation 改為 @Auditable(action = OTP_VERIFY_ATTEMPT)，無法區分「成功驗證」和「失敗嘗試」在 audit log 中的 action；若用兩個 annotation（@AuditableOnSuccess(action = OTP_VERIFY_SUCCESS) + @AuditableOnFailure(action = OTP_VERIFY_FAILED)），annotation 增多；目前方案：用 SUCCESS variant 作為 annotation value，aspect 在 failure 時自動映射到 FAILED variant，讓 audit log 清楚記錄「成功」vs「失敗」的 OTP 驗證，是合理的設計。

### Category

Audit Logging and AOP

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAction.java

### Verified classes and methods

- AuditAspect.resolveFailureAction() (private)
- OtpAppService.verifyOtp()

### Execution flow

1. Client calls OTP send or verify API
2. OtpAppService.sendOtp() / verifyOtp() loads parameters and OtpRecord
3. OtpRecord.verify() enforces expiry and retry; success may call Application.verifyOtp()

### Why this may be asked

The success/failure action remapping is a subtle but elegant audit design; tests deep reading of the AuditAspect implementation.

### Possible follow-up questions

- What would the audit log show if OTP_VERIFY_SUCCESS was not remapped on failure?
- How would you add a failure remapping for APPLICATION_SUBMIT → APPLICATION_SUBMIT_FAILED?
- Could you use a different annotation design (e.g., separate onSuccess and onFailure attributes) to express this more explicitly?

### Verification status

- Verified


---

<a id="Q278"></a>

## Q278 — The Thymeleaf web controllers (AuthController, ApplicationWebController, AdminController) exist alongside the REST API controllers. What is the dual-surface architecture?

### 中文筆記

雙介面架構：REST API controllers（/api/v1/**）：返回 JSON，由前端 SPA、curl、行動 app 呼叫；Thymeleaf web controllers（/login、/apply/**、/admin/**、/review/**）：返回 HTML pages，由 browser 直接存取，是 server-rendered UI。TLBank 的雙介面設計理由：(1) 申請人 flow（/apply/**）：Thymeleaf 頁面（ApplicationWebController）提供填表 → OTP → 文件上傳的完整 web flow，不需要前端 SPA 框架；(2) Staff portal（/admin/**、/review/**）：Thymeleaf 頁面讓 reviewer/admin 直接 browser 操作，不需要部署獨立的前端 app；(3) API 同時開放：同樣的業務操作可以透過 REST API 執行（系統整合），也可以透過 Thymeleaf UI 執行（human operator）。共享 Service 層：ApplicationAppService.submitApplication() 被 REST controller（ApplicationApiController）和 Web controller（ApplicationWebController）都呼叫，業務邏輯只在 application service，不重複。這是 Spring Boot 常見的 Server-Side Rendering + REST API 混合架構。

### Category

REST API Design and OpenAPI

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/web/ApplicationWebController.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Dual-surface architecture (API + SSR) is a common enterprise Java pattern; tests understanding of when and why to use both.

### Possible follow-up questions

- What Spring annotation makes a controller return JSON vs. an HTML view?
- How does @ResponseBody (via @RestController) differ from a method that returns a String view name?
- What would the SPA (frontend JS) approach look like as an alternative to Thymeleaf?

### Verification status

- Documentation-only


---

<a id="Q279"></a>

## Q279 — If a new developer joins and wants to understand the codebase in 30 minutes, what path would you recommend through the documentation?

### 中文筆記

30 分鐘 onboarding 路徑：(1) 5 分鐘 — docs/handbook/07-cheat-sheet.md：一頁速覽，state machine、技術棧、feature index，快速建立 mental model；(2) 5 分鐘 — docs/handbook/00-project-overview.md §1（business problem）和 §3（架構圖）：了解系統要解決什麼問題，monolith 還是微服務；(3) 5 分鐘 — docs/decisions/ 的 ADR 0001（架構）、ADR 0006（session vs JWT）：理解最重要的兩個技術決策和其理由，這些 ADR 解釋了為什麼這樣設計而非其他方式；(4) 5 分鐘 — src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java（state machine）和 Application.java（aggregate）：看實際 code 驗證 ADR 的說法；(5) 5 分鐘 — docs/handbook/02-architecture-handbook.md 的 feature → file map table：了解「我想改 OTP 邏輯，要找哪個 class」；(6) 5 分鐘 — docs/design/20-maintenance-and-future-enhancement.md：了解 known limitations，避免「為什麼 Terraform 只有 local provider」的困惑。這個路徑：概念 → 決策 → code → 地圖 → honest gaps，30 分鐘後新開發者可以找到任何 feature 的入口並知道設計背後的理由。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/handbook/07-cheat-sheet.md
- sp2-springboot/docs/decisions/0001-use-clean-architecture.md
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Ability to guide others through a codebase reveals communication skills and documentation awareness.

### Possible follow-up questions

- What would a 5-minute onboarding look like if the new developer was senior?
- What is missing from the documentation that would make onboarding faster?
- How would you maintain documentation freshness as the codebase evolves?

### Verification status

- Documentation-only


---

<a id="Q280"></a>

## Q280 — What is the primary difference in risk profile between this portfolio project and a bank system you would work on at Trend Micro or a similar company?

### 中文筆記

Portfolio vs 企業系統的 risk profile 差異：核心差異在「失敗的後果」：Portfolio：失敗 = 面試官問了你不知道的問題，或 demo 時某個功能沒跑起來。後果有限，可以 iterate quickly，document limitations honestly。企業系統（如 Trend Micro 的後端）：失敗 = 客戶資料洩漏、系統停機影響幾百萬用戶、法律責任、SLA breach。具體 risk 差異：(1) Data integrity：portfolio 可以 DROP TABLE（Known Limitation 的 V14），企業系統每次 schema change 都需要 DBA review、staging canary、rollback plan；(2) Security：portfolio 的 permitAll() applicant endpoints 在企業環境是 security finding，需要 pentest + fix before go-live；(3) Availability：portfolio 的 single Mac staging 可以接受（deploy-staging 只是 demo），企業系統需要 N+1 redundancy、multi-AZ、automated failover；(4) Audit & Compliance：portfolio audit log 有 OTP plaintext（Known Limitation），企業環境會觸發 PCI DSS violation；(5) On-call：portfolio 沒有 alerting，企業系統有 24×7 on-call、PagerDuty、runbook。這個誠實的 risk profile 差異讓面試官看到你理解 production engineering 的責任，而非只看到 portfolio 的技術能力。

### Category

Behavioral Questions Based on the Project

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Understanding the gap between portfolio and production shows maturity; candidates who think their portfolio is already production-ready reveal naivety.

### Possible follow-up questions

- What is a runbook and would you write one for TLBank?
- How would you approach your first production deployment if you were hired tomorrow?
- What question would you ask a senior engineer on your first day to understand the team's production risk tolerance?

### Verification status

- Documentation-only


---

<a id="Q281"></a>

## Q281 — How would you explain the difference between @Transactional on a Spring service method and @Transactional in an @Async context, using TLBank's AuditLogWriter as an example?

### 中文筆記

兩種 @Transactional 上下文的差異（AuditLogWriter 為例）：正常同步 @Transactional（ApplicationAppService.createApplication()）：Spring AOP proxy 攔截方法呼叫，在呼叫前 open transaction（從 DataSource connection pool 取 connection），方法結束後 commit/rollback。@Async + PROPAGATION_REQUIRES_NEW（AuditLogWriter.saveAsync()）：saveAsync() 被標記 @Async，Spring 在一個 thread pool thread 上執行它；在 async thread 上，沒有 caller 的 transaction（不同 thread 沒有 transaction propagation）；TransactionTemplate(transactionManager) 的 PROPAGATION_REQUIRES_NEW 在 async thread 上 explicitly begin a new transaction，執行 auditLogRepository.save(auditLog)，commit，完成。關鍵差異：(1) 同步 @Transactional：transaction 附著在 calling thread；(2) async thread 上，calling thread 的 transaction 不存在，必須 explicitly new transaction；(3) 若 async thread 的 save() 失敗，caller 的 transaction 不受影響（獨立 transaction）。這就是 PROPAGATION_REQUIRES_NEW 在 async context 中有特殊意義的原因：不是「暫停 caller 的 transaction」（caller 在不同 thread），而是「建立一個沒有父 transaction 的新 transaction」。

### Category

Transactions

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java
- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java

### Verified classes and methods

- AuditLogWriter.saveAsync()
- ApplicationAppService.createApplication()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The intersection of @Async and transaction propagation is a senior Spring question; tests understanding of thread-bound transaction context.

### Possible follow-up questions

- What happens to the @Async thread's transaction if the DataSource connection pool is exhausted?
- Could you use @Transactional(propagation = REQUIRES_NEW) on saveAsync() itself instead of TransactionTemplate?
- Why does TransactionTemplate give more explicit control than @Transactional on an async method?

### Verification status

- Verified


---

<a id="Q282"></a>

## Q282 — How does AuditAspect combine with @Auditable, and what does AuditDetailBuilder sanitize before persistence?

### 中文筆記

方法標註 @Auditable 後，AuditAspect 於呼叫前後寫入稽核。AuditDetailBuilder 會過濾 password、otpCode、nationalId 等敏感欄位，並以 regex 將 password= 值遮罩，避免明文進入 audit_logs.detail。

### Category

Audit Logging and AOP

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/Auditable.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditDetailBuilder.java

### Verified classes and methods

- AuditAspect
- Auditable
- AuditDetailBuilder

### Execution flow

1. Annotated application/security method executes
2. AuditAspect intercepts and builds AuditLog fields
3. AuditDetailBuilder sanitizes detail; persistence via audit repository/async writer

### Why this may be asked

Validates AOP cross-cutting knowledge tied to this codebase PII masking rules.

### Possible follow-up questions

- Which actions are audited on login versus business methods?
- Is audit writing synchronous or async?
- What happens if detail sanitization is skipped?

### Verification status

- Verified


---

<a id="Q283"></a>

## Q283 — The project has a TlbankLendingApplicationTests smoke test. What does it test, and why is a smoke test valuable in CI?

### 中文筆記

Smoke test 的作用：TlbankLendingApplicationTests 通常只有：@SpringBootTest + @ActiveProfiles("dev") 在 class level，測試 method 可能是空的（@Test void contextLoads() {}）。這個空測試實際上驗證的是：Spring Boot 可以成功啟動（所有 bean 可以被建立、所有 @Autowired 可以被解析、@PostConstruct 不拋 exception、Flyway migration 在 H2 上全部通過、ddl-auto: validate 沒有 schema mismatch）。在 CI 的 value：若任何 bean 定義有問題（circular dependency、missing @Bean、Flyway migration failure），這個最簡單的 smoke test 會 fail，讓開發者在 push 後幾秒就知道「Application 根本跑不起來」，而不是在 staging deploy 後 30 分鐘才發現 container 一直 crash。成本極低（只啟動一次 Spring context，然後 pass），是 CI 的第一道防線。在 ApplicationFlowIntegrationTest 的上下文中，contextLoads() 和 integration test 共享同一個 Spring context（Spring Test context caching），不多花 startup 時間。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Basic

### Verified source files

- sp2-springboot/src/test/java/com/tlbank/lending/TlbankLendingApplicationTests.java

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

Smoke tests are a fundamental CI quality gate; tests understanding of their purpose vs. functional tests.

### Possible follow-up questions

- What is the difference between a smoke test and a sanity test?
- If contextLoads() passes but ApplicationFlowIntegrationTest fails, what does that tell you?
- Would you add a smoke test that checks the database connection and Redis connection?

### Verification status

- Documentation-only


---

<a id="Q284"></a>

## Q284 — CacheManagementService accesses CachedCardProductRepository directly rather than through the CardProductRepository port. Is this a Clean Architecture violation?

### 中文筆記

依賴分析：CacheManagementService 注入 CachedCardProductRepository（具體 infrastructure class），而非 CardProductRepository（domain port interface）。Clean Architecture 的 violation 判斷：CacheManagementService 在 application.cache.service package，屬於 application layer；CachedCardProductRepository 在 infrastructure.cache package，屬於 infrastructure layer。Application layer 直接依賴 infrastructure class（不是 port interface）確實是違反依賴方向的 leak。理由分析：CacheManagementService.refreshProducts() 呼叫 cachedCardProductRepository.refreshCache()，這個 refreshCache() 方法是 CachedCardProductRepository 的特有方法（不在 CardProductRepository port 中），沒有 port 可以依賴。設計選項：(1) 在 CardProductRepository port 加 void refreshCache() 方法——但 cache refresh 是 infrastructure concern，不是 domain port 的職責，這讓 port 洩漏 infrastructure concern；(2) 建立 CacheRefreshPort interface，CachedCardProductRepository 實作它，CacheManagementService 注入 CacheRefreshPort——這保持 Clean Architecture 但增加一層抽象；(3) 接受目前的 impurity（和 ADR 0001 的其他 known leak 一起）——在不影響業務可測試性的情況下最 pragmatic。

### Category

Clean / Hexagonal Architecture

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java
- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java

### Verified classes and methods

- CachedCardProductRepository.refreshCache()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Identifying when a Clean Architecture violation is pragmatic vs. correctible is a mature engineering judgment question.

### Possible follow-up questions

- How would you define a CacheRefreshPort interface to fix this violation?
- Is refreshCache() a domain concern or an infrastructure concern?
- Would you fix this violation in the next sprint? Why or why not?

### Verification status

- Verified


---

<a id="Q285"></a>

## Q285 — What is the @Pstaging Maven profile in the Dockerfile's build command ./mvnw clean package -DskipTests -Pstaging? What does it affect?

### 中文筆記

-Pstaging Maven profile 的作用：在 pom.xml 中通常有 <profiles> section，staging profile 可以：(1) 設定 spring.profiles.active=staging（讓 Spring Boot 在 Docker container 中自動使用 application-staging.yml）；(2) 或 override 特定 build 設定（如跳過某些 plugin、使用不同的 resource filtering）。在 Dockerfile context：RUN chmod +x mvnw && ./mvnw clean package -DskipTests -Pstaging 建立 JAR，-Pstaging 確保 build 時帶入 staging 相關的配置（如 SQL Server 方言 Flyway location）。注意：SPRING_PROFILES_ACTIVE 在 docker-compose.yml 的 environment section 也設定了（SPRING_PROFILES_ACTIVE: ${SPRING_PROFILES_ACTIVE:-staging}），這是 runtime 的 profile 設定，確保 Spring Boot 在容器中 runtime 使用 application-staging.yml，即使 JAR 中沒有 bake-in 的 default。兩者可以互補：Maven profile 影響 build-time（如 resource filtering）；Spring profile 影響 runtime（如 datasource 配置）。若 -Pstaging 不做任何 build-time 特殊配置，只是 semantic label，可以省略，-Pstaging 和不帶 -P 生成的 JAR 完全相同。

### Category

Docker and Docker Compose

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docker/app/Dockerfile
- sp2-springboot/pom.xml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. docker compose up builds/runs SQL Server and app services
2. App container uses staging profile and SQL Server migrations
3. Healthcheck hits actuator endpoint (currently auth-gated)

### Why this may be asked

Maven build profiles and Spring runtime profiles are distinct; tests whether the candidate can distinguish them.

### Possible follow-up questions

- What is the difference between a Maven profile (-Pstaging) and a Spring Boot profile (spring.profiles.active=staging)?
- What happens if SPRING_PROFILES_ACTIVE is not set in Docker Compose?
- Could you remove -Pstaging from the Dockerfile build command without affecting the staging deployment?

### Verification status

- Documentation-only


---

<a id="Q286"></a>

## Q286 — ApplicationAppService.cancelApplication() takes an operator parameter. Who provides this operator in the REST API controller, and is it validated?

### 中文筆記

operator 的來源：ApplicationApiController 的 cancel endpoint（POST /api/v1/applications/{id}/actions/cancel）是 permitAll()（申請人可以取消自己的申請，不需要登入）。問題：若 endpoint 是 anonymous，SecurityContextHolder.getAuthentication().getName() 返回 "anonymousUser"，不是有意義的 operator。目前可能的設計：(1) 從 request body 取 operator（CancelApplicationRequest.operator）——但 anonymous 呼叫者可以填入任意名字，偽造 operator；(2) operator = "APPLICANT"（硬編碼，表示這是申請人操作）——不能識別是哪個申請人；(3) 從 OTP 驗證的 context 取（需要某種 applicant session）——目前沒有這個機制。WorkflowHistory 中的 operator 欄位（action_by）對 anonymous 申請人就會是空白或 "APPLICANT" 這個 literal string，缺少真正的身份識別。這是 permitAll() applicant endpoint 的核心問題之一：無法追蹤是哪個申請人執行了 cancel，影響 audit trail 的可信度。

### Category

Security and Production Risks

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java

### Verified classes and methods

- ApplicationAppService.cancelApplication()
- WorkflowHistory

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Operator attribution for anonymous API calls is a security and audit correctness question.

### Possible follow-up questions

- What would you log as the operator for an anonymous applicant's cancel action?
- How would you link a cancel action to a specific applicant's OTP-verified identity?
- What is the audit implication if operator is always "APPLICANT" for all cancel actions?

### Verification status

- Verified


---

<a id="Q287"></a>

## Q287 — Given TLBank's tech stack, which Trend Micro Cloud Backend DevOps Engineer job requirements align most directly, and which would need further development?

### 中文筆記

技術棧對比（依據 MJ 的 background 和 Trend Micro 常見 JD 推斷）：直接 align 的技術：Java（TLBank 是 Java 17 + Spring Boot 3.x，MJ 明確提到 Java 是核心）、Git/GitHub（GitHub Actions CI/CD，ADR 0004）、CI/CD pipelines（5-job GitHub Actions pipeline）、Docker（multi-stage Dockerfile，Docker Compose）、Terraform/IaC（local provider，workflow 熟悉）、Spring Security / API design（RESTful，session auth，RBAC）。需要進一步發展的：(1) Python（MJ 提到 Python，TLBank 只有 Java；Trend Micro 的 threat analysis 用 Python 做 scripting）；(2) AWS 實際操作（TLBank Terraform 是 local-only，沒有 ECS/RDS/ALB 等 AWS 資源經驗）；(3) 資安 Domain 知識（Trend Micro 是 cybersecurity 公司，熟悉 malware detection、APK analysis pipeline 等）；(4) Go 語言（部分 Trend Micro cloud backend 用 Go）。面試策略：強調「Java backend + CI/CD + Docker + IaC workflow 的生產品質實踐」，誠實承認 AWS cloud 和 Python 是需要補強的，說明計劃（AWS provider skeleton、Python side project）。

### Category

Behavioral Questions Based on the Project

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/handbook/00-project-overview.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Matching your skills to the job requirements honestly is a career conversation that Trend Micro interviewers will have; tests self-awareness.

### Possible follow-up questions

- What would you do in the first 90 days to fill the gaps you identified?
- How would you explain the local-only Terraform to a Trend Micro engineer who uses AWS Terraform daily?
- What aspect of cybersecurity backend engineering excites you most?

### Verification status

- Documentation-only


---

<a id="Q288"></a>

## Q288 — ApplicationAppService has 7 public methods. What cohesion principle does this suggest, and when should it be split?

### 中文筆記

Cohesion 分析：7 public methods：createApplication()、getApplication()、uploadDocuments()、submitApplication()、cancelApplication()、findAllEnabledProducts()、toMaskedApplicant()（private helper）。這些方法都屬於「Credit Card Application lifecycle」這個同一個 use case 範圍，沒有明顯的 sub-domain 混淆。findAllEnabledProducts() 稍微偏離（是 product catalog，不是 application lifecycle），但從 applicant 的角度（申請時需要查詢可用產品），放在 ApplicationAppService 是合理的使用者旅程視角。Single Responsibility Principle：若 7 個方法都圍繞同一個 business process（申請的建立、進展、查詢），責任是 cohesive 的。分拆的 trigger：(1) 某個方法的 dependency 集合（注入的 collaborator）和其他方法完全不重疊；(2) 某些方法的 change frequency 明顯不同（product catalog 很少改，application flow 常改）；(3) 某個 use case 有獨立的 team 負責（在 TLBank 單人 portfolio 場景，不適用）。目前的 7 個方法不需要拆分，ApplicationAppService 的職責是 cohesive 的。若未來加入申請 amend、appeal 等功能，可以考慮拆出 ApplicationQueryService 和 ApplicationLifecycleService。

### Category

Clean / Hexagonal Architecture

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Service class cohesion is an important design question that reveals understanding of Single Responsibility Principle beyond buzzword level.

### Possible follow-up questions

- What is the Single Responsibility Principle, and how do you apply it to a service class?
- How many public methods is "too many" for a service class?
- Should findAllEnabledProducts() be moved to a dedicated CardProductAppService?

### Verification status

- Documentation-only


---

<a id="Q289"></a>

## Q289 — The .github/workflows/markdown.yml workflow exists. What does it do, and why is linting markdown files in CI valuable for a portfolio project?

### 中文筆記

markdown.yml 的功能：一個獨立的 GitHub Actions workflow，在 .md 文件變更時觸發，執行 markdownlint 或類似工具（如 DavidAnson/markdownlint-cli2-action），檢查：broken link syntax（[text](url)）、consistent heading levels（## 不在 # 之前）、trailing whitespace、list indent consistency、code block language tags。為什麼 markdown lint 對 portfolio project 有價值：(1) TLBank 的文件是 differentiator（21 份 SDD + 7 份 ADR + 9 份 handbook），文件品質直接影響 interviewer 的第一印象；(2) 自動 lint 防止 README 的 typo、broken internal link（[See ADR 0001](docs/decisions/0001.md) 路徑錯誤）；(3) 展示對 docs-as-code 的理解：文件和 code 一樣有 CI gate；(4) CI feedback：若任何文件 lint fail，PR 不能 merge，確保 main branch 的文件永遠是 lint-clean 的狀態。對於一個 portfolio，文件的完整性和一致性是展示工程師細心度的機會。

### Category

GitHub Actions CI/CD

### Difficulty

Basic

### Verified source files

- .github/workflows/markdown.yml
- sp2-springboot/docs/README.md
- sp2-springboot/docs/handbook/00-project-overview.md

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Push/PR triggers CI jobs in .github/workflows/ci.yml
2. build-test runs mvn clean verify; later jobs scan and may push GHCR image
3. deploy-staging runs only on workflow_dispatch against self-hosted macOS runner

### Why this may be asked

Documentation CI is an often-overlooked engineering practice; tests whether the candidate treats docs with the same rigor as code.

### Possible follow-up questions

- What markdownlint rule would catch a heading level skip (e.g., ###  directly after # )?
- What is docs-as-code and how does this workflow demonstrate it?
- What other documentation quality checks would you add besides markdown lint?

### Verification status

- Documentation-only


---

<a id="Q290"></a>

## Q290 — Looking at the full 21-document SDD set, what documentation gap do you think is most critical to fill before an actual production deployment?

### 中文筆記

最關鍵的文件缺口（honest assessment）：(1) Operations Runbook（最關鍵）：目前無。生產環境的 ops team 需要知道：如何 restart 服務、如何 rollback deployment、log 在哪裡 grep、如何 manually 觸發 OTP cleanup scheduler、DB backup / recovery 步驟、Redis failover 程序、on-call escalation path。沒有 runbook，on-call 工程師面對 3 AM incident 不知道怎麼診斷；(2) Incident Response Playbook：系統崩潰時的 checklist（先看 Actuator health → 看 error.log → 看 DB connection pool → 嘗試 restart）；(3) Security Runbook：如果發現有人在暴力嘗試 login，如何封鎖 IP、如何 revoke 特定 session；(4) Data Recovery Procedure：SQL Server backup 在哪裡、如何做 point-in-time recovery、documents volume 的 backup 策略；(5) Capacity Planning Document：預期多少 concurrent user、DB connection pool size 是否足夠、Redis memory 需要多少。目前的 20-maintenance-and-future-enhancement.md 有 future enhancement roadmap，但沒有 operational procedure。Gap 的 priority：production ops 文件是 real production 的 pre-requisite，但 portfolio 不需要，所以這個 gap 是 deliberate scope boundary。

### Category

Technical Debt and Known Limitations

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md
- sp2-springboot/docs/README.md
- sp2-springboot/docs/handbook/00-project-overview.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Identifying operational documentation gaps reveals production engineering maturity; the best candidates think beyond code.

### Possible follow-up questions

- What would go into the first version of a runbook for TLBank?
- How would you maintain a runbook so it doesn't become stale?
- What is a postmortem and how would you write one if TLBank had a staging outage?

### Verification status

- Documentation-only


---

<a id="Q291"></a>

## Q291 — What is the most technically challenging piece of code you wrote in TLBank, and how did you approach debugging it?

### 中文筆記

最挑戰性的 code 選項（選一個最有說服力的）：最佳答案：AuditAspect + AuditLogWriter（@Around + @Async + PROPAGATION_REQUIRES_NEW 的組合）。挑戰性：(1) @Async 和 @Around AOP 的 interaction：若 AuditAspect 呼叫 auditLogWriter.saveAsync()，需要確保 AOP proxy 正確工作（不是 self-invocation 繞過 proxy）；(2) PROPAGATION_REQUIRES_NEW 在 async thread 的語意和在同步 caller thread 的語意不同（前者是「begin standalone TX」，後者是「suspend caller TX + begin new TX」）；(3) AuditContext.clear() 在 finally 的時機：若 async thread 已開始執行，main thread 的 finally 清除 ThreadLocal 不影響 async thread（不同 thread 有各自的 ThreadLocal）；(4) Test 的 TestConfig 替換 executor 為 synchronous 的技術（AuditAspectTest）。Debug 方法：加 log.debug("[AUDIT] saving audit log for action={}") 在 saveAsync() 前後，確認執行時機；用 @Async executor 的 thread name prefix（如 [audit-]）在 log 中識別哪個 log 在 async thread 執行；寫 AuditAspectTest 驗證 failure case 的 audit log 也被寫入。

### Category

Behavioral Questions Based on the Project

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java
- sp2-springboot/src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java

### Verified classes and methods

- AuditLogWriter.saveAsync()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

The hardest code question reveals problem-solving depth and debugging methodology.

### Possible follow-up questions

- What debugging tool or technique helped you most in solving this?
- What would you have done differently knowing what you know now?
- How did writing the test (AuditAspectTest) help you understand the bug?

### Verification status

- Verified


---

<a id="Q292"></a>

## Q292 — If Jasper from Google gave you one piece of advice about the TLBank project, what do you think it would be, and how would you act on it?

### 中文筆記

Jasper 的可能建議（基於他 Google L3 level 的建議 + LeetCode focus）：最可能的技術建議：「 architecture 很好，但 LeetCode 才是 Google 面試的 gate。 architecture 知識對 L4+ 有用，但先確保 Medium 題在 10 分鐘內解得出來，再談 system design。」如何落實：(1) 繼續 NeetCode150 的完整進度（Sliding Window、Stack、Binary Search、Heap、DP 等剩餘 category）；(2) 每週 2-3 次計時練習（10-15 分鐘 Medium），建立肌肉記憶；(3) 暫時不要把時間分散到「加 AWS Terraform」或「接 real SMS API」，這些對 Google L3 面試不是 gate。另一個可能的建議：「在 LinkedIn 分享 architecture decision（如 session vs JWT）作為技術 post，讓更多人看到思維，這樣當你申請時 recruiter 已經知道名字。」行動：每兩週寫一篇 150 字的 LinkedIn post，解釋 TLBank 的一個設計決策，用 engineering philosophy framing（「為什麼 session 比 JWT 更適合 server-rendered app」），不是功能列表。

### Category

Behavioral Questions Based on the Project

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/handbook/00-project-overview.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Ability to internalize mentor feedback and translate it to actions reveals career learning agility.

### Possible follow-up questions

- What is the specific LeetCode problem type you find hardest right now?
- How do you balance LeetCode practice with TLBank development?
- What would you say to Jasper at your next LinkedIn touchpoint?

### Verification status

- Documentation-only


---

<a id="Q293"></a>

## Q293 — What soft skill was most important in building TLBank as a solo project, and how would that skill translate to a team environment?

### 中文筆記

關鍵 soft skill：Self-directed learning with calibrated scope（自主學習 + 準確的 scope 判斷）。Solo project 的挑戰：沒有 tech lead 告訴你「這個設計對」或「那個 scope 太大」，必須自己決定。在 TLBank 中：每次加新 component（Terraform、Redis、CI/CD）都面對「這個值得花時間嗎，還是 overengineering？」的問題。ADR 的 discipline 強迫自己說清楚「為什麼這樣做」，避免 scope creep（加功能是因為業務需要，不是因為技術好玩）；承認 Known Limitation 而非假裝 complete，防止 self-deception。Team 的翻譯：在 team 中，這個 skill 變成：(1) Pull request descriptions 說明「為什麼這樣改，trade-off 是什麼」；(2) In design review 時能分辨「這是必要的複雜度 vs overengineering」；(3) Sprint planning 時能說「這個功能我不確定能在本 sprint 完成，原因是...」（honest estimation）；(4) 接受 senior 的 review feedback 時，能區分「這是有道理的改進」vs「這只是 style preference」。

### Category

Behavioral Questions Based on the Project

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/handbook/00-project-overview.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Soft skills for team collaboration are as important as technical skills; this question reveals self-awareness about the transition from solo to team work.

### Possible follow-up questions

- What was the hardest part of working without a tech lead or code reviewer?
- How did you make sure you were learning the right things in the right order?
- What would you specifically ask a senior engineer to review in TLBank to improve it most?

### Verification status

- Documentation-only


---

<a id="Q294"></a>

## Q294 — You mentioned the .cursor/rules.md says "never generate code outside the current Sprint." What is a Sprint in TLBank's context, and what was Sprint 17 about?

### 中文筆記

Sprint 的定義（TLBank 的 context）：19-cursor-implementation-roadmap.md 定義了 16-sprint 的 Cursor implementation roadmap，每個 Sprint 對應一個業務功能的完整 backend slice：Sprint 1：Project setup（Spring Boot skeleton、H2、Flyway、security skeleton）；Sprint 5：OTP flow；Sprint 9：Audit logging；Sprint 12：Redis idempotency；Sprint 15：Report generation；Sprint 16：CI/CD + Terraform。Sprint 17（未在 roadmap 中，是追加的）：根據 20-maintenance-and-future-enhancement.md 中「Resolved (Sprint 17)」的 known limitations：(1) 修復 REVIEW_CASE_NOT_FOUND 的 HTTP status（400 → 404）；(2) 修復 SystemParameterService.update() 的 cache eviction 遺漏；(3) 加入 @Auditable 在 DOCUMENT_UPLOAD 方法；(4) 修復文件完整性驗證（在 Application.submit() 中）；(5) 刪除 root-level 的 legacy Dockerfile。Sprint 17 是 bug fix + known debt cleanup sprint，不是新 feature sprint。這個 sprint-based 追蹤讓 20-maintenance-and-future-enhancement.md 的 status 欄位成為真正的 living document。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Basic

### Verified source files

- sp2-springboot/docs/design/19-cursor-implementation-roadmap.md
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Understanding the development process reveals engineering discipline; sprint-based development shows structured learning.

### Possible follow-up questions

- How long did each sprint take on average?
- What would Sprint 18 contain?
- How did you decide what to include in each sprint vs. defer to later?

### Verification status

- Documentation-only


---

<a id="Q295"></a>

## Q295 — infra/local Terraform uses the hashicorp/local provider. What does it create, and what must not be claimed in an interview?

### 中文筆記

infra/local 以 local provider 產生本機文件產物（例如環境說明），CI 的 terraform.yml 做 fmt/validate/plan。不建立 AWS/Azure/GCP 資源，也不能描述成雲端基礎設施或正式環境佈建。

### Category

Terraform (Local)

### Difficulty

Basic

### Verified source files

- infra/local/main.tf
- .github/workflows/terraform.yml
- sp2-springboot/docs/decisions/0005-use-terraform-local.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

Prevents overclaiming cloud IaC experience from a local-provider learning module.

### Possible follow-up questions

- Which workflow validates Terraform?
- Why keep Terraform at monorepo root?
- What would a real cloud root look like later?

### Verification status

- Documentation-only


---

<a id="Q296"></a>

## Q296 — TLBank has 36 test classes and 133 test methods. Is this number sufficient, and how would you decide if you need more?

### 中文筆記

測試充分性的評估框架：133 個 test methods 覆蓋了 domain unit tests（state machine exhaustive、value object validation）、application service unit tests（每個 use case 的 happy path + edge case）、integration tests（full flow）。是否足夠的判斷標準：(1) JaCoCo coverage：若 line coverage > 70%（business logic 層，不計 config/entity/dto），基線是 ok 的；(2) Bug escape rate：在目前的 Known Limitations 中，哪些是「測試沒覆蓋到的 bug」？大多數 known limitation 是 architectural decision（session store、local cache），不是 untested logic；(3) Test pyramid balance：domain tests（快、多）> service tests > integration tests（慢、少），目前的結構符合；(4) Regression safety：每次 sprint 加新功能後，是否有 test 會在 regression 時 fail？若 CI 一直 green 而你加了 bug，說明有 gap。需要更多 test 的 signal：(1) 發現 bug 是在 staging 而非 CI；(2) 某個 code path（如 cancel flow 的 event non-emission）沒有任何測試覆蓋；(3) 有人在 code review 問「這個 edge case 有測試嗎？」而答案是 no。

### Category

Testing (JUnit + Mockito + MockMvc)

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/docs/design/16-testing-strategy.md
- sp2-springboot/pom.xml

### Verified classes and methods

- Documentation-level question

### Execution flow

1. Domain event published after workflow milestone
2. NotificationEventHandler invokes NotificationServiceImpl
3. MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock)

### Why this may be asked

Test coverage philosophy is a nuanced question; experienced engineers know that more tests aren't always better.

### Possible follow-up questions

- What would you test next if you had to add 10 more test methods?
- What is the risk of having 100% line coverage but still having bugs?
- What is mutation testing and would it be valuable for TLBank?

### Verification status

- Documentation-only


---

<a id="Q297"></a>

## Q297 — What would you do if, in a technical interview at Trend Micro, an interviewer opened the ApplicationRepositoryImpl.save() method and asked you to explain every line?

### 中文筆記

面對 code walkthrough 的策略：ApplicationRepositoryImpl.save() 的 key lines：(1) findByApplicationNo(applicationId.value()) — 「用業務 key 查詢，而非 JPA 的數字 id，因為 domain aggregate 不知道 DB 的 surrogate key；Optional.map(entity -> { applyToEntity(entity, application); return entity; }).orElseGet(() -> toEntity(application)) — 「有就 update，無就 insert；applicationJpaRepository.save(entity) — 「JPA save，若 entity 是 managed（from findById），JPA 追蹤 dirty，commit 時 UPDATE；若是新 entity（transient），JPA INSERT」。若遇到不確定的部分：「這段的意圖是...，但我需要確認的是 JPA 的 merge behavior 在 entity 有 @GeneratedValue 時的細節，讓我解釋我理解的部分，然後說明我怎麼驗證剩下的部分。」關鍵：誠實說「這部分我不 100% 確定」比假裝知道更好，但先解釋你知道的 70%，再說 30% 的不確定，不是一開始就放棄。

### Category

Behavioral Questions Based on the Project

### Difficulty

Advanced

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java

### Verified classes and methods

- ApplicationRepositoryImpl.save()

### Execution flow

1. Request enters presentation layer (REST or Thymeleaf controller)
2. Application service orchestrates domain aggregate and ports
3. Infrastructure adapter persists or calls external/mock dependency

### Why this may be asked

Live code walkthrough under pressure tests both technical knowledge and communication skills.

### Possible follow-up questions

- What part of the codebase would you least want an interviewer to open and ask about?
- How do you handle a question in an interview when you genuinely don't know the answer?
- What is the most important thing to demonstrate during a live coding review?

### Verification status

- Verified


---

<a id="Q298"></a>

## Q298 — LocalDocumentStorageService stores uploads under tlbank.upload.base-path. What are the production risks of local disk storage here?

### 中文筆記

文件經 LocalDocumentStorageService 寫入本機目錄（dev 預設與設定檔 base-path）。單機可運作，但多實例無法共享檔案、容器重啟可能遺失資料，且 Compose 僅掛載 volume 到單一 app 容器。後續應改物件儲存；現況不可稱為高可用文件服務。

### Category

File Upload and Storage

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java
- sp2-springboot/src/main/resources/application.yml
- sp2-springboot/src/main/resources/application-dev.yml
- sp2-springboot/docker-compose.yml

### Verified classes and methods

- LocalDocumentStorageService
- Application.uploadDocuments()

### Execution flow

1. Upload API accepts multipart file
2. ApplicationAppService delegates to LocalDocumentStorageService
3. Metadata saved with application; bytes stored under configured base-path

### Why this may be asked

Connects storage adapter choice to horizontal-scaling limits already present in the repo.

### Possible follow-up questions

- Which application status is required before upload?
- How are document types validated on submit?
- Where is the upload path set per profile?

### Verification status

- Verified


---

<a id="Q299"></a>

## Q299 — How do you ensure that the design decisions documented in ADRs are not just aspirational but actually reflected in the code?

### 中文筆記

ADR 與 code 一致性的保障機制（TLBank 的實際做法）：(1) ADR 的 code anchor section：每份 ADR 有「Code anchors」或「File references」標註具體的 class/method，讓讀者可以 verify（如 ADR 0001 說「domain 不依賴 infrastructure」，anchor 指向 Application.java — 打開就能驗證沒有 JPA import）；(2) Tests as living spec：ApplicationStatusTest.canTransitionTo_allIllegalTransitions_shouldReturnFalse() 在每次 CI 執行時驗證 ADR 0002 的 state machine 規則仍然成立；AuditAspectTest 驗證 ADR 中「audit never blocks business」的說法；(3) Known Limitation tracking：若 ADR 的決策和現實有差距（如 @Service 在 WorkflowDomainService），明確記錄在 ADR 的 trade-off section 和 20-maintenance-and-future-enhancement.md，不掩蓋；(4) ArchUnit（計劃）：ADR 提到未來加 ArchUnit test 自動在 CI 驗證 dependency direction；(5) Documentation sync rule：19-cursor-implementation-roadmap.md 和 .cursor/rules.md 都要求「always update documentation after implementation」，維持 doc 和 code 的 sync。沒有任何一個機制是完美的，但組合起來讓 deviation 有地方被 observed 和 discussed。

### Category

Project Overview and One-Repository Strategy

### Difficulty

Advanced

### Verified source files

- sp2-springboot/docs/decisions/0001-use-clean-architecture.md
- sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationStatusTest.java
- sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md

### Verified classes and methods

- Documentation-level question

### Execution flow

- Not applicable — design or documentation question

### Why this may be asked

The gap between documented intent and actual code is a real engineering problem; the best candidates have concrete mechanisms.

### Possible follow-up questions

- What ArchUnit rule would you write to enforce ADR 0001's dependency direction?
- What would you do if you discovered that the code violated an ADR but the ADR was the one that was wrong?
- How frequently should ADRs be reviewed and updated?

### Verification status

- Documentation-only


---

<a id="Q300"></a>

## Q300 — GlobalExceptionHandler maps domain and validation failures to ApiResponse. How are WorkflowException and validation errors represented?

### 中文筆記

GlobalExceptionHandler 位於 presentation.api.advice，將例外轉成統一 ApiResponse 與 HTTP 狀態。工作流非法轉換走 WorkflowException；Bean Validation 失敗則回傳欄位錯誤資訊。錯誤碼集中於 ErrorCode，避免控制器各自組裝錯誤格式。

### Category

Validation and Exception Handling

### Difficulty

Intermediate

### Verified source files

- sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java
- sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java

### Verified classes and methods

- GlobalExceptionHandler
- ErrorCode
- ApiResponse

### Execution flow

1. Controller/service throws or validation fails
2. GlobalExceptionHandler selects @ExceptionHandler method
3. Returns ApiResponse.error(...) with ErrorCode and HTTP status

### Why this may be asked

Confirms API error-contract knowledge grounded in this project exception layer.

### Possible follow-up questions

- Where is WorkflowException thrown?
- How does @Valid on DTOs reach this handler?
- Why keep ApiResponse as a shared envelope?

### Verification status

- Verified


---

# Referenced File Appendix

Unique files listed under **Verified source files** for Q001–Q300. Each path appears once.

Total unique files: **194**.

---

<a id="file--github-workflows-ci-yml"></a>

## `.github/workflows/ci.yml`

### English explanation

**Main responsibility:** GitHub Actions workflow `TLBank CI/CD`.

**Triggers:** `push`, `pull_request`, `workflow_dispatch`.

**Jobs:** `build-test`, `code-quality`, `dependency-scan`, `build-and-push-image`, `deploy-staging`.

**Runtime effect:** Orchestrates build, quality, scan, image, and/or deploy steps for this monorepo; read job `needs:` and `if:` conditions in the file for exact sequencing (for example `deploy-staging` may require `workflow_dispatch`).

**Why open in an interview:** Distinguishes automatic CI from manual `workflow_dispatch` deploy behavior.

**Limitations / trade-offs:** Self-hosted / local Docker assumptions in comments and steps are not equivalent to managed cloud CD.

### 中文筆記

職責：GitHub Actions workflow `TLBank CI/CD`。
觸發：`push`, `pull_request`, `workflow_dispatch`。工作：`build-test`, `code-quality`, `dependency-scan`, `build-and-push-image`, `deploy-staging`。
執行效果依 `on`／`needs`／`if` 決定編譯、品質、掃描、映像與部署順序（例如 `deploy-staging` 可能僅在 `workflow_dispatch` 執行）。
面談重點：分辨自動 CI 與手動部署。
限制：註解中的本機／self-hosted 假設不等於雲端自動交付。

### Related questions

- Q005
- Q007
- Q008
- Q088
- Q158
- Q160
- Q189
- Q191
- Q193
- Q194
- Q195
- Q196
- Q197
- Q243

---

<a id="file--github-workflows-markdown-yml"></a>

## `.github/workflows/markdown.yml`

### English explanation

**Main responsibility:** GitHub Actions workflow `Markdown Check`.

**Triggers:** `push`, `pull_request`.

**Jobs:** `markdown-check`.

**Runtime effect:** Orchestrates build, quality, scan, image, and/or deploy steps for this monorepo; read job `needs:` and `if:` conditions in the file for exact sequencing (for example `deploy-staging` may require `workflow_dispatch`).

**Why open in an interview:** Distinguishes automatic CI from manual `workflow_dispatch` deploy behavior.

**Limitations / trade-offs:** Self-hosted / local Docker assumptions in comments and steps are not equivalent to managed cloud CD.

### 中文筆記

職責：GitHub Actions workflow `Markdown Check`。
觸發：`push`, `pull_request`。工作：`markdown-check`。
執行效果依 `on`／`needs`／`if` 決定編譯、品質、掃描、映像與部署順序（例如 `deploy-staging` 可能僅在 `workflow_dispatch` 執行）。
面談重點：分辨自動 CI 與手動部署。
限制：註解中的本機／self-hosted 假設不等於雲端自動交付。

### Related questions

- Q197
- Q289

---

<a id="file--github-workflows-terraform-yml"></a>

## `.github/workflows/terraform.yml`

### English explanation

**Main responsibility:** GitHub Actions workflow `Terraform Check`.

**Triggers:** `push`, `pull_request`.

**Jobs:** `terraform-check`.

**Runtime effect:** Orchestrates build, quality, scan, image, and/or deploy steps for this monorepo; read job `needs:` and `if:` conditions in the file for exact sequencing (for example `deploy-staging` may require `workflow_dispatch`).

**Why open in an interview:** Distinguishes automatic CI from manual `workflow_dispatch` deploy behavior.

**Limitations / trade-offs:** Self-hosted / local Docker assumptions in comments and steps are not equivalent to managed cloud CD.

### 中文筆記

職責：GitHub Actions workflow `Terraform Check`。
觸發：`push`, `pull_request`。工作：`terraform-check`。
執行效果依 `on`／`needs`／`if` 決定編譯、品質、掃描、映像與部署順序（例如 `deploy-staging` 可能僅在 `workflow_dispatch` 執行）。
面談重點：分辨自動 CI 與手動部署。
限制：註解中的本機／self-hosted 假設不等於雲端自動交付。

### Related questions

- Q197
- Q198
- Q295

---

<a id="file-SP2-src-main-java-dao-DbConnection-java"></a>

## `SP2/src/main/java/dao/DbConnection.java`

### English explanation

**Main responsibility:** class `DbConnection` in package `dao`.

**Important types:** `class DbConnection` in `dao` (layer: other).

**Annotations:** none prominent at type level.

**Important methods / members:** `main()`, `getdb()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Repository strategy.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the other layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：DbConnection（class）位於 `dao`，屬 other 層。class `DbConnection` in package `dao`.
註解：none prominent at type level。主要方法：`main()`, `getdb()`。依賴取樣：limited internal imports extracted。
業務關聯：Repository strategy。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q007

---

<a id="file-infra-local-main-tf"></a>

## `infra/local/main.tf`

### English explanation

**Main responsibility:** Terraform configuration under `infra/local` (local provider scope).

**Providers / resources:** providers `local`; resources `local_file.staging_environment_doc`.

**Variables / outputs:** variables none; outputs none.

**Why open in an interview:** Separates portfolio IaC practice from cloud provisioning claims.

**Limitations / trade-offs:** `hashicorp/local` creates local files only; no AWS/GCP/Azure resources here.

### 中文筆記

職責：`infra/local` Terraform 設定。provider：`local`；resource：`local_file.staging_environment_doc`。
變數／輸出：none；none。
面談重點：作品集 IaC 練習 ≠ 雲端建置。
限制：`hashicorp/local` 只產生本機檔案。

### Related questions

- Q008
- Q198
- Q199
- Q241
- Q295

---

<a id="file-infra-local-outputs-tf"></a>

## `infra/local/outputs.tf`

### English explanation

**Main responsibility:** Terraform configuration under `infra/local` (local provider scope).

**Providers / resources:** providers see `required_providers`; resources none in this file.

**Variables / outputs:** variables none; outputs `environment`, `backend_url`, `generated_doc_path`.

**Why open in an interview:** Separates portfolio IaC practice from cloud provisioning claims.

**Limitations / trade-offs:** `hashicorp/local` creates local files only; no AWS/GCP/Azure resources here.

### 中文筆記

職責：`infra/local` Terraform 設定。provider：see `required_providers`；resource：none in this file。
變數／輸出：none；`environment`, `backend_url`, `generated_doc_path`。
面談重點：作品集 IaC 練習 ≠ 雲端建置。
限制：`hashicorp/local` 只產生本機檔案。

### Related questions

- Q199

---

<a id="file-infra-local-terraform-tfstate"></a>

## `infra/local/terraform.tfstate`

### English explanation

**Main responsibility:** Local Terraform state snapshot for `infra/local`.

**Important contents:** Records resource instances and outputs after `terraform apply`.

**State implications:** Shows what the local provider created; not remote cloud state.

**Why open in an interview:** Demonstrates local-only IaC evidence versus cloud accounts.

**Limitations / trade-offs:** Committing state has collaboration/security trade-offs; scope remains local files.

### 中文筆記

職責：`infra/local` 的本機 Terraform state。
內容：記錄 apply 後的資源實例與 outputs。
意涵：證明 local provider 產物，不是遠端雲端 state。
面談重點：本機 IaC 與雲端帳號的界線。
限制：state 入庫有協作與敏感資訊取捨；範圍仍是本機檔案。

### Related questions

- Q199

---

<a id="file-infra-local-variables-tf"></a>

## `infra/local/variables.tf`

### English explanation

**Main responsibility:** Terraform configuration under `infra/local` (local provider scope).

**Providers / resources:** providers see `required_providers`; resources none in this file.

**Variables / outputs:** variables `app_name`, `environment`, `backend_port`; outputs none.

**Why open in an interview:** Separates portfolio IaC practice from cloud provisioning claims.

**Limitations / trade-offs:** `hashicorp/local` creates local files only; no AWS/GCP/Azure resources here.

### 中文筆記

職責：`infra/local` Terraform 設定。provider：see `required_providers`；resource：none in this file。
變數／輸出：`app_name`, `environment`, `backend_port`；none。
面談重點：作品集 IaC 練習 ≠ 雲端建置。
限制：`hashicorp/local` 只產生本機檔案。

### Related questions

- Q199

---

<a id="file-sp2-springboot-cursor-rules-md"></a>

## `sp2-springboot/.cursor/rules.md`

### English explanation

**Main responsibility:** Documentation — `Rules`.

**Records:** Always read every document under /docs before generating code.

**Interview support:** Backs design/decision questions Q200, Q240.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《Rules》。摘要：Always read every document under /docs before generating code.
支援題目：Q200, Q240。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q200
- Q240

---

<a id="file-sp2-springboot-env-example"></a>

## `sp2-springboot/.env.example`

### English explanation

**Main responsibility:** Example environment variable template for local/staging runs.

**Important sections:** Key=value placeholders copied into a real env file (not committed secrets).

**Runtime effect:** Documents required secrets/ports/URLs operators must supply.

**Why open in an interview:** Shows which config is externalized versus hard-coded.

**Limitations / trade-offs:** Example values are not production credentials.

### 中文筆記

職責：本機／staging 環境變數範本。
內容：key=value 佔位，複製到真實 env（密鑰不應提交）。
效果：標出需外部提供的密鑰、埠、URL。
面談用途：區分外部化設定與寫死常數。
限制：範例值不是正式環境憑證。

### Related questions

- Q204

---

<a id="file-sp2-springboot-README-md"></a>

## `sp2-springboot/README.md`

### English explanation

**Main responsibility:** Documentation — `TLBank Digital Lending Platform`.

**Records:** !CI/CD !Terraform

**Interview support:** Backs design/decision questions Q001.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《TLBank Digital Lending Platform》。摘要：!CI/CD !Terraform
支援題目：Q001。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q001

---

<a id="file-sp2-springboot-docker-compose-yml"></a>

## `sp2-springboot/docker-compose.yml`

### English explanation

**Main responsibility:** Compose file defining local multi-container topology.

**Sections:** Top-level keys include `services`, `volumes`, `networks`.

**Runtime effect:** Starts dependent services (DB/Redis/app as declared) for local development.

**Why open in an interview:** Shows what actually runs locally versus what docs call “infrastructure”.

**Limitations / trade-offs:** Local compose is not production orchestration.

### 中文筆記

職責：本機多容器編排。頂層鍵：services, volumes, networks。
執行效果：依檔案宣告啟動 DB／Redis／應用等依賴。
面談重點：本機 runtime 與文件所稱基礎設施的差異。
限制：Compose 拓樸不是正式環境編排。

### Related questions

- Q021
- Q186
- Q187
- Q188
- Q189
- Q190
- Q219
- Q298

---

<a id="file-sp2-springboot-docker-app-Dockerfile"></a>

## `sp2-springboot/docker/app/Dockerfile`

### English explanation

**Main responsibility:** Container image build for the Spring Boot app.

**Stages / base images:** FROM eclipse-temurin:17-jdk, eclipse-temurin:17-jre; named stages builder, runtime.

**Users / ports / health:** USER tlbank; EXPOSE 8080; HEALTHCHECK not declared in file.

**Why open in an interview:** Shows how the runnable artifact is packaged for Compose/CI.

**Limitations / trade-offs:** Image packaging ≠ production cluster scheduling or secrets management.

### 中文筆記

職責：應用容器映像建置。FROM：eclipse-temurin:17-jdk, eclipse-temurin:17-jre；階段：builder, runtime。
USER：tlbank；EXPOSE：8080；HEALTHCHECK：無。
面談用途：說明 Compose／CI 如何得到可執行映像。
限制：映像打包不等于叢集調度或密鑰治理。

### Related questions

- Q021
- Q158
- Q183
- Q184
- Q185
- Q285

---

<a id="file-sp2-springboot-docker-sqlserver-init-01-init-database-sql"></a>

## `sp2-springboot/docker/sqlserver/init/01-init-database.sql`

### English explanation

**Main responsibility:** Flyway migration version `V?` applying schema/data changes.

**Important sections:** see migration body.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V?`。主要語句：see migration body。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q186

---

<a id="file-sp2-springboot-docker-sqlserver-init-02-create-app-user-sql"></a>

## `sp2-springboot/docker/sqlserver/init/02-create-app-user.sql`

### English explanation

**Main responsibility:** Flyway migration version `V?` applying schema/data changes.

**Important sections:** see migration body.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V?`。主要語句：see migration body。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q186

---

<a id="file-sp2-springboot-docs-README-md"></a>

## `sp2-springboot/docs/README.md`

### English explanation

**Main responsibility:** Documentation — `TLBank Documentation`.

**Records:** Documentation home for the `sp2-springboot` module (TLBank Digital Lending Platform).

**Interview support:** Backs design/decision questions Q289, Q290.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《TLBank Documentation》。摘要：Documentation home for the `sp2-springboot` module (TLBank Digital Lending Platform).
支援題目：Q289, Q290。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q289
- Q290

---

<a id="file-sp2-springboot-docs-decisions-0001-use-clean-architecture-md"></a>

## `sp2-springboot/docs/decisions/0001-use-clean-architecture.md`

### English explanation

**Main responsibility:** Documentation — `ADR 0001 — Use Clean / Hexagonal Architecture`.

**Records:** - **Status:** Accepted - **Module:** `sp2-springboot` - **Code anchors:** `com.tlbank.lending.{presentation,application,domain,infrastructure,security,common}`

**Interview support:** Backs design/decision questions Q002, Q006, Q015, Q027, Q032, Q040, Q104, Q143.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《ADR 0001 — Use Clean / Hexagonal Architecture》。摘要：- **Status:** Accepted - **Module:** `sp2-springboot` - **Code anchors:** `com.tlbank.lending.{presentation,application,domain,infrastructure,security,common}`
支援題目：Q002, Q006, Q015, Q027, Q032, Q040, Q104, Q143。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q002
- Q006
- Q015
- Q027
- Q032
- Q040
- Q104
- Q143
- Q225
- Q240
- Q243
- Q244
- Q279
- Q299

---

<a id="file-sp2-springboot-docs-decisions-0002-use-ddd-md"></a>

## `sp2-springboot/docs/decisions/0002-use-ddd.md`

### English explanation

**Main responsibility:** Documentation — `ADR 0002 — Use DDD-lite Aggregates and a Domain State Machine`.

**Records:** - **Status:** Accepted - **Module:** `sp2-springboot` - **Code anchors:** `domain.application.Application`, `domain.application.ApplicationStatus`, `domain.review.ReviewCase`, `domain.otp.OtpRecord`, `domain.service.WorkflowDomainService`

**Interview support:** Backs design/decision questions Q041.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《ADR 0002 — Use DDD-lite Aggregates and a Domain State Machine》。摘要：- **Status:** Accepted - **Module:** `sp2-springboot` - **Code anchors:** `domain.application.Application`, `domain.application.ApplicationStatus`, `domain.review.ReviewCase`, `domain.otp.OtpRecord`, `domain.service.WorkflowDomainService`
支援題目：Q041。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q041

---

<a id="file-sp2-springboot-docs-decisions-0004-use-github-actions-md"></a>

## `sp2-springboot/docs/decisions/0004-use-github-actions.md`

### English explanation

**Main responsibility:** Documentation — `ADR 0004 — Use GitHub Actions for CI and Manual CD`.

**Records:** - **Status:** Accepted - **Module:** monorepo workflows under `.github/workflows/` - **Code anchors:** `.github/workflows/ci.yml`, `.github/workflows/terraform.yml`, `.github/workflows/markdown.yml`

**Interview support:** Backs design/decision questions Q194, Q239.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《ADR 0004 — Use GitHub Actions for CI and Manual CD》。摘要：- **Status:** Accepted - **Module:** monorepo workflows under `.github/workflows/` - **Code anchors:** `.github/workflows/ci.yml`, `.github/workflows/terraform.yml`, `.github/workflows/markdown.yml`
支援題目：Q194, Q239。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q194
- Q239

---

<a id="file-sp2-springboot-docs-decisions-0005-use-terraform-local-md"></a>

## `sp2-springboot/docs/decisions/0005-use-terraform-local.md`

### English explanation

**Main responsibility:** Documentation — `ADR 0005 — Use Terraform with the Local Provider`.

**Records:** - **Status:** Accepted - **Module:** `infra/local/` (monorepo root) - **Code anchors:** `infra/local/*.tf`, `.github/workflows/terraform.yml`

**Interview support:** Backs design/decision questions Q239, Q241, Q295.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《ADR 0005 — Use Terraform with the Local Provider》。摘要：- **Status:** Accepted - **Module:** `infra/local/` (monorepo root) - **Code anchors:** `infra/local/*.tf`, `.github/workflows/terraform.yml`
支援題目：Q239, Q241, Q295。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q239
- Q241
- Q295

---

<a id="file-sp2-springboot-docs-decisions-0006-session-over-jwt-md"></a>

## `sp2-springboot/docs/decisions/0006-session-over-jwt.md`

### English explanation

**Main responsibility:** Documentation — `ADR 0006 — Prefer Session Authentication over JWT`.

**Records:** - **Status:** Accepted - **Module:** `sp2-springboot` - **Code anchors:** `security.config.SecurityConfig` (`SessionCreationPolicy.IF_REQUIRED`, `maximumSessions(1)`), form login to `/api/v1/auth/login`, handlers under `security.handler`

**Interview support:** Backs design/decision questions Q025, Q081, Q085, Q087, Q239.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《ADR 0006 — Prefer Session Authentication over JWT》。摘要：- **Status:** Accepted - **Module:** `sp2-springboot` - **Code anchors:** `security.config.SecurityConfig` (`SessionCreationPolicy.IF_REQUIRED`, `maximumSessions(1)`), form login to `/api/v1/auth/login`, handlers under `security.handler`
支援題目：Q025, Q081, Q085, Q087, Q239。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q025
- Q081
- Q085
- Q087
- Q239

---

<a id="file-sp2-springboot-docs-decisions-0007-h2-vs-sqlserver-md"></a>

## `sp2-springboot/docs/decisions/0007-h2-vs-sqlserver.md`

### English explanation

**Main responsibility:** Documentation — `ADR 0007 — Use H2 for Dev/Tests and SQL Server for Staging`.

**Records:** - **Status:** Accepted - **Module:** `sp2-springboot` - **Code anchors:** `application-dev.yml` (H2 `MODE=MSSQLServer`), `application-staging.yml` / `application-prod.yml` (SQL Server), Flyway trees under `src/main/resources/db/migration` and `db/migration-sqlserver`

**Interview support:** Backs design/decision questions Q119.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《ADR 0007 — Use H2 for Dev/Tests and SQL Server for Staging》。摘要：- **Status:** Accepted - **Module:** `sp2-springboot` - **Code anchors:** `application-dev.yml` (H2 `MODE=MSSQLServer`), `application-staging.yml` / `application-prod.yml` (SQL Server), Flyway trees under `src/main/resources/db/migration` and `db/migration-sqlserver`
支援題目：Q119。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q119

---

<a id="file-sp2-springboot-docs-decisions-README-md"></a>

## `sp2-springboot/docs/decisions/README.md`

### English explanation

**Main responsibility:** Documentation — `Architecture Decision Records (ADRs)`.

**Records:** Lightweight decisions that shaped `sp2-springboot`. Each ADR records **context, choice, alternatives, trade-offs, consequences, and future work** without re-documenting the full system.

**Interview support:** Backs design/decision questions Q006.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《Architecture Decision Records (ADRs)》。摘要：Lightweight decisions that shaped `sp2-springboot`. Each ADR records **context, choice, alternatives, trade-offs, consequences, and future work** without re-documenting the full system.
支援題目：Q006。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q006

---

<a id="file-sp2-springboot-docs-design-00-sdd-overview-md"></a>

## `sp2-springboot/docs/design/00-sdd-overview.md`

### English explanation

**Main responsibility:** Documentation — `00 – Software Design Document Overview`.

**Records:** This Software Design Document (SDD) describes the design of **TLBank Digital Lending Platform**, a fictional enterprise-style credit card application and credit review backend built with Java 17 and Spring Boot 3.

**Interview support:** Backs design/decision questions Q006.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《00 – Software Design Document Overview》。摘要：This Software Design Document (SDD) describes the design of **TLBank Digital Lending Platform**, a fictional enterprise-style credit card application and credit review backend built with Java 17 and Spring Boot 3.
支援題目：Q006。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q006

---

<a id="file-sp2-springboot-docs-design-02-architecture-design-md"></a>

## `sp2-springboot/docs/design/02-architecture-design.md`

### English explanation

**Main responsibility:** Documentation — `02 – Architecture Design`.

**Records:** TLBank Digital Lending Platform follows **Clean Architecture / Hexagonal Architecture**, layered as:

**Interview support:** Backs design/decision questions Q006, Q027, Q033.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《02 – Architecture Design》。摘要：TLBank Digital Lending Platform follows **Clean Architecture / Hexagonal Architecture**, layered as:
支援題目：Q006, Q027, Q033。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q006
- Q027
- Q033

---

<a id="file-sp2-springboot-docs-design-07-security-design-md"></a>

## `sp2-springboot/docs/design/07-security-design.md`

### English explanation

**Main responsibility:** Documentation — `07 – Security Design`.

**Records:** TLBank Digital Lending Platform uses **classic session-based authentication** via Spring Security form login — not JWT/OAuth2 — which is appropriate for its primary surface (a server-rendered Thymeleaf back office for reviewers/admins) while still exposing a JSON-friendly login for API clients and tests.

**Interview support:** Backs design/decision questions Q203.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《07 – Security Design》。摘要：TLBank Digital Lending Platform uses **classic session-based authentication** via Spring Security form login — not JWT/OAuth2 — which is appropriate for its primary surface (a server-rendered Thymeleaf back office for reviewers/admins) while still exposing a JSON-friendly login for API clients and tests.
支援題目：Q203。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q203

---

<a id="file-sp2-springboot-docs-design-11-audit-logging-md"></a>

## `sp2-springboot/docs/design/11-audit-logging.md`

### English explanation

**Main responsibility:** Documentation — `11 – Audit Logging`.

**Records:** The platform writes to `audit_logs` through **two distinct mechanisms**, each appropriate to where the event originates:

**Interview support:** Backs design/decision questions Q201, Q220.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《11 – Audit Logging》。摘要：The platform writes to `audit_logs` through **two distinct mechanisms**, each appropriate to where the event originates:
支援題目：Q201, Q220。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q201
- Q220

---

<a id="file-sp2-springboot-docs-design-13-scheduler-design-md"></a>

## `sp2-springboot/docs/design/13-scheduler-design.md`

### English explanation

**Main responsibility:** Documentation — `13 – Scheduler Design`.

**Records:** All scheduled jobs use Spring's native `@Scheduled` annotation with cron expressions sourced from configuration (`tlbank.scheduler.*.cron`), enabled via `@EnableScheduling` (declared, somewhat redundantly, in both `common.config.SchedulingConfig` and `common.config.SchedulerConfig` — see `20-maintenance-and-future-enhancement.md` for the consolidation note) and a dedicated scheduling thread pool (

**Interview support:** Backs design/decision questions Q211.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《13 – Scheduler Design》。摘要：All scheduled jobs use Spring's native `@Scheduled` annotation with cron expressions sourced from configuration (`tlbank.scheduler.*.cron`), enabled via `@EnableScheduling` (declared, somewhat redundantly, in both `common.config.SchedulingConfig` and `common.config.SchedulerConfig` — see `20-maintenance-and-future-enhancement.md` for the consolidation note) and a dedicated scheduling thread pool (
支援題目：Q211。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q211

---

<a id="file-sp2-springboot-docs-design-16-testing-strategy-md"></a>

## `sp2-springboot/docs/design/16-testing-strategy.md`

### English explanation

**Main responsibility:** Documentation — `16 – Testing Strategy`.

**Records:** ```mermaid flowchart TB     A[Domain Unit Tests<br/>plain JUnit 5, no Spring context] --> B[Application Service Unit Tests<br/>JUnit 5 + Mockito]     B --> C[Infrastructure Unit Tests<br/>JUnit 5, real clock/cache, mocked I/O boundaries]     C --> D[Integration / Slice Tests<br/>@SpringBootTest + MockMvc, H2, dev profile]     D --> E[Security Integration Tests<br/>full filter chain, real form logi

**Interview support:** Backs design/decision questions Q213, Q224, Q245, Q296.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《16 – Testing Strategy》。摘要：```mermaid flowchart TB     A[Domain Unit Tests<br/>plain JUnit 5, no Spring context] --> B[Application Service Unit Tests<br/>JUnit 5 + Mockito]     B --> C[Infrastructure Unit Tests<br/>JUnit 5, real clock/cache, mocked I/O boundaries]     C --> D[Integration / Slice Tests<br/>@SpringBootTest + MockMvc, H2, dev profile]     D --> E[Security Integration Tests<br/>full filter chain, real form logi
支援題目：Q213, Q224, Q245, Q296。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q213
- Q224
- Q245
- Q296

---

<a id="file-sp2-springboot-docs-design-19-cursor-implementation-roadmap-md"></a>

## `sp2-springboot/docs/design/19-cursor-implementation-roadmap.md`

### English explanation

**Main responsibility:** Documentation — `19 – Cursor Implementation Roadmap`.

**Records:** This roadmap breaks the platform into 16 sprints, in the same order the actual codebase evolved (the Flyway migration filenames `V11__extend_applications_for_sprint5.sql`, `V12__..._for_sprint6.sql`, `V13__..._for_sprint8.sql`, and `V14__..._for_sprint9.sql` are literally named after this sprint plan, confirming the mapping below). Each sprint section is self-contained and gives Cursor (or any AI

**Interview support:** Backs design/decision questions Q200, Q294.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《19 – Cursor Implementation Roadmap》。摘要：This roadmap breaks the platform into 16 sprints, in the same order the actual codebase evolved (the Flyway migration filenames `V11__extend_applications_for_sprint5.sql`, `V12__..._for_sprint6.sql`, `V13__..._for_sprint8.sql`, and `V14__..._for_sprint9.sql` are literally named after this sprint plan, confirming the mapping below). Each sprint section is self-contained and gives Cursor (or any 輔助工具
支援題目：Q200, Q294。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q200
- Q294

---

<a id="file-sp2-springboot-docs-design-20-maintenance-and-future-enhancement-md"></a>

## `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md`

### English explanation

**Main responsibility:** Documentation — `20 – Maintenance and Future Enhancement`.

**Records:** This document tracks known limitations and deliberate simplifications in the current implementation, and lays out a realistic enhancement roadmap. Every item here was identified by reading the actual codebase, not hypothesized — each is cross-referenced to the document where it is explained in context.

**Interview support:** Backs design/decision questions Q038, Q062, Q087, Q133, Q167, Q171, Q172, Q192.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《20 – Maintenance and Future Enhancement》。摘要：This document tracks known limitations and deliberate simplifications in the current implementation, and lays out a realistic enhancement roadmap. Every item here was identified by reading the actual codebase, not hypothesized — each is cross-referenced to the document where it is explained in context.
支援題目：Q038, Q062, Q087, Q133, Q167, Q171, Q172, Q192。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q038
- Q062
- Q087
- Q133
- Q167
- Q171
- Q172
- Q192
- Q204
- Q205
- Q206
- Q222
- Q223
- Q224
- Q227
- Q228
- Q230
- Q234
- Q242
- Q248
- Q249
- Q274
- Q279
- Q280
- Q290
- Q294
- Q299

---

<a id="file-sp2-springboot-docs-handbook-00-project-overview-md"></a>

## `sp2-springboot/docs/handbook/00-project-overview.md`

### English explanation

**Main responsibility:** Documentation — `TLBank Project Overview`.

**Records:** Executive overview of the TLBank Digital Lending Platform repository. This document orients readers quickly; deeper detail lives in the handbooks and SDD set linked at the end.

**Interview support:** Backs design/decision questions Q001, Q002, Q003, Q004, Q006, Q099, Q249, Q287.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《TLBank Project Overview》。摘要：Executive overview of the TLBank Digital Lending Platform repository. This document orients readers quickly; deeper detail lives in the handbooks and SDD set linked at the end.
支援題目：Q001, Q002, Q003, Q004, Q006, Q099, Q249, Q287。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q001
- Q002
- Q003
- Q004
- Q006
- Q099
- Q249
- Q287
- Q289
- Q290
- Q292
- Q293

---

<a id="file-sp2-springboot-docs-handbook-01-repository-handbook-md"></a>

## `sp2-springboot/docs/handbook/01-repository-handbook.md`

### English explanation

**Main responsibility:** Documentation — `TLBank Repository Handbook`.

**Records:** Internal engineering wiki for the `sp2-springboot` monorepo module. This document is the **entry point** for navigation, onboarding, and interview preparation.

**Interview support:** Backs design/decision questions Q001, Q006.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《TLBank Repository Handbook》。摘要：Internal engineering wiki for the `sp2-springboot` monorepo module. This document is the **entry point** for navigation, onboarding, and interview preparation.
支援題目：Q001, Q006。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q001
- Q006

---

<a id="file-sp2-springboot-docs-handbook-03-business-feature-handbook-md"></a>

## `sp2-springboot/docs/handbook/03-business-feature-handbook.md`

### English explanation

**Main responsibility:** Documentation — `TLBank Business Feature Handbook`.

**Records:** <!-- markdownlint-disable MD024 -->

**Interview support:** Backs design/decision questions Q004.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《TLBank Business Feature Handbook》。摘要：<!-- markdownlint-disable MD024 -->
支援題目：Q004。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q004

---

<a id="file-sp2-springboot-docs-handbook-06-system-design-handbook-md"></a>

## `sp2-springboot/docs/handbook/06-system-design-handbook.md`

### English explanation

**Main responsibility:** Documentation — `TLBank System Design Handbook`.

**Records:** How this portfolio backend would evolve toward a production-scale system. **Current state is documented elsewhere** — this file is the growth path only.

**Interview support:** Backs design/decision questions Q209, Q231, Q232, Q233, Q237, Q238.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《TLBank System Design Handbook》。摘要：How this portfolio backend would evolve toward a production-scale system. **Current state is documented elsewhere** — this file is the growth path only.
支援題目：Q209, Q231, Q232, Q233, Q237, Q238。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q209
- Q231
- Q232
- Q233
- Q237
- Q238

---

<a id="file-sp2-springboot-docs-handbook-07-cheat-sheet-md"></a>

## `sp2-springboot/docs/handbook/07-cheat-sheet.md`

### English explanation

**Main responsibility:** Documentation — `TLBank Cheat Sheet`.

**Records:** 30-minute pre-interview review. **One short card per topic** — open the linked handbook only if you blank.

**Interview support:** Backs design/decision questions Q279.

**Why open in an interview:** Establishes intended architecture or process before reading code.

**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests.

### 中文筆記

職責：文件《TLBank Cheat Sheet》。摘要：30-minute pre-interview review. **One short card per topic** — open the linked handbook only if you blank.
支援題目：Q279。
面談用途：先讀意圖與決策，再對照程式。
限制：文件可能落後；關鍵主張需用原始碼與測試核對。

### Related questions

- Q279

---

<a id="file-sp2-springboot-pom-xml"></a>

## `sp2-springboot/pom.xml`

### English explanation

**Main responsibility:** Maven project descriptor `pom.xml`.

**Important coordinates:** artifactIds sampled: `spring-boot-starter-parent`, `tlbank-lending`, `spring-boot-starter-web`, `spring-boot-starter-security`, `spring-boot-starter-data-jpa`, `spring-boot-starter-thymeleaf`, `thymeleaf-extras-springsecurity6`, `spring-boot-starter-validation`.

**Dependencies / plugins:** `spring-boot-starter-web`, `spring-boot-starter-security`, `spring-boot-starter-data-jpa`, `spring-boot-starter-thymeleaf`, `thymeleaf-extras-springsecurity6`, `spring-boot-starter-validation`, `spring-boot-starter-actuator`, `spring-boot-starter-aop`, `mssql-jdbc`, `h2`, `flyway-core`, `flyway-sqlserver`, `lombok`, `mapstruct`, `springdoc-openapi-starter-webmvc-ui`.

**Runtime effect:** Defines Java version, Spring Boot parent, and test/coverage plugins used by CI.

**Why open in an interview:** Grounds stack claims (Boot version, JaCoCo, drivers) in build metadata.

**Limitations / trade-offs:** Declared dependency ≠ every feature is used at runtime.

### 中文筆記

職責：Maven 專案描述。artifactId 取樣：spring-boot-starter-parent, tlbank-lending, spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, spring-boot-starter-thymeleaf, thymeleaf-extras-springsecurity6, spring-boot-starter-validation。
依賴／外掛取樣：spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, spring-boot-starter-thymeleaf, thymeleaf-extras-springsecurity6, spring-boot-starter-validation, spring-boot-starter-actuator, spring-boot-starter-aop, mssql-jdbc, h2, flyway-core, flyway-sqlserver, lombok, mapstruct, springdoc-openapi-starter-webmvc-ui。
效果：決定 Java／Spring Boot 版本與 CI 測試覆蓋外掛。
面談用途：用建置中繼資料核對技術棧主張。
限制：有依賴不代表執行期每個功能都有使用。

### Related questions

- Q015
- Q017
- Q040
- Q107
- Q157
- Q158
- Q159
- Q160
- Q174
- Q222
- Q224
- Q285
- Q296

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-TlbankLendingApplication-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/TlbankLendingApplication.java`

### English explanation

**Main responsibility:** Entry point for the TLBank Digital Lending Platform. <p> Bootstraps the Spring Boot application context for the credit card application system. Business modules are wired in subsequent sprints.

**Important types:** `class TlbankLendingApplication` in `com.tlbank.lending` (layer: other).

**Annotations:** `@SpringBootApplication`.

**Important methods / members:** `main()`.

**Direct dependencies (sampled imports):** `org.springframework.boot.SpringApplication`, `org.springframework.boot.autoconfigure.SpringBootApplication`.

**Business flow:** Appears in interview topics around: Spring Boot 3.x.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the other layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：TlbankLendingApplication（class）位於 `com.tlbank.lending`，屬 other 層。Entry point for the TLBank Digital Lending Platform. <p> Bootstraps the Spring Boot application context for the credit card application system. Business modules are wired in subsequent sprints.
註解：`@SpringBootApplication`。主要方法：`main()`。依賴取樣：`org.springframework.boot.SpringApplication`, `org.springframework.boot.autoconfigure.SpringBootApplication`。
業務關聯：Spring Boot 3.x。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q018

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-ApplicationAppService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`

### English explanation

**Main responsibility:** Application service for credit card application lifecycle use cases.

**Important types:** `class ApplicationAppService` in `com.tlbank.lending.application.application.service` (layer: application).

**Annotations:** `@RequiredArgsConstructor`, `@Service`.

**Important methods / members:** `createApplication()`, `getApplication()`, `uploadDocuments()`, `submitApplication()`, `cancelApplication()`, `findAllEnabledProducts()`, `findApplicationOrThrow()`, `toApplicant()`, `toMaskedApplicant()`.

**Direct dependencies (sampled imports):** `org.springframework.context.ApplicationEventPublisher`, `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `org.springframework.web.multipart.MultipartFile`, `com.tlbank.lending.application.dto.request.CreateApplicationRequest`, `com.tlbank.lending.application.dto.response.DocumentUploadResponse`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.Auditable`.

**Business flow:** Appears in interview topics around: CI/CD pipeline, Architecture boundaries, JPA and Hibernate, Notifications (mock), Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationAppService（class）位於 `com.tlbank.lending.application.application.service`，屬 application 層。Application service for credit card application lifecycle use cases.
註解：`@RequiredArgsConstructor`, `@Service`。主要方法：`createApplication()`, `getApplication()`, `uploadDocuments()`, `submitApplication()`, `cancelApplication()`, `findAllEnabledProducts()`, `findApplicationOrThrow()`, `toApplicant()`, `toMaskedApplicant()`。依賴取樣：`org.springframework.context.ApplicationEventPublisher`, `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `org.springframework.web.multipart.MultipartFile`, `com.tlbank.lending.application.dto.request.CreateApplicationRequest`, `com.tlbank.lending.application.dto.response.DocumentUploadResponse`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.Auditable`。
業務關聯：CI/CD pipeline, Architecture boundaries, JPA and Hibernate, Notifications (mock), Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q030
- Q031
- Q037
- Q094
- Q096
- Q100
- Q106
- Q127
- Q131
- Q151
- Q227
- Q252
- Q256
- Q274
- Q281
- Q286
- Q288

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-ApplicationSummaryResponse-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationSummaryResponse.java`

### English explanation

**Main responsibility:** Summary response DTO for a credit card application.

**Important types:** `record ApplicationSummaryResponse` in `com.tlbank.lending.application.application.service` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.ApplicationStatus`.

**Business flow:** Appears in interview topics around: Architecture boundaries.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationSummaryResponse（record）位於 `com.tlbank.lending.application.application.service`，屬 application 層。Summary response DTO for a credit card application.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.ApplicationStatus`。
業務關聯：Architecture boundaries。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q276

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-DocumentInfoResponse-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/DocumentInfoResponse.java`

### English explanation

**Main responsibility:** Summary of an uploaded application document.

**Important types:** `record DocumentInfoResponse` in `com.tlbank.lending.application.application.service` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.DocumentType`.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：DocumentInfoResponse（record）位於 `com.tlbank.lending.application.application.service`，屬 application 層。Summary of an uploaded application document.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.DocumentType`。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q182

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-MaskedApplicantResponse-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/MaskedApplicantResponse.java`

### English explanation

**Main responsibility:** Masked applicant information for API responses.

**Important types:** `record MaskedApplicantResponse` in `com.tlbank.lending.application.application.service` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.ApplicationStatus`.

**Business flow:** Appears in interview topics around: Notifications (mock).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：MaskedApplicantResponse（record）位於 `com.tlbank.lending.application.application.service`，屬 application 層。Masked applicant information for API responses.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.ApplicationStatus`。
業務關聯：Notifications (mock)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q096

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-application-service-package-info-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/package-info.java`

### English explanation

**Main responsibility:** Application use-case services for credit card application lifecycle.

**Important types:** `type package-info` in `com.tlbank.lending.application.application.service` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Architecture boundaries.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：package-info（type）位於 `com.tlbank.lending.application.application.service`，屬 application 層。Application use-case services for credit card application lifecycle.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Architecture boundaries。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q036

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-cache-service-CacheManagementService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java`

### English explanation

**Main responsibility:** Application service for cache refresh and statistics operations.

**Important types:** `class CacheManagementService` in `com.tlbank.lending.application.cache.service` (layer: application).

**Annotations:** `@RequiredArgsConstructor`, `@Service`.

**Important methods / members:** `refreshAll()`, `refreshSystemParameters()`, `refreshProducts()`, `getStats()`, `estimateEntryBytes()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Service`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.infrastructure.cache.CacheStore`, `com.tlbank.lending.infrastructure.cache.CachedCardProductRepository`.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CacheManagementService（class）位於 `com.tlbank.lending.application.cache.service`，屬 application 層。Application service for cache refresh and statistics operations.
註解：`@RequiredArgsConstructor`, `@Service`。主要方法：`refreshAll()`, `refreshSystemParameters()`, `refreshProducts()`, `getStats()`, `estimateEntryBytes()`。依賴取樣：`org.springframework.stereotype.Service`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.infrastructure.cache.CacheStore`, `com.tlbank.lending.infrastructure.cache.CachedCardProductRepository`。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q086
- Q144
- Q263
- Q284

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-request-AddressRequest-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/AddressRequest.java`

### English explanation

**Main responsibility:** Request DTO for applicant address information.

**Important types:** `record AddressRequest` in `com.tlbank.lending.application.dto.request` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `jakarta.validation.constraints.NotBlank`.

**Business flow:** Appears in interview topics around: API errors & validation.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AddressRequest（record）位於 `com.tlbank.lending.application.dto.request`，屬 application 層。Request DTO for applicant address information.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`jakarta.validation.constraints.NotBlank`。
業務關聯：API errors & validation。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q069

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-request-ApplicantRequest-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/ApplicantRequest.java`

### English explanation

**Main responsibility:** Request DTO for applicant personal information.

**Important types:** `record ApplicantRequest` in `com.tlbank.lending.application.dto.request` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `jakarta.validation.Valid`, `jakarta.validation.constraints.NotBlank`, `jakarta.validation.constraints.NotNull`.

**Business flow:** Appears in interview topics around: API errors & validation.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicantRequest（record）位於 `com.tlbank.lending.application.dto.request`，屬 application 層。Request DTO for applicant personal information.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`jakarta.validation.Valid`, `jakarta.validation.constraints.NotBlank`, `jakarta.validation.constraints.NotNull`。
業務關聯：API errors & validation。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q069
- Q275

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-request-CreateApplicationRequest-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/CreateApplicationRequest.java`

### English explanation

**Main responsibility:** Request DTO for creating a new credit card application.

**Important types:** `record CreateApplicationRequest` in `com.tlbank.lending.application.dto.request` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `jakarta.validation.Valid`, `jakarta.validation.constraints.NotBlank`, `jakarta.validation.constraints.NotNull`.

**Business flow:** Appears in interview topics around: Idempotent application create, API errors & validation.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CreateApplicationRequest（record）位於 `com.tlbank.lending.application.dto.request`，屬 application 層。Request DTO for creating a new credit card application.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`jakarta.validation.Valid`, `jakarta.validation.constraints.NotBlank`, `jakarta.validation.constraints.NotNull`。
業務關聯：Idempotent application create, API errors & validation。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q035
- Q069

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-response-DocumentUploadResponse-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/dto/response/DocumentUploadResponse.java`

### English explanation

**Main responsibility:** Response DTO after a successful document upload.

**Important types:** `record DocumentUploadResponse` in `com.tlbank.lending.application.dto.response` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.DocumentType`.

**Business flow:** Appears in interview topics around: Architecture boundaries.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：DocumentUploadResponse（record）位於 `com.tlbank.lending.application.dto.response`，屬 application 層。Response DTO after a successful document upload.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.DocumentType`。
業務關聯：Architecture boundaries。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q276

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-dto-response-LoginResponse-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/dto/response/LoginResponse.java`

### English explanation

**Main responsibility:** Response payload returned upon successful authentication.

**Important types:** `record LoginResponse` in `com.tlbank.lending.application.dto.response` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Idempotent application create.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：LoginResponse（record）位於 `com.tlbank.lending.application.dto.response`，屬 application 層。Response payload returned upon successful authentication.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Idempotent application create。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q035

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-idempotency-IdempotencyService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java`

### English explanation

**Main responsibility:** Coordinates idempotent API execution. 協調冪等 API 執行：相同 Key + 相同 Body → 回傳快取；相同 Key + 不同 Body → 409。

**Important types:** `class IdempotencyService` in `com.tlbank.lending.application.idempotency` (layer: application).

**Annotations:** `@RequiredArgsConstructor`, `@Service`, `@Slf4j`.

**Important methods / members:** `execute()`, `rebuildResponse()`, `serializeBody()`, `hashRequest()`.

**Direct dependencies (sampled imports):** `org.springframework.beans.factory.annotation.Value`, `org.springframework.http.ResponseEntity`, `org.springframework.stereotype.Service`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.response.ApiResponse`, `com.tlbank.lending.infrastructure.idempotency.IdempotencyEntry`, `com.tlbank.lending.infrastructure.idempotency.IdempotencyStore`.

**Business flow:** Appears in interview topics around: Java 17 Features, Idempotent application create, API errors & validation.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：IdempotencyService（class）位於 `com.tlbank.lending.application.idempotency`，屬 application 層。協調冪等 API 執行：相同 Key + 相同 Body → 回傳快取；相同 Key + 不同 Body → 409。
註解：`@RequiredArgsConstructor`, `@Service`, `@Slf4j`。主要方法：`execute()`, `rebuildResponse()`, `serializeBody()`, `hashRequest()`。依賴取樣：`org.springframework.beans.factory.annotation.Value`, `org.springframework.http.ResponseEntity`, `org.springframework.stereotype.Service`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.response.ApiResponse`, `com.tlbank.lending.infrastructure.idempotency.IdempotencyEntry`, `com.tlbank.lending.infrastructure.idempotency.IdempotencyStore`。
業務關聯：Java 17 Features, Idempotent application create, API errors & validation。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q009
- Q011
- Q035
- Q063
- Q067
- Q132
- Q269

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-notification-service-NotificationServiceImpl-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/notification/service/NotificationServiceImpl.java`

### English explanation

**Main responsibility:** Sends SMS and email notifications using centralized templates.

**Important types:** `class NotificationServiceImpl` in `com.tlbank.lending.application.notification.service` (layer: application).

**Annotations:** `@RequiredArgsConstructor`, `@Service`, `@Slf4j`.

**Important methods / members:** `sendOtpNotification()`, `sendApplicationSubmittedNotification()`, `sendApplicationApprovedNotification()`, `sendApplicationRejectedNotification()`, `sendSmsSafely()`, `sendEmailSafely()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Service`, `com.tlbank.lending.common.util.MaskingUtil`, `com.tlbank.lending.infrastructure.notification.EmailMessage`, `com.tlbank.lending.infrastructure.notification.EmailSender`, `com.tlbank.lending.infrastructure.notification.NotificationTemplate`, `com.tlbank.lending.infrastructure.notification.SmsMessage`, `com.tlbank.lending.infrastructure.notification.SmsSender`.

**Business flow:** Appears in interview topics around: Notifications (mock), OTP verification, Evolution & limitations.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：NotificationServiceImpl（class）位於 `com.tlbank.lending.application.notification.service`，屬 application 層。Sends SMS and email notifications using centralized templates.
註解：`@RequiredArgsConstructor`, `@Service`, `@Slf4j`。主要方法：`sendOtpNotification()`, `sendApplicationSubmittedNotification()`, `sendApplicationApprovedNotification()`, `sendApplicationRejectedNotification()`, `sendSmsSafely()`, `sendEmailSafely()`。依賴取樣：`org.springframework.stereotype.Service`, `com.tlbank.lending.common.util.MaskingUtil`, `com.tlbank.lending.infrastructure.notification.EmailMessage`, `com.tlbank.lending.infrastructure.notification.EmailSender`, `com.tlbank.lending.infrastructure.notification.NotificationTemplate`, `com.tlbank.lending.infrastructure.notification.SmsMessage`, `com.tlbank.lending.infrastructure.notification.SmsSender`。
業務關聯：Notifications (mock), OTP verification, Evolution & limitations。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q162
- Q164
- Q229
- Q233

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-otp-service-OtpAppService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java`

### English explanation

**Main responsibility:** Application service for OTP send and verify use cases.

**Important types:** `class OtpAppService` in `com.tlbank.lending.application.otp.service` (layer: application).

**Annotations:** `@RequiredArgsConstructor`, `@Service`, `@Slf4j`.

**Important methods / members:** `sendOtp()`, `verifyOtp()`, `generateOtpCode()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.AuditContext`, `com.tlbank.lending.common.audit.Auditable`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.exception.WorkflowException`.

**Business flow:** Appears in interview topics around: OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：OtpAppService（class）位於 `com.tlbank.lending.application.otp.service`，屬 application 層。Application service for OTP send and verify use cases.
註解：`@RequiredArgsConstructor`, `@Service`, `@Slf4j`。主要方法：`sendOtp()`, `verifyOtp()`, `generateOtpCode()`。依賴取樣：`org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.AuditContext`, `com.tlbank.lending.common.audit.Auditable`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.exception.WorkflowException`。
業務關聯：OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q046
- Q103
- Q135
- Q137
- Q138
- Q139
- Q150
- Q164
- Q204
- Q205
- Q251
- Q272
- Q277

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-parameter-service-SystemParameterService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/parameter/service/SystemParameterService.java`

### English explanation

**Main responsibility:** Application service for reading and updating system configuration parameters.

**Important types:** `class SystemParameterService` in `com.tlbank.lending.application.parameter.service` (layer: application).

**Annotations:** `@RequiredArgsConstructor`, `@Service`, `@Slf4j`.

**Important methods / members:** `getValue()`, `getValueOrDefault()`, `getIntValue()`, `findByGroup()`, `findAllEnabled()`, `update()`, `refreshCache()`, `toResponse()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.domain.parameter.SystemParameter`, `com.tlbank.lending.domain.parameter.SystemParameterRepository`, `com.tlbank.lending.infrastructure.cache.CacheKeys`, `com.tlbank.lending.infrastructure.cache.CacheStore`.

**Business flow:** Appears in interview topics around: Product / parameter cache, Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SystemParameterService（class）位於 `com.tlbank.lending.application.parameter.service`，屬 application 層。Application service for reading and updating system configuration parameters.
註解：`@RequiredArgsConstructor`, `@Service`, `@Slf4j`。主要方法：`getValue()`, `getValueOrDefault()`, `getIntValue()`, `findByGroup()`, `findAllEnabled()`, `update()`, `refreshCache()`, `toResponse()`。依賴取樣：`org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.domain.parameter.SystemParameter`, `com.tlbank.lending.domain.parameter.SystemParameterRepository`, `com.tlbank.lending.infrastructure.cache.CacheKeys`, `com.tlbank.lending.infrastructure.cache.CacheStore`。
業務關聯：Product / parameter cache, Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q098
- Q141
- Q143
- Q180

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-report-service-DailyStatisticsData-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/DailyStatisticsData.java`

### English explanation

**Main responsibility:** Aggregated daily application statistics used for report generation.

**Important types:** `record DailyStatisticsData` in `com.tlbank.lending.application.report.service` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.ApplicationStatus`.

**Business flow:** Appears in interview topics around: Reporting.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：DailyStatisticsData（record）位於 `com.tlbank.lending.application.report.service`，屬 application 層。Aggregated daily application statistics used for report generation.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.ApplicationStatus`。
業務關聯：Reporting。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q175

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-report-service-ReportAppService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java`

### English explanation

**Main responsibility:** Application service for generating downloadable daily statistics reports.

**Important types:** `class ReportAppService` in `com.tlbank.lending.application.report.service` (layer: application).

**Annotations:** `@RequiredArgsConstructor`, `@Service`.

**Important methods / members:** `generateDailyStatisticsReport()`, `getFileName()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Service`, `com.tlbank.lending.application.dto.request.GenerateReportRequest`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.Auditable`, `com.tlbank.lending.infrastructure.report.ExcelReportGenerator`, `com.tlbank.lending.infrastructure.report.PdfReportGenerator`.

**Business flow:** Appears in interview topics around: Reporting, Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReportAppService（class）位於 `com.tlbank.lending.application.report.service`，屬 application 層。Application service for generating downloadable daily statistics reports.
註解：`@RequiredArgsConstructor`, `@Service`。主要方法：`generateDailyStatisticsReport()`, `getFileName()`。依賴取樣：`org.springframework.stereotype.Service`, `com.tlbank.lending.application.dto.request.GenerateReportRequest`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.Auditable`, `com.tlbank.lending.infrastructure.report.ExcelReportGenerator`, `com.tlbank.lending.infrastructure.report.PdfReportGenerator`。
業務關聯：Reporting, Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q133
- Q171
- Q173
- Q176
- Q209
- Q231

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-report-service-ReportFormat-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportFormat.java`

### English explanation

**Main responsibility:** Supported export formats for generated reports.

**Important types:** `enum ReportFormat` in `com.tlbank.lending.application.report.service` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Reporting.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReportFormat（enum）位於 `com.tlbank.lending.application.report.service`，屬 application 層。Supported export formats for generated reports.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Reporting。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q176

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-review-service-ApproveCaseCommand-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ApproveCaseCommand.java`

### English explanation

**Main responsibility:** Command to approve a review case.

**Important types:** `record ApproveCaseCommand` in `com.tlbank.lending.application.review.service` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApproveCaseCommand（record）位於 `com.tlbank.lending.application.review.service`，屬 application 層。Command to approve a review case.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q267

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-review-service-ReviewAppService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java`

### English explanation

**Main responsibility:** Application service for credit review case use cases.

**Important types:** `class ReviewAppService` in `com.tlbank.lending.application.review.service` (layer: application).

**Annotations:** `@RequiredArgsConstructor`, `@Service`.

**Important methods / members:** `searchCases()`, `getCaseDetail()`, `startCaseReview()`, `approveCase()`, `rejectCase()`, `addRemark()`, `ensureApplicationUnderReview()`, `toSummary()`, `toDetail()`, `toRemarkResponse()`.

**Direct dependencies (sampled imports):** `org.springframework.context.ApplicationEventPublisher`, `org.springframework.data.domain.Page`, `org.springframework.data.domain.Pageable`, `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.application.application.service.DocumentInfoResponse`, `com.tlbank.lending.application.application.service.MaskedApplicantResponse`, `com.tlbank.lending.application.application.service.WorkflowHistoryResponse`.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReviewAppService（class）位於 `com.tlbank.lending.application.review.service`，屬 application 層。Application service for credit review case use cases.
註解：`@RequiredArgsConstructor`, `@Service`。主要方法：`searchCases()`, `getCaseDetail()`, `startCaseReview()`, `approveCase()`, `rejectCase()`, `addRemark()`, `ensureApplicationUnderReview()`, `toSummary()`, `toDetail()`, `toRemarkResponse()`。依賴取樣：`org.springframework.context.ApplicationEventPublisher`, `org.springframework.data.domain.Page`, `org.springframework.data.domain.Pageable`, `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.application.application.service.DocumentInfoResponse`, `com.tlbank.lending.application.application.service.MaskedApplicantResponse`, `com.tlbank.lending.application.application.service.WorkflowHistoryResponse`。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q104
- Q109
- Q110
- Q130
- Q148
- Q209
- Q214

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-application-user-service-UserAppService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/application/user/service/UserAppService.java`

### English explanation

**Main responsibility:** Application service coordinating user management use cases.

**Important types:** `class UserAppService` in `com.tlbank.lending.application.user.service` (layer: application).

**Annotations:** `@RequiredArgsConstructor`, `@Service`.

**Important methods / members:** `createUser()`, `updateStatus()`, `findAll()`, `findById()`, `toResponse()`.

**Direct dependencies (sampled imports):** `org.springframework.security.crypto.password.PasswordEncoder`, `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.domain.user.Role`, `com.tlbank.lending.domain.user.User`, `com.tlbank.lending.domain.user.UserId`.

**Business flow:** Appears in interview topics around: Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：UserAppService（class）位於 `com.tlbank.lending.application.user.service`，屬 application 層。Application service coordinating user management use cases.
註解：`@RequiredArgsConstructor`, `@Service`。主要方法：`createUser()`, `updateStatus()`, `findAll()`, `findById()`, `toResponse()`。依賴取樣：`org.springframework.security.crypto.password.PasswordEncoder`, `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.domain.user.Role`, `com.tlbank.lending.domain.user.User`, `com.tlbank.lending.domain.user.UserId`。
業務關聯：Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q206

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditAction-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAction.java`

### English explanation

**Main responsibility:** Types of auditable actions recorded in the audit log.

**Important types:** `enum AuditAction` in `com.tlbank.lending.common.audit` (layer: common).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AuditAction（enum）位於 `com.tlbank.lending.common.audit`，屬 common 層。Types of auditable actions recorded in the audit log.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q277

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditAspect-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java`

### English explanation

**Main responsibility:** AOP aspect that records audit logs for methods annotated with {@link Auditable}.

**Important types:** `class AuditAspect` in `com.tlbank.lending.common.audit` (layer: common).

**Annotations:** `@Aspect`, `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `audit()`, `resolveFailureAction()`, `resolveUsername()`, `mergeDetail()`, `truncateMessage()`.

**Direct dependencies (sampled imports):** `org.springframework.security.core.Authentication`, `org.springframework.security.core.context.SecurityContextHolder`, `org.springframework.stereotype.Component`, `jakarta.servlet.http.HttpServletRequest`.

**Business flow:** Appears in interview topics around: Audit logging, OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AuditAspect（class）位於 `com.tlbank.lending.common.audit`，屬 common 層。AOP aspect that records audit logs for methods annotated with {@link Auditable}.
註解：`@Aspect`, `@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`audit()`, `resolveFailureAction()`, `resolveUsername()`, `mergeDetail()`, `truncateMessage()`。依賴取樣：`org.springframework.security.core.Authentication`, `org.springframework.security.core.context.SecurityContextHolder`, `org.springframework.stereotype.Component`, `jakarta.servlet.http.HttpServletRequest`。
業務關聯：Audit logging, OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q024
- Q072
- Q073
- Q074
- Q076
- Q135
- Q277
- Q282
- Q291

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditContext-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditContext.java`

### English explanation

**Main responsibility:** Thread-local holder for supplemental audit detail captured during method execution.

**Important types:** `class AuditContext` in `com.tlbank.lending.common.audit` (layer: common).

**Annotations:** `@UtilityClass`.

**Important methods / members:** `put()`, `buildSuffix()`, `clear()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: OTP verification, Audit logging.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AuditContext（class）位於 `com.tlbank.lending.common.audit`，屬 common 層。Thread-local holder for supplemental audit detail captured during method execution.
註解：`@UtilityClass`。主要方法：`put()`, `buildSuffix()`, `clear()`。依賴取樣：limited internal imports extracted。
業務關聯：OTP verification, Audit logging。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q135
- Q264

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditDetailBuilder-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditDetailBuilder.java`

### English explanation

**Main responsibility:** Builds sanitized audit log detail strings from method arguments.

**Important types:** `class AuditDetailBuilder` in `com.tlbank.lending.common.audit` (layer: common).

**Annotations:** `@UtilityClass`.

**Important methods / members:** `buildDetail()`, `appendArg()`, `appendStringArg()`, `appendSegment()`, `sanitize()`, `isSensitiveKey()`, `truncate()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.application.otp.service.SendOtpCommand`, `com.tlbank.lending.application.otp.service.VerifyOtpCommand`, `com.tlbank.lending.application.dto.request.GenerateReportRequest`, `com.tlbank.lending.application.review.service.ApproveCaseCommand`, `com.tlbank.lending.application.review.service.RejectCaseCommand`, `com.tlbank.lending.common.util.MaskingUtil`.

**Business flow:** Appears in interview topics around: OTP verification, Audit logging.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AuditDetailBuilder（class）位於 `com.tlbank.lending.common.audit`，屬 common 層。Builds sanitized audit log detail strings from method arguments.
註解：`@UtilityClass`。主要方法：`buildDetail()`, `appendArg()`, `appendStringArg()`, `appendSegment()`, `sanitize()`, `isSensitiveKey()`, `truncate()`。依賴取樣：`com.tlbank.lending.application.otp.service.SendOtpCommand`, `com.tlbank.lending.application.otp.service.VerifyOtpCommand`, `com.tlbank.lending.application.dto.request.GenerateReportRequest`, `com.tlbank.lending.application.review.service.ApproveCaseCommand`, `com.tlbank.lending.application.review.service.RejectCaseCommand`, `com.tlbank.lending.common.util.MaskingUtil`。
業務關聯：OTP verification, Audit logging。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q201
- Q282

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditIpResolver-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditIpResolver.java`

### English explanation

**Main responsibility:** Utility for resolving client IP addresses from HTTP requests.

**Important types:** `class AuditIpResolver` in `com.tlbank.lending.common.audit` (layer: common).

**Annotations:** none prominent at type level.

**Important methods / members:** `resolveClientIp()`.

**Direct dependencies (sampled imports):** `jakarta.servlet.http.HttpServletRequest`.

**Business flow:** Appears in interview topics around: Audit logging.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AuditIpResolver（class）位於 `com.tlbank.lending.common.audit`，屬 common 層。Utility for resolving client IP addresses from HTTP requests.
註解：none prominent at type level。主要方法：`resolveClientIp()`。依賴取樣：`jakarta.servlet.http.HttpServletRequest`。
業務關聯：Audit logging。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q076

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditLog-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLog.java`

### English explanation

**Main responsibility:** Persistent record of a security or business audit event.

**Important types:** `class AuditLog` in `com.tlbank.lending.common.audit` (layer: common).

**Annotations:** `@AllArgsConstructor`, `@Builder`, `@Entity`, `@EntityListeners`, `@Getter`, `@NoArgsConstructor`, `@Table`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.data.annotation.CreatedDate`, `org.springframework.data.jpa.domain.support.AuditingEntityListener`, `jakarta.persistence.Column`, `jakarta.persistence.Entity`, `jakarta.persistence.EntityListeners`, `jakarta.persistence.EnumType`, `jakarta.persistence.Enumerated`, `jakarta.persistence.GeneratedValue`.

**Business flow:** Appears in interview topics around: Audit logging, Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AuditLog（class）位於 `com.tlbank.lending.common.audit`，屬 common 層。Persistent record of a security or business audit event.
註解：`@AllArgsConstructor`, `@Builder`, `@Entity`, `@EntityListeners`, `@Getter`, `@NoArgsConstructor`, `@Table`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.data.annotation.CreatedDate`, `org.springframework.data.jpa.domain.support.AuditingEntityListener`, `jakarta.persistence.Column`, `jakarta.persistence.Entity`, `jakarta.persistence.EntityListeners`, `jakarta.persistence.EnumType`, `jakarta.persistence.Enumerated`, `jakarta.persistence.GeneratedValue`。
業務關聯：Audit logging, Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q117
- Q220

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-AuditLogWriter-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java`

### English explanation

**Main responsibility:** Asynchronously persists audit log entries without blocking business operations.

**Important types:** `class AuditLogWriter` in `com.tlbank.lending.common.audit` (layer: common).

**Annotations:** `@Component`, `@Slf4j`.

**Important methods / members:** `saveAsync()`.

**Direct dependencies (sampled imports):** `org.springframework.scheduling.annotation.Async`, `org.springframework.stereotype.Component`, `org.springframework.transaction.PlatformTransactionManager`, `org.springframework.transaction.TransactionDefinition`, `org.springframework.transaction.support.TransactionTemplate`.

**Business flow:** Appears in interview topics around: Audit logging, Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AuditLogWriter（class）位於 `com.tlbank.lending.common.audit`，屬 common 層。Asynchronously persists audit log entries without blocking business operations.
註解：`@Component`, `@Slf4j`。主要方法：`saveAsync()`。依賴取樣：`org.springframework.scheduling.annotation.Async`, `org.springframework.stereotype.Component`, `org.springframework.transaction.PlatformTransactionManager`, `org.springframework.transaction.TransactionDefinition`, `org.springframework.transaction.support.TransactionTemplate`。
業務關聯：Audit logging, Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q024
- Q074
- Q079
- Q128
- Q149
- Q281
- Q291

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-audit-Auditable-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/Auditable.java`

### English explanation

**Main responsibility:** Marks a method for automatic audit logging via {@link AuditAspect}.

**Important types:** `type Auditable` in `com.tlbank.lending.common.audit` (layer: common).

**Annotations:** `@Retention`, `@Target`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Audit logging.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：Auditable（type）位於 `com.tlbank.lending.common.audit`，屬 common 層。Marks a method for automatic audit logging via {@link AuditAspect}.
註解：`@Retention`, `@Target`。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Audit logging。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q073
- Q282

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-AsyncConfig-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/config/AsyncConfig.java`

### English explanation

**Main responsibility:** Enables asynchronous execution for non-blocking audit log persistence.

**Important types:** `class AsyncConfig` in `com.tlbank.lending.common.config` (layer: common).

**Annotations:** `@Configuration`, `@EnableAsync`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.context.annotation.Configuration`, `org.springframework.scheduling.annotation.EnableAsync`.

**Business flow:** Appears in interview topics around: Spring Boot 3.x, Audit logging.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AsyncConfig（class）位於 `com.tlbank.lending.common.config`，屬 common 層。Enables asynchronous execution for non-blocking audit log persistence.
註解：`@Configuration`, `@EnableAsync`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.context.annotation.Configuration`, `org.springframework.scheduling.annotation.EnableAsync`。
業務關聯：Spring Boot 3.x, Audit logging。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q018
- Q024

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-CommonConfig-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/config/CommonConfig.java`

### English explanation

**Main responsibility:** Common Spring bean definitions shared across the application.

**Important types:** `class CommonConfig` in `com.tlbank.lending.common.config` (layer: common).

**Annotations:** `@Configuration`.

**Important methods / members:** `clock()`.

**Direct dependencies (sampled imports):** `org.springframework.context.annotation.Bean`, `org.springframework.context.annotation.Configuration`.

**Business flow:** Appears in interview topics around: Spring Boot 3.x, OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CommonConfig（class）位於 `com.tlbank.lending.common.config`，屬 common 層。Common Spring bean definitions shared across the application.
註解：`@Configuration`。主要方法：`clock()`。依賴取樣：`org.springframework.context.annotation.Bean`, `org.springframework.context.annotation.Configuration`。
業務關聯：Spring Boot 3.x, OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q022
- Q060

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-JpaConfig-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/config/JpaConfig.java`

### English explanation

**Main responsibility:** JPA configuration enabling automatic population of audit fields on {@link com.tlbank.lending.common.entity.BaseEntity}.

**Important types:** `class JpaConfig` in `com.tlbank.lending.common.config` (layer: common).

**Annotations:** `@Configuration`, `@EnableJpaAuditing`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.context.annotation.Configuration`, `org.springframework.data.jpa.repository.config.EnableJpaAuditing`.

**Business flow:** Appears in interview topics around: JPA and Hibernate.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：JpaConfig（class）位於 `com.tlbank.lending.common.config`，屬 common 層。JPA configuration enabling automatic population of audit fields on {@link com.tlbank.lending.common.entity.BaseEntity}.
註解：`@Configuration`, `@EnableJpaAuditing`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.context.annotation.Configuration`, `org.springframework.data.jpa.repository.config.EnableJpaAuditing`。
業務關聯：JPA and Hibernate。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q089

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-SchedulerConfig-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulerConfig.java`

### English explanation

**Main responsibility:** Enables Spring's scheduled task execution.

**Important types:** `class SchedulerConfig` in `com.tlbank.lending.common.config` (layer: common).

**Annotations:** `@Configuration`, `@EnableScheduling`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.context.annotation.Configuration`, `org.springframework.scheduling.annotation.EnableScheduling`.

**Business flow:** Appears in interview topics around: Document upload / storage, Scheduled jobs.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SchedulerConfig（class）位於 `com.tlbank.lending.common.config`，屬 common 層。Enables Spring's scheduled task execution.
註解：`@Configuration`, `@EnableScheduling`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.context.annotation.Configuration`, `org.springframework.scheduling.annotation.EnableScheduling`。
業務關聯：Document upload / storage, Scheduled jobs。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q167
- Q192

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-SchedulingConfig-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java`

### English explanation

**Main responsibility:** Enables scheduled tasks such as cache cleanup.

**Important types:** `class SchedulingConfig` in `com.tlbank.lending.common.config` (layer: common).

**Annotations:** `@Configuration`, `@EnableScheduling`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.context.annotation.Configuration`, `org.springframework.scheduling.annotation.EnableScheduling`.

**Business flow:** Appears in interview topics around: Spring Boot 3.x, Authentication & authorization, Document upload / storage, Scheduled jobs.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SchedulingConfig（class）位於 `com.tlbank.lending.common.config`，屬 common 層。Enables scheduled tasks such as cache cleanup.
註解：`@Configuration`, `@EnableScheduling`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.context.annotation.Configuration`, `org.springframework.scheduling.annotation.EnableScheduling`。
業務關聯：Spring Boot 3.x, Authentication & authorization, Document upload / storage, Scheduled jobs。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q018
- Q033
- Q167
- Q192

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-StandardApiResponses-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/config/StandardApiResponses.java`

### English explanation

**Main responsibility:** Standard OpenAPI response descriptions for REST API endpoints.

**Important types:** `type StandardApiResponses` in `com.tlbank.lending.common.config` (layer: common).

**Annotations:** `@ApiResponses`, `@Retention`, `@Target`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: API errors & validation.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：StandardApiResponses（type）位於 `com.tlbank.lending.common.config`，屬 common 層。Standard OpenAPI response descriptions for REST API endpoints.
註解：`@ApiResponses`, `@Retention`, `@Target`。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：API errors & validation。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q064

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-config-SwaggerConfig-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SwaggerConfig.java`

### English explanation

**Main responsibility:** OpenAPI / Swagger configuration for REST API documentation.

**Important types:** `class SwaggerConfig` in `com.tlbank.lending.common.config` (layer: common).

**Annotations:** `@Configuration`, `@OpenAPIDefinition`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.context.annotation.Configuration`.

**Business flow:** Appears in interview topics around: API contract, Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SwaggerConfig（class）位於 `com.tlbank.lending.common.config`，屬 common 層。OpenAPI / Swagger configuration for REST API documentation.
註解：`@Configuration`, `@OpenAPIDefinition`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.context.annotation.Configuration`。
業務關聯：API contract, Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q020
- Q207

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-entity-BaseEntity-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/entity/BaseEntity.java`

### English explanation

**Main responsibility:** Base JPA mapped superclass providing audit timestamps for all persistent entities.

**Important types:** `class BaseEntity` in `com.tlbank.lending.common.entity` (layer: common).

**Annotations:** `@EntityListeners`, `@Getter`, `@MappedSuperclass`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.data.annotation.CreatedDate`, `org.springframework.data.annotation.LastModifiedDate`, `org.springframework.data.jpa.domain.support.AuditingEntityListener`, `jakarta.persistence.Column`, `jakarta.persistence.EntityListeners`, `jakarta.persistence.MappedSuperclass`.

**Business flow:** Appears in interview topics around: JPA and Hibernate.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：BaseEntity（class）位於 `com.tlbank.lending.common.entity`，屬 common 層。Base JPA mapped superclass providing audit timestamps for all persistent entities.
註解：`@EntityListeners`, `@Getter`, `@MappedSuperclass`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.data.annotation.CreatedDate`, `org.springframework.data.annotation.LastModifiedDate`, `org.springframework.data.jpa.domain.support.AuditingEntityListener`, `jakarta.persistence.Column`, `jakarta.persistence.EntityListeners`, `jakarta.persistence.MappedSuperclass`。
業務關聯：JPA and Hibernate。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q089

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-exception-BusinessException-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/BusinessException.java`

### English explanation

**Main responsibility:** Base runtime exception for domain and application-level business rule violations. <p> Carries an {@link ErrorCode} that is translated to an HTTP response by the global handler.

**Important types:** `class BusinessException` in `com.tlbank.lending.common.exception` (layer: common).

**Annotations:** `@Getter`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: CI/CD pipeline.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：BusinessException（class）位於 `com.tlbank.lending.common.exception`，屬 common 層。Base runtime exception for domain and application-level business rule violations. <p> Carries an {@link ErrorCode} that is translated to an HTTP response by the global handler.
註解：`@Getter`。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：CI/CD pipeline。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q071

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-exception-ErrorCode-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java`

### English explanation

**Main responsibility:** Standardized error codes for the TLBank Digital Lending Platform. <p> Each code maps to a specific HTTP status in {@link com.tlbank.lending.presentation.api.advice.GlobalExceptionHandler}.

**Important types:** `enum ErrorCode` in `com.tlbank.lending.common.exception` (layer: common).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: API errors & validation, Authentication & authorization, OTP verification, CI/CD pipeline.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ErrorCode（enum）位於 `com.tlbank.lending.common.exception`，屬 common 層。Standardized error codes for the TLBank Digital Lending Platform. <p> Each code maps to a specific HTTP status in {@link com.tlbank.lending.presentation.api.advice.GlobalExceptionHandler}.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：API errors & validation, Authentication & authorization, OTP verification, CI/CD pipeline。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q013
- Q033
- Q265
- Q300

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-exception-WorkflowException-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/WorkflowException.java`

### English explanation

**Main responsibility:** Exception thrown when an application workflow state transition is not permitted. <p> Mapped to HTTP 409 Conflict with {@link ErrorCode#INVALID_WORKFLOW_TRANSITION}.

**Important types:** `class WorkflowException` in `com.tlbank.lending.common.exception` (layer: common).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: CI/CD pipeline.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：WorkflowException（class）位於 `com.tlbank.lending.common.exception`，屬 common 層。Exception thrown when an application workflow state transition is not permitted. <p> Mapped to HTTP 409 Conflict with {@link ErrorCode#INVALID_WORKFLOW_TRANSITION}.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：CI/CD pipeline。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q071

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-response-ApiResponse-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java`

### English explanation

**Main responsibility:** Uniform API response envelope for all REST endpoints. @param <T> type of the response payload

**Important types:** `record ApiResponse` in `com.tlbank.lending.common.response` (layer: common).

**Annotations:** none prominent at type level.

**Important methods / members:** `success()`, `error()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.common.exception.ErrorCode`.

**Business flow:** Appears in interview topics around: API errors & validation, CI/CD pipeline.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApiResponse（record）位於 `com.tlbank.lending.common.response`，屬 common 層。Uniform API response envelope for all REST endpoints. @param <T> type of the response payload
註解：none prominent at type level。主要方法：`success()`, `error()`。依賴取樣：`com.tlbank.lending.common.exception.ErrorCode`。
業務關聯：API errors & validation, CI/CD pipeline。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q061
- Q300

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-response-FieldErrorDetail-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/response/FieldErrorDetail.java`

### English explanation

**Main responsibility:** Describes a single field-level validation error.

**Important types:** `record FieldErrorDetail` in `com.tlbank.lending.common.response` (layer: common).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: API errors & validation.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：FieldErrorDetail（record）位於 `com.tlbank.lending.common.response`，屬 common 層。Describes a single field-level validation error.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：API errors & validation。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q061
- Q075

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-response-PageResponse-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/response/PageResponse.java`

### English explanation

**Main responsibility:** Paginated response wrapper for list endpoints. @param <T> type of elements in the page

**Important types:** `record PageResponse` in `com.tlbank.lending.common.response` (layer: common).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：PageResponse（record）位於 `com.tlbank.lending.common.response`，屬 common 層。Paginated response wrapper for list endpoints. @param <T> type of elements in the page
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q148

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-common-util-MaskingUtil-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/common/util/MaskingUtil.java`

### English explanation

**Main responsibility:** Utility for masking sensitive personal data in logs and API responses.

**Important types:** `class MaskingUtil` in `com.tlbank.lending.common.util` (layer: common).

**Annotations:** `@UtilityClass`.

**Important methods / members:** `maskMobile()`, `maskNationalId()`, `maskName()`, `maskEmail()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Notifications (mock).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：MaskingUtil（class）位於 `com.tlbank.lending.common.util`，屬 common 層。Utility for masking sensitive personal data in logs and API responses.
註解：`@UtilityClass`。主要方法：`maskMobile()`, `maskNationalId()`, `maskName()`, `maskEmail()`。依賴取樣：limited internal imports extracted。
業務關聯：Notifications (mock)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q096

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-Address-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Address.java`

### English explanation

**Main responsibility:** Value object representing a postal address.

**Important types:** `record Address` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Domain model.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：Address（record）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Value object representing a postal address.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Domain model。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q253

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-Applicant-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Applicant.java`

### English explanation

**Main responsibility:** Value object representing an application applicant's personal information.

**Important types:** `record Applicant` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Domain model, API errors & validation.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：Applicant（record）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Value object representing an application applicant's personal information.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Domain model, API errors & validation。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q048
- Q254
- Q275

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-Application-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### English explanation

**Main responsibility:** Aggregate root representing a credit card application and its workflow lifecycle.

**Important types:** `class Application` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** `@Builder`, `@Getter`.

**Important methods / members:** `verifyOtp()`, `uploadDocuments()`, `submit()`, `startReview()`, `approve()`, `reject()`, `cancel()`, `validateRequiredDocuments()`, `transitionTo()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.exception.WorkflowException`.

**Business flow:** Appears in interview topics around: Java 17 Features, Architecture boundaries, CI/CD pipeline.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：Application（class）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Aggregate root representing a credit card application and its workflow lifecycle.
註解：`@Builder`, `@Getter`。主要方法：`verifyOtp()`, `uploadDocuments()`, `submit()`, `startReview()`, `approve()`, `reject()`, `cancel()`, `validateRequiredDocuments()`, `transitionTo()`。依賴取樣：`com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.exception.WorkflowException`。
業務關聯：Java 17 Features, Architecture boundaries, CI/CD pipeline。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q014
- Q015
- Q027
- Q029
- Q034
- Q037
- Q041
- Q042
- Q045
- Q049
- Q051
- Q053
- Q054
- Q055
- Q056
- Q057
- Q058
- Q131
- Q146
- Q210
- Q236

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-ApplicationId-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java`

### English explanation

**Main responsibility:** Value object representing a unique business identifier for a credit card application.

**Important types:** `record ApplicationId` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** `generate()`, `of()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Credit review workflow, JPA and Hibernate, Project narrative / trade-offs, OTP verification, Domain model.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationId（record）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Value object representing a unique business identifier for a credit card application.
註解：none prominent at type level。主要方法：`generate()`, `of()`。依賴取樣：limited internal imports extracted。
業務關聯：Credit review workflow, JPA and Hibernate, Project narrative / trade-offs, OTP verification, Domain model。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q043
- Q091
- Q247
- Q251
- Q256

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-ApplicationStatus-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`

### English explanation

**Main responsibility:** Represents the lifecycle status of a credit card application.

**Important types:** `enum ApplicationStatus` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** `canTransitionTo()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Java 17 Features, Application lifecycle, Domain model.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationStatus（enum）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Represents the lifecycle status of a credit card application.
註解：none prominent at type level。主要方法：`canTransitionTo()`。依賴取樣：limited internal imports extracted。
業務關聯：Java 17 Features, Application lifecycle, Domain model。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q009
- Q012
- Q014
- Q042
- Q051
- Q052
- Q055
- Q059
- Q114
- Q145
- Q243
- Q246

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-CardProductId-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/CardProductId.java`

### English explanation

**Main responsibility:** Value object representing a card product business identifier.

**Important types:** `record CardProductId` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** `of()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CardProductId（record）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Value object representing a card product business identifier.
註解：none prominent at type level。主要方法：`of()`。依賴取樣：limited internal imports extracted。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q043

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-DocumentInfo-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentInfo.java`

### English explanation

**Main responsibility:** Value object representing metadata for an uploaded application document.

**Important types:** `record DocumentInfo` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：DocumentInfo（record）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Value object representing metadata for an uploaded application document.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q050
- Q255

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-DocumentType-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentType.java`

### English explanation

**Main responsibility:** Types of documents that can be attached to a credit card application.

**Important types:** `enum DocumentType` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：DocumentType（enum）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Types of documents that can be attached to a credit card application.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q050
- Q179

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-Email-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Email.java`

### English explanation

**Main responsibility:** Value object representing an email address.

**Important types:** `record Email` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** `of()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Java 17 Features, Notifications (mock), Domain model.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：Email（record）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Value object representing an email address.
註解：none prominent at type level。主要方法：`of()`。依賴取樣：limited internal imports extracted。
業務關聯：Java 17 Features, Notifications (mock), Domain model。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q009
- Q010
- Q254

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-MobileNumber-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/MobileNumber.java`

### English explanation

**Main responsibility:** Value object representing a Taiwan mobile phone number.

**Important types:** `record MobileNumber` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** `masked()`, `of()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Java 17 Features, Notifications (mock), API errors & validation, Domain model.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：MobileNumber（record）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Value object representing a Taiwan mobile phone number.
註解：none prominent at type level。主要方法：`masked()`, `of()`。依賴取樣：limited internal imports extracted。
業務關聯：Java 17 Features, Notifications (mock), API errors & validation, Domain model。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q009
- Q010
- Q252
- Q254

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-WorkflowHistory-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/WorkflowHistory.java`

### English explanation

**Main responsibility:** Records a single workflow status transition for an application.

**Important types:** `class WorkflowHistory` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** `@Builder`, `@Getter`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: CI/CD pipeline.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：WorkflowHistory（class）位於 `com.tlbank.lending.domain.application`，屬 domain 層。Records a single workflow status transition for an application.
註解：`@Builder`, `@Getter`。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：CI/CD pipeline。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q034
- Q058
- Q257

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-repository-ApplicationRepository-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java`

### English explanation

**Main responsibility:** Domain repository port for {@link Application} persistence.

**Important types:** `interface ApplicationRepository` in `com.tlbank.lending.domain.application.repository` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.Application`, `com.tlbank.lending.domain.application.ApplicationId`, `com.tlbank.lending.domain.application.ApplicationStatus`.

**Business flow:** Appears in interview topics around: Architecture boundaries, Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationRepository（interface）位於 `com.tlbank.lending.domain.application.repository`，屬 domain 層。Domain repository port for {@link Application} persistence.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.Application`, `com.tlbank.lending.domain.application.ApplicationId`, `com.tlbank.lending.domain.application.ApplicationStatus`。
業務關聯：Architecture boundaries, Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q028
- Q101
- Q110

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-application-repository-package-info-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/package-info.java`

### English explanation

**Main responsibility:** Domain repository port for credit card application aggregates.

**Important types:** `type package-info` in `com.tlbank.lending.domain.application.repository` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Architecture boundaries.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：package-info（type）位於 `com.tlbank.lending.domain.application.repository`，屬 domain 層。Domain repository port for credit card application aggregates.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Architecture boundaries。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q036

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-event-ApplicationApprovedEvent-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java`

### English explanation

**Main responsibility:** Domain event published when a credit card application is approved.

**Important types:** `record ApplicationApprovedEvent` in `com.tlbank.lending.domain.event` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Credit review workflow, Domain events.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationApprovedEvent（record）位於 `com.tlbank.lending.domain.event`，屬 domain 層。Domain event published when a credit card application is approved.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Credit review workflow, Domain events。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q038
- Q044
- Q047
- Q109
- Q130

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-event-ApplicationCancelledEvent-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationCancelledEvent.java`

### English explanation

**Main responsibility:** Domain event published when a credit card application is cancelled.

**Important types:** `record ApplicationCancelledEvent` in `com.tlbank.lending.domain.event` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: OTP verification, Domain events.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationCancelledEvent（record）位於 `com.tlbank.lending.domain.event`，屬 domain 層。Domain event published when a credit card application is cancelled.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：OTP verification, Domain events。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q223
- Q274

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-event-ApplicationSubmittedEvent-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationSubmittedEvent.java`

### English explanation

**Main responsibility:** Domain event published when a credit card application is submitted for review.

**Important types:** `record ApplicationSubmittedEvent` in `com.tlbank.lending.domain.event` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Domain events.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationSubmittedEvent（record）位於 `com.tlbank.lending.domain.event`，屬 domain 層。Domain event published when a credit card application is submitted for review.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Domain events。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q044
- Q047

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-event-OtpGeneratedEvent-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/OtpGeneratedEvent.java`

### English explanation

**Main responsibility:** Domain event published when an OTP is generated (for future notification use).

**Important types:** `record OtpGeneratedEvent` in `com.tlbank.lending.domain.event` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Domain events, OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：OtpGeneratedEvent（record）位於 `com.tlbank.lending.domain.event`，屬 domain 層。Domain event published when an OTP is generated (for future notification use).
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Domain events, OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q047
- Q223

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-otp-OtpPurpose-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpPurpose.java`

### English explanation

**Main responsibility:** Defines the business purpose of an OTP record.

**Important types:** `enum OtpPurpose` in `com.tlbank.lending.domain.otp` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：OtpPurpose（enum）位於 `com.tlbank.lending.domain.otp`，屬 domain 層。Defines the business purpose of an OTP record.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q235
- Q266

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-otp-OtpRecord-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java`

### English explanation

**Main responsibility:** Aggregate root representing a one-time password verification record.

**Important types:** `class OtpRecord` in `com.tlbank.lending.domain.otp` (layer: domain).

**Annotations:** `@Builder`, `@Getter`.

**Important methods / members:** `verify()`, `isExpired()`, `markExpired()`, `cancel()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`.

**Business flow:** Appears in interview topics around: Domain model, OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：OtpRecord（class）位於 `com.tlbank.lending.domain.otp`，屬 domain 層。Aggregate root representing a one-time password verification record.
註解：`@Builder`, `@Getter`。主要方法：`verify()`, `isExpired()`, `markExpired()`, `cancel()`。依賴取樣：`com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`。
業務關聯：Domain model, OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q045
- Q046
- Q060
- Q136
- Q138
- Q140
- Q235
- Q266

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-otp-VerifyResult-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/VerifyResult.java`

### English explanation

**Main responsibility:** Result of a successful OTP verification attempt.

**Important types:** `enum VerifyResult` in `com.tlbank.lending.domain.otp` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：VerifyResult（enum）位於 `com.tlbank.lending.domain.otp`，屬 domain 層。Result of a successful OTP verification attempt.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q046
- Q136

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-product-CardProduct-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/product/CardProduct.java`

### English explanation

**Main responsibility:** Domain model representing a credit card product offering.

**Important types:** `class CardProduct` in `com.tlbank.lending.domain.product` (layer: domain).

**Annotations:** `@Builder`, `@Getter`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.CardProductId`.

**Business flow:** Appears in interview topics around: Domain model.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CardProduct（class）位於 `com.tlbank.lending.domain.product`，屬 domain 層。Domain model representing a credit card product offering.
註解：`@Builder`, `@Getter`。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.CardProductId`。
業務關聯：Domain model。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q045
- Q262

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-product-ProductFeature-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/product/ProductFeature.java`

### English explanation

**Main responsibility:** Value object representing a card product feature.

**Important types:** `record ProductFeature` in `com.tlbank.lending.domain.product` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Domain model.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ProductFeature（record）位於 `com.tlbank.lending.domain.product`，屬 domain 層。Value object representing a card product feature.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Domain model。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q262

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-product-repository-CardProductRepository-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/product/repository/CardProductRepository.java`

### English explanation

**Main responsibility:** Domain repository port for {@link CardProduct} persistence.

**Important types:** `interface CardProductRepository` in `com.tlbank.lending.domain.product.repository` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.CardProductId`, `com.tlbank.lending.domain.product.CardProduct`.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CardProductRepository（interface）位於 `com.tlbank.lending.domain.product.repository`，屬 domain 層。Domain repository port for {@link CardProduct} persistence.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.CardProductId`, `com.tlbank.lending.domain.product.CardProduct`。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q039

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-ReviewCase-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java`

### English explanation

**Main responsibility:** Aggregate root representing a credit review case for a submitted application.

**Important types:** `class ReviewCase` in `com.tlbank.lending.domain.review` (layer: domain).

**Annotations:** `@Builder`, `@Getter`.

**Important methods / members:** `createFor()`, `assign()`, `startReview()`, `approve()`, `reject()`, `addRemark()`, `transitionTo()`, `addRemarkInternal()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.common.exception.WorkflowException`.

**Business flow:** Appears in interview topics around: Domain model, Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReviewCase（class）位於 `com.tlbank.lending.domain.review`，屬 domain 層。Aggregate root representing a credit review case for a submitted application.
註解：`@Builder`, `@Getter`。主要方法：`createFor()`, `assign()`, `startReview()`, `approve()`, `reject()`, `addRemark()`, `transitionTo()`, `addRemarkInternal()`。依賴取樣：`com.tlbank.lending.common.exception.WorkflowException`。
業務關聯：Domain model, Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q041
- Q045
- Q049
- Q145
- Q146
- Q147
- Q232
- Q273

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-ReviewCaseId-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCaseId.java`

### English explanation

**Main responsibility:** Value object representing a unique business identifier for a review case.

**Important types:** `record ReviewCaseId` in `com.tlbank.lending.domain.review` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** `generate()`, `of()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReviewCaseId（record）位於 `com.tlbank.lending.domain.review`，屬 domain 層。Value object representing a unique business identifier for a review case.
註解：none prominent at type level。主要方法：`generate()`, `of()`。依賴取樣：limited internal imports extracted。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q043

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-ReviewCaseSearchCriteria-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCaseSearchCriteria.java`

### English explanation

**Main responsibility:** Search criteria for filtering review cases.

**Important types:** `record ReviewCaseSearchCriteria` in `com.tlbank.lending.domain.review` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReviewCaseSearchCriteria（record）位於 `com.tlbank.lending.domain.review`，屬 domain 層。Search criteria for filtering review cases.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q105

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-ReviewRemark-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewRemark.java`

### English explanation

**Main responsibility:** A remark added to a review case by a reviewer.

**Important types:** `class ReviewRemark` in `com.tlbank.lending.domain.review` (layer: domain).

**Annotations:** `@Builder`, `@Getter`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReviewRemark（class）位於 `com.tlbank.lending.domain.review`，屬 domain 層。A remark added to a review case by a reviewer.
註解：`@Builder`, `@Getter`。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q147

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-review-repository-ReviewCaseRepository-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/repository/ReviewCaseRepository.java`

### English explanation

**Main responsibility:** Domain repository port for {@link ReviewCase} persistence.

**Important types:** `interface ReviewCaseRepository` in `com.tlbank.lending.domain.review.repository` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.data.domain.Page`, `org.springframework.data.domain.Pageable`, `com.tlbank.lending.domain.review.ReviewCase`, `com.tlbank.lending.domain.review.ReviewCaseId`, `com.tlbank.lending.domain.review.ReviewCaseSearchCriteria`, `com.tlbank.lending.domain.review.ReviewStatus`.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReviewCaseRepository（interface）位於 `com.tlbank.lending.domain.review.repository`，屬 domain 層。Domain repository port for {@link ReviewCase} persistence.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.data.domain.Page`, `org.springframework.data.domain.Pageable`, `com.tlbank.lending.domain.review.ReviewCase`, `com.tlbank.lending.domain.review.ReviewCaseId`, `com.tlbank.lending.domain.review.ReviewCaseSearchCriteria`, `com.tlbank.lending.domain.review.ReviewStatus`。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q104
- Q105
- Q110

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-service-WorkflowDomainService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java`

### English explanation

**Main responsibility:** Domain service for validating application workflow status transitions.

**Important types:** `class WorkflowDomainService` in `com.tlbank.lending.domain.service` (layer: domain).

**Annotations:** `@Service`.

**Important methods / members:** `validateTransition()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Service`, `com.tlbank.lending.common.exception.WorkflowException`, `com.tlbank.lending.domain.application.ApplicationStatus`.

**Business flow:** Appears in interview topics around: CI/CD pipeline, Architecture boundaries.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：WorkflowDomainService（class）位於 `com.tlbank.lending.domain.service`，屬 domain 層。Domain service for validating application workflow status transitions.
註解：`@Service`。主要方法：`validateTransition()`。依賴取樣：`org.springframework.stereotype.Service`, `com.tlbank.lending.common.exception.WorkflowException`, `com.tlbank.lending.domain.application.ApplicationStatus`。
業務關聯：CI/CD pipeline, Architecture boundaries。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q030
- Q032
- Q225

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-user-Role-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/user/Role.java`

### English explanation

**Main responsibility:** Application roles mapped to Spring Security authorities.

**Important types:** `enum Role` in `com.tlbank.lending.domain.user` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Architecture boundaries.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：Role（enum）位於 `com.tlbank.lending.domain.user`，屬 domain 層。Application roles mapped to Spring Security authorities.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Architecture boundaries。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q270

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-user-User-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/user/User.java`

### English explanation

**Main responsibility:** Domain aggregate representing a platform user account.

**Important types:** `class User` in `com.tlbank.lending.domain.user` (layer: domain).

**Annotations:** `@Builder`, `@Getter`.

**Important methods / members:** `enable()`, `disable()`, `assignRole()`, `removeRole()`, `hasRole()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Domain model, Architecture boundaries, Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：User（class）位於 `com.tlbank.lending.domain.user`，屬 domain 層。Domain aggregate representing a platform user account.
註解：`@Builder`, `@Getter`。主要方法：`enable()`, `disable()`, `assignRole()`, `removeRole()`, `hasRole()`。依賴取樣：limited internal imports extracted。
業務關聯：Domain model, Architecture boundaries, Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q045
- Q270
- Q271

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-domain-user-UserId-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/domain/user/UserId.java`

### English explanation

**Main responsibility:** Value object representing a unique business identifier for a user.

**Important types:** `record UserId` in `com.tlbank.lending.domain.user` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** `generate()`, `of()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: JPA and Hibernate.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：UserId（record）位於 `com.tlbank.lending.domain.user`，屬 domain 層。Value object representing a unique business identifier for a user.
註解：none prominent at type level。主要方法：`generate()`, `of()`。依賴取樣：limited internal imports extracted。
業務關聯：JPA and Hibernate。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q260

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-cache-CacheKeys-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CacheKeys.java`

### English explanation

**Main responsibility:** Well-known cache key prefixes and identifiers.

**Important types:** `class CacheKeys` in `com.tlbank.lending.infrastructure.cache` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** `systemParamKey()`, `cardProductKey()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CacheKeys（class）位於 `com.tlbank.lending.infrastructure.cache`，屬 infrastructure 層。Well-known cache key prefixes and identifiers.
註解：none prominent at type level。主要方法：`systemParamKey()`, `cardProductKey()`。依賴取樣：limited internal imports extracted。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q141
- Q212

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-cache-CacheStore-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CacheStore.java`

### English explanation

**Main responsibility:** Generic cache storage port without framework dependencies. @param <V> type of cached values

**Important types:** `interface CacheStore` in `com.tlbank.lending.infrastructure.cache` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CacheStore（interface）位於 `com.tlbank.lending.infrastructure.cache`，屬 infrastructure 層。Generic cache storage port without framework dependencies. @param <V> type of cached values
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q143
- Q144
- Q228

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-cache-CachedCardProductRepository-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java`

### English explanation

**Main responsibility:** Caching decorator around the JPA-backed {@link CardProductRepository}.

**Important types:** `class CachedCardProductRepository` in `com.tlbank.lending.infrastructure.cache` (layer: infrastructure).

**Annotations:** `@Component`, `@Primary`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `findAllEnabled()`, `findById()`, `refreshCache()`, `evictCardProductKeys()`.

**Direct dependencies (sampled imports):** `org.springframework.beans.factory.annotation.Qualifier`, `org.springframework.context.annotation.Primary`, `org.springframework.stereotype.Component`, `com.tlbank.lending.domain.application.CardProductId`, `com.tlbank.lending.domain.product.CardProduct`, `com.tlbank.lending.domain.product.repository.CardProductRepository`.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CachedCardProductRepository（class）位於 `com.tlbank.lending.infrastructure.cache`，屬 infrastructure 層。Caching decorator around the JPA-backed {@link CardProductRepository}.
註解：`@Component`, `@Primary`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`findAllEnabled()`, `findById()`, `refreshCache()`, `evictCardProductKeys()`。依賴取樣：`org.springframework.beans.factory.annotation.Qualifier`, `org.springframework.context.annotation.Primary`, `org.springframework.stereotype.Component`, `com.tlbank.lending.domain.application.CardProductId`, `com.tlbank.lending.domain.product.CardProduct`, `com.tlbank.lending.domain.product.repository.CardProductRepository`。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q039
- Q086
- Q108
- Q212
- Q284

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-cache-InMemoryCacheStore-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java`

### English explanation

**Main responsibility:** Thread-safe in-memory implementation of {@link CacheStore}.

**Important types:** `class InMemoryCacheStore` in `com.tlbank.lending.infrastructure.cache` (layer: infrastructure).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `get()`, `put()`, `evict()`, `evictAll()`, `keys()`, `cleanupExpiredEntries()`.

**Direct dependencies (sampled imports):** `org.springframework.scheduling.annotation.Scheduled`, `org.springframework.stereotype.Component`.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：InMemoryCacheStore（class）位於 `com.tlbank.lending.infrastructure.cache`，屬 infrastructure 層。Thread-safe in-memory implementation of {@link CacheStore}.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`get()`, `put()`, `evict()`, `evictAll()`, `keys()`, `cleanupExpiredEntries()`。依賴取樣：`org.springframework.scheduling.annotation.Scheduled`, `org.springframework.stereotype.Component`。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q086
- Q097
- Q141
- Q142
- Q212
- Q215
- Q228

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-event-NotificationEventHandler-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java`

### English explanation

**Main responsibility:** Handles domain events by dispatching customer notifications.

**Important types:** `class NotificationEventHandler` in `com.tlbank.lending.infrastructure.event` (layer: infrastructure).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `onApplicationSubmitted()`, `onApplicationApproved()`, `onApplicationRejected()`.

**Direct dependencies (sampled imports):** `org.springframework.context.event.EventListener`, `org.springframework.stereotype.Component`, `com.tlbank.lending.application.notification.service.NotificationService`, `com.tlbank.lending.domain.event.ApplicationApprovedEvent`, `com.tlbank.lending.domain.event.ApplicationRejectedEvent`, `com.tlbank.lending.domain.event.ApplicationSubmittedEvent`.

**Business flow:** Appears in interview topics around: Repository strategy, Credit review workflow, Domain events, Notifications (mock).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：NotificationEventHandler（class）位於 `com.tlbank.lending.infrastructure.event`，屬 infrastructure 層。Handles domain events by dispatching customer notifications.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`onApplicationSubmitted()`, `onApplicationApproved()`, `onApplicationRejected()`。依賴取樣：`org.springframework.context.event.EventListener`, `org.springframework.stereotype.Component`, `com.tlbank.lending.application.notification.service.NotificationService`, `com.tlbank.lending.domain.event.ApplicationApprovedEvent`, `com.tlbank.lending.domain.event.ApplicationRejectedEvent`, `com.tlbank.lending.domain.event.ApplicationSubmittedEvent`。
業務關聯：Repository strategy, Credit review workflow, Domain events, Notifications (mock)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q002
- Q038
- Q044
- Q099
- Q100
- Q109
- Q130
- Q233

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-event-ReviewEventHandler-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java`

### English explanation

**Main responsibility:** Handles domain events related to the credit review workflow.

**Important types:** `class ReviewEventHandler` in `com.tlbank.lending.infrastructure.event` (layer: infrastructure).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `onApplicationSubmitted()`.

**Direct dependencies (sampled imports):** `org.springframework.context.event.EventListener`, `org.springframework.stereotype.Component`, `com.tlbank.lending.domain.event.ApplicationSubmittedEvent`, `com.tlbank.lending.domain.review.ReviewCase`, `com.tlbank.lending.domain.review.repository.ReviewCaseRepository`.

**Business flow:** Appears in interview topics around: Repository strategy, Credit review workflow, Domain events.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReviewEventHandler（class）位於 `com.tlbank.lending.infrastructure.event`，屬 infrastructure 層。Handles domain events related to the credit review workflow.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`onApplicationSubmitted()`。依賴取樣：`org.springframework.context.event.EventListener`, `org.springframework.stereotype.Component`, `com.tlbank.lending.domain.event.ApplicationSubmittedEvent`, `com.tlbank.lending.domain.review.ReviewCase`, `com.tlbank.lending.domain.review.repository.ReviewCaseRepository`。
業務關聯：Repository strategy, Credit review workflow, Domain events。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q002
- Q038
- Q044
- Q049
- Q100
- Q227
- Q238

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-idempotency-IdempotencyEntry-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/IdempotencyEntry.java`

### English explanation

**Main responsibility:** Cached idempotent response entry. 快取的冪等回應紀錄（stored response snapshot）。

**Important types:** `record IdempotencyEntry` in `com.tlbank.lending.infrastructure.idempotency` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Idempotent application create.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：IdempotencyEntry（record）位於 `com.tlbank.lending.infrastructure.idempotency`，屬 infrastructure 層。快取的冪等回應紀錄（stored response snapshot）。
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Idempotent application create。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q016

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-idempotency-IdempotencyStore-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/IdempotencyStore.java`

### English explanation

**Main responsibility:** Port for idempotency storage. 冪等儲存介面（Redis / In-Memory 實作）。

**Important types:** `interface IdempotencyStore` in `com.tlbank.lending.infrastructure.idempotency` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Architecture boundaries.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：IdempotencyStore（interface）位於 `com.tlbank.lending.infrastructure.idempotency`，屬 infrastructure 層。冪等儲存介面（Redis / In-Memory 實作）。
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Architecture boundaries。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q028

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-idempotency-InMemoryIdempotencyStore-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/InMemoryIdempotencyStore.java`

### English explanation

**Main responsibility:** In-memory idempotency store for tests. 測試用記憶體實作（不需 Redis）。

**Important types:** `class InMemoryIdempotencyStore` in `com.tlbank.lending.infrastructure.idempotency` (layer: infrastructure).

**Annotations:** `@Component`, `@ConditionalOnProperty`.

**Important methods / members:** `find()`, `save()`, `tryAcquireLock()`, `releaseLock()`, `TimedEntry()`.

**Direct dependencies (sampled imports):** `org.springframework.boot.autoconfigure.condition.ConditionalOnProperty`, `org.springframework.stereotype.Component`.

**Business flow:** Appears in interview topics around: Idempotent application create.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：InMemoryIdempotencyStore（class）位於 `com.tlbank.lending.infrastructure.idempotency`，屬 infrastructure 層。測試用記憶體實作（不需 Redis）。
註解：`@Component`, `@ConditionalOnProperty`。主要方法：`find()`, `save()`, `tryAcquireLock()`, `releaseLock()`, `TimedEntry()`。依賴取樣：`org.springframework.boot.autoconfigure.condition.ConditionalOnProperty`, `org.springframework.stereotype.Component`。
業務關聯：Idempotent application create。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q134

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-idempotency-RedisIdempotencyStore-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java`

### English explanation

**Main responsibility:** Redis-backed idempotency store (production / dev with Redis). 使用 Redis 儲存冪等紀錄，TTL 到期自動清除。

**Important types:** `class RedisIdempotencyStore` in `com.tlbank.lending.infrastructure.idempotency` (layer: infrastructure).

**Annotations:** `@Component`, `@ConditionalOnProperty`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `find()`, `save()`, `tryAcquireLock()`, `releaseLock()`.

**Direct dependencies (sampled imports):** `org.springframework.boot.autoconfigure.condition.ConditionalOnProperty`, `org.springframework.data.redis.core.StringRedisTemplate`, `org.springframework.stereotype.Component`.

**Business flow:** Appears in interview topics around: Idempotent application create, Architecture boundaries, Evolution & limitations.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：RedisIdempotencyStore（class）位於 `com.tlbank.lending.infrastructure.idempotency`，屬 infrastructure 層。使用 Redis 儲存冪等紀錄，TTL 到期自動清除。
註解：`@Component`, `@ConditionalOnProperty`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`find()`, `save()`, `tryAcquireLock()`, `releaseLock()`。依賴取樣：`org.springframework.boot.autoconfigure.condition.ConditionalOnProperty`, `org.springframework.data.redis.core.StringRedisTemplate`, `org.springframework.stereotype.Component`。
業務關聯：Idempotent application create, Architecture boundaries, Evolution & limitations。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q016
- Q028
- Q132
- Q134
- Q229
- Q268

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-EmailSender-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/EmailSender.java`

### English explanation

**Main responsibility:** Port for sending email notifications.

**Important types:** `interface EmailSender` in `com.tlbank.lending.infrastructure.notification` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Notifications (mock).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：EmailSender（interface）位於 `com.tlbank.lending.infrastructure.notification`，屬 infrastructure 層。Port for sending email notifications.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Notifications (mock)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q165

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-MockEmailSender-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockEmailSender.java`

### English explanation

**Main responsibility:** Mock email sender that logs messages instead of sending real email.

**Important types:** `class MockEmailSender` in `com.tlbank.lending.infrastructure.notification` (layer: infrastructure).

**Annotations:** `@Component`, `@ConditionalOnProperty`, `@Slf4j`.

**Important methods / members:** `send()`.

**Direct dependencies (sampled imports):** `org.springframework.boot.autoconfigure.condition.ConditionalOnProperty`, `org.springframework.stereotype.Component`.

**Business flow:** Appears in interview topics around: CI/CD pipeline, Notifications (mock).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：MockEmailSender（class）位於 `com.tlbank.lending.infrastructure.notification`，屬 infrastructure 層。Mock email sender that logs messages instead of sending real email.
註解：`@Component`, `@ConditionalOnProperty`, `@Slf4j`。主要方法：`send()`。依賴取樣：`org.springframework.boot.autoconfigure.condition.ConditionalOnProperty`, `org.springframework.stereotype.Component`。
業務關聯：CI/CD pipeline, Notifications (mock)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q008
- Q161

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-MockSmsSender-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java`

### English explanation

**Main responsibility:** Mock SMS sender that logs messages instead of sending real SMS.

**Important types:** `class MockSmsSender` in `com.tlbank.lending.infrastructure.notification` (layer: infrastructure).

**Annotations:** `@Component`, `@ConditionalOnProperty`, `@Slf4j`.

**Important methods / members:** `send()`, `redactOtpCode()`.

**Direct dependencies (sampled imports):** `org.springframework.boot.autoconfigure.condition.ConditionalOnProperty`, `org.springframework.stereotype.Component`, `com.tlbank.lending.common.util.MaskingUtil`.

**Business flow:** Appears in interview topics around: CI/CD pipeline, Notifications (mock).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：MockSmsSender（class）位於 `com.tlbank.lending.infrastructure.notification`，屬 infrastructure 層。Mock SMS sender that logs messages instead of sending real SMS.
註解：`@Component`, `@ConditionalOnProperty`, `@Slf4j`。主要方法：`send()`, `redactOtpCode()`。依賴取樣：`org.springframework.boot.autoconfigure.condition.ConditionalOnProperty`, `org.springframework.stereotype.Component`, `com.tlbank.lending.common.util.MaskingUtil`。
業務關聯：CI/CD pipeline, Notifications (mock)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q008
- Q161
- Q165

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-NotificationTemplate-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/NotificationTemplate.java`

### English explanation

**Main responsibility:** Centralized notification message templates.

**Important types:** `class NotificationTemplate` in `com.tlbank.lending.infrastructure.notification` (layer: infrastructure).

**Annotations:** `@UtilityClass`.

**Important methods / members:** `formatOtpSms()`, `formatSubmitSms()`, `formatApprovedSms()`, `formatRejectedSms()`, `formatOtpEmailBody()`, `formatSubmitEmailSubject()`, `formatApprovedEmailSubject()`, `formatRejectedEmailSubject()`, `formatSubmitEmailBody()`, `formatApprovedEmailBody()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Notifications (mock).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：NotificationTemplate（class）位於 `com.tlbank.lending.infrastructure.notification`，屬 infrastructure 層。Centralized notification message templates.
註解：`@UtilityClass`。主要方法：`formatOtpSms()`, `formatSubmitSms()`, `formatApprovedSms()`, `formatRejectedSms()`, `formatOtpEmailBody()`, `formatSubmitEmailSubject()`, `formatApprovedEmailSubject()`, `formatRejectedEmailSubject()`, `formatSubmitEmailBody()`, `formatApprovedEmailBody()`。依賴取樣：limited internal imports extracted。
業務關聯：Notifications (mock)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q163
- Q166

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-notification-SmsSender-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/SmsSender.java`

### English explanation

**Main responsibility:** Port for sending SMS notifications.

**Important types:** `interface SmsSender` in `com.tlbank.lending.infrastructure.notification` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Notifications (mock).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SmsSender（interface）位於 `com.tlbank.lending.infrastructure.notification`，屬 infrastructure 層。Port for sending SMS notifications.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Notifications (mock)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q165

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicantEmbeddable-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicantEmbeddable.java`

### English explanation

**Main responsibility:** Embeddable value object for applicant personal information.

**Important types:** `class ApplicantEmbeddable` in `com.tlbank.lending.infrastructure.persistence.application` (layer: application).

**Annotations:** `@AllArgsConstructor`, `@Builder`, `@Embeddable`, `@Getter`, `@NoArgsConstructor`, `@Setter`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `jakarta.persistence.Column`, `jakarta.persistence.Embeddable`.

**Business flow:** Appears in interview topics around: Domain model.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicantEmbeddable（class）位於 `com.tlbank.lending.infrastructure.persistence.application`，屬 application 層。Embeddable value object for applicant personal information.
註解：`@AllArgsConstructor`, `@Builder`, `@Embeddable`, `@Getter`, `@NoArgsConstructor`, `@Setter`。主要方法：no prominent public methods extracted。依賴取樣：`jakarta.persistence.Column`, `jakarta.persistence.Embeddable`。
業務關聯：Domain model。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q048

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicationDocumentEntity-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationDocumentEntity.java`

### English explanation

**Main responsibility:** JPA entity mapping the {@code application_documents} table.

**Important types:** `class ApplicationDocumentEntity` in `com.tlbank.lending.infrastructure.persistence.application` (layer: application).

**Annotations:** `@AllArgsConstructor`, `@Builder`, `@Entity`, `@Getter`, `@NoArgsConstructor`, `@Setter`, `@Table`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.DocumentType`, `jakarta.persistence.Column`, `jakarta.persistence.Entity`, `jakarta.persistence.EnumType`, `jakarta.persistence.Enumerated`, `jakarta.persistence.FetchType`, `jakarta.persistence.GeneratedValue`, `jakarta.persistence.GenerationType`.

**Business flow:** Appears in interview topics around: Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationDocumentEntity（class）位於 `com.tlbank.lending.infrastructure.persistence.application`，屬 application 層。JPA entity mapping the {@code application_documents} table.
註解：`@AllArgsConstructor`, `@Builder`, `@Entity`, `@Getter`, `@NoArgsConstructor`, `@Setter`, `@Table`。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.DocumentType`, `jakarta.persistence.Column`, `jakarta.persistence.Entity`, `jakarta.persistence.EnumType`, `jakarta.persistence.Enumerated`, `jakarta.persistence.FetchType`, `jakarta.persistence.GeneratedValue`, `jakarta.persistence.GenerationType`。
業務關聯：Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q050
- Q181

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicationEntity-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`

### English explanation

**Main responsibility:** JPA entity mapping the {@code applications} table.

**Important types:** `class ApplicationEntity` in `com.tlbank.lending.infrastructure.persistence.application` (layer: application).

**Annotations:** `@AllArgsConstructor`, `@Builder`, `@Entity`, `@Getter`, `@NoArgsConstructor`, `@Setter`, `@Table`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.common.entity.BaseEntity`, `com.tlbank.lending.domain.application.ApplicationStatus`, `jakarta.persistence.CascadeType`, `jakarta.persistence.Column`, `jakarta.persistence.Embedded`, `jakarta.persistence.Entity`, `jakarta.persistence.EnumType`, `jakarta.persistence.Enumerated`.

**Business flow:** Appears in interview topics around: Architecture boundaries, JPA and Hibernate, Document upload / storage, Transactions.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationEntity（class）位於 `com.tlbank.lending.infrastructure.persistence.application`，屬 application 層。JPA entity mapping the {@code applications} table.
註解：`@AllArgsConstructor`, `@Builder`, `@Entity`, `@Getter`, `@NoArgsConstructor`, `@Setter`, `@Table`。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.common.entity.BaseEntity`, `com.tlbank.lending.domain.application.ApplicationStatus`, `jakarta.persistence.CascadeType`, `jakarta.persistence.Column`, `jakarta.persistence.Embedded`, `jakarta.persistence.Entity`, `jakarta.persistence.EnumType`, `jakarta.persistence.Enumerated`。
業務關聯：Architecture boundaries, JPA and Hibernate, Document upload / storage, Transactions。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q029
- Q089
- Q090
- Q091
- Q092
- Q127
- Q259

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicationJpaRepository-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java`

### English explanation

**Main responsibility:** Spring Data JPA repository for {@link ApplicationEntity}.

**Important types:** `interface ApplicationJpaRepository` in `com.tlbank.lending.infrastructure.persistence.application` (layer: application).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.data.jpa.repository.JpaRepository`, `org.springframework.data.jpa.repository.Query`, `org.springframework.data.repository.query.Param`, `com.tlbank.lending.domain.application.ApplicationStatus`.

**Business flow:** Appears in interview topics around: Reporting, JPA and Hibernate, Architecture boundaries.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationJpaRepository（interface）位於 `com.tlbank.lending.infrastructure.persistence.application`，屬 application 層。Spring Data JPA repository for {@link ApplicationEntity}.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.data.jpa.repository.JpaRepository`, `org.springframework.data.jpa.repository.Query`, `org.springframework.data.repository.query.Param`, `com.tlbank.lending.domain.application.ApplicationStatus`。
業務關聯：Reporting, JPA and Hibernate, Architecture boundaries。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q068
- Q091
- Q095
- Q102

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-ApplicationRepositoryImpl-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`

### English explanation

**Main responsibility:** JPA-backed implementation of the {@link ApplicationRepository} domain port.

**Important types:** `class ApplicationRepositoryImpl` in `com.tlbank.lending.infrastructure.persistence.application` (layer: application).

**Annotations:** `@Repository`, `@RequiredArgsConstructor`.

**Important methods / members:** `findById()`, `save()`, `findByStatus()`, `toDomain()`, `toApplicant()`, `toEntity()`, `applyToEntity()`, `syncWorkflowHistories()`, `syncDocuments()`, `toApplicantEmbeddable()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Repository`, `com.tlbank.lending.domain.application.Address`, `com.tlbank.lending.domain.application.Applicant`, `com.tlbank.lending.domain.application.Application`, `com.tlbank.lending.domain.application.ApplicationId`, `com.tlbank.lending.domain.application.ApplicationStatus`, `com.tlbank.lending.domain.application.CardProductId`, `com.tlbank.lending.domain.application.DocumentInfo`.

**Business flow:** Appears in interview topics around: Architecture boundaries, CI/CD pipeline, Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationRepositoryImpl（class）位於 `com.tlbank.lending.infrastructure.persistence.application`，屬 application 層。JPA-backed implementation of the {@link ApplicationRepository} domain port.
註解：`@Repository`, `@RequiredArgsConstructor`。主要方法：`findById()`, `save()`, `findByStatus()`, `toDomain()`, `toApplicant()`, `toEntity()`, `applyToEntity()`, `syncWorkflowHistories()`, `syncDocuments()`, `toApplicantEmbeddable()`。依賴取樣：`org.springframework.stereotype.Repository`, `com.tlbank.lending.domain.application.Address`, `com.tlbank.lending.domain.application.Applicant`, `com.tlbank.lending.domain.application.Application`, `com.tlbank.lending.domain.application.ApplicationId`, `com.tlbank.lending.domain.application.ApplicationStatus`, `com.tlbank.lending.domain.application.CardProductId`, `com.tlbank.lending.domain.application.DocumentInfo`。
業務關聯：Architecture boundaries, CI/CD pipeline, Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q027
- Q028
- Q029
- Q034
- Q092
- Q101
- Q102
- Q107
- Q210
- Q258
- Q297

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-application-WorkflowHistoryEntity-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/WorkflowHistoryEntity.java`

### English explanation

**Main responsibility:** JPA entity mapping the {@code workflow_histories} table.

**Important types:** `class WorkflowHistoryEntity` in `com.tlbank.lending.infrastructure.persistence.application` (layer: application).

**Annotations:** `@AllArgsConstructor`, `@Builder`, `@Entity`, `@Getter`, `@NoArgsConstructor`, `@Setter`, `@Table`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.domain.application.ApplicationStatus`, `jakarta.persistence.Column`, `jakarta.persistence.Entity`, `jakarta.persistence.EnumType`, `jakarta.persistence.Enumerated`, `jakarta.persistence.FetchType`, `jakarta.persistence.GeneratedValue`, `jakarta.persistence.GenerationType`.

**Business flow:** Appears in interview topics around: CI/CD pipeline.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：WorkflowHistoryEntity（class）位於 `com.tlbank.lending.infrastructure.persistence.application`，屬 application 層。JPA entity mapping the {@code workflow_histories} table.
註解：`@AllArgsConstructor`, `@Builder`, `@Entity`, `@Getter`, `@NoArgsConstructor`, `@Setter`, `@Table`。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.domain.application.ApplicationStatus`, `jakarta.persistence.Column`, `jakarta.persistence.Entity`, `jakarta.persistence.EnumType`, `jakarta.persistence.Enumerated`, `jakarta.persistence.FetchType`, `jakarta.persistence.GeneratedValue`, `jakarta.persistence.GenerationType`。
業務關聯：CI/CD pipeline。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q034
- Q257
- Q258

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-otp-OtpJpaRepository-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java`

### English explanation

**Main responsibility:** Spring Data JPA repository for {@link OtpRecordEntity}.

**Important types:** `interface OtpJpaRepository` in `com.tlbank.lending.infrastructure.persistence.otp` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.data.jpa.repository.JpaRepository`, `org.springframework.data.jpa.repository.Modifying`, `org.springframework.data.jpa.repository.Query`, `org.springframework.data.repository.query.Param`, `com.tlbank.lending.domain.otp.OtpStatus`.

**Business flow:** Appears in interview topics around: OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：OtpJpaRepository（interface）位於 `com.tlbank.lending.infrastructure.persistence.otp`，屬 infrastructure 層。Spring Data JPA repository for {@link OtpRecordEntity}.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.data.jpa.repository.JpaRepository`, `org.springframework.data.jpa.repository.Modifying`, `org.springframework.data.jpa.repository.Query`, `org.springframework.data.repository.query.Param`, `com.tlbank.lending.domain.otp.OtpStatus`。
業務關聯：OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q093
- Q103
- Q129
- Q272

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-otp-OtpRepositoryImpl-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpRepositoryImpl.java`

### English explanation

**Main responsibility:** JPA-backed implementation of the {@link OtpRepository} domain port.

**Important types:** `class OtpRepositoryImpl` in `com.tlbank.lending.infrastructure.persistence.otp` (layer: infrastructure).

**Annotations:** `@Repository`, `@RequiredArgsConstructor`.

**Important methods / members:** `save()`, `findLatestPendingByMobile()`, `markExpiredBefore()`, `toDomain()`, `toEntity()`, `applyToEntity()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Repository`, `com.tlbank.lending.domain.otp.OtpRecord`, `com.tlbank.lending.domain.otp.OtpStatus`, `com.tlbank.lending.domain.otp.repository.OtpRepository`.

**Business flow:** Appears in interview topics around: OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：OtpRepositoryImpl（class）位於 `com.tlbank.lending.infrastructure.persistence.otp`，屬 infrastructure 層。JPA-backed implementation of the {@link OtpRepository} domain port.
註解：`@Repository`, `@RequiredArgsConstructor`。主要方法：`save()`, `findLatestPendingByMobile()`, `markExpiredBefore()`, `toDomain()`, `toEntity()`, `applyToEntity()`。依賴取樣：`org.springframework.stereotype.Repository`, `com.tlbank.lending.domain.otp.OtpRecord`, `com.tlbank.lending.domain.otp.OtpStatus`, `com.tlbank.lending.domain.otp.repository.OtpRepository`。
業務關聯：OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q107
- Q137
- Q138

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-product-CardProductRepositoryImpl-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/product/CardProductRepositoryImpl.java`

### English explanation

**Main responsibility:** JPA-backed implementation of the {@link CardProductRepository} domain port.

**Important types:** `class CardProductRepositoryImpl` in `com.tlbank.lending.infrastructure.persistence.product` (layer: infrastructure).

**Annotations:** `@Repository`, `@RequiredArgsConstructor`.

**Important methods / members:** `findAllEnabled()`, `findById()`, `toDomain()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Repository`, `com.tlbank.lending.domain.application.CardProductId`, `com.tlbank.lending.domain.product.CardProduct`, `com.tlbank.lending.domain.product.ProductFeature`, `com.tlbank.lending.domain.product.repository.CardProductRepository`.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CardProductRepositoryImpl（class）位於 `com.tlbank.lending.infrastructure.persistence.product`，屬 infrastructure 層。JPA-backed implementation of the {@link CardProductRepository} domain port.
註解：`@Repository`, `@RequiredArgsConstructor`。主要方法：`findAllEnabled()`, `findById()`, `toDomain()`。依賴取樣：`org.springframework.stereotype.Repository`, `com.tlbank.lending.domain.application.CardProductId`, `com.tlbank.lending.domain.product.CardProduct`, `com.tlbank.lending.domain.product.ProductFeature`, `com.tlbank.lending.domain.product.repository.CardProductRepository`。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q039
- Q108

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-persistence-user-UserEntity-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java`

### English explanation

**Main responsibility:** JPA entity mapping the {@code users} table and associated role assignments.

**Important types:** `class UserEntity` in `com.tlbank.lending.infrastructure.persistence.user` (layer: infrastructure).

**Annotations:** `@AllArgsConstructor`, `@Builder`, `@Entity`, `@Getter`, `@NoArgsConstructor`, `@Setter`, `@Table`.

**Important methods / members:** `isEnabled()`, `updateLastLoginAt()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.common.entity.BaseEntity`, `jakarta.persistence.CollectionTable`, `jakarta.persistence.Column`, `jakarta.persistence.ElementCollection`, `jakarta.persistence.Entity`, `jakarta.persistence.FetchType`, `jakarta.persistence.Id`, `jakarta.persistence.JoinColumn`.

**Business flow:** Appears in interview topics around: JPA and Hibernate, Architecture boundaries, Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：UserEntity（class）位於 `com.tlbank.lending.infrastructure.persistence.user`，屬 infrastructure 層。JPA entity mapping the {@code users} table and associated role assignments.
註解：`@AllArgsConstructor`, `@Builder`, `@Entity`, `@Getter`, `@NoArgsConstructor`, `@Setter`, `@Table`。主要方法：`isEnabled()`, `updateLastLoginAt()`。依賴取樣：`com.tlbank.lending.common.entity.BaseEntity`, `jakarta.persistence.CollectionTable`, `jakarta.persistence.Column`, `jakarta.persistence.ElementCollection`, `jakarta.persistence.Entity`, `jakarta.persistence.FetchType`, `jakarta.persistence.Id`, `jakarta.persistence.JoinColumn`。
業務關聯：JPA and Hibernate, Architecture boundaries, Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q259
- Q260
- Q270
- Q271

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-report-ExcelReportGenerator-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java`

### English explanation

**Main responsibility:** Generates Excel workbooks for daily application statistics reports.

**Important types:** `class ExcelReportGenerator` in `com.tlbank.lending.infrastructure.report` (layer: infrastructure).

**Annotations:** `@Component`, `@RequiredArgsConstructor`.

**Important methods / members:** `generateDailyStatistics()`, `writeSummarySheet()`, `writeProductSheet()`, `createBoldStyle()`, `createHeaderCell()`, `autoSizeColumns()`, `formatPercentage()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Component`, `com.tlbank.lending.application.report.service.DailyStatisticsData`, `com.tlbank.lending.domain.application.ApplicationStatus`.

**Business flow:** Appears in interview topics around: Reporting.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ExcelReportGenerator（class）位於 `com.tlbank.lending.infrastructure.report`，屬 infrastructure 層。Generates Excel workbooks for daily application statistics reports.
註解：`@Component`, `@RequiredArgsConstructor`。主要方法：`generateDailyStatistics()`, `writeSummarySheet()`, `writeProductSheet()`, `createBoldStyle()`, `createHeaderCell()`, `autoSizeColumns()`, `formatPercentage()`。依賴取樣：`org.springframework.stereotype.Component`, `com.tlbank.lending.application.report.service.DailyStatisticsData`, `com.tlbank.lending.domain.application.ApplicationStatus`。
業務關聯：Reporting。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q133
- Q155
- Q171
- Q172
- Q226

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-report-PdfReportGenerator-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/PdfReportGenerator.java`

### English explanation

**Main responsibility:** Generates PDF documents for daily application statistics reports.

**Important types:** `class PdfReportGenerator` in `com.tlbank.lending.infrastructure.report` (layer: infrastructure).

**Annotations:** `@Component`.

**Important methods / members:** `generateDailyStatistics()`, `formatPercentage()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Component`, `com.tlbank.lending.application.report.service.DailyStatisticsData`, `com.tlbank.lending.domain.application.ApplicationStatus`.

**Business flow:** Appears in interview topics around: Reporting.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：PdfReportGenerator（class）位於 `com.tlbank.lending.infrastructure.report`，屬 infrastructure 層。Generates PDF documents for daily application statistics reports.
註解：`@Component`。主要方法：`generateDailyStatistics()`, `formatPercentage()`。依賴取樣：`org.springframework.stereotype.Component`, `com.tlbank.lending.application.report.service.DailyStatisticsData`, `com.tlbank.lending.domain.application.ApplicationStatus`。
業務關聯：Reporting。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q133
- Q172
- Q174
- Q226

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-scheduler-CacheRefreshScheduler-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/CacheRefreshScheduler.java`

### English explanation

**Main responsibility:** Scheduled task that refreshes system parameter cache and evicts card product cache entries.

**Important types:** `class CacheRefreshScheduler` in `com.tlbank.lending.infrastructure.scheduler` (layer: infrastructure).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `refreshCaches()`, `evictCardProductCacheKeys()`.

**Direct dependencies (sampled imports):** `org.springframework.scheduling.annotation.Scheduled`, `org.springframework.stereotype.Component`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.infrastructure.cache.CacheKeys`, `com.tlbank.lending.infrastructure.cache.CacheStore`.

**Business flow:** Appears in interview topics around: Product / parameter cache, OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CacheRefreshScheduler（class）位於 `com.tlbank.lending.infrastructure.scheduler`，屬 infrastructure 層。Scheduled task that refreshes system parameter cache and evicts card product cache entries.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`refreshCaches()`, `evictCardProductCacheKeys()`。依賴取樣：`org.springframework.scheduling.annotation.Scheduled`, `org.springframework.stereotype.Component`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.infrastructure.cache.CacheKeys`, `com.tlbank.lending.infrastructure.cache.CacheStore`。
業務關聯：Product / parameter cache, OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q098
- Q170

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-scheduler-DailyStatisticsScheduler-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/DailyStatisticsScheduler.java`

### English explanation

**Main responsibility:** Scheduled task that builds and logs daily application statistics.

**Important types:** `class DailyStatisticsScheduler` in `com.tlbank.lending.infrastructure.scheduler` (layer: infrastructure).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `generateDailyStatistics()`.

**Direct dependencies (sampled imports):** `org.springframework.scheduling.annotation.Scheduled`, `org.springframework.stereotype.Component`, `com.tlbank.lending.application.report.service.DailyStatisticsData`, `com.tlbank.lending.application.report.service.ReportDataService`.

**Business flow:** Appears in interview topics around: Scheduled jobs, OTP verification, Reporting.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：DailyStatisticsScheduler（class）位於 `com.tlbank.lending.infrastructure.scheduler`，屬 infrastructure 層。Scheduled task that builds and logs daily application statistics.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`generateDailyStatistics()`。依賴取樣：`org.springframework.scheduling.annotation.Scheduled`, `org.springframework.stereotype.Component`, `com.tlbank.lending.application.report.service.DailyStatisticsData`, `com.tlbank.lending.application.report.service.ReportDataService`。
業務關聯：Scheduled jobs, OTP verification, Reporting。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q169
- Q170
- Q231

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-scheduler-OtpCleanupScheduler-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java`

### English explanation

**Main responsibility:** Scheduled task that marks expired pending OTP records as EXPIRED.

**Important types:** `class OtpCleanupScheduler` in `com.tlbank.lending.infrastructure.scheduler` (layer: infrastructure).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `cleanupExpiredOtps()`.

**Direct dependencies (sampled imports):** `org.springframework.scheduling.annotation.Scheduled`, `org.springframework.stereotype.Component`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.domain.otp.repository.OtpRepository`.

**Business flow:** Appears in interview topics around: Spring Boot 3.x, OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：OtpCleanupScheduler（class）位於 `com.tlbank.lending.infrastructure.scheduler`，屬 infrastructure 層。Scheduled task that marks expired pending OTP records as EXPIRED.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`cleanupExpiredOtps()`。依賴取樣：`org.springframework.scheduling.annotation.Scheduled`, `org.springframework.stereotype.Component`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.domain.otp.repository.OtpRepository`。
業務關聯：Spring Boot 3.x, OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q022
- Q060
- Q093
- Q129
- Q140
- Q168
- Q170

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-storage-DocumentStorageService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/DocumentStorageService.java`

### English explanation

**Main responsibility:** Port for storing and validating application document uploads.

**Important types:** `interface DocumentStorageService` in `com.tlbank.lending.infrastructure.storage` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.web.multipart.MultipartFile`, `com.tlbank.lending.domain.application.DocumentType`.

**Business flow:** Appears in interview topics around: Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：DocumentStorageService（interface）位於 `com.tlbank.lending.infrastructure.storage`，屬 infrastructure 層。Port for storing and validating application document uploads.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.web.multipart.MultipartFile`, `com.tlbank.lending.domain.application.DocumentType`。
業務關聯：Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q106

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-infrastructure-storage-LocalDocumentStorageService-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java`

### English explanation

**Main responsibility:** Local filesystem implementation of {@link DocumentStorageService}.

**Important types:** `class LocalDocumentStorageService` in `com.tlbank.lending.infrastructure.storage` (layer: infrastructure).

**Annotations:** `@RequiredArgsConstructor`, `@Service`, `@Slf4j`.

**Important methods / members:** `validate()`, `store()`, `extractExtension()`.

**Direct dependencies (sampled imports):** `org.springframework.beans.factory.annotation.Value`, `org.springframework.stereotype.Service`, `jakarta.annotation.PostConstruct`, `org.springframework.web.multipart.MultipartFile`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.domain.application.DocumentType`.

**Business flow:** Appears in interview topics around: CI/CD pipeline, Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：LocalDocumentStorageService（class）位於 `com.tlbank.lending.infrastructure.storage`，屬 infrastructure 層。Local filesystem implementation of {@link DocumentStorageService}.
註解：`@RequiredArgsConstructor`, `@Service`, `@Slf4j`。主要方法：`validate()`, `store()`, `extractExtension()`。依賴取樣：`org.springframework.beans.factory.annotation.Value`, `org.springframework.stereotype.Service`, `jakarta.annotation.PostConstruct`, `org.springframework.web.multipart.MultipartFile`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.domain.application.DocumentType`。
業務關聯：CI/CD pipeline, Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q008
- Q053
- Q106
- Q177
- Q178
- Q179
- Q180
- Q181
- Q182
- Q208
- Q216
- Q255
- Q298

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-package-info-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/package-info.java`

### English explanation

**Main responsibility:** TLBank Digital Lending Platform root package. <p> Hexagonal architecture layers: <ul> <li>{@code presentation} – web and REST adapters</li> <li>{@code application} – use cases and DTOs</li> <li>{@code domain} – aggregates, value objects, repository ports</li> <li>{@code infrastructure} – JPA, cache, notification adapte

**Important types:** `type package-info` in `com.tlbank.lending` (layer: other).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Architecture boundaries.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the other layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：package-info（type）位於 `com.tlbank.lending`，屬 other 層。TLBank Digital Lending Platform root package. <p> Hexagonal architecture layers: <ul> <li>{@code presentation} – web and REST adapters</li> <li>{@code application} – use cases and DTOs</li> <li>{@code domain} – aggregates, value objects, repository ports</li> <li>{@code infrastructure} – JPA, cache, notification adapte
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Architecture boundaries。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q036

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-api-advice-GlobalExceptionHandler-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`

### English explanation

**Main responsibility:** Global exception handler translating thrown exceptions into standardized {@link ApiResponse} payloads.

**Important types:** `class GlobalExceptionHandler` in `com.tlbank.lending.presentation.api.advice` (layer: presentation).

**Annotations:** `@RestControllerAdvice`, `@Slf4j`.

**Important methods / members:** `handleBusinessException()`, `handleWorkflowException()`, `handleValidationException()`, `handleAccessDeniedException()`, `handleAuthenticationException()`, `handleException()`.

**Direct dependencies (sampled imports):** `org.springframework.http.HttpStatus`, `org.springframework.http.ResponseEntity`, `org.springframework.security.access.AccessDeniedException`, `org.springframework.security.core.AuthenticationException`, `org.springframework.web.bind.MethodArgumentNotValidException`, `org.springframework.web.bind.annotation.ExceptionHandler`, `org.springframework.web.bind.annotation.RestControllerAdvice`, `com.tlbank.lending.common.exception.BusinessException`.

**Business flow:** Appears in interview topics around: Java 17 Features, API errors & validation, Architecture boundaries, Authentication & authorization, CI/CD pipeline, Audit logging.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the presentation layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：GlobalExceptionHandler（class）位於 `com.tlbank.lending.presentation.api.advice`，屬 presentation 層。Global exception handler translating thrown exceptions into standardized {@link ApiResponse} payloads.
註解：`@RestControllerAdvice`, `@Slf4j`。主要方法：`handleBusinessException()`, `handleWorkflowException()`, `handleValidationException()`, `handleAccessDeniedException()`, `handleAuthenticationException()`, `handleException()`。依賴取樣：`org.springframework.http.HttpStatus`, `org.springframework.http.ResponseEntity`, `org.springframework.security.access.AccessDeniedException`, `org.springframework.security.core.AuthenticationException`, `org.springframework.web.bind.MethodArgumentNotValidException`, `org.springframework.web.bind.annotation.ExceptionHandler`, `org.springframework.web.bind.annotation.RestControllerAdvice`, `com.tlbank.lending.common.exception.BusinessException`。
業務關聯：Java 17 Features, API errors & validation, Architecture boundaries, Authentication & authorization, CI/CD pipeline, Audit logging。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q009
- Q013
- Q031
- Q070
- Q071
- Q072
- Q075
- Q265
- Q300

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-api-v1-ApplicationApiController-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java`

### English explanation

**Main responsibility:** REST API for credit card application creation and retrieval.

**Important types:** `class ApplicationApiController` in `com.tlbank.lending.presentation.api.v1` (layer: presentation).

**Annotations:** `@RequestMapping`, `@RequiredArgsConstructor`, `@RestController`, `@Tag`.

**Important methods / members:** `createApplication()`, `getApplication()`, `uploadDocument()`, `submitApplication()`, `cancelApplication()`.

**Direct dependencies (sampled imports):** `org.springframework.http.HttpStatus`, `org.springframework.http.ResponseEntity`, `org.springframework.web.bind.annotation.GetMapping`, `org.springframework.web.bind.annotation.PathVariable`, `org.springframework.web.bind.annotation.PostMapping`, `org.springframework.web.bind.annotation.RequestBody`, `org.springframework.web.bind.annotation.RequestHeader`, `org.springframework.web.bind.annotation.RequestMapping`.

**Business flow:** Appears in interview topics around: Architecture boundaries, Schema migration, Idempotent application create, API errors & validation, Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the presentation layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationApiController（class）位於 `com.tlbank.lending.presentation.api.v1`，屬 presentation 層。REST API for credit card application creation and retrieval.
註解：`@RequestMapping`, `@RequiredArgsConstructor`, `@RestController`, `@Tag`。主要方法：`createApplication()`, `getApplication()`, `uploadDocument()`, `submitApplication()`, `cancelApplication()`。依賴取樣：`org.springframework.http.HttpStatus`, `org.springframework.http.ResponseEntity`, `org.springframework.web.bind.annotation.GetMapping`, `org.springframework.web.bind.annotation.PathVariable`, `org.springframework.web.bind.annotation.PostMapping`, `org.springframework.web.bind.annotation.RequestBody`, `org.springframework.web.bind.annotation.RequestHeader`, `org.springframework.web.bind.annotation.RequestMapping`。
業務關聯：Architecture boundaries, Schema migration, Idempotent application create, API errors & validation, Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q031
- Q037
- Q062
- Q063
- Q064
- Q065
- Q066
- Q067
- Q177
- Q278
- Q286

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-api-v1-ReviewApiController-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ReviewApiController.java`

### English explanation

**Main responsibility:** REST API for credit review case management.

**Important types:** `class ReviewApiController` in `com.tlbank.lending.presentation.api.v1` (layer: presentation).

**Annotations:** `@PreAuthorize`, `@RequestMapping`, `@RequiredArgsConstructor`, `@RestController`, `@StandardApiResponses`, `@Tag`.

**Important methods / members:** `getCases()`, `getCaseDetail()`, `approveCase()`, `rejectCase()`, `addRemark()`.

**Direct dependencies (sampled imports):** `org.springframework.data.domain.PageRequest`, `org.springframework.data.domain.Pageable`, `org.springframework.data.domain.Sort`, `org.springframework.http.HttpStatus`, `org.springframework.http.ResponseEntity`, `org.springframework.security.access.prepost.PreAuthorize`, `org.springframework.security.core.annotation.AuthenticationPrincipal`, `org.springframework.security.core.userdetails.UserDetails`.

**Business flow:** Appears in interview topics around: Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the presentation layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReviewApiController（class）位於 `com.tlbank.lending.presentation.api.v1`，屬 presentation 層。REST API for credit review case management.
註解：`@PreAuthorize`, `@RequestMapping`, `@RequiredArgsConstructor`, `@RestController`, `@StandardApiResponses`, `@Tag`。主要方法：`getCases()`, `getCaseDetail()`, `approveCase()`, `rejectCase()`, `addRemark()`。依賴取樣：`org.springframework.data.domain.PageRequest`, `org.springframework.data.domain.Pageable`, `org.springframework.data.domain.Sort`, `org.springframework.http.HttpStatus`, `org.springframework.http.ResponseEntity`, `org.springframework.security.access.prepost.PreAuthorize`, `org.springframework.security.core.annotation.AuthenticationPrincipal`, `org.springframework.security.core.userdetails.UserDetails`。
業務關聯：Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q267

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-api-v1-SchedulerApiController-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/SchedulerApiController.java`

### English explanation

**Main responsibility:** REST API for manually triggering scheduled background tasks.

**Important types:** `class SchedulerApiController` in `com.tlbank.lending.presentation.api.v1` (layer: presentation).

**Annotations:** `@PreAuthorize`, `@RequestMapping`, `@RequiredArgsConstructor`, `@RestController`, `@StandardApiResponses`, `@Tag`.

**Important methods / members:** `runOtpCleanup()`, `runCacheRefresh()`, `runDailyStatistics()`.

**Direct dependencies (sampled imports):** `org.springframework.format.annotation.DateTimeFormat`, `org.springframework.security.access.prepost.PreAuthorize`, `org.springframework.web.bind.annotation.PostMapping`, `org.springframework.web.bind.annotation.RequestMapping`, `org.springframework.web.bind.annotation.RequestParam`, `org.springframework.web.bind.annotation.RestController`, `com.tlbank.lending.common.config.StandardApiResponses`, `com.tlbank.lending.common.response.ApiResponse`.

**Business flow:** Appears in interview topics around: Scheduled jobs.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the presentation layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SchedulerApiController（class）位於 `com.tlbank.lending.presentation.api.v1`，屬 presentation 層。REST API for manually triggering scheduled background tasks.
註解：`@PreAuthorize`, `@RequestMapping`, `@RequiredArgsConstructor`, `@RestController`, `@StandardApiResponses`, `@Tag`。主要方法：`runOtpCleanup()`, `runCacheRefresh()`, `runDailyStatistics()`。依賴取樣：`org.springframework.format.annotation.DateTimeFormat`, `org.springframework.security.access.prepost.PreAuthorize`, `org.springframework.web.bind.annotation.PostMapping`, `org.springframework.web.bind.annotation.RequestMapping`, `org.springframework.web.bind.annotation.RequestParam`, `org.springframework.web.bind.annotation.RestController`, `com.tlbank.lending.common.config.StandardApiResponses`, `com.tlbank.lending.common.response.ApiResponse`。
業務關聯：Scheduled jobs。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q169

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-presentation-web-ApplicationWebController-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/presentation/web/ApplicationWebController.java`

### English explanation

**Main responsibility:** Web controller serving Thymeleaf-based application flow pages.

**Important types:** `class ApplicationWebController` in `com.tlbank.lending.presentation.web` (layer: presentation).

**Annotations:** `@Controller`, `@RequiredArgsConstructor`.

**Important methods / members:** `home()`, `products()`, `applyForm()`, `createApplication()`, `otpPage()`, `uploadPage()`, `submitConfirmPage()`, `submitApplication()`, `applicationComplete()`.

**Direct dependencies (sampled imports):** `org.springframework.stereotype.Controller`, `org.springframework.ui.Model`, `org.springframework.web.bind.annotation.GetMapping`, `org.springframework.web.bind.annotation.ModelAttribute`, `org.springframework.web.bind.annotation.PostMapping`, `org.springframework.web.bind.annotation.RequestParam`, `com.tlbank.lending.application.application.service.ApplicationAppService`, `com.tlbank.lending.application.application.service.ApplicationDetailResponse`.

**Business flow:** Appears in interview topics around: API contract.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the presentation layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationWebController（class）位於 `com.tlbank.lending.presentation.web`，屬 presentation 層。Web controller serving Thymeleaf-based application flow pages.
註解：`@Controller`, `@RequiredArgsConstructor`。主要方法：`home()`, `products()`, `applyForm()`, `createApplication()`, `otpPage()`, `uploadPage()`, `submitConfirmPage()`, `submitApplication()`, `applicationComplete()`。依賴取樣：`org.springframework.stereotype.Controller`, `org.springframework.ui.Model`, `org.springframework.web.bind.annotation.GetMapping`, `org.springframework.web.bind.annotation.ModelAttribute`, `org.springframework.web.bind.annotation.PostMapping`, `org.springframework.web.bind.annotation.RequestParam`, `com.tlbank.lending.application.application.service.ApplicationAppService`, `com.tlbank.lending.application.application.service.ApplicationDetailResponse`。
業務關聯：API contract。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q278

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-security-config-SecurityConfig-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### English explanation

**Main responsibility:** Central Spring Security configuration for session-based authentication and role-based access control.

**Important types:** `class SecurityConfig` in `com.tlbank.lending.security.config` (layer: security).

**Annotations:** `@Configuration`, `@EnableMethodSecurity`, `@EnableWebSecurity`, `@RequiredArgsConstructor`.

**Important methods / members:** `passwordEncoder()`, `authenticationManager()`, `sessionRegistry()`, `securityFilterChain()`, `isDevProfile()`.

**Direct dependencies (sampled imports):** `org.springframework.context.annotation.Bean`, `org.springframework.context.annotation.Configuration`, `org.springframework.core.env.Environment`, `org.springframework.http.HttpMethod`, `org.springframework.security.authentication.AuthenticationManager`, `org.springframework.security.authentication.ProviderManager`, `org.springframework.security.authentication.dao.DaoAuthenticationProvider`, `org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity`.

**Business flow:** Appears in interview topics around: Spring Boot 3.x, API contract, Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the security layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SecurityConfig（class）位於 `com.tlbank.lending.security.config`，屬 security 層。Central Spring Security configuration for session-based authentication and role-based access control.
註解：`@Configuration`, `@EnableMethodSecurity`, `@EnableWebSecurity`, `@RequiredArgsConstructor`。主要方法：`passwordEncoder()`, `authenticationManager()`, `sessionRegistry()`, `securityFilterChain()`, `isDevProfile()`。依賴取樣：`org.springframework.context.annotation.Bean`, `org.springframework.context.annotation.Configuration`, `org.springframework.core.env.Environment`, `org.springframework.http.HttpMethod`, `org.springframework.security.authentication.AuthenticationManager`, `org.springframework.security.authentication.ProviderManager`, `org.springframework.security.authentication.dao.DaoAuthenticationProvider`, `org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity`。
業務關聯：Spring Boot 3.x, API contract, Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q017
- Q020
- Q023
- Q025
- Q033
- Q070
- Q077
- Q081
- Q083
- Q084
- Q085
- Q087
- Q112
- Q203
- Q207
- Q208
- Q250

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-security-filter-MdcLoggingFilter-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java`

### English explanation

**Main responsibility:** Populates SLF4J MDC with request-scoped correlation identifiers for structured logging.

**Important types:** `class MdcLoggingFilter` in `com.tlbank.lending.security.filter` (layer: security).

**Annotations:** `@Component`.

**Important methods / members:** `doFilterInternal()`, `resolveUsername()`.

**Direct dependencies (sampled imports):** `org.springframework.security.core.Authentication`, `org.springframework.security.core.context.SecurityContextHolder`, `org.springframework.stereotype.Component`, `org.springframework.web.filter.OncePerRequestFilter`, `jakarta.servlet.FilterChain`, `jakarta.servlet.ServletException`, `jakarta.servlet.http.HttpServletRequest`, `jakarta.servlet.http.HttpServletResponse`.

**Business flow:** Appears in interview topics around: Authentication & authorization, Observability and Logging, Evolution & limitations.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the security layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：MdcLoggingFilter（class）位於 `com.tlbank.lending.security.filter`，屬 security 層。Populates SLF4J MDC with request-scoped correlation identifiers for structured logging.
註解：`@Component`。主要方法：`doFilterInternal()`, `resolveUsername()`。依賴取樣：`org.springframework.security.core.Authentication`, `org.springframework.security.core.context.SecurityContextHolder`, `org.springframework.stereotype.Component`, `org.springframework.web.filter.OncePerRequestFilter`, `jakarta.servlet.FilterChain`, `jakarta.servlet.ServletException`, `jakarta.servlet.http.HttpServletRequest`, `jakarta.servlet.http.HttpServletResponse`。
業務關聯：Authentication & authorization, Observability and Logging, Evolution & limitations。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q080
- Q218
- Q234
- Q237

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-security-handler-CustomAccessDeniedHandler-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/CustomAccessDeniedHandler.java`

### English explanation

**Main responsibility:** Returns a JSON 403 response when an authenticated user lacks the required authority.

**Important types:** `class CustomAccessDeniedHandler` in `com.tlbank.lending.security.handler` (layer: security).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `handle()`.

**Direct dependencies (sampled imports):** `org.springframework.security.access.AccessDeniedException`, `org.springframework.security.web.access.AccessDeniedHandler`, `org.springframework.stereotype.Component`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.response.ApiResponse`, `com.tlbank.lending.security.util.JsonResponseWriter`, `jakarta.servlet.http.HttpServletRequest`, `jakarta.servlet.http.HttpServletResponse`.

**Business flow:** Appears in interview topics around: Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the security layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：CustomAccessDeniedHandler（class）位於 `com.tlbank.lending.security.handler`，屬 security 層。Returns a JSON 403 response when an authenticated user lacks the required authority.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`handle()`。依賴取樣：`org.springframework.security.access.AccessDeniedException`, `org.springframework.security.web.access.AccessDeniedHandler`, `org.springframework.stereotype.Component`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.response.ApiResponse`, `com.tlbank.lending.security.util.JsonResponseWriter`, `jakarta.servlet.http.HttpServletRequest`, `jakarta.servlet.http.HttpServletResponse`。
業務關聯：Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q070

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-security-handler-LoginFailureHandler-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginFailureHandler.java`

### English explanation

**Main responsibility:** Handles failed login attempts by returning a JSON 401 response.

**Important types:** `class LoginFailureHandler` in `com.tlbank.lending.security.handler` (layer: security).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `onAuthenticationFailure()`.

**Direct dependencies (sampled imports):** `org.springframework.security.core.AuthenticationException`, `org.springframework.security.web.authentication.AuthenticationFailureHandler`, `org.springframework.stereotype.Component`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.AuditIpResolver`, `com.tlbank.lending.common.audit.AuditLog`, `com.tlbank.lending.common.audit.AuditLogRepository`, `com.tlbank.lending.common.exception.ErrorCode`.

**Business flow:** Appears in interview topics around: Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the security layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：LoginFailureHandler（class）位於 `com.tlbank.lending.security.handler`，屬 security 層。Handles failed login attempts by returning a JSON 401 response.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`onAuthenticationFailure()`。依賴取樣：`org.springframework.security.core.AuthenticationException`, `org.springframework.security.web.authentication.AuthenticationFailureHandler`, `org.springframework.stereotype.Component`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.AuditIpResolver`, `com.tlbank.lending.common.audit.AuditLog`, `com.tlbank.lending.common.audit.AuditLogRepository`, `com.tlbank.lending.common.exception.ErrorCode`。
業務關聯：Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q078
- Q202

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-security-handler-LoginSuccessHandler-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java`

### English explanation

**Main responsibility:** Handles successful form-login authentication by returning a JSON {@link LoginResponse}.

**Important types:** `class LoginSuccessHandler` in `com.tlbank.lending.security.handler` (layer: security).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `onAuthenticationSuccess()`, `resolveRedirectUrl()`.

**Direct dependencies (sampled imports):** `org.springframework.security.core.Authentication`, `org.springframework.security.web.authentication.AuthenticationSuccessHandler`, `org.springframework.stereotype.Component`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.application.dto.response.LoginResponse`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.AuditIpResolver`, `com.tlbank.lending.common.audit.AuditLog`.

**Business flow:** Appears in interview topics around: Audit logging, Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the security layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：LoginSuccessHandler（class）位於 `com.tlbank.lending.security.handler`，屬 security 層。Handles successful form-login authentication by returning a JSON {@link LoginResponse}.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`onAuthenticationSuccess()`, `resolveRedirectUrl()`。依賴取樣：`org.springframework.security.core.Authentication`, `org.springframework.security.web.authentication.AuthenticationSuccessHandler`, `org.springframework.stereotype.Component`, `org.springframework.transaction.annotation.Transactional`, `com.tlbank.lending.application.dto.response.LoginResponse`, `com.tlbank.lending.common.audit.AuditAction`, `com.tlbank.lending.common.audit.AuditIpResolver`, `com.tlbank.lending.common.audit.AuditLog`。
業務關聯：Audit logging, Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q076
- Q079
- Q082
- Q088
- Q261
- Q271

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-security-handler-SessionExpiredStrategy-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/SessionExpiredStrategy.java`

### English explanation

**Main responsibility:** Returns a JSON 401 response when a concurrent login invalidates the existing session.

**Important types:** `class SessionExpiredStrategy` in `com.tlbank.lending.security.handler` (layer: security).

**Annotations:** `@Component`, `@RequiredArgsConstructor`, `@Slf4j`.

**Important methods / members:** `onExpiredSessionDetected()`.

**Direct dependencies (sampled imports):** `org.springframework.security.web.session.SessionInformationExpiredEvent`, `org.springframework.security.web.session.SessionInformationExpiredStrategy`, `org.springframework.stereotype.Component`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.response.ApiResponse`, `com.tlbank.lending.security.util.JsonResponseWriter`.

**Business flow:** Appears in interview topics around: Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the security layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SessionExpiredStrategy（class）位於 `com.tlbank.lending.security.handler`，屬 security 層。Returns a JSON 401 response when a concurrent login invalidates the existing session.
註解：`@Component`, `@RequiredArgsConstructor`, `@Slf4j`。主要方法：`onExpiredSessionDetected()`。依賴取樣：`org.springframework.security.web.session.SessionInformationExpiredEvent`, `org.springframework.security.web.session.SessionInformationExpiredStrategy`, `org.springframework.stereotype.Component`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.response.ApiResponse`, `com.tlbank.lending.security.util.JsonResponseWriter`。
業務關聯：Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q084

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-security-service-UserDetailsServiceImpl-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java`

### English explanation

**Main responsibility:** Loads user account details from the database for Spring Security authentication.

**Important types:** `class UserDetailsServiceImpl` in `com.tlbank.lending.security.service` (layer: security).

**Annotations:** `@RequiredArgsConstructor`, `@Service`.

**Important methods / members:** `loadUserByUsername()`, `toSpringRole()`.

**Direct dependencies (sampled imports):** `org.springframework.security.authentication.DisabledException`, `org.springframework.security.core.GrantedAuthority`, `org.springframework.security.core.authority.SimpleGrantedAuthority`, `org.springframework.security.core.userdetails.UserDetails`, `org.springframework.security.core.userdetails.UserDetailsService`, `org.springframework.security.core.userdetails.UsernameNotFoundException`, `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`.

**Business flow:** Appears in interview topics around: Authentication & authorization, JPA and Hibernate.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the security layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：UserDetailsServiceImpl（class）位於 `com.tlbank.lending.security.service`，屬 security 層。Loads user account details from the database for Spring Security authentication.
註解：`@RequiredArgsConstructor`, `@Service`。主要方法：`loadUserByUsername()`, `toSpringRole()`。依賴取樣：`org.springframework.security.authentication.DisabledException`, `org.springframework.security.core.GrantedAuthority`, `org.springframework.security.core.authority.SimpleGrantedAuthority`, `org.springframework.security.core.userdetails.UserDetails`, `org.springframework.security.core.userdetails.UserDetailsService`, `org.springframework.security.core.userdetails.UsernameNotFoundException`, `org.springframework.stereotype.Service`, `org.springframework.transaction.annotation.Transactional`。
業務關聯：Authentication & authorization, JPA and Hibernate。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q078
- Q083
- Q094

---

<a id="file-sp2-springboot-src-main-java-com-tlbank-lending-security-util-LoginResponseMode-java"></a>

## `sp2-springboot/src/main/java/com/tlbank/lending/security/util/LoginResponseMode.java`

### English explanation

**Main responsibility:** Determines whether an authentication request expects a JSON API response or a browser redirect.

**Important types:** `class LoginResponseMode` in `com.tlbank.lending.security.util` (layer: security).

**Annotations:** `@UtilityClass`.

**Important methods / members:** `prefersJson()`.

**Direct dependencies (sampled imports):** `jakarta.servlet.http.HttpServletRequest`.

**Business flow:** Appears in interview topics around: Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the security layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：LoginResponseMode（class）位於 `com.tlbank.lending.security.util`，屬 security 層。Determines whether an authentication request expects a JSON API response or a browser redirect.
註解：`@UtilityClass`。主要方法：`prefersJson()`。依賴取樣：`jakarta.servlet.http.HttpServletRequest`。
業務關聯：Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q082
- Q261

---

<a id="file-sp2-springboot-src-main-resources-application-dev-yml"></a>

## `sp2-springboot/src/main/resources/application-dev.yml`

### English explanation

**Main responsibility:** Spring / runtime configuration for profile or shared settings.

**Configuration sections:** `spring`, `logging`, `tlbank`, `springdoc`.

**Runtime effect:** Controls datasource, security, idempotency store, upload paths, and related beans when the matching profile is active.

**Why open in an interview:** Properties cited in answers must match this file, not memory.

**Limitations / trade-offs:** Profile-specific files may override; absence of a key means behavior falls back elsewhere or fails to wire.

### 中文筆記

職責：Spring／runtime 設定。頂層區段：spring, logging, tlbank, springdoc。
執行效果：依 profile 影響資料源、安全、冪等儲存、上傳路徑等 bean 組裝。
面談重點：設定鍵必須與檔案一致。
限制：profile 覆寫或鍵缺失會改變實際行為。

### Related questions

- Q003
- Q111
- Q112
- Q113
- Q119
- Q121
- Q168
- Q268
- Q298

---

<a id="file-sp2-springboot-src-main-resources-application-prod-yml"></a>

## `sp2-springboot/src/main/resources/application-prod.yml`

### English explanation

**Main responsibility:** Spring / runtime configuration for profile or shared settings.

**Configuration sections:** `spring`, `springdoc`, `logging`.

**Runtime effect:** Controls datasource, security, idempotency store, upload paths, and related beans when the matching profile is active.

**Why open in an interview:** Properties cited in answers must match this file, not memory.

**Limitations / trade-offs:** Profile-specific files may override; absence of a key means behavior falls back elsewhere or fails to wire.

### 中文筆記

職責：Spring／runtime 設定。頂層區段：spring, springdoc, logging。
執行效果：依 profile 影響資料源、安全、冪等儲存、上傳路徑等 bean 組裝。
面談重點：設定鍵必須與檔案一致。
限制：profile 覆寫或鍵缺失會改變實際行為。

### Related questions

- Q003
- Q008

---

<a id="file-sp2-springboot-src-main-resources-application-staging-yml"></a>

## `sp2-springboot/src/main/resources/application-staging.yml`

### English explanation

**Main responsibility:** Spring / runtime configuration for profile or shared settings.

**Configuration sections:** `spring`, `server`, `springdoc`, `logging`, `tlbank`.

**Runtime effect:** Controls datasource, security, idempotency store, upload paths, and related beans when the matching profile is active.

**Why open in an interview:** Properties cited in answers must match this file, not memory.

**Limitations / trade-offs:** Profile-specific files may override; absence of a key means behavior falls back elsewhere or fails to wire.

### 中文筆記

職責：Spring／runtime 設定。頂層區段：spring, server, springdoc, logging, tlbank。
執行效果：依 profile 影響資料源、安全、冪等儲存、上傳路徑等 bean 組裝。
面談重點：設定鍵必須與檔案一致。
限制：profile 覆寫或鍵缺失會改變實際行為。

### Related questions

- Q003
- Q119

---

<a id="file-sp2-springboot-src-main-resources-application-yml"></a>

## `sp2-springboot/src/main/resources/application.yml`

### English explanation

**Main responsibility:** Spring / runtime configuration for profile or shared settings.

**Configuration sections:** `spring`, `server`, `management`, `springdoc`, `tlbank`.

**Runtime effect:** Controls datasource, security, idempotency store, upload paths, and related beans when the matching profile is active.

**Why open in an interview:** Properties cited in answers must match this file, not memory.

**Limitations / trade-offs:** Profile-specific files may override; absence of a key means behavior falls back elsewhere or fails to wire.

### 中文筆記

職責：Spring／runtime 設定。頂層區段：spring, server, management, springdoc, tlbank。
執行效果：依 profile 影響資料源、安全、冪等儲存、上傳路徑等 bean 組裝。
面談重點：設定鍵必須與檔案一致。
限制：profile 覆寫或鍵缺失會改變實際行為。

### Related questions

- Q019
- Q020
- Q021
- Q121
- Q123
- Q211
- Q219
- Q298

---

<a id="file-sp2-springboot-src-main-resources-db-dev-seed-V100__seed_test_data-sql"></a>

## `sp2-springboot/src/main/resources/db/dev-seed/V100__seed_test_data.sql`

### English explanation

**Main responsibility:** Flyway migration version `V100` applying schema/data changes.

**Important sections:** INSERT INTO users; INSERT INTO user_roles; INSERT INTO card_products; INSERT INTO product_features; INSERT INTO system_parameters.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V100`。主要語句：INSERT INTO users; INSERT INTO user_roles; INSERT INTO card_products; INSERT INTO product_features; INSERT INTO system_parameters。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q122

---

<a id="file-sp2-springboot-src-main-resources-db-dev-seed-V101__add_user_136628-sql"></a>

## `sp2-springboot/src/main/resources/db/dev-seed/V101__add_user_136628.sql`

### English explanation

**Main responsibility:** Flyway migration version `V101` applying schema/data changes.

**Important sections:** INSERT INTO users; INSERT INTO user_roles.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V101`。主要語句：INSERT INTO users; INSERT INTO user_roles。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q122

---

<a id="file-sp2-springboot-src-main-resources-db-migration-sqlserver-V1__create_users-sql"></a>

## `sp2-springboot/src/main/resources/db/migration-sqlserver/V1__create_users.sql`

### English explanation

**Main responsibility:** Flyway migration version `V1` applying schema/data changes.

**Important sections:** CREATE TABLE users; CREATE TABLE user_roles; CREATE INDEX idx_users_status; CREATE INDEX idx_users_username; CREATE INDEX idx_user_roles_user_id.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V1`。主要語句：CREATE TABLE users; CREATE TABLE user_roles; CREATE INDEX idx_users_status; CREATE INDEX idx_users_username; CREATE INDEX idx_user_roles_user_id。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q111
- Q115
- Q116

---

<a id="file-sp2-springboot-src-main-resources-db-migration-V10__extend_system_parameters-sql"></a>

## `sp2-springboot/src/main/resources/db/migration/V10__extend_system_parameters.sql`

### English explanation

**Main responsibility:** Flyway migration version `V10` applying schema/data changes.

**Important sections:** ALTER TABLE system_parameters; ALTER TABLE system_parameters; ALTER TABLE system_parameters; ALTER TABLE system_parameters; ALTER TABLE system_parameters.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V10`。主要語句：ALTER TABLE system_parameters; ALTER TABLE system_parameters; ALTER TABLE system_parameters; ALTER TABLE system_parameters; ALTER TABLE system_parameters。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q120

---

<a id="file-sp2-springboot-src-main-resources-db-migration-V14__reshape_audit_logs_for_sprint9-sql"></a>

## `sp2-springboot/src/main/resources/db/migration/V14__reshape_audit_logs_for_sprint9.sql`

### English explanation

**Main responsibility:** Flyway migration version `V14` applying schema/data changes.

**Important sections:** CREATE TABLE audit_logs; CREATE INDEX idx_audit_logs_username; CREATE INDEX idx_audit_logs_action; CREATE INDEX idx_audit_logs_created_at.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V14`。主要語句：CREATE TABLE audit_logs; CREATE INDEX idx_audit_logs_username; CREATE INDEX idx_audit_logs_action; CREATE INDEX idx_audit_logs_created_at。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q117
- Q118
- Q126

---

<a id="file-sp2-springboot-src-main-resources-db-migration-V1__create_users-sql"></a>

## `sp2-springboot/src/main/resources/db/migration/V1__create_users.sql`

### English explanation

**Main responsibility:** Flyway migration version `V1` applying schema/data changes.

**Important sections:** CREATE TABLE users; CREATE TABLE user_roles; CREATE INDEX idx_users_status.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V1`。主要語句：CREATE TABLE users; CREATE TABLE user_roles; CREATE INDEX idx_users_status。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q019
- Q111
- Q115
- Q116
- Q121
- Q123

---

<a id="file-sp2-springboot-src-main-resources-db-migration-V3__create_applications-sql"></a>

## `sp2-springboot/src/main/resources/db/migration/V3__create_applications.sql`

### English explanation

**Main responsibility:** Flyway migration version `V3` applying schema/data changes.

**Important sections:** CREATE TABLE applications; CREATE TABLE workflow_histories; CREATE TABLE application_documents; CREATE INDEX idx_applications_user_id; CREATE INDEX idx_applications_status.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V3`。主要語句：CREATE TABLE applications; CREATE TABLE workflow_histories; CREATE TABLE application_documents; CREATE INDEX idx_applications_user_id; CREATE INDEX idx_applications_status。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q114
- Q257

---

<a id="file-sp2-springboot-src-main-resources-db-migration-V5__create_review_cases-sql"></a>

## `sp2-springboot/src/main/resources/db/migration/V5__create_review_cases.sql`

### English explanation

**Main responsibility:** Flyway migration version `V5` applying schema/data changes.

**Important sections:** CREATE TABLE review_cases; CREATE TABLE review_remarks; CREATE INDEX idx_review_cases_status.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V5`。主要語句：CREATE TABLE review_cases; CREATE TABLE review_remarks; CREATE INDEX idx_review_cases_status。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q147

---

<a id="file-sp2-springboot-src-main-resources-db-migration-V6__create_audit_logs-sql"></a>

## `sp2-springboot/src/main/resources/db/migration/V6__create_audit_logs.sql`

### English explanation

**Main responsibility:** Flyway migration version `V6` applying schema/data changes.

**Important sections:** CREATE TABLE audit_logs; CREATE INDEX idx_audit_logs_entity; CREATE INDEX idx_audit_logs_created_at.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V6`。主要語句：CREATE TABLE audit_logs; CREATE INDEX idx_audit_logs_entity; CREATE INDEX idx_audit_logs_created_at。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q126

---

<a id="file-sp2-springboot-src-main-resources-db-migration-V8__add_user_last_login-sql"></a>

## `sp2-springboot/src/main/resources/db/migration/V8__add_user_last_login.sql`

### English explanation

**Main responsibility:** Flyway migration version `V8` applying schema/data changes.

**Important sections:** ALTER TABLE users.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V8`。主要語句：ALTER TABLE users。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q124

---

<a id="file-sp2-springboot-src-main-resources-db-migration-V9__add_user_business_id-sql"></a>

## `sp2-springboot/src/main/resources/db/migration/V9__add_user_business_id.sql`

### English explanation

**Main responsibility:** Flyway migration version `V9` applying schema/data changes.

**Important sections:** ALTER TABLE users; ALTER TABLE users; ALTER TABLE users.

**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter constraints introduced here.

**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.

**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.

**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices.

### 中文筆記

職責：Flyway 遷移 `V9`。主要語句：ALTER TABLE users; ALTER TABLE users; ALTER TABLE users。
相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。
與領域關係：對應 `infrastructure.persistence` 的 entity／repository。
面談重點：欄位與約束是否真的落在 schema。
限制：SQL Server 與 H2 差異會影響遷移寫法。

### Related questions

- Q124

---

<a id="file-sp2-springboot-src-main-resources-logback-spring-xml"></a>

## `sp2-springboot/src/main/resources/logback-spring.xml`

### English explanation

**Main responsibility:** Maven project descriptor `logback-spring.xml`.

**Important coordinates:** artifactIds sampled: .

**Dependencies / plugins:** .

**Runtime effect:** Defines Java version, Spring Boot parent, and test/coverage plugins used by CI.

**Why open in an interview:** Grounds stack claims (Boot version, JaCoCo, drivers) in build metadata.

**Limitations / trade-offs:** Declared dependency ≠ every feature is used at runtime.

### 中文筆記

職責：Maven 專案描述。artifactId 取樣：。
依賴／外掛取樣：。
效果：決定 Java／Spring Boot 版本與 CI 測試覆蓋外掛。
面談用途：用建置中繼資料核對技術棧主張。
限制：有依賴不代表執行期每個功能都有使用。

### Related questions

- Q080
- Q217
- Q218
- Q221
- Q234

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-TlbankLendingApplicationTests-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/TlbankLendingApplicationTests.java`

### English explanation

**Main responsibility:** Smoke test verifying the Spring application context loads under the dev profile.

**Important types:** `class TlbankLendingApplicationTests` in `com.tlbank.lending` (layer: test).

**Annotations:** `@ActiveProfiles`, `@SpringBootTest`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.boot.test.context.SpringBootTest`, `org.springframework.test.context.ActiveProfiles`.

**Business flow:** Appears in interview topics around: Testing (JUnit + Mockito + MockMvc).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the test layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：TlbankLendingApplicationTests（class）位於 `com.tlbank.lending`，屬 test 層。Smoke test verifying the Spring application context loads under the dev profile.
註解：`@ActiveProfiles`, `@SpringBootTest`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.boot.test.context.SpringBootTest`, `org.springframework.test.context.ActiveProfiles`。
業務關聯：Testing (JUnit + Mockito + MockMvc)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q283

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-application-ApplicationFlowIntegrationTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java`

### English explanation

**Main responsibility:** Integration tests for the full credit card application workflow.

**Important types:** `class ApplicationFlowIntegrationTest` in `com.tlbank.lending.application` (layer: application).

**Annotations:** `@ActiveProfiles`, `@AutoConfigureMockMvc`, `@SpringBootTest`.

**Important methods / members:** `sendOtpForMobile()`, `uploadDocument()`, `createApplication()`, `sendOtp()`, `setOtpCodeInDatabase()`, `verifyOtp()`.

**Direct dependencies (sampled imports):** `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.status`, `org.springframework.beans.factory.annotation.Autowired`, `org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc`, `org.springframework.boot.test.context.SpringBootTest`.

**Business flow:** Appears in interview topics around: Spring Boot 3.x, Schema migration, OTP verification, Credit review workflow, Performance and Scalability.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationFlowIntegrationTest（class）位於 `com.tlbank.lending.application`，屬 application 層。Integration tests for the full credit card application workflow.
註解：`@ActiveProfiles`, `@AutoConfigureMockMvc`, `@SpringBootTest`。主要方法：`sendOtpForMobile()`, `uploadDocument()`, `createApplication()`, `sendOtp()`, `setOtpCodeInDatabase()`, `verifyOtp()`。依賴取樣：`org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.status`, `org.springframework.beans.factory.annotation.Autowired`, `org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc`, `org.springframework.boot.test.context.SpringBootTest`。
業務關聯：Spring Boot 3.x, Schema migration, OTP verification, Credit review workflow, Performance and Scalability。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q026
- Q125
- Q150
- Q156
- Q213

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-application-ApplicationIdempotencyIntegrationTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java`

### English explanation

**Main responsibility:** class `ApplicationIdempotencyIntegrationTest` in package `com.tlbank.lending.application`.

**Important types:** `class ApplicationIdempotencyIntegrationTest` in `com.tlbank.lending.application` (layer: application).

**Annotations:** `@ActiveProfiles`, `@AutoConfigureMockMvc`, `@SpringBootTest`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.status`, `org.springframework.beans.factory.annotation.Autowired`, `org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc`, `org.springframework.boot.test.context.SpringBootTest`, `org.springframework.http.MediaType`, `org.springframework.test.context.ActiveProfiles`.

**Business flow:** Appears in interview topics around: Idempotent application create.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationIdempotencyIntegrationTest（class）位於 `com.tlbank.lending.application`，屬 application 層。class `ApplicationIdempotencyIntegrationTest` in package `com.tlbank.lending.application`.
註解：`@ActiveProfiles`, `@AutoConfigureMockMvc`, `@SpringBootTest`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.status`, `org.springframework.beans.factory.annotation.Autowired`, `org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc`, `org.springframework.boot.test.context.SpringBootTest`, `org.springframework.http.MediaType`, `org.springframework.test.context.ActiveProfiles`。
業務關聯：Idempotent application create。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q011

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-application-application-ApplicationAppServiceTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java`

### English explanation

**Main responsibility:** class `ApplicationAppServiceTest` in package `com.tlbank.lending.application.application`.

**Important types:** `class ApplicationAppServiceTest` in `com.tlbank.lending.application.application` (layer: application).

**Annotations:** `@ExtendWith`.

**Important methods / members:** `savedApplication()`, `allRequiredDocuments()`, `sampleRequest()`, `sampleProduct()`.

**Direct dependencies (sampled imports):** `org.springframework.context.ApplicationEventPublisher`, `com.tlbank.lending.application.application.service.ApplicationAppService`, `com.tlbank.lending.application.application.service.ApplicationSummaryResponse`, `com.tlbank.lending.application.dto.request.AddressRequest`, `com.tlbank.lending.application.dto.request.ApplicantRequest`, `com.tlbank.lending.application.dto.request.CreateApplicationRequest`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`.

**Business flow:** Appears in interview topics around: Architecture boundaries, Testing (JUnit + Mockito + MockMvc).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationAppServiceTest（class）位於 `com.tlbank.lending.application.application`，屬 application 層。class `ApplicationAppServiceTest` in package `com.tlbank.lending.application.application`.
註解：`@ExtendWith`。主要方法：`savedApplication()`, `allRequiredDocuments()`, `sampleRequest()`, `sampleProduct()`。依賴取樣：`org.springframework.context.ApplicationEventPublisher`, `com.tlbank.lending.application.application.service.ApplicationAppService`, `com.tlbank.lending.application.application.service.ApplicationSummaryResponse`, `com.tlbank.lending.application.dto.request.AddressRequest`, `com.tlbank.lending.application.dto.request.ApplicantRequest`, `com.tlbank.lending.application.dto.request.CreateApplicationRequest`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`。
業務關聯：Architecture boundaries, Testing (JUnit + Mockito + MockMvc)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q101
- Q151

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-application-notification-NotificationServiceImplTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/application/notification/NotificationServiceImplTest.java`

### English explanation

**Main responsibility:** class `NotificationServiceImplTest` in package `com.tlbank.lending.application.notification`.

**Important types:** `class NotificationServiceImplTest` in `com.tlbank.lending.application.notification` (layer: application).

**Annotations:** `@ExtendWith`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `com.tlbank.lending.application.notification.service.NotificationServiceImpl`, `com.tlbank.lending.infrastructure.notification.EmailMessage`, `com.tlbank.lending.infrastructure.notification.EmailSender`, `com.tlbank.lending.infrastructure.notification.NotificationTemplate`, `com.tlbank.lending.infrastructure.notification.SmsMessage`, `com.tlbank.lending.infrastructure.notification.SmsSender`.

**Business flow:** Appears in interview topics around: Notifications (mock).

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：NotificationServiceImplTest（class）位於 `com.tlbank.lending.application.notification`，屬 application 層。class `NotificationServiceImplTest` in package `com.tlbank.lending.application.notification`.
註解：`@ExtendWith`。主要方法：no prominent public methods extracted。依賴取樣：`com.tlbank.lending.application.notification.service.NotificationServiceImpl`, `com.tlbank.lending.infrastructure.notification.EmailMessage`, `com.tlbank.lending.infrastructure.notification.EmailSender`, `com.tlbank.lending.infrastructure.notification.NotificationTemplate`, `com.tlbank.lending.infrastructure.notification.SmsMessage`, `com.tlbank.lending.infrastructure.notification.SmsSender`。
業務關聯：Notifications (mock)。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q162

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-application-otp-OtpAppServiceTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/application/otp/OtpAppServiceTest.java`

### English explanation

**Main responsibility:** class `OtpAppServiceTest` in package `com.tlbank.lending.application.otp`.

**Important types:** `class OtpAppServiceTest` in `com.tlbank.lending.application.otp` (layer: application).

**Annotations:** `@ExtendWith`.

**Important methods / members:** `pendingOtp()`, `sampleApplication()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.application.notification.service.NotificationService`, `com.tlbank.lending.application.otp.service.OtpAppService`, `com.tlbank.lending.application.otp.service.OtpResponse`, `com.tlbank.lending.application.otp.service.SendOtpCommand`, `com.tlbank.lending.application.otp.service.VerifyOtpCommand`, `com.tlbank.lending.application.otp.service.VerifyOtpResponse`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.common.exception.BusinessException`.

**Business flow:** Appears in interview topics around: OTP verification.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：OtpAppServiceTest（class）位於 `com.tlbank.lending.application.otp`，屬 application 層。class `OtpAppServiceTest` in package `com.tlbank.lending.application.otp`.
註解：`@ExtendWith`。主要方法：`pendingOtp()`, `sampleApplication()`。依賴取樣：`com.tlbank.lending.application.notification.service.NotificationService`, `com.tlbank.lending.application.otp.service.OtpAppService`, `com.tlbank.lending.application.otp.service.OtpResponse`, `com.tlbank.lending.application.otp.service.SendOtpCommand`, `com.tlbank.lending.application.otp.service.VerifyOtpCommand`, `com.tlbank.lending.application.otp.service.VerifyOtpResponse`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.common.exception.BusinessException`。
業務關聯：OTP verification。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q152

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-application-parameter-SystemParameterServiceCacheTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/application/parameter/SystemParameterServiceCacheTest.java`

### English explanation

**Main responsibility:** class `SystemParameterServiceCacheTest` in package `com.tlbank.lending.application.parameter`.

**Important types:** `class SystemParameterServiceCacheTest` in `com.tlbank.lending.application.parameter` (layer: application).

**Annotations:** `@ExtendWith`.

**Important methods / members:** `sampleParameter()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.domain.parameter.SystemParameter`, `com.tlbank.lending.domain.parameter.SystemParameterRepository`, `com.tlbank.lending.infrastructure.cache.CacheKeys`, `com.tlbank.lending.infrastructure.cache.CacheStore`, `com.tlbank.lending.infrastructure.cache.CacheTtlProvider`.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SystemParameterServiceCacheTest（class）位於 `com.tlbank.lending.application.parameter`，屬 application 層。class `SystemParameterServiceCacheTest` in package `com.tlbank.lending.application.parameter`.
註解：`@ExtendWith`。主要方法：`sampleParameter()`。依賴取樣：`com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.domain.parameter.SystemParameter`, `com.tlbank.lending.domain.parameter.SystemParameterRepository`, `com.tlbank.lending.infrastructure.cache.CacheKeys`, `com.tlbank.lending.infrastructure.cache.CacheStore`, `com.tlbank.lending.infrastructure.cache.CacheTtlProvider`。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q154

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-application-parameter-SystemParameterServiceTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/application/parameter/SystemParameterServiceTest.java`

### English explanation

**Main responsibility:** class `SystemParameterServiceTest` in package `com.tlbank.lending.application.parameter`.

**Important types:** `class SystemParameterServiceTest` in `com.tlbank.lending.application.parameter` (layer: application).

**Annotations:** `@ExtendWith`.

**Important methods / members:** `sampleParameter()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.application.parameter.service.SystemParameterResponse`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.application.parameter.service.UpdateParameterCommand`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.domain.parameter.SystemParameter`, `com.tlbank.lending.domain.parameter.SystemParameterRepository`, `com.tlbank.lending.infrastructure.cache.CacheKeys`.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the application layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SystemParameterServiceTest（class）位於 `com.tlbank.lending.application.parameter`，屬 application 層。class `SystemParameterServiceTest` in package `com.tlbank.lending.application.parameter`.
註解：`@ExtendWith`。主要方法：`sampleParameter()`。依賴取樣：`com.tlbank.lending.application.parameter.service.SystemParameterResponse`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.application.parameter.service.UpdateParameterCommand`, `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.domain.parameter.SystemParameter`, `com.tlbank.lending.domain.parameter.SystemParameterRepository`, `com.tlbank.lending.infrastructure.cache.CacheKeys`。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q154

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-common-audit-AuditAspectTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java`

### English explanation

**Main responsibility:** class `AuditAspectTest` in package `com.tlbank.lending.common.audit`.

**Important types:** `class AuditAspectTest` in `com.tlbank.lending.common.audit` (layer: common).

**Annotations:** `@ActiveProfiles`, `@Import`, `@SpringBootTest`.

**Important methods / members:** `countSuccessLogs()`, `awaitSuccessLog()`, `awaitFailureLog()`, `countFailureLogs()`.

**Direct dependencies (sampled imports):** `org.springframework.beans.factory.annotation.Autowired`, `org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration`, `org.springframework.boot.test.context.SpringBootTest`, `org.springframework.boot.test.context.TestConfiguration`, `org.springframework.context.annotation.Bean`, `org.springframework.context.annotation.Import`, `org.springframework.context.annotation.Primary`, `org.springframework.test.context.ActiveProfiles`.

**Business flow:** Appears in interview topics around: Audit logging, Project narrative / trade-offs.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the common layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AuditAspectTest（class）位於 `com.tlbank.lending.common.audit`，屬 common 層。class `AuditAspectTest` in package `com.tlbank.lending.common.audit`.
註解：`@ActiveProfiles`, `@Import`, `@SpringBootTest`。主要方法：`countSuccessLogs()`, `awaitSuccessLog()`, `awaitFailureLog()`, `countFailureLogs()`。依賴取樣：`org.springframework.beans.factory.annotation.Autowired`, `org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration`, `org.springframework.boot.test.context.SpringBootTest`, `org.springframework.boot.test.context.TestConfiguration`, `org.springframework.context.annotation.Bean`, `org.springframework.context.annotation.Import`, `org.springframework.context.annotation.Primary`, `org.springframework.test.context.ActiveProfiles`。
業務關聯：Audit logging, Project narrative / trade-offs。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q149
- Q291

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-domain-application-AddressTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/domain/application/AddressTest.java`

### English explanation

**Main responsibility:** class `AddressTest` in package `com.tlbank.lending.domain.application`.

**Important types:** `class AddressTest` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Domain model.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：AddressTest（class）位於 `com.tlbank.lending.domain.application`，屬 domain 層。class `AddressTest` in package `com.tlbank.lending.domain.application`.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Domain model。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q253

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-domain-application-ApplicationStatusTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationStatusTest.java`

### English explanation

**Main responsibility:** class `ApplicationStatusTest` in package `com.tlbank.lending.domain.application`.

**Important types:** `class ApplicationStatusTest` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** `isLegalTransition()`.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Credit review workflow, Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationStatusTest（class）位於 `com.tlbank.lending.domain.application`，屬 domain 層。class `ApplicationStatusTest` in package `com.tlbank.lending.domain.application`.
註解：none prominent at type level。主要方法：`isLegalTransition()`。依賴取樣：limited internal imports extracted。
業務關聯：Credit review workflow, Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q246
- Q273
- Q299

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-domain-application-ApplicationTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java`

### English explanation

**Main responsibility:** class `ApplicationTest` in package `com.tlbank.lending.domain.application`.

**Important types:** `class ApplicationTest` in `com.tlbank.lending.domain.application` (layer: domain).

**Annotations:** none prominent at type level.

**Important methods / members:** `assertHistory()`, `sampleApplication()`, `sampleApplicant()`, `sampleDocument()`, `allRequiredDocuments()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.exception.WorkflowException`.

**Business flow:** Appears in interview topics around: Application lifecycle, Project narrative / trade-offs.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the domain layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ApplicationTest（class）位於 `com.tlbank.lending.domain.application`，屬 domain 層。class `ApplicationTest` in package `com.tlbank.lending.domain.application`.
註解：none prominent at type level。主要方法：`assertHistory()`, `sampleApplication()`, `sampleApplicant()`, `sampleDocument()`, `allRequiredDocuments()`。依賴取樣：`com.tlbank.lending.common.exception.BusinessException`, `com.tlbank.lending.common.exception.ErrorCode`, `com.tlbank.lending.common.exception.WorkflowException`。
業務關聯：Application lifecycle, Project narrative / trade-offs。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q056
- Q240
- Q244
- Q245

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-infrastructure-cache-InMemoryCacheStoreTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStoreTest.java`

### English explanation

**Main responsibility:** class `InMemoryCacheStoreTest` in package `com.tlbank.lending.infrastructure.cache`.

**Important types:** `class InMemoryCacheStoreTest` in `com.tlbank.lending.infrastructure.cache` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** limited internal imports extracted.

**Business flow:** Appears in interview topics around: Product / parameter cache.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：InMemoryCacheStoreTest（class）位於 `com.tlbank.lending.infrastructure.cache`，屬 infrastructure 層。class `InMemoryCacheStoreTest` in package `com.tlbank.lending.infrastructure.cache`.
註解：none prominent at type level。主要方法：no prominent public methods extracted。依賴取樣：limited internal imports extracted。
業務關聯：Product / parameter cache。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q142

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-infrastructure-report-ExcelReportGeneratorTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/report/ExcelReportGeneratorTest.java`

### English explanation

**Main responsibility:** class `ExcelReportGeneratorTest` in package `com.tlbank.lending.infrastructure.report`.

**Important types:** `class ExcelReportGeneratorTest` in `com.tlbank.lending.infrastructure.report` (layer: infrastructure).

**Annotations:** none prominent at type level.

**Important methods / members:** `sampleData()`, `emptyData()`, `collectSheetText()`.

**Direct dependencies (sampled imports):** `com.tlbank.lending.application.report.service.DailyStatisticsData`, `com.tlbank.lending.domain.application.ApplicationStatus`.

**Business flow:** Appears in interview topics around: Reporting.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ExcelReportGeneratorTest（class）位於 `com.tlbank.lending.infrastructure.report`，屬 infrastructure 層。class `ExcelReportGeneratorTest` in package `com.tlbank.lending.infrastructure.report`.
註解：none prominent at type level。主要方法：`sampleData()`, `emptyData()`, `collectSheetText()`。依賴取樣：`com.tlbank.lending.application.report.service.DailyStatisticsData`, `com.tlbank.lending.domain.application.ApplicationStatus`。
業務關聯：Reporting。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q155
- Q175

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-infrastructure-storage-LocalDocumentStorageServiceTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageServiceTest.java`

### English explanation

**Main responsibility:** class `LocalDocumentStorageServiceTest` in package `com.tlbank.lending.infrastructure.storage`.

**Important types:** `class LocalDocumentStorageServiceTest` in `com.tlbank.lending.infrastructure.storage` (layer: infrastructure).

**Annotations:** `@ExtendWith`.

**Important methods / members:** no prominent public methods extracted.

**Direct dependencies (sampled imports):** `org.springframework.mock.web.MockMultipartFile`, `org.springframework.test.util.ReflectionTestUtils`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.domain.application.DocumentType`.

**Business flow:** Appears in interview topics around: Document upload / storage.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the infrastructure layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：LocalDocumentStorageServiceTest（class）位於 `com.tlbank.lending.infrastructure.storage`，屬 infrastructure 層。class `LocalDocumentStorageServiceTest` in package `com.tlbank.lending.infrastructure.storage`.
註解：`@ExtendWith`。主要方法：no prominent public methods extracted。依賴取樣：`org.springframework.mock.web.MockMultipartFile`, `org.springframework.test.util.ReflectionTestUtils`, `com.tlbank.lending.application.parameter.service.SystemParameterService`, `com.tlbank.lending.domain.application.DocumentType`。
業務關聯：Document upload / storage。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q153

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-presentation-api-v1-ReviewApiControllerTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/presentation/api/v1/ReviewApiControllerTest.java`

### English explanation

**Main responsibility:** Integration tests for the review cases REST API.

**Important types:** `class ReviewApiControllerTest` in `com.tlbank.lending.presentation.api.v1` (layer: presentation).

**Annotations:** `@ActiveProfiles`, `@AutoConfigureMockMvc`, `@SpringBootTest`.

**Important methods / members:** `loginAs()`.

**Direct dependencies (sampled imports):** `org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.status`, `org.springframework.beans.factory.annotation.Autowired`, `org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc`, `org.springframework.boot.test.context.SpringBootTest`.

**Business flow:** Appears in interview topics around: Spring Boot 3.x, Credit review workflow.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the presentation layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：ReviewApiControllerTest（class）位於 `com.tlbank.lending.presentation.api.v1`，屬 presentation 層。Integration tests for the review cases REST API.
註解：`@ActiveProfiles`, `@AutoConfigureMockMvc`, `@SpringBootTest`。主要方法：`loginAs()`。依賴取樣：`org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.status`, `org.springframework.beans.factory.annotation.Autowired`, `org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc`, `org.springframework.boot.test.context.SpringBootTest`。
業務關聯：Spring Boot 3.x, Credit review workflow。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q026
- Q156

---

<a id="file-sp2-springboot-src-test-java-com-tlbank-lending-security-SecurityIntegrationTest-java"></a>

## `sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java`

### English explanation

**Main responsibility:** Integration tests verifying Sprint 2 security and login behaviour.

**Important types:** `class SecurityIntegrationTest` in `com.tlbank.lending.security` (layer: security).

**Annotations:** `@ActiveProfiles`, `@AutoConfigureMockMvc`, `@Import`, `@SpringBootTest`.

**Important methods / members:** `probe()`.

**Direct dependencies (sampled imports):** `org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.content`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.status`, `org.springframework.beans.factory.annotation.Autowired`.

**Business flow:** Appears in interview topics around: Spring Boot 3.x, Authentication & authorization.

**Why open in an interview:** Confirms how this repository implements the claim under discussion inside the security layer rather than relying on a generic textbook pattern.

**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume cloud, multi-region, or production hardening unless shown in neighboring adapters and config.

### 中文筆記

職責：SecurityIntegrationTest（class）位於 `com.tlbank.lending.security`，屬 security 層。Integration tests verifying Sprint 2 security and login behaviour.
註解：`@ActiveProfiles`, `@AutoConfigureMockMvc`, `@Import`, `@SpringBootTest`。主要方法：`probe()`。依賴取樣：`org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get`, `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.content`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl`, `org.springframework.test.web.servlet.result.MockMvcResultMatchers.status`, `org.springframework.beans.factory.annotation.Autowired`。
業務關聯：Spring Boot 3.x, Authentication & authorization。面談時用來對照該層實際實作，避免只背抽象名詞。
限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。

### Related questions

- Q026
- Q078
- Q250

---

<a id="file-sp2-springboot-src-test-resources-application-dev-yml"></a>

## `sp2-springboot/src/test/resources/application-dev.yml`

### English explanation

**Main responsibility:** Spring / runtime configuration for profile or shared settings.

**Configuration sections:** `spring`, `tlbank`.

**Runtime effect:** Controls datasource, security, idempotency store, upload paths, and related beans when the matching profile is active.

**Why open in an interview:** Properties cited in answers must match this file, not memory.

**Limitations / trade-offs:** Profile-specific files may override; absence of a key means behavior falls back elsewhere or fails to wire.

### 中文筆記

職責：Spring／runtime 設定。頂層區段：spring, tlbank。
執行效果：依 profile 影響資料源、安全、冪等儲存、上傳路徑等 bean 組裝。
面談重點：設定鍵必須與檔案一致。
限制：profile 覆寫或鍵缺失會改變實際行為。

### Related questions

- Q011
- Q125

---
