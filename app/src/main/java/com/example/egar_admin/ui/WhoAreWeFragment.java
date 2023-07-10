package com.example.egar_admin.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.R;
import com.example.egar_admin.databinding.FragmentWhoAreWeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WhoAreWeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WhoAreWeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentWhoAreWeBinding binding;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WhoAreWeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WhoAreWeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WhoAreWeFragment newInstance(String param1, String param2) {
        WhoAreWeFragment fragment = new WhoAreWeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding  = FragmentWhoAreWeBinding.inflate(inflater);
        binding.tvWhoAreWeInEgar.setText(text());
        return binding.getRoot();
    }
    private String text(){
        String text = "في تطبيق Egar، نحن فخورون بأن نكون منصة رائدة في مجال تأجير المنازل ومساحات العمل والسيارات والمعدات. نحن نسعى جاهدين لتلبية احتياجاتك وتوفير تجربة تأجير فريدة ومريحة لعملائنا.\n" +
                "\n" +
                "تعتبر Egar خيارًا موثوقًا وموفرًا للوقت للعديد من الأسباب. نحن نقدم مجموعة واسعة من الخيارات لتلبية احتياجاتك الفردية والتجارية. سواء كنت تبحث عن منزل أحلامك أو مكان للعمل المناسب لنشاطك التجاري، فإن لدينا ما يناسبك.\n" +
                "\n" +
                "نحن نؤمن بأهمية التنوع والتكيف مع تطلعات عملائنا المتنوعة. لذلك، نقدم مجموعة متنوعة من العقارات التي تتضمن المنازل الفاخرة، والشقق المريحة، ومساحات العمل الحديثة، والسيارات المتنوعة، والمعدات المتخصصة. نحن نعمل مع شبكة واسعة من الملاك والشركاء لضمان توافر الخيارات المناسبة لكل عميل.\n" +
                "\n" +
                "منصتنا سهلة الاستخدام وتمنحك قوة الاختيار والتحكم الكامل في عملية التأجير. يمكنك الاطلاع على تفاصيل كل عقار بما في ذلك الصور والوصف والتواريخ المتاحة. يمكنك حجز العقار المفضل لديك بسهولة وسرعة عبر تطبيقنا المبتكر.\n" +
                "\n" +
                "نحن نضع عملائنا في المقام الأول ونولي اهتمامًا كبيرًا لتجربتهم. فريق دعم العملاء لدينا متواجد على مدار الساعة للإجابة على استفساراتك وتقديم المساعدة في أي مشكلة قد تواجهها. نحن نسعى جاهدين لتوفير خدمة عملاء استثنائية وضمان رضاك التام.\n" +
                "\n" +
                "انضم إلى Egar اليوم واستمتع بتجربة تأجير مميزة. دعنا نساعدك في تحقيق احتياجاتك السكنية والتجارية بسهولة ويسر. ";
        return text;
    }
}